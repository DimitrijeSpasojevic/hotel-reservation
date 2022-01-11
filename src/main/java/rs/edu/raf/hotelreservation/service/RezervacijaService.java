package rs.edu.raf.hotelreservation.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rs.edu.raf.hotelreservation.dto.CreateRezervacijaDto;
import rs.edu.raf.hotelreservation.dto.RezervacijaDto;

public interface RezervacijaService {
    RezervacijaDto getRezervacijaById(Long RezervacijaId);
    Page<RezervacijaDto> getRezervacijaByHotelId(Long hotelId, Pageable pageable);
    RezervacijaDto createRezervacija(CreateRezervacijaDto createRezervacijaDto);
    RezervacijaDto deleteRezervacijaById(Long rezervacijaId);
    void remindOfRezervacija();
    Page<RezervacijaDto> getRezervacijaByUser(Long userId, Pageable pageable);
}
