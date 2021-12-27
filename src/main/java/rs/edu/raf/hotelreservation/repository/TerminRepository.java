package rs.edu.raf.hotelreservation.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.edu.raf.hotelreservation.domain.Termin;

@Repository
public interface TerminRepository extends JpaRepository<Termin, Long> {

}
