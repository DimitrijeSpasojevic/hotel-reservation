package rs.edu.raf.hotelreservation.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.edu.raf.hotelreservation.dto.CreateTerminDto;
import rs.edu.raf.hotelreservation.dto.SearchTerminsByDateDto;
import rs.edu.raf.hotelreservation.service.TerminService;
import rs.edu.raf.hotelreservation.dto.TerminDto;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;

@RestController
@RequestMapping("termin/")
public class TerminController {
    private TerminService terminService;

    public TerminController(TerminService terminService) {
        this.terminService = terminService;
    }

    @ApiOperation(value = "Get all available termini by city")
    @GetMapping("/city/{city}")
    public ResponseEntity<Page<TerminDto>> getAllTerminiByCity(@PathVariable("city") String city, @ApiIgnore Pageable pageable) {
        return new ResponseEntity<>(terminService.getAllAvailableByCity(city, pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Get all available termini by hotel")
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<Page<TerminDto>> getAllTerminiByHotel(@PathVariable("hotelId") Long hotelId, @ApiIgnore Pageable pageable) {
        return new ResponseEntity<>(terminService.getAllAvailableByHotel(hotelId, pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Get all available termini by period")
    @PostMapping("/period")
    public ResponseEntity<Page<TerminDto>> getAllTerminiByPeriod(@RequestBody @Valid SearchTerminsByDateDto searchTerminsByDateDto,
                                                                 @ApiIgnore Pageable pageable) {
        return new ResponseEntity<>(terminService.
                getAllAvailableByPeriod(searchTerminsByDateDto.getStartDate(), searchTerminsByDateDto.getEndDate(), pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Get all available termini by price")
    @GetMapping("/price/{minPrice}/{maxPrice}")
    public ResponseEntity<Page<TerminDto>> getAllTerminiByPrice(@PathVariable("minPrice") BigDecimal minPrice,
                                                                @PathVariable("maxPrice") BigDecimal maxPrice, @ApiIgnore Pageable pageable) {
        return new ResponseEntity<>(terminService.getAllAvailableByPrice(minPrice, maxPrice, pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Create termin")
    @PostMapping
    public ResponseEntity<TerminDto> createTermin(@RequestBody @Valid CreateTerminDto createTerminDto) {
        return new ResponseEntity<>(terminService.createTermin(createTerminDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete termin")
    @DeleteMapping("/{id}")
    public ResponseEntity<TerminDto> deleteTermin(@PathVariable("id") Long id) {
        return new ResponseEntity<>(terminService.deleteTerminById(id), HttpStatus.OK);
    }
}
