package rs.edu.raf.hotelreservation.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rs.edu.raf.hotelreservation.dto.CreateHotelDto;
import rs.edu.raf.hotelreservation.dto.CreateTipSobeDto;
import rs.edu.raf.hotelreservation.dto.HotelDto;
import rs.edu.raf.hotelreservation.dto.TipSobeDto;

public interface HotelService {
    Page<HotelDto> getAll(Pageable pageable);
    HotelDto addHotel(CreateHotelDto createHotelDto);
    HotelDto changeHotel(HotelDto hotelDto);
    HotelDto getHotelById(Long id);
    Page<TipSobeDto> getAllRoomTypes(Pageable pageable);
    TipSobeDto createRoomType(CreateTipSobeDto createTipSobeDto);
}
