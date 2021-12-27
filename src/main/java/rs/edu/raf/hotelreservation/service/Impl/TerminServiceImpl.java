package rs.edu.raf.hotelreservation.service.Impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.edu.raf.hotelreservation.domain.Termin;
import rs.edu.raf.hotelreservation.dto.CreateTerminDto;
import rs.edu.raf.hotelreservation.dto.TerminDto;
import rs.edu.raf.hotelreservation.service.TerminService;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class TerminServiceImpl implements TerminService {
    @Override
    public Page<TerminDto> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public Page<TerminDto> getAllByCity(String city, Pageable pageable) {
        return null;
    }

    @Override
    public Page<TerminDto> getAllByHotel(Long hotelId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<TerminDto> getAllByPeriod(Date pocetniDatum, Date krajnjiDatum, Pageable pageable) {
        return null;
    }

    @Override
    public Page<TerminDto> getAllByPrice(BigDecimal minimalnaCena, BigDecimal maksimalnaCena, Pageable pageable) {
        return null;
    }

    @Override
    public TerminDto createTermin(CreateTerminDto createTerminDto) {
        return null;
    }
}
