package rs.edu.raf.hotelreservation.mapper;

import org.springframework.stereotype.Component;
import rs.edu.raf.hotelreservation.domain.Hotel;
import rs.edu.raf.hotelreservation.dto.CreateHotelDto;
import rs.edu.raf.hotelreservation.dto.HotelDto;

@Component
public class HotelMapper {

    public HotelDto hotelToHotelDto(Hotel hotel){
        HotelDto hotelDto = new HotelDto();
        hotelDto.setGrad(hotel.getGrad());
        hotelDto.setId(hotel.getId());
        hotelDto.setIme(hotel.getIme());
        hotelDto.setOpis(hotel.getOpis());
        return hotelDto;
    }

    public Hotel createHotelDtoToHotel(CreateHotelDto createHotelDto){
        Hotel hotel = new Hotel();
        hotel.setGrad(createHotelDto.getGrad());
        hotel.setIme(createHotelDto.getIme());
        hotel.setOpis(createHotelDto.getOpis());
        return hotel;
    }
}
