package rs.edu.raf.hotelreservation.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rs.edu.raf.hotelreservation.dto.CreateRecenzijaDto;
import rs.edu.raf.hotelreservation.dto.RecenzijaDto;

public interface RecenzijaService {
    RecenzijaDto deleteById(Long id);
    RecenzijaDto updateById(Long id);
    RecenzijaDto createRecenzija(CreateRecenzijaDto createRecenzijaDto);
    Page<RecenzijaDto> findByHotelId(Long hotelId, Pageable pageable);
    Page<RecenzijaDto> findByCity(String city, Pageable pageable);
    Page<RecenzijaDto> findByUserId(Long userId, Pageable pageable);
}
