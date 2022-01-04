package rs.edu.raf.hotelreservation.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import rs.edu.raf.hotelreservation.service.RezervacijaService;

@Component
public class ReservationReminderScheduler {
    private RezervacijaService rezervacijaService;

    public ReservationReminderScheduler(RezervacijaService rezervacijaService) {
        this.rezervacijaService = rezervacijaService;
    }

    @Scheduled(initialDelay = 60_000, fixedDelay = 60_000)
    public void reminder() {
        rezervacijaService.remindOfRezervacija();
    }
}
