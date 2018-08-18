package com.mybooking.reservations;

public class ReservationNotFoundException extends RuntimeException {
    public ReservationNotFoundException(Long id) {
        super("Reservation with id: "+id+" has been not found");
    }
}
