package rs.edu.raf.hotelreservation.service.Impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.edu.raf.hotelreservation.dto.CreateHotelDto;
import rs.edu.raf.hotelreservation.dto.CreateTipSobeDto;
import rs.edu.raf.hotelreservation.dto.HotelDto;
import rs.edu.raf.hotelreservation.dto.TipSobeDto;
import rs.edu.raf.hotelreservation.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {


    @Override
    public Page<HotelDto> getAll(Pageable pageable) {
        return null;
    }
    @Override
    public HotelDto addHotel(CreateHotelDto createHotelDto) {
        return null;
    }

    @Override
    public HotelDto changeHotel(HotelDto hotelDto) {
        return null;
    }

    @Override
    public HotelDto getHotelById(Long id) {
        return null;
    }


    @Override
    public Page<TipSobeDto> getAllRoomTypes(Pageable pageable) {
        return null;
    }

    @Override
    public TipSobeDto createRoomType(CreateTipSobeDto createTipSobeDto) {
        return null;
    }
}
