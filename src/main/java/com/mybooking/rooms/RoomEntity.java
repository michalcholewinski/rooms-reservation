package com.mybooking.rooms;

import com.mybooking.reservations.ReservationEntity;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "MYBOOKING_ROOM")
public class RoomEntity {
    @Id
    @GenericGenerator(
        name = "room_id_generator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
            @org.hibernate.annotations.Parameter(name = "sequence_name", value = "MYBOOKING_ROOM_ID_SEQ")
        }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_id_generator")
    private Long id;

    private Integer number;
    private Integer price;
    private String details;
    private String hotelName;
    private String city;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private Set<ReservationEntity> reservations;

}
