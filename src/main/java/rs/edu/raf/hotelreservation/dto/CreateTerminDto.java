package rs.edu.raf.hotelreservation.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class CreateTerminDto {

    @NotEmpty(message = "datum ne moze da ostane prazan")
    private Date datum;
    @NotNull
    private Long tipSobeId;

    public Long getTipSobeId() {
        return tipSobeId;
    }

    public void setTipSobeId(Long tipSobeId) {
        this.tipSobeId = tipSobeId;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }
}
