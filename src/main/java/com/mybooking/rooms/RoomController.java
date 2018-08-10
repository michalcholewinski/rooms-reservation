package com.mybooking.rooms;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    @GetMapping
    public List<Room> getRooms(@RequestParam("priceMin") int priceMin,
                               @RequestParam("priceMax") int priceMax,
                               @RequestParam("city") String city,
                               @RequestParam("startDate") LocalDateTime startDate,
                               @RequestParam("endDate") LocalDateTime endDate){
        return roomService.getRooms(priceMin, priceMax, city, startDate, endDate);
    }

    @PostMapping
    public Room createRoom(@RequestBody Room room){
        return null;
    }

    @PutMapping("/{id}")
    public Room updateRoom(@PathVariable("id") Long id, @RequestBody Room room){
        return null;
    }

    //delete not needed
}
