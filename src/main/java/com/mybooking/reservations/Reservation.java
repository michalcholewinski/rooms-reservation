package com.mybooking.reservations;

import com.mybooking.customers.Customer;
import com.mybooking.rooms.Room;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Reservation {

    private Long id;
    private LocalDate fromTime;
    private LocalDate toTime;
    private Room room;
    private Customer customer;
}
