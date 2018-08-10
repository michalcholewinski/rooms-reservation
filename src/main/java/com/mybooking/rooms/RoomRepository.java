package com.mybooking.rooms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {

    @Query("SELECT r FROM RoomEntity r where r.price "
               + "BETWEEN :priceMin "
               + "AND :priceMax "
               + "AND lower(r.city) like %lower(:phrase)% "
               + "AND r.id NOT IN (SELECT r1.id from RoomEntity r1 "
               + "JOIN r.reservations res "
               + "WHERE (res.fromTime <= :startDate AND res.toTime >=:startDate) " //from > startDate > to  start Date somewhere in between of occupied period
               + "OR (res.fromTime <= :endDate AND res.fromTime >= :endDate) "  //from > endDate > to end Date somewhere in between of occupied period
               + "OR (res.fromTime > :startDate AND res.toTime < :endDate)") //startDate >from>to> endDate desired period contains already occupied period
    List<Room> findAllByRoomsByParameters(@Param("priceMin") int priceMin,
                                          @Param("priceMax") int priceMax,
                                          @Param("city") String city,
                                          @Param("startDate") LocalDateTime startDate,
                                          @Param("endDate") LocalDateTime endDate);
}
