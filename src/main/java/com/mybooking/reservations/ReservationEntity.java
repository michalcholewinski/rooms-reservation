package com.mybooking.reservations;

import com.mybooking.rooms.RoomEntity;
import com.mybooking.users.UserEntity;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "MYBOOKING_RESERVATION")
public class ReservationEntity {

    @Id
    @GenericGenerator(
        name = "reservation_id_generator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
            @org.hibernate.annotations.Parameter(name = "sequence_name", value = "MYBOOKING_RESERVATION_ID_SEQ")
        }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_id_generator")
    private Long id;

    @NotNull
    private LocalDateTime fromTime;

    @NotNull
    private LocalDateTime toTime;

    @ManyToOne
    private RoomEntity room;

    @ManyToOne
    private UserEntity customer;

}
