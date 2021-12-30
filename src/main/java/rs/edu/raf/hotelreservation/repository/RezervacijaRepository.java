package rs.edu.raf.hotelreservation.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.edu.raf.hotelreservation.domain.Rezervacija;

@Repository
public interface RezervacijaRepository extends JpaRepository<Rezervacija, Long> {
    Page<Rezervacija> getRezervacijaByTipSobe_Hotel_Id(Long hotelId, Pageable pageable);
}
