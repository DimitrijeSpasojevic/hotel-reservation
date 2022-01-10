package rs.edu.raf.hotelreservation.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CreateRezervacijaDto {

    @NotNull(message = "tip sobe ne moze da bude prazno")
    private Long tipSobeId;
    @NotNull(message = "pocetak termina ne moze biti prazno")
    private Long pocetniTerminId;
    @NotNull(message = "kraj termina ne moze biti prazno")
    private Long krajnjiTerminId;
    private Long userId;

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
}
