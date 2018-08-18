package com.mybooking.reservations;

import com.mybooking.customers.CustomerMapper;
import com.mybooking.rooms.RoomMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RoomMapper.class, CustomerMapper.class})
public interface ReservationMapper {

    Reservation toReservation(ReservationEntity reservationEntity);

    List<Reservation> toReservations(List<ReservationEntity> reservations);

}
