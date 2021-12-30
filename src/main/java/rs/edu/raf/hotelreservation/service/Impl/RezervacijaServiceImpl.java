package rs.edu.raf.hotelreservation.service.Impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.edu.raf.hotelreservation.domain.Rezervacija;
import rs.edu.raf.hotelreservation.dto.CreateRezervacijaDto;
import rs.edu.raf.hotelreservation.dto.RezervacijaDto;
import rs.edu.raf.hotelreservation.exception.NotFoundException;
import rs.edu.raf.hotelreservation.mapper.RezervacijaMapper;
import rs.edu.raf.hotelreservation.repository.RezervacijaRepository;
import rs.edu.raf.hotelreservation.repository.TerminRepository;
import rs.edu.raf.hotelreservation.service.RezervacijaService;

@Service
public class RezervacijaServiceImpl implements RezervacijaService {
    private RezervacijaRepository rezervacijaRepository;
    private RezervacijaMapper rezervacijaMapper;

    public RezervacijaServiceImpl(RezervacijaRepository rezervacijaRepository, RezervacijaMapper rezervacijaMapper,
                                  TerminRepository terminRepository) {
        this.rezervacijaRepository = rezervacijaRepository;
        this.rezervacijaMapper = rezervacijaMapper;
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
        // TODO dohvati rank i popust korisnika iz korisničkog servisa sinhrono sa ponavljanjem
        // TODO ažuriraj termine i broj slobodnih soba
        // TODO obavesti korisnički servis o rezervaciji
        Rezervacija rezervacija = rezervacijaRepository.save(rezervacijaMapper.createRezervacijaDtoToRezervacija(createRezervacijaDto));
        return rezervacijaMapper.rezervacijaToRezervacijaDto(rezervacija);
    }

    @Override
    public RezervacijaDto deleteRezervacijaById(Long rezervacijaId) {
        // TODO obavesti korisnički servis o rezervaciji
        Rezervacija rezervacija = rezervacijaRepository.findById(rezervacijaId)
                .orElseThrow(() -> new NotFoundException(String.format("Rezervacija with id: %d not found.", rezervacijaId)));
        rezervacijaRepository.delete(rezervacija);
        return rezervacijaMapper.rezervacijaToRezervacijaDto(rezervacija);
    }
}
