package rs.edu.raf.hotelreservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.edu.raf.hotelreservation.domain.Rezervacija;

@Repository
public interface RezervacijaRepository extends JpaRepository<Rezervacija, Long> {
}
