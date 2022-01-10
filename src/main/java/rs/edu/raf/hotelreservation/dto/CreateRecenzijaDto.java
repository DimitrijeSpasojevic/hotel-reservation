package rs.edu.raf.hotelreservation.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CreateRecenzijaDto {

    @NotNull
    private Long hotelId;
    @NotNull(message = "Ne moze da postoji recenzija bez koisnika")
    private Long userId;
    @NotNull
    @Min(value = 1)
    @Max(value = 5)
    private int ocena;
    @NotEmpty(message = "Ne može da postoji recenzija bez komentara")
    private String komentar;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }
}
