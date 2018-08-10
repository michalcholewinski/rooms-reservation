package com.mybooking.rooms;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    Room toRoom(RoomEntity roomEntity);
    List<Room> toRooms(List<RoomEntity> rooms);

}
