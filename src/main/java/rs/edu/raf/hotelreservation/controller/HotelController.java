package rs.edu.raf.hotelreservation.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.edu.raf.hotelreservation.dto.*;
import rs.edu.raf.hotelreservation.security.CheckSecurity;
import rs.edu.raf.hotelreservation.service.HotelService;
import rs.edu.raf.hotelreservation.service.TerminService;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    private HotelService hotelService;
    private TerminService terminService;

    public HotelController(HotelService hotelService, TerminService terminService) {
        this.hotelService = hotelService;
        this.terminService = terminService;
    }

    @ApiOperation(value = "Add hotel")
    @PostMapping
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<HotelDto> addHotel(@RequestBody @Valid CreateHotelDto createHotelDto, @RequestHeader("authorization") String authorization) {
        return new ResponseEntity<>(hotelService.addHotel(createHotelDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get all hotels")
    @GetMapping
    public ResponseEntity<Page<HotelDto>> getAllHotels(@ApiIgnore Pageable pageable) {
        return new ResponseEntity<>(hotelService.getAll(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Get hotel by ID")
    @GetMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_CLIENT", "ROLE_MANAGER"})
    public ResponseEntity<HotelDto> getHotelById(@PathVariable("id") Long id, @RequestHeader("authorization") String authorization){
        return new ResponseEntity<>(hotelService.getHotelById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Get all room types for hotel")
    @GetMapping("/{id}/types")
    @CheckSecurity(roles = {"ROLE_CLIENT", "ROLE_MANAGER"})
    public ResponseEntity<Page<TipSobeDto>> getAllRoomTypes(@PathVariable("id") Long id, @ApiIgnore Pageable pageable, @RequestHeader("authorization") String authorization){
        return new ResponseEntity<>(hotelService.getAllRoomTypes(id, pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Create room type")
    @PostMapping("/types")
    @CheckSecurity(roles = {"ROLE_MANAGER"})
    public ResponseEntity<TipSobeDto> createRoomType(@RequestBody @Valid CreateTipSobeDto createTipSobeDto,@RequestHeader("authorization") String authorization) {
        return new ResponseEntity<>(hotelService.createRoomType(createTipSobeDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Change hotel")
    @PutMapping
    @CheckSecurity(roles = {"ROLE_MANAGER"})
    public ResponseEntity<HotelDto> changeHotel(@RequestBody @Valid HotelDto hotelDto, @RequestHeader("authorization") String authorization) {
        return new ResponseEntity<>(hotelService.changeHotel(hotelDto), HttpStatus.OK);
    }

    @ApiOperation(value = "Get all termini for hotel")
    @GetMapping("/{id}/termini")
    @CheckSecurity(roles = {"ROLE_CLIENT", "ROLE_MANAGER"})
    public ResponseEntity<Page<TerminDto>> getAllTermini(@PathVariable("id") Long id, @ApiIgnore Pageable pageable, @RequestHeader("authorization") String authorization) {
        return new ResponseEntity<>(terminService.getAllByHotel(id, pageable), HttpStatus.OK);
    }
}
