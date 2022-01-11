package rs.edu.raf.hotelreservation.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.edu.raf.hotelreservation.domain.TipSobe;
import rs.edu.raf.hotelreservation.dto.CreateTerminDto;
import rs.edu.raf.hotelreservation.dto.TerminDto;
import rs.edu.raf.hotelreservation.dto.TipSobeDto;
import rs.edu.raf.hotelreservation.security.CheckSecurity;
import rs.edu.raf.hotelreservation.service.TerminService;

import javax.validation.Valid;

@RestController
@RequestMapping("/tipsobe")
public class TipSobeController {

    private TerminService terminService;

    public TipSobeController(TerminService terminService) {
        this.terminService = terminService;
    }

    @ApiOperation(value = "Get tip Sobe By TerminId")
    @GetMapping("/{id}")
    public ResponseEntity<TipSobeDto> getTipSobeByTerminId(@PathVariable("id") Long terminId) {
        return new ResponseEntity<>(terminService.getTipSobeByTerminId(terminId), HttpStatus.OK);
    }
}
