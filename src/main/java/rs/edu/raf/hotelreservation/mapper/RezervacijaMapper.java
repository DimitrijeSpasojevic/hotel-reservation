package rs.edu.raf.hotelreservation.mapper;

import org.springframework.stereotype.Component;
import rs.edu.raf.hotelreservation.domain.Rezervacija;
import rs.edu.raf.hotelreservation.domain.Termin;
import rs.edu.raf.hotelreservation.domain.TipSobe;
import rs.edu.raf.hotelreservation.dto.CreateRezervacijaDto;
import rs.edu.raf.hotelreservation.dto.RezervacijaDto;
import rs.edu.raf.hotelreservation.exception.NotFoundException;
import rs.edu.raf.hotelreservation.repository.TerminRepository;
import rs.edu.raf.hotelreservation.repository.TipSobeRepository;

import java.math.BigDecimal;
import java.util.List;

@Component
public class RezervacijaMapper {
    private TipSobeRepository tipSobeRepository;
    private TerminRepository terminRepository;

    public RezervacijaMapper(TipSobeRepository tipSobeRepository, TerminRepository terminRepository) {
        this.tipSobeRepository = tipSobeRepository;
        this.terminRepository = terminRepository;
    }

    public RezervacijaDto rezervacijaToRezervacijaDto(Rezervacija rezervacija) {
        RezervacijaDto rezervacijaDto = new RezervacijaDto();
        rezervacijaDto.setId(rezervacija.getId());
        rezervacijaDto.setTipSobeId(rezervacija.getTipSobe().getId());
        rezervacijaDto.setPocetniTerminId(rezervacija.getTermini().get(0).getId());
        rezervacijaDto.setKrajnjiTerminId(rezervacija.getTermini().get(rezervacija.getTermini().size() - 1).getId());
        rezervacijaDto.setUserId(rezervacija.getUserId());
        rezervacijaDto.setCena(rezervacija.getCena());
        return rezervacijaDto;
    }

    public Rezervacija createRezervacijaDtoToRezervacija(CreateRezervacijaDto createRezervacijaDto) {
        Rezervacija rezervacija = new Rezervacija();
        TipSobe tipSobe = tipSobeRepository.getById(createRezervacijaDto.getTipSobeId());
        rezervacija.setTipSobe(tipSobe);
        Termin pocetak = terminRepository.findById(createRezervacijaDto.getPocetniTerminId())
                .orElseThrow(() -> new NotFoundException(String.format("Termin with id: %d not found", createRezervacijaDto.getPocetniTerminId())));
        Termin kraj = terminRepository.findById(createRezervacijaDto.getKrajnjiTerminId())
                .orElseThrow(() -> new NotFoundException(String.format("Termin with id: %d not found", createRezervacijaDto.getKrajnjiTerminId())));
        List<Termin> termini = terminRepository.findAllByTipSobe_IdAndDatumBetween(tipSobe.getId(), pocetak.getDatum(), kraj.getDatum());
        rezervacija.setTermini(termini);
        rezervacija.setReminded(false);
        rezervacija.setUserId(createRezervacijaDto.getUserId());
        BigDecimal cena = new BigDecimal("0.0");
        for (Termin termin: termini)
            cena = cena.add(termin.getTipSobe().getCena());
        rezervacija.setCena(cena);
        return rezervacija;
    }

}
