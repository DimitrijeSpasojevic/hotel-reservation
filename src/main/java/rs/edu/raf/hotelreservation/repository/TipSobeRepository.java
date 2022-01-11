package rs.edu.raf.hotelreservation.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.edu.raf.hotelreservation.domain.Termin;
import rs.edu.raf.hotelreservation.domain.TipSobe;

@Repository
public interface TipSobeRepository extends JpaRepository<TipSobe, Long> {
    Page<TipSobe> findAllByHotel_Id(Long hotelId, Pageable pageable);
}
