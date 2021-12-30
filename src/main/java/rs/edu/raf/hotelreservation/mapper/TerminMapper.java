package rs.edu.raf.hotelreservation.mapper;

import org.springframework.stereotype.Component;
import rs.edu.raf.hotelreservation.domain.TipSobe;
import rs.edu.raf.hotelreservation.dto.CreateTerminDto;
import rs.edu.raf.hotelreservation.domain.Termin;
import rs.edu.raf.hotelreservation.dto.TerminDto;
import rs.edu.raf.hotelreservation.exception.NotFoundException;
import rs.edu.raf.hotelreservation.repository.TipSobeRepository;

@Component
public class TerminMapper {
    private TipSobeRepository tipSobeRepository;

    public TerminMapper(TipSobeRepository tipSobeRepository) {
        this.tipSobeRepository = tipSobeRepository;
    }

    public TerminDto terminToTerminDto(Termin termin) {
        TerminDto terminDto = new TerminDto();
        terminDto.setId(termin.getId());
        terminDto.setDatum(termin.getDatum());
        terminDto.setBrojSlobodnihSoba(termin.getBrojSlobodnihSoba());
        return terminDto;
    }

    public Termin createTerminDtoToTermin(CreateTerminDto createTerminDto) {
        Termin termin = new Termin();
        termin.setDatum(createTerminDto.getDatum());
        TipSobe tipSobe = tipSobeRepository.findById(createTerminDto.getTipSobeId())
                .orElseThrow(() -> new NotFoundException(String.format("TipSobe with id: %d not found.", createTerminDto.getTipSobeId())));
        termin.setTipSobe(tipSobe);
        termin.setBrojSlobodnihSoba(tipSobe.getKrajOpsegaSoba() - tipSobe.getPocetakOpsegaSoba());
        return termin;
    }
}
