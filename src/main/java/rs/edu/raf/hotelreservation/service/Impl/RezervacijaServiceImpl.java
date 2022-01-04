package rs.edu.raf.hotelreservation.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import rs.edu.raf.hotelreservation.domain.Rezervacija;
import rs.edu.raf.hotelreservation.domain.Termin;
import rs.edu.raf.hotelreservation.dto.CreateRezervacijaDto;
import rs.edu.raf.hotelreservation.dto.NotificationSendDto;
import rs.edu.raf.hotelreservation.dto.RezervacijaDto;
import rs.edu.raf.hotelreservation.exception.NotFoundException;
import rs.edu.raf.hotelreservation.mapper.RezervacijaMapper;
import rs.edu.raf.hotelreservation.repository.RezervacijaRepository;
import rs.edu.raf.hotelreservation.repository.TerminRepository;
import rs.edu.raf.hotelreservation.service.RezervacijaService;

import java.time.LocalDate;
import java.util.List;

@Service
public class RezervacijaServiceImpl implements RezervacijaService {
    private RezervacijaRepository rezervacijaRepository;
    private RezervacijaMapper rezervacijaMapper;
    private TerminRepository terminRepository;
    private JmsTemplate jmsTemplate;
    private ObjectMapper objectMapper;
    private String notificationQueue;

    public RezervacijaServiceImpl(RezervacijaRepository rezervacijaRepository, RezervacijaMapper rezervacijaMapper,
                                  TerminRepository terminRepository, JmsTemplate jmsTemplate, ObjectMapper objectMapper,
                                  @Value("${destination.sendNotification}") String notificationQueue) {
        this.rezervacijaRepository = rezervacijaRepository;
        this.rezervacijaMapper = rezervacijaMapper;
        this.terminRepository = terminRepository;
        this.jmsTemplate = jmsTemplate;
        this.objectMapper = objectMapper;
        this.notificationQueue = notificationQueue;
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
        // TODO dohvati rank i popust korisnika iz korisničkog servisa sinhrono sa ponavljanje
        // TODO obavesti korisnički servis o rezervaciji
        Rezervacija rezervacija = rezervacijaRepository.save(rezervacijaMapper.createRezervacijaDtoToRezervacija(createRezervacijaDto));
        for (Termin termin : rezervacija.getTermini()) {
            termin.setBrojSlobodnihSoba(termin.getBrojSlobodnihSoba() - 1);
            terminRepository.save(termin);
        }
        return rezervacijaMapper.rezervacijaToRezervacijaDto(rezervacija);
    }

    @Override
    public RezervacijaDto deleteRezervacijaById(Long rezervacijaId) {
        // TODO obavesti korisnički servis o rezervaciji
        Rezervacija rezervacija = rezervacijaRepository.findById(rezervacijaId)
                .orElseThrow(() -> new NotFoundException(String.format("Rezervacija with id: %d not found.", rezervacijaId)));
        for (Termin termin : rezervacija.getTermini()) {
            termin.setBrojSlobodnihSoba(termin.getBrojSlobodnihSoba() + 1);
            terminRepository.save(termin);
        }
        rezervacijaRepository.delete(rezervacija);
        return rezervacijaMapper.rezervacijaToRezervacijaDto(rezervacija);
    }

    private void sendRezervacijaNotification(Rezervacija rezervacija) {
        NotificationSendDto notificationSendDto = new NotificationSendDto();
        notificationSendDto.setNotificationType("reservationReminder");
        // TODO dohvati email, ime i prezime sa user servisa i dodaj kao parametre

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
                sendRezervacijaNotification(rezervacija);
        }
    }
}
