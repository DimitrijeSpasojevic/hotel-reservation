package rs.edu.raf.hotelreservation.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Rezervacija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private TipSobe tipSobe;
    @ManyToOne
    private Termin pocetak;
    @ManyToOne
    private Termin kraj;

    public TipSobe getTipSobe() {
        return tipSobe;
    }

    public void setTipSobe(TipSobe tipSobe) {
        this.tipSobe = tipSobe;
    }

    public Termin getPocetak() {
        return pocetak;
    }

    public void setPocetak(Termin pocetak) {
        this.pocetak = pocetak;
    }

    public Termin getKraj() {
        return kraj;
    }

    public void setKraj(Termin kraj) {
        this.kraj = kraj;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
