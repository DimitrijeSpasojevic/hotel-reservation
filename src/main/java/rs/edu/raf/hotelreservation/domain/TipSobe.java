package rs.edu.raf.hotelreservation.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TipSobe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ime;
    private BigDecimal cena;
    private int pocetakOpsegaSoba;
    private int krajOpsegaSoba;
    @ManyToOne
    private Hotel hotel;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipSobe", orphanRemoval = true)
    private List<Termin> termini = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }

    public List<Termin> getTermini() {
        return termini;
    }

    public void setTermini(List<Termin> termini) {
        this.termini = termini;
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

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Long getId() {
        return id;
    }
}
