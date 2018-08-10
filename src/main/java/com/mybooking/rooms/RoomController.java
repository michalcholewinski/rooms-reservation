package com.mybooking.rooms;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    @GetMapping
    public List<Room> getRooms(@RequestParam("priceMin") int priceMin,
                               @RequestParam("priceMax") int priceMax,
                               @RequestParam("city") String city,
                               @RequestParam("startDate") @DateTimeFormat(iso = DATE) LocalDate startDate,
                               @RequestParam("endDate") @DateTimeFormat(iso = DATE) LocalDate endDate) {
        return roomService.getRooms(priceMin, priceMax, city, startDate, endDate);
    }

    @PostMapping
    public Room createRoom(@RequestBody Room room) {
        return null;
    }

    @PutMapping("/{id}")
    public Room updateRoom(@PathVariable("id") Long id, @RequestBody Room room) {
        return null;
    }

    //delete not needed
}
