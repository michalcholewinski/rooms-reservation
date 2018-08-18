package com.mybooking.reservations;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public Reservation createReservation(@RequestBody ReservationRequest reservationRequest) {
        Reservation reservation = reservationService.createReservation(reservationRequest);
        return reservation;
    }

    @GetMapping
    public List<Reservation> getReservations(@RequestParam(value = "userId", required = false) Long userId){
        return reservationService.getReservations(userId);
    }
}
