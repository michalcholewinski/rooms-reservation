package com.mybooking.rooms;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @GetMapping
    public List<Room> getRooms(@RequestParam("period") int period,
                               @RequestParam("city") String city,
                               @RequestParam("startDate") LocalDateTime startDate,
                               @RequestParam("endDate") LocalDateTime endDate){
        return null;
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
