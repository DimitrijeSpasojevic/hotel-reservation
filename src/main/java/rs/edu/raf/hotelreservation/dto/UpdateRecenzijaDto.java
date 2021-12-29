package rs.edu.raf.hotelreservation.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UpdateRecenzijaDto {

    private Long id;
    @NotNull
    @Min(value = 1)
    @Max(value = 5)
    private int ocena;
    @NotEmpty(message = "Ne može da postoji recenzija bez komentara")
    private String komentar;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }
}
