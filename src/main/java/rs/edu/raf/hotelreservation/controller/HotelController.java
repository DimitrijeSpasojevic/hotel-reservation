package rs.edu.raf.hotelreservation.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.edu.raf.hotelreservation.dto.CreateHotelDto;
import rs.edu.raf.hotelreservation.dto.CreateTipSobeDto;
import rs.edu.raf.hotelreservation.dto.HotelDto;
import rs.edu.raf.hotelreservation.dto.TipSobeDto;
import rs.edu.raf.hotelreservation.service.HotelService;

import javax.validation.Valid;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    private HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @ApiOperation(value = "Get all hotels")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "What page number you want", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "Number of items to return", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})

    @GetMapping
    public ResponseEntity<Page<HotelDto>> getAllHotels(Pageable pageable){
        return new ResponseEntity<>(hotelService.getAll(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Add hotel")
    @PostMapping
    public ResponseEntity<HotelDto> addHotel(@RequestBody @Valid CreateHotelDto createHotelDto) {
        return new ResponseEntity<>(hotelService.addHotel(createHotelDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get hotel by ID")
    @GetMapping("/{id}")
    public ResponseEntity<HotelDto> getHotelById(@PathVariable("id") Long id){
        return new ResponseEntity<>(hotelService.getHotelById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Get all room types")
    @GetMapping("/types")
    public ResponseEntity<Page<TipSobeDto>> getAllRoomTypes(Pageable pageable){
        return new ResponseEntity<>(hotelService.getAllRoomTypes(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Create room type")
    @PostMapping("/types")
    public ResponseEntity<TipSobeDto> createRoomType(@RequestBody @Valid CreateTipSobeDto createTipSobeDto) {
        return new ResponseEntity<>(hotelService.createRoomType(createTipSobeDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Change hotel")
    @PutMapping
    public ResponseEntity<HotelDto> changeHotel(@RequestBody @Valid HotelDto hotelDto) {
        return new ResponseEntity<>(hotelService.changeHotel(hotelDto), HttpStatus.OK);
    }
}
