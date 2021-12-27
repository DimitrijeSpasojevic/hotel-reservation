package rs.edu.raf.hotelreservation.dto;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class TipSobeDto {
    private Long id;
    @NotEmpty(message = "Ne može da postoji hotel bez imena")
    private String ime;
    @Min(value = 0, message = "Cena mora da bude pozitivna")
    private BigDecimal cena;
    @NotNull
    @Min(value = 0, message = "Početak opsega mora da bude pozitivan")
    private int pocetakOpsegaSoba;
    @NotNull
    @Min(value = 0, message = "Kraj opsega mora da bude pozitivan")
    private int krajOpsegaSoba;
    @NotNull
    private Long hotelId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }

    public int getPocetakOpsegaSoba() {
        return pocetakOpsegaSoba;
    }

    public void setPocetakOpsegaSoba(int pocetakOpsegaSoba) {
        this.pocetakOpsegaSoba = pocetakOpsegaSoba;
    }

    public int getKrajOpsegaSoba() {
        return krajOpsegaSoba;
    }

    public void setKrajOpsegaSoba(int krajOpsegaSoba) {
        this.krajOpsegaSoba = krajOpsegaSoba;
    }
}
