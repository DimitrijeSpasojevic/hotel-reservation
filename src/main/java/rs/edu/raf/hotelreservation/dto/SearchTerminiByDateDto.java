package rs.edu.raf.hotelreservation.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class SearchTerminiByDateDto {
    @NotNull(message = "startDate ne moze da bude null")
    private LocalDate startDate;
    @NotNull(message = "endDate ne moze da bude null")
    private LocalDate endDate;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
