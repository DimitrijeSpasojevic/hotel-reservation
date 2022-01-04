package rs.edu.raf.hotelreservation.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class CreateTerminDto {
    @NotNull(message = "datum ne moze da bude null")
    private LocalDate datum;
    @NotNull(message = "id tipa sobe ne moze da bude null")
    private Long tipSobeId;

    public Long getTipSobeId() {
        return tipSobeId;
    }

    public void setTipSobeId(Long tipSobeId) {
        this.tipSobeId = tipSobeId;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }
}
