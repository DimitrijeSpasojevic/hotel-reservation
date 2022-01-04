package rs.edu.raf.hotelreservation.dto;


import javax.validation.constraints.NotEmpty;
import java.util.List;

public class NotificationSendDto {
    @NotEmpty(message = "Notification type can't be empty")
    private String notificationType;
    @NotEmpty(message = "Email can't be empty")
    private String email;
    private List<NotificationParameterDto> parameters;

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<NotificationParameterDto> getParameters() {
        return parameters;
    }

    public void setParameters(List<NotificationParameterDto> parameters) {
        this.parameters = parameters;
    }
}
