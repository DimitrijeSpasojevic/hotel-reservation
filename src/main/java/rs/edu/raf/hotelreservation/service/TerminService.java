package rs.edu.raf.hotelreservation.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rs.edu.raf.hotelreservation.domain.TipSobe;
import rs.edu.raf.hotelreservation.dto.CreateTerminDto;
import rs.edu.raf.hotelreservation.dto.TerminDto;
import rs.edu.raf.hotelreservation.dto.TipSobeDto;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface TerminService {
    Page<TerminDto> getAllByHotel(Long hotelId, Pageable pageable);
    Page<TerminDto> getAllAvailableByCity(String city, Pageable pageable);
    Page<TerminDto> getAllAvailableByHotel(Long hotelId, Pageable pageable);
    Page<TerminDto> getAllAvailableByPeriod(LocalDate pocetniDatum, LocalDate krajnjiDatum, Pageable pageable);
    Page<TerminDto> getAllAvailableByPrice(BigDecimal minimalnaCena, BigDecimal maksimalnaCena, Pageable pageable);
    TerminDto createTermin(CreateTerminDto createTerminDto);
    TerminDto deleteTerminById(Long id);
    TipSobeDto getTipSobeByTerminId(Long terminId);
    TerminDto getTerminById(Long terminId);
}
