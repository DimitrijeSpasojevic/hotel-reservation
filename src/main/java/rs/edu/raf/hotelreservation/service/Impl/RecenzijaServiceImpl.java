package rs.edu.raf.hotelreservation.service.Impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.edu.raf.hotelreservation.domain.Recenzija;
import rs.edu.raf.hotelreservation.dto.CreateRecenzijaDto;
import rs.edu.raf.hotelreservation.dto.RecenzijaDto;
import rs.edu.raf.hotelreservation.dto.UpdateRecenzijaDto;
import rs.edu.raf.hotelreservation.exception.NotFoundException;
import rs.edu.raf.hotelreservation.mapper.RecenzijaMapper;
import rs.edu.raf.hotelreservation.repository.RecenzijaRepository;
import rs.edu.raf.hotelreservation.service.RecenzijaService;

@Service
public class RecenzijaServiceImpl implements RecenzijaService {
    private RecenzijaRepository recenzijaRepository;
    private RecenzijaMapper recenzijaMapper;

    public RecenzijaServiceImpl(RecenzijaRepository recenzijaRepository, RecenzijaMapper recenzijaMapper) {
        this.recenzijaRepository = recenzijaRepository;
        this.recenzijaMapper = recenzijaMapper;
    }

    @Override
    public RecenzijaDto deleteById(Long id) {
        Recenzija recenzija = recenzijaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Recenzija with id: %d not found.", id)));
        recenzijaRepository.delete(recenzija);
        return recenzijaMapper.recenzijaToRecenzijaDto(recenzija);
    }

    @Override
    public RecenzijaDto update(UpdateRecenzijaDto updateRecenzijaDto) {
        Recenzija recenzija = recenzijaRepository.findById(updateRecenzijaDto.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Recenzija with id: %d not found.", updateRecenzijaDto.getId())));
        recenzija.setOcena(updateRecenzijaDto.getOcena());
        recenzija.setKomentar(updateRecenzijaDto.getKomentar());
        recenzijaRepository.save(recenzija);
        return recenzijaMapper.recenzijaToRecenzijaDto(recenzija);
    }

    @Override
    public RecenzijaDto createRecenzija(CreateRecenzijaDto createRecenzijaDto) {
        Recenzija recenzija = recenzijaRepository.save(recenzijaMapper.createRecenzijaDtoToRecenzija(createRecenzijaDto));
        return recenzijaMapper.recenzijaToRecenzijaDto(recenzija);
    }

    @Override
    public Page<RecenzijaDto> findByHotelId(Long hotelId, Pageable pageable) {
        return recenzijaRepository.findAllByHotel_Id(hotelId, pageable).map(recenzijaMapper::recenzijaToRecenzijaDto);
    }

    @Override
    public Page<RecenzijaDto> findByCity(String city, Pageable pageable) {
        return recenzijaRepository.findAllByHotel_Grad(city, pageable).map(recenzijaMapper::recenzijaToRecenzijaDto);
    }

    @Override
    public Page<RecenzijaDto> findByUserId(Long userId, Pageable pageable) {
        return recenzijaRepository.findAllByUserId(userId, pageable).map(recenzijaMapper::recenzijaToRecenzijaDto);
    }
}
