package rs.edu.raf.hotelreservation.mapper;

import org.springframework.stereotype.Component;
import rs.edu.raf.hotelreservation.domain.Hotel;
import rs.edu.raf.hotelreservation.domain.TipSobe;
import rs.edu.raf.hotelreservation.dto.CreateTipSobeDto;
import rs.edu.raf.hotelreservation.dto.TipSobeDto;
import rs.edu.raf.hotelreservation.exception.NotFoundException;
import rs.edu.raf.hotelreservation.repository.HotelRepository;

@Component
public class TipSobeMapper {
    private HotelRepository hotelRepository;

    public TipSobeMapper(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public TipSobeDto tipSobeToTipSobeDto(TipSobe tipSobe){
        TipSobeDto tipSobeDto = new TipSobeDto();
        tipSobeDto.setId(tipSobe.getId());
        tipSobeDto.setCena(tipSobe.getCena());
        tipSobeDto.setIme(tipSobe.getIme());
        tipSobeDto.setHotelId(tipSobe.getHotel().getId());
        tipSobeDto.setPocetakOpsegaSoba(tipSobe.getPocetakOpsegaSoba());
        tipSobeDto.setKrajOpsegaSoba(tipSobe.getKrajOpsegaSoba());
        return tipSobeDto;
    }

    public TipSobe createTipSobeDtoToTipSobe(CreateTipSobeDto createTipSobeDto) {
        TipSobe tipSobe = new TipSobe();
        tipSobe.setCena(createTipSobeDto.getCena());
        tipSobe.setIme(createTipSobeDto.getIme());
        Hotel hotel = hotelRepository.findById(createTipSobeDto.getHotelId())
                .orElseThrow(() -> new NotFoundException(String.format("Hotel with id: %d not found.", createTipSobeDto.getHotelId())));
        tipSobe.setHotel(hotel);
        tipSobe.setPocetakOpsegaSoba(createTipSobeDto.getPocetakOpsegaSoba());
        tipSobe.setKrajOpsegaSoba(createTipSobeDto.getKrajOpsegaSoba());
        return tipSobe;
    }
}
