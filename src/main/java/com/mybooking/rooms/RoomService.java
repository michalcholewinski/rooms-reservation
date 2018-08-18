package com.mybooking.rooms;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    public List<Room> getRooms(int priceMin, int priceMax, String city, LocalDate startDate, LocalDate endDate) {
        if(!endDate.isAfter(startDate)){
            throw new IncorrectDateRangeException("EndDate have to be after startDate");
        }
        if(priceMax<priceMin){
            throw new IncorrectPriceRangeException("Max price have to be bigger or equal than minumum price");
        }

        List<RoomEntity> rooms = roomRepository.findAllByRoomsByParameters(priceMin, priceMax, city, startDate, endDate);
        return roomMapper.toRooms(rooms);
    }
}
