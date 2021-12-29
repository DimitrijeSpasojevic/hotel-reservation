package rs.edu.raf.hotelreservation.service.Impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.edu.raf.hotelreservation.domain.Hotel;
import rs.edu.raf.hotelreservation.domain.TipSobe;
import rs.edu.raf.hotelreservation.dto.CreateHotelDto;
import rs.edu.raf.hotelreservation.dto.CreateTipSobeDto;
import rs.edu.raf.hotelreservation.dto.HotelDto;
import rs.edu.raf.hotelreservation.dto.TipSobeDto;
import rs.edu.raf.hotelreservation.exception.NotFoundException;
import rs.edu.raf.hotelreservation.mapper.HotelMapper;
import rs.edu.raf.hotelreservation.mapper.TipSobeMapper;
import rs.edu.raf.hotelreservation.repository.HotelRepository;
import rs.edu.raf.hotelreservation.repository.TipSobeRepository;
import rs.edu.raf.hotelreservation.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

    private HotelRepository hotelRepository;
    private HotelMapper hotelMapper;
    private TipSobeRepository tipSobeRepository;
    private TipSobeMapper tipSobeMapper;

    public HotelServiceImpl(HotelRepository hotelRepository, HotelMapper hotelMapper,
                            TipSobeRepository tipSobeRepository, TipSobeMapper tipSobeMapper) {
        this.hotelRepository = hotelRepository;
        this.hotelMapper = hotelMapper;
        this.tipSobeRepository = tipSobeRepository;
        this.tipSobeMapper = tipSobeMapper;
    }

    @Override
    public Page<HotelDto> getAll(Pageable pageable) {
        return hotelRepository.findAll(pageable).map(hotelMapper::hotelToHotelDto);
    }

    @Override
    public HotelDto addHotel(CreateHotelDto createHotelDto) {
        return hotelMapper.hotelToHotelDto(hotelRepository.save(hotelMapper.createHotelDtoToHotel(createHotelDto)));
    }

    @Override
    public HotelDto changeHotel(HotelDto hotelDto) {
        Hotel hotel = hotelRepository.getById(hotelDto.getId());
        hotel.setOpis(hotelDto.getOpis());
        hotel.setIme(hotelDto.getIme());
        hotel.setGrad(hotelDto.getGrad());
        return hotelMapper.hotelToHotelDto(hotel);
    }

    @Override
    public HotelDto getHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Hotel with id: %d not found", id)));
        return hotelMapper.hotelToHotelDto(hotel);
    }

    @Override
    public Page<TipSobeDto> getAllRoomTypes(Long hotelId, Pageable pageable) {
        return tipSobeRepository.findAllByHotel_Id(hotelId, pageable).map(tipSobeMapper::tipSobeToTipSobeDto);
    }

    @Override
    public TipSobeDto createRoomType(CreateTipSobeDto createTipSobeDto) {
        return null;
    }
}
