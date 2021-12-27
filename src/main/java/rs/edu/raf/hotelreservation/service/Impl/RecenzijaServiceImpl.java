package rs.edu.raf.hotelreservation.service.Impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.edu.raf.hotelreservation.dto.CreateRecenzijaDto;
import rs.edu.raf.hotelreservation.dto.RecenzijaDto;
import rs.edu.raf.hotelreservation.service.RecenzijaService;

@Service
public class RecenzijaServiceImpl implements RecenzijaService {
    @Override
    public RecenzijaDto deleteById(Long id) {
        return null;
    }

    @Override
    public RecenzijaDto updateById(Long id) {
        return null;
    }

    @Override
    public RecenzijaDto createRecenzija(CreateRecenzijaDto createRecenzijaDto) {
        return null;
    }

    @Override
    public Page<RecenzijaDto> findByHotelId(Long hotelId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<RecenzijaDto> findByCity(String city, Pageable pageable) {
        return null;
    }

    @Override
    public Page<RecenzijaDto> findByUserId(Long userId, Pageable pageable) {
        return null;
    }
}
