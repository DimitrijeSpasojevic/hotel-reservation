package rs.edu.raf.hotelreservation.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Termin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date datum;
    @ManyToOne
    private TipSobe tipSobe;
    private int brojSlobodnihSoba;

    public TipSobe getTipSobe() {
        return tipSobe;
    }

    public void setTipSobe(TipSobe tipSobe) {
        this.tipSobe = tipSobe;
    }

    public Date getDatum() {
        return datum;
    }

    public int getBrojSlobodnihSoba() {
        return brojSlobodnihSoba;
    }

    public void setBrojSlobodnihSoba(int brojSlobodnihSoba) {
        this.brojSlobodnihSoba = brojSlobodnihSoba;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
