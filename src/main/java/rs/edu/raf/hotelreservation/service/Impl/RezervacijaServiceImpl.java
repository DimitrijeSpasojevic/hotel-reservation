package rs.edu.raf.hotelreservation.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rs.edu.raf.hotelreservation.domain.Rezervacija;
import rs.edu.raf.hotelreservation.domain.Termin;
import rs.edu.raf.hotelreservation.dto.*;
import rs.edu.raf.hotelreservation.exception.NotFoundException;
import rs.edu.raf.hotelreservation.mapper.RezervacijaMapper;
import rs.edu.raf.hotelreservation.repository.RezervacijaRepository;
import rs.edu.raf.hotelreservation.repository.TerminRepository;
import rs.edu.raf.hotelreservation.service.RezervacijaService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RezervacijaServiceImpl implements RezervacijaService {
    private RezervacijaRepository rezervacijaRepository;
    private RezervacijaMapper rezervacijaMapper;
    private TerminRepository terminRepository;
    private JmsTemplate jmsTemplate;
    private ObjectMapper objectMapper;
    private String notificationQueue;
    private RestTemplate userServiceTemplate;

    public RezervacijaServiceImpl(RezervacijaRepository rezervacijaRepository, RezervacijaMapper rezervacijaMapper,
                                  TerminRepository terminRepository, JmsTemplate jmsTemplate, ObjectMapper objectMapper,
                                  @Value("${destination.sendNotification}") String notificationQueue, RestTemplate userServiceTemplate) {
        this.rezervacijaRepository = rezervacijaRepository;
        this.rezervacijaMapper = rezervacijaMapper;
        this.terminRepository = terminRepository;
        this.jmsTemplate = jmsTemplate;
        this.objectMapper = objectMapper;
        this.notificationQueue = notificationQueue;
        this.userServiceTemplate = userServiceTemplate;
    }

    @Override
    public RezervacijaDto getRezervacijaById(Long rezervacijaId) {
        Rezervacija rezervacija = rezervacijaRepository.findById(rezervacijaId)
                .orElseThrow(() -> new NotFoundException(String.format("Rezervacija with id: %d not found.", rezervacijaId)));
        return rezervacijaMapper.rezervacijaToRezervacijaDto(rezervacija);
    }

    @Override
    public Page<RezervacijaDto> getRezervacijaByHotelId(Long hotelId, Pageable pageable) {
        return rezervacijaRepository.getRezervacijaByTipSobe_Hotel_Id(hotelId, pageable)
                .map(rezervacijaMapper::rezervacijaToRezervacijaDto);
    }

    @Override
    public RezervacijaDto createRezervacija(CreateRezervacijaDto createRezervacijaDto) {
        Rezervacija rezervacija = rezervacijaMapper.createRezervacijaDtoToRezervacija(createRezervacijaDto);
        RankDto rankDto = userServiceTemplate.getForObject("/api/user/"+ rezervacija.getUserId() + "/rank", RankDto.class);
        BigDecimal discount = new BigDecimal(1 - (rankDto.getDiscount() * 0.01));
        rezervacija.setCena(rezervacija.getCena().multiply(discount));
        rezervacija = rezervacijaRepository.save(rezervacija);
        for (Termin termin : rezervacija.getTermini()) {
            termin.setBrojSlobodnihSoba(termin.getBrojSlobodnihSoba() - 1);
            terminRepository.save(termin);
        }
        userServiceTemplate.put("/api/user/" + rezervacija.getUserId() + "/reservation", null);
        return rezervacijaMapper.rezervacijaToRezervacijaDto(rezervacija);
    }

    @Override
    public RezervacijaDto deleteRezervacijaById(Long rezervacijaId) {
        Rezervacija rezervacija = rezervacijaRepository.findById(rezervacijaId)
                .orElseThrow(() -> new NotFoundException(String.format("Rezervacija with id: %d not found.", rezervacijaId)));
        for (Termin termin : rezervacija.getTermini()) {
            termin.setBrojSlobodnihSoba(termin.getBrojSlobodnihSoba() + 1);
            terminRepository.save(termin);
        }
        rezervacijaRepository.delete(rezervacija);
        userServiceTemplate.delete("/api/user/" + rezervacija.getUserId() + "/reservation");
        return rezervacijaMapper.rezervacijaToRezervacijaDto(rezervacija);
    }

    private void sendRezervacijaReminder(Rezervacija rezervacija) {
        NotificationSendDto notificationSendDto = new NotificationSendDto();
        notificationSendDto.setNotificationType("reservationReminder");
        ClientDto clientDto = userServiceTemplate.getForObject("/api/user/" + rezervacija.getUserId(), ClientDto.class);
        notificationSendDto.setEmail(clientDto.getEmail());
        List<NotificationParameterDto> parameterDtos = new ArrayList<>();
        parameterDtos.add(new NotificationParameterDto("hotel", rezervacija.getTipSobe().getHotel().getIme()));
        parameterDtos.add(new NotificationParameterDto("firstName", clientDto.getFirstName()));
        parameterDtos.add(new NotificationParameterDto("lastName", clientDto.getLastName()));
        parameterDtos.add(new NotificationParameterDto("roomType", rezervacija.getTipSobe().getIme()));
        notificationSendDto.setParameters(parameterDtos);

        String message;
        try {
             message = objectMapper.writeValueAsString(notificationSendDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Problem pri mapiranju objekta");
        }
        jmsTemplate.convertAndSend(notificationQueue, message);
        rezervacija.setReminded(true);
        rezervacijaRepository.save(rezervacija);
    }

    @Override
    public void remindOfRezervacija() {
        List<Rezervacija> unnotifiedRezervacijaList = rezervacijaRepository.getRezervacijaByReminded(false);
        for (Rezervacija rezervacija: unnotifiedRezervacijaList) {
            if (rezervacija.getTermini().get(0).getDatum().equals(LocalDate.now().plusDays(2)))
                sendRezervacijaReminder(rezervacija);
        }
    }

    @Override
    public Page<RezervacijaDto> getRezervacijaByUser(Long userId, Pageable pageable) {
        return rezervacijaRepository.getRezervacijaByUserId(userId, pageable)
                .map(rezervacijaMapper::rezervacijaToRezervacijaDto);
    }
}
