package rs.edu.raf.hotelreservation.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Rezervacija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private TipSobe tipSobe;
    @ManyToMany
    private List<Termin> termini = new ArrayList<>();
    private boolean reminded;
    private Long userId;
    private BigDecimal cena;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public TipSobe getTipSobe() {
        return tipSobe;
    }

    public void setTipSobe(TipSobe tipSobe) {
        this.tipSobe = tipSobe;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Termin> getTermini() {
        return termini;
    }

    public void setTermini(List<Termin> termini) {
        this.termini = termini;
    }

    public boolean isReminded() {
        return reminded;
    }

    public void setReminded(boolean notified) {
        this.reminded = notified;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }
}
