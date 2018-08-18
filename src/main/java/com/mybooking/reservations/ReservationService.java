package com.mybooking.reservations;

import com.mybooking.rooms.IncorrectDateRangeException;
import com.mybooking.rooms.RoomEntity;
import com.mybooking.rooms.RoomRepository;
import com.mybooking.users.UserEntity;
import com.mybooking.users.UserNotFoundException;
import com.mybooking.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    @Transactional
    public Reservation createReservation(ReservationRequest reservationRequest) {
        if (!reservationRequest.getEndDate().isAfter(reservationRequest.getStartDate())) {
            throw new IncorrectDateRangeException("EndDate have to be after startDate");
        }
        RoomEntity room = retrieveRoom(reservationRequest.getRoomId());
        if (isRoomOccupiedInDesiredPeriod(room, reservationRequest)) {
            throw new ReservationAlreadyExistsException("Room already occupied within desired date range");
        }

        UserEntity user = retrieveUser(reservationRequest);
        ReservationEntity reservationEntity = ReservationEntity.builder()
                .customer(user)
                .room(room)
                .fromTime(reservationRequest.getStartDate())
                .toTime(reservationRequest.getEndDate())
                .build();
        ReservationEntity newReservation = reservationRepository.save(reservationEntity);
        return reservationMapper.toReservation(newReservation);

    }

    private UserEntity retrieveUser(ReservationRequest reservationRequest) {
        Optional<UserEntity> user = userRepository.findById(reservationRequest.getUserId());
        return user.orElseThrow(() -> new UserNotFoundException(reservationRequest.getUserId()));
    }

    private boolean isRoomOccupiedInDesiredPeriod(RoomEntity room, ReservationRequest reservationRequest) {
        Set<ReservationEntity> reservations = room.getReservations();
        return reservations.stream()
                .filter(res -> isOccupiedInGivenPeriod(reservationRequest.getStartDate(), reservationRequest.getEndDate(), res))
                .findAny()
                .isPresent();

    }

    private RoomEntity retrieveRoom(@NotNull Long roomId) {
        Optional<RoomEntity> room = roomRepository.findById(roomId);
        return room.orElseThrow(() -> new RoomNotFoundException("No room with id: " + roomId));
    }

    //    s-start, e-end <--user choice
    //f-fromTime, t-toTime <-- time from reservation
    //1. --------s-f------t-e--- OCCUPIED
    //2. --------f-s------t-e--- OCCUPIED
    //3. --------f-s------e-t--- OCCUPIED
    //4. --------s-f------e-t--- OCCUPIED
    //5. --------sf-------e-t--- OCCUPIED
    //6. --------sf-------t-e--- OCCUPIED
    //7. --------s-f------et---- OCCUPIED
    //9. --------s-f------te---- OCCUPIED
    //10.---s--e---f---t-------- FREE
    //11.----f--t----s-----e---- FREE
    //12.-----s--ef------t------ FREE
    //13.------f----ts----e----- FREE
    private boolean isOccupiedInGivenPeriod(LocalDate startDate, LocalDate endDate, ReservationEntity res) {
        return !(endDate.isBefore(res.getFromTime()) //10
                || endDate.isEqual(res.getFromTime())//12
                || startDate.isEqual(res.getToTime())//13
                || startDate.isAfter(res.getToTime()));//11

    }

    public List<Reservation> getReservations(Long userId) {
        List<ReservationEntity> reservations;
        if (userId == null) {
            reservations = reservationRepository.findAll();
        } else {
            reservations = reservationRepository.findAllByCustomerId(userId);
        }
        return reservationMapper.toReservations(reservations);
    }
}
