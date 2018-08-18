package com.mybooking.reservations;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

@Data
public class ReservationRequest {
    @NotNull
    @DateTimeFormat(iso = DATE)
    private LocalDate startDate;
    @NotNull
    @DateTimeFormat(iso = DATE)
    private LocalDate endDate;
    @NotNull
    private Long userId;
    @NotNull
    private Long roomId;
}
