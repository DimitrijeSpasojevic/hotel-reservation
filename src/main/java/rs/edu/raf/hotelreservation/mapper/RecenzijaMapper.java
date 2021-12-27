package rs.edu.raf.hotelreservation.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.edu.raf.hotelreservation.domain.Recenzija;
import rs.edu.raf.hotelreservation.dto.CreateRecenzijaDto;
import rs.edu.raf.hotelreservation.dto.RecenzijaDto;
import rs.edu.raf.hotelreservation.repository.HotelRepository;

@Component
public class RecenzijaMapper {

    HotelRepository hotelRepository;

    public RecenzijaMapper(HotelRepository hotelRepository){
        this.hotelRepository = hotelRepository;
    }

    public RecenzijaDto recenzijaToRecenzijaDto(Recenzija recenzija){
        RecenzijaDto recenzijaDto = new RecenzijaDto();
        recenzijaDto.setId(recenzija.getId());
        recenzijaDto.setHotelId(recenzija.getHotel().getId());
        recenzijaDto.setKomentar(recenzija.getKomentar());
        recenzijaDto.setOcena(recenzija.getOcena());
        recenzijaDto.setUserId(recenzija.getUserId());
        return recenzijaDto;
    }

    public Recenzija createRecenzijaDtoToRecenzija(CreateRecenzijaDto createRecenzijaDto){
        Recenzija recenzija = new Recenzija();
        recenzija.setKomentar(createRecenzijaDto.getKomentar());
        recenzija.setOcena(createRecenzijaDto.getOcena());
        recenzija.setUserId(createRecenzijaDto.getUserId());
        recenzija.setHotel(hotelRepository.getById(createRecenzijaDto.getHotelId()));
        return recenzija;
    }
}
