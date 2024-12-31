package com.sbs.client.controller;

import com.sbs.client.dto.AdDto;
import com.sbs.client.dto.ReservationDto;
import com.sbs.client.dto.UserDto;
import com.sbs.client.entity.Reservation;
import com.sbs.client.enums.ReservationStatus;
import com.sbs.client.external.AuthService;
import com.sbs.client.external.CompanyService;
import com.sbs.client.service.ReservationService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private AuthService authService;

    @Autowired
    private CompanyService companyService;

    @PostMapping(path = "/bookService")
    public ResponseEntity<?> bookService(@RequestBody ReservationDto dto){

        UserDto client = authService.getUser(dto.getUserId());
        if(client==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found with userId:" + dto.getUserId());
        }
        UserDto company = authService.getUser(dto.getCompanyId());
        if(company==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found with userId:" + dto.getCompanyId());
        }
        AdDto adDto = companyService.getAd(dto.getAdId());
        if(adDto==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ad not found with id:" + dto.getAdId());
        }
        ReservationDto reservationDto = reservationService.bookService(dto);

        return ResponseEntity.ok().body(reservationDto);
    }

    @GetMapping(path = "/bookings")
    public ResponseEntity<List<ReservationDto>> getAllBookings() {
        return ResponseEntity.ok().body(reservationService.getAllBookings());
    }

    @GetMapping(path = "/bookings/{userId}")
    public ResponseEntity<List<ReservationDto>> getAllBookingsByUserId(@PathVariable long userId) {
        return ResponseEntity.ok().body(reservationService.getBookingsByUserId(userId));
    }

    @PutMapping(path = "/changeStatus/{reservationId}/{status}")
    public ResponseEntity<?> changeBookingStatus(@PathVariable long reservationId, @PathVariable ReservationStatus status){
        ReservationDto reservationDto = reservationService.changeBookingStatus(reservationId,status);
        if(reservationDto!=null){
            return ResponseEntity.ok().body(reservationDto);
        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reservation not found");
    }
}
