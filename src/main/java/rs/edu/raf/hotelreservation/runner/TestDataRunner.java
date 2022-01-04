package rs.edu.raf.hotelreservation.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import rs.edu.raf.hotelreservation.domain.Hotel;
import rs.edu.raf.hotelreservation.domain.Termin;
import rs.edu.raf.hotelreservation.domain.TipSobe;
import rs.edu.raf.hotelreservation.repository.HotelRepository;
import rs.edu.raf.hotelreservation.repository.RezervacijaRepository;
import rs.edu.raf.hotelreservation.repository.TerminRepository;
import rs.edu.raf.hotelreservation.repository.TipSobeRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Profile({"default"})
@Component
public class TestDataRunner implements CommandLineRunner {

    private HotelRepository hotelRepository;
    private TerminRepository terminRepository;
    private TipSobeRepository tipSobeRepository;

    public TestDataRunner(HotelRepository hotelRepository, TerminRepository terminRepository, TipSobeRepository tipSobeRepository, RezervacijaRepository rezervacijaRepository) {
        this.hotelRepository = hotelRepository;
        this.terminRepository = terminRepository;
        this.tipSobeRepository = tipSobeRepository;
    }

    @Override
    public void run(String... args) {
        Hotel hotel = new Hotel();
        hotel.setGrad("Beograd");
        hotel.setIme("HOTELBEOGRAD");
        hotel.setOpis("Dobar hotel opis");
        hotelRepository.save(hotel);

        TipSobe tipSobe = new TipSobe();
        tipSobe.setPocetakOpsegaSoba(0);
        tipSobe.setKrajOpsegaSoba(250);
        tipSobe.setHotel(hotel);
        tipSobe.setCena(new BigDecimal(20));
        tipSobe.setIme("tipA");
        tipSobeRepository.save(tipSobe);

        Termin termin = new Termin();
        termin.setDatum(LocalDate.of(2022, 1, 4));
        termin.setTipSobe(tipSobe);
        termin.setBrojSlobodnihSoba(30);

        List<Termin> terminList = new ArrayList<>();
        terminList.add(termin);

        terminRepository.save(termin);
        tipSobe.setTermini(terminList);
        tipSobeRepository.save(tipSobe);
    }
}
