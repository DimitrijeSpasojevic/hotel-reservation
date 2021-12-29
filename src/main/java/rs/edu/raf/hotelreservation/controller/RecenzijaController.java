package rs.edu.raf.hotelreservation.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.edu.raf.hotelreservation.dto.CreateHotelDto;
import rs.edu.raf.hotelreservation.dto.CreateRecenzijaDto;
import rs.edu.raf.hotelreservation.dto.RecenzijaDto;
import rs.edu.raf.hotelreservation.dto.UpdateRecenzijaDto;
import rs.edu.raf.hotelreservation.service.RecenzijaService;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping("hotel/{id}/recenzija")
public class RecenzijaController {

    private RecenzijaService recenzijaService;

    public RecenzijaController(RecenzijaService recenzijaService) {
        this.recenzijaService = recenzijaService;
    }

    @ApiOperation(value = "Vrati sve recenzije za hotelId")
    @GetMapping("/hotel/{id}")
    public ResponseEntity<Page<RecenzijaDto>> findByHotelId(@PathVariable("id") Long hotelId, @ApiIgnore  Pageable pageable){
        return new ResponseEntity<>(recenzijaService.findByHotelId(hotelId,pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Vrati sve recenzije za grad")
    @GetMapping("/city/{city}")
    public ResponseEntity<Page<RecenzijaDto>> findByCity(@PathVariable("city") String city, @ApiIgnore Pageable pageable){
        return new ResponseEntity<>(recenzijaService.findByCity(city, pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Vrati sve recenzije za korisnika")
    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<RecenzijaDto>> findByUserId(@PathVariable("userId") Long userId, @ApiIgnore Pageable pageable){
        return new ResponseEntity<>(recenzijaService.findByUserId(userId,pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Obrisi po id-u recenzije")
    @DeleteMapping("/{id}")
    public ResponseEntity<RecenzijaDto> deleteById(@PathVariable("id") Long id){
        return new ResponseEntity<>(recenzijaService.deleteById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Update po id-u recenziju")
    @PutMapping
    public ResponseEntity<RecenzijaDto> updateById(@RequestBody @Valid UpdateRecenzijaDto updateRecenzijaDto){
        return new ResponseEntity<>(recenzijaService.update(updateRecenzijaDto), HttpStatus.OK);
    }

    @ApiOperation(value = "Kreiraj recenziju")
    @PostMapping
    public ResponseEntity<RecenzijaDto> createRecenzija(@RequestBody @Valid CreateRecenzijaDto createRecenzijaDto){
        return new ResponseEntity<>(recenzijaService.createRecenzija(createRecenzijaDto), HttpStatus.OK);
    }
}
