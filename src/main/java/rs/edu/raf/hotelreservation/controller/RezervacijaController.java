package rs.edu.raf.hotelreservation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.edu.raf.hotelreservation.service.RezervacijaService;

@RestController
@RequestMapping("hotel/{id}/rezervacija")
public class RezervacijaController {
    private RezervacijaService rezervacijaService;

    public RezervacijaController(RezervacijaService rezervacijaService) {
        this.rezervacijaService = rezervacijaService;
    }

}
