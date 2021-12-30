package rs.edu.raf.hotelreservation.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.edu.raf.hotelreservation.domain.Recenzija;

@Repository
public interface RecenzijaRepository extends JpaRepository<Recenzija, Long> {
    Page<Recenzija> findAllByHotel_Id(Long hotelId, Pageable pageable);
    Page<Recenzija> findAllByHotel_Grad(String city, Pageable pageable);
    Page<Recenzija> findAllByUserId(Long userId, Pageable pageable);
}
