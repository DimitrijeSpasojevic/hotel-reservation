package rs.edu.raf.hotelreservation.service.Impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.edu.raf.hotelreservation.dto.CreateRezervacijaDto;
import rs.edu.raf.hotelreservation.dto.RezervacijaDto;
import rs.edu.raf.hotelreservation.service.RezervacijaService;

@Service
public class RezervacijaServiceImpl implements RezervacijaService {
    @Override
    public RezervacijaDto getRezervacijaById(Long RezervacijaId) {
        return null;
    }

    @Override
    public Page<RezervacijaDto> getRezervacijaByHotelId(Long hotelId, Pageable pageable) {
        return null;
    }

    @Override
    public RezervacijaDto createRezervacija(CreateRezervacijaDto createRezervacijaDto) {
        return null;
    }

    @Override
    public RezervacijaDto deleteRezervacijaById(Long rezervacijaId) {
        return null;
    }
}
