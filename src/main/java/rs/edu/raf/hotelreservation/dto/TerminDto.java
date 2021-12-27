package rs.edu.raf.hotelreservation.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class TerminDto {
    private Long id;
    @NotEmpty(message = "datum ne moze da ostane prazan")
    private Date datum;
    @NotNull
    @Min(value = 0, message = "Broj slobodnih soba mora biti pozitivan")
    private int brojSlobodnihSoba;
    @NotNull
    private Long tipSobeId;

    public Long getTipSobeId() {
        return tipSobeId;
    }

    public void setTipSobeId(Long tipSobeId) {
        this.tipSobeId = tipSobeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getBrojSlobodnihSoba() {
        return brojSlobodnihSoba;
    }

    public void setBrojSlobodnihSoba(int brojSlobodnihSoba) {
        this.brojSlobodnihSoba = brojSlobodnihSoba;
    }
}
