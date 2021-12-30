package rs.edu.raf.hotelreservation.domain;

import javax.persistence.*;
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
}
