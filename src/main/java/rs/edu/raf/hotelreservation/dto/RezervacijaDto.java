package rs.edu.raf.hotelreservation.dto;

import rs.edu.raf.hotelreservation.domain.Termin;
import rs.edu.raf.hotelreservation.domain.TipSobe;
import javax.validation.constraints.NotEmpty;

public class RezervacijaDto {

    private Long id;
    @NotEmpty(message = "tip sobe ne moze da bude prazno")
    private Long tipSobeId;
    @NotEmpty(message = "pocetak termina ne moze biti prazno")
    private Long pocetniTerminId;
    @NotEmpty(message = "kraj termina ne moze biti prazno")
    private Long krajnjiTerminId;

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
}
