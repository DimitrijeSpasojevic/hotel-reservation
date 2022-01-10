package rs.edu.raf.hotelreservation.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class RezervacijaDto {

    private Long id;
    @NotNull(message = "tip sobe ne moze da bude prazno")
    private Long tipSobeId;
    @NotNull(message = "pocetak termina ne moze biti prazno")
    private Long pocetniTerminId;
    @NotNull(message = "kraj termina ne moze biti prazno")
    private Long krajnjiTerminId;
    private Long userId;
    private BigDecimal cena;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTipSobeId() {
        return tipSobeId;
    }

    public void setTipSobeId(Long tipSobeId) {
        this.tipSobeId = tipSobeId;
    }

    public Long getPocetniTerminId() {
        return pocetniTerminId;
    }

    public void setPocetniTerminId(Long pocetniTerminId) {
        this.pocetniTerminId = pocetniTerminId;
    }

    public Long getKrajnjiTerminId() {
        return krajnjiTerminId;
    }

    public void setKrajnjiTerminId(Long krajnjiTerminId) {
        this.krajnjiTerminId = krajnjiTerminId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }
}
