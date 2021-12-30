package rs.edu.raf.hotelreservation.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.edu.raf.hotelreservation.dto.CreateRezervacijaDto;
import rs.edu.raf.hotelreservation.dto.RezervacijaDto;
import rs.edu.raf.hotelreservation.service.RezervacijaService;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping("/rezervacija")
public class RezervacijaController {
    private RezervacijaService rezervacijaService;

    public RezervacijaController(RezervacijaService rezervacijaService) {
        this.rezervacijaService = rezervacijaService;
    }

    @ApiOperation("Get a reservation")
    @GetMapping("/{id}")
    public ResponseEntity<RezervacijaDto> getReservationById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(rezervacijaService.getRezervacijaById(id), HttpStatus.OK);
    }

    @ApiOperation("Get all reservations")
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<Page<RezervacijaDto>> getReservationsByHotel(@PathVariable("hotelId") Long hotelId, @ApiIgnore Pageable pageable) {
        return new ResponseEntity<>(rezervacijaService.getRezervacijaByHotelId(hotelId, pageable), HttpStatus.OK);
    }

    @ApiOperation("Create reservation")
    @PostMapping
    public ResponseEntity<RezervacijaDto> createReservation(@RequestBody @Valid CreateRezervacijaDto createRezervacijaDto) {
        return new ResponseEntity<>(rezervacijaService.createRezervacija(createRezervacijaDto), HttpStatus.CREATED);
    }

    @ApiOperation("Delete reservation")
    @DeleteMapping("/{id}")
    public ResponseEntity<RezervacijaDto> deleteReservation(@PathVariable("id") Long id) {
        return new ResponseEntity<>(rezervacijaService.deleteRezervacijaById(id), HttpStatus.OK);
    }
}
