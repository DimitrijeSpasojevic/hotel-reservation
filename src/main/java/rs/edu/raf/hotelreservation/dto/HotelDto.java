package rs.edu.raf.hotelreservation.dto;

import javax.validation.constraints.NotEmpty;

public class HotelDto {
    private Long id;
    @NotEmpty(message = "Ime ne moze da bude prazno")
    private String ime;
    @NotEmpty(message = "Ne moze da nema opisa")
    private String opis;
    @NotEmpty(message = "Grad ne moze biti prazno polje")
    private String grad;

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

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }
}
