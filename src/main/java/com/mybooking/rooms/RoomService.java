package com.mybooking.rooms;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public List<Room> getRooms(int priceMin, int priceMax, String city, LocalDateTime startDate, LocalDateTime endDate) {
        return null;
    }
}
