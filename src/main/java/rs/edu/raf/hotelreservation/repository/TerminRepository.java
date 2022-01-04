package rs.edu.raf.hotelreservation.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.edu.raf.hotelreservation.domain.Termin;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TerminRepository extends JpaRepository<Termin, Long> {
    Page<Termin> findAllByTipSobe_Hotel_GradAndBrojSlobodnihSobaNot(String grad, int notBrojSlobodnihSoba, Pageable pageable);
    Page<Termin> findAllByTipSobe_HotelId(Long hotelId, Pageable pageable);
    Page<Termin> findAllByTipSobe_Hotel_IdAndBrojSlobodnihSobaNot(Long hotelId, int notBrojSlobodnihSoba, Pageable pageable);
    Page<Termin> findAllByDatumBetweenAndBrojSlobodnihSobaNot(LocalDate start, LocalDate end, int notBrojSlobodnihSoba, Pageable pageable);
    Page<Termin> findAllByTipSobe_CenaBetweenAndBrojSlobodnihSobaNot(BigDecimal min, BigDecimal max, int notBrojSlobodnihSoba, Pageable pageable);
    List<Termin> findAllByTipSobe_IdAndDatumBetween(Long tipSobeId, LocalDate start, LocalDate end);
}
