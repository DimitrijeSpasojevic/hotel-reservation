package rs.edu.raf.hotelreservation.mapper;

import org.springframework.stereotype.Component;
import rs.edu.raf.hotelreservation.domain.Rezervacija;
import rs.edu.raf.hotelreservation.domain.Termin;
import rs.edu.raf.hotelreservation.domain.TipSobe;
import rs.edu.raf.hotelreservation.dto.CreateRezervacijaDto;
import rs.edu.raf.hotelreservation.dto.RezervacijaDto;
import rs.edu.raf.hotelreservation.repository.TerminRepository;
import rs.edu.raf.hotelreservation.repository.TipSobeRepository;

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
        return rezervacijaDto;
    }

    public Rezervacija createRezervacijaDtoToRezervacija(CreateRezervacijaDto createRezervacijaDto) {
        Rezervacija rezervacija = new Rezervacija();
        TipSobe tipSobe = tipSobeRepository.getById(createRezervacijaDto.getTipSobeId());
        rezervacija.setTipSobe(tipSobe);
        Termin pocetak = terminRepository.getById(createRezervacijaDto.getPocetniTerminId());
        rezervacija.setPocetak(pocetak);
        Termin kraj = terminRepository.getById(createRezervacijaDto.getKrajnjiTerminId());
        rezervacija.setKraj(kraj);
        return rezervacija;
    }

}
