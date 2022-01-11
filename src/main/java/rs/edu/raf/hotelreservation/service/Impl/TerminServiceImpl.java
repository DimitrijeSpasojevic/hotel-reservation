package rs.edu.raf.hotelreservation.service.Impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.edu.raf.hotelreservation.domain.Termin;
import rs.edu.raf.hotelreservation.domain.TipSobe;
import rs.edu.raf.hotelreservation.dto.CreateTerminDto;
import rs.edu.raf.hotelreservation.dto.TerminDto;
import rs.edu.raf.hotelreservation.dto.TipSobeDto;
import rs.edu.raf.hotelreservation.exception.NotFoundException;
import rs.edu.raf.hotelreservation.mapper.TerminMapper;
import rs.edu.raf.hotelreservation.mapper.TipSobeMapper;
import rs.edu.raf.hotelreservation.repository.TerminRepository;
import rs.edu.raf.hotelreservation.repository.TipSobeRepository;
import rs.edu.raf.hotelreservation.service.TerminService;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class TerminServiceImpl implements TerminService {
    private TerminRepository terminRepository;
    private TerminMapper terminMapper;
    private TipSobeRepository tipSobeRepository;
    private TipSobeMapper tipSobeMapper;

    public TerminServiceImpl(TerminRepository terminRepository, TerminMapper terminMapper, TipSobeRepository tipSobeRepository, TipSobeMapper tipSobeMapper) {
        this.terminRepository = terminRepository;
        this.terminMapper = terminMapper;
        this.tipSobeRepository = tipSobeRepository;
        this.tipSobeMapper = tipSobeMapper;
    }

    @Override
    public Page<TerminDto> getAllAvailableByCity(String city, Pageable pageable) {
        return terminRepository.findAllByTipSobe_Hotel_GradAndBrojSlobodnihSobaNot(city, 0, pageable)
                .map(terminMapper::terminToTerminDto);
    }

    @Override
    public Page<TerminDto> getAllAvailableByHotel(Long hotelId, Pageable pageable) {
        return terminRepository.findAllByTipSobe_Hotel_IdAndBrojSlobodnihSobaNot(hotelId, 0, pageable)
                .map(terminMapper::terminToTerminDto);
    }

    @Override
    public Page<TerminDto> getAllByHotel(Long hotelId, Pageable pageable) {
        return terminRepository.findAllByTipSobe_HotelId(hotelId, pageable).map(terminMapper::terminToTerminDto);
    }

    @Override
    public Page<TerminDto> getAllAvailableByPeriod(LocalDate pocetniDatum, LocalDate krajnjiDatum, Pageable pageable) {
        return terminRepository.findAllByDatumBetweenAndBrojSlobodnihSobaNot(pocetniDatum, krajnjiDatum, 0, pageable)
                .map(terminMapper::terminToTerminDto);
    }

    @Override
    public Page<TerminDto> getAllAvailableByPrice(BigDecimal minimalnaCena, BigDecimal maksimalnaCena, Pageable pageable) {
        return terminRepository.findAllByTipSobe_CenaBetweenAndBrojSlobodnihSobaNot(minimalnaCena, maksimalnaCena, 0, pageable)
                .map(terminMapper::terminToTerminDto);
    }

    @Override
    public TerminDto createTermin(CreateTerminDto createTerminDto) {
        Termin termin = terminRepository.save(terminMapper.createTerminDtoToTermin(createTerminDto));
        return terminMapper.terminToTerminDto(termin);
    }

    @Override
    public TerminDto deleteTerminById(Long id) {
        Termin termin = terminRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Termin with id: %d not found.", id)));
        terminRepository.delete(termin);
        return terminMapper.terminToTerminDto(termin);
    }

    @Override
    public TipSobeDto getTipSobeByTerminId(Long terminId) {
        return tipSobeMapper.tipSobeToTipSobeDto(terminRepository.getById(terminId).getTipSobe());
    }
}
