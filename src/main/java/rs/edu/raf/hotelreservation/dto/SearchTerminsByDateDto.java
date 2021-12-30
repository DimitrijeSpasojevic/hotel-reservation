package rs.edu.raf.hotelreservation.dto;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class SearchTerminsByDateDto {
    @NotEmpty(message = "startDate ne moze da bude prazan")
    private Date startDate;
    @NotEmpty(message = "endDate ne moze da bude prazan")
    private Date endDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
