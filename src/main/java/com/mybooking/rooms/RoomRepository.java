package com.mybooking.rooms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {

    @Query("SELECT r FROM RoomEntity r where r.price "
               + "BETWEEN :priceMin "
               + "AND :priceMax "
               + "AND lower(r.city) like lower(concat('%', :city,'%')) "
               + "AND r.id NOT IN (SELECT r1.id from RoomEntity r1 "
               + "JOIN r1.reservations res "
               + "WHERE (:startDate > res.fromTime AND :startDate < res.toTime) "
               + "OR (:endDate > res.fromTime AND :endDate < res.toTime) "
               + "OR (res.fromTime > :startDate and :endDate > res.fromTime) "
               + "OR (:startDate = res.fromTime and :endDate > res.toTime) "
               + "OR (:startDate < res.fromTime and :endDate = res.toTime)"
               + "OR(:startDate = res.fromTime and :endDate=res.toTime))")
    List<RoomEntity> findAllByRoomsByParameters(@Param("priceMin") int priceMin,
                                                @Param("priceMax") int priceMax,
                                                @Param("city") String city,
                                                @Param("startDate") LocalDate startDate,
                                                @Param("endDate") LocalDate endDate);
}
