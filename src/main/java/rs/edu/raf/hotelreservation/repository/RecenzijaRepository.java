package rs.edu.raf.hotelreservation.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.edu.raf.hotelreservation.domain.Recenzija;

@Repository
public interface RecenzijaRepository extends JpaRepository<Recenzija, Long> {
}
