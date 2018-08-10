package com.mybooking.rooms;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = NONE)
@AutoConfigureEmbeddedDatabase
@DataJpaTest
@Sql("classpath:sql/findAllRoomsByParametersRepositoryTest/loadDB.sql")
public class FindAllRoomsByParametersRepositoryTest {

    @Autowired
    private RoomRepository roomRepository;


    @Test
    public void shouldFind3Rooms() {
        //given
        int expectedNumOfElements = 3;
        int priceMin = 100;
        int priceMax = 1000;
        String city = "Kielce";
        LocalDate startDate = LocalDate.of(2018, 8, 15);
        LocalDate endDate = LocalDate.of(2018, 8, 17);
        //when
        List<RoomEntity> rooms = roomRepository.findAllByRoomsByParameters(priceMin, priceMax, city, startDate, endDate);
        //then
        assertThat(rooms).isNotEmpty();
        assertThat(rooms).size().isEqualTo(expectedNumOfElements);
    }

    @Test
    public void shouldFind3RoomsIgnoringCaseOfCity() {
        //given
        int expectedNumOfElements = 3;
        int priceMin = 100;
        int priceMax = 1000;
        String city = "kiElCe";
        LocalDate startDate = LocalDate.of(2018, 8, 15);
        LocalDate endDate = LocalDate.of(2018, 8, 17);
        //when
        List<RoomEntity> rooms = roomRepository.findAllByRoomsByParameters(priceMin, priceMax, city, startDate, endDate);
        //then
        assertThat(rooms).isNotEmpty();
        assertThat(rooms).size().isEqualTo(expectedNumOfElements);
    }

    @Test
    public void shouldFind3RoomsWithoutFullNameOfCity() {
        //given
        int expectedNumOfElements = 3;
        int priceMin = 100;
        int priceMax = 1000;
        String city = "Kie";
        LocalDate startDate = LocalDate.of(2018, 8, 15);
        LocalDate endDate = LocalDate.of(2018, 8, 17);
        //when
        List<RoomEntity> rooms = roomRepository.findAllByRoomsByParameters(priceMin, priceMax, city, startDate, endDate);
        //then
        assertThat(rooms).isNotEmpty();
        assertThat(rooms).size().isEqualTo(expectedNumOfElements);
    }

    @Test
    public void shouldNotFindAnyAvailableRoomInWarsawInGivenTimeRange() {
        //given
        int priceMin = 100;
        int priceMax = 1000;
        String city = "Warszawa";
        LocalDate startDate = LocalDate.of(2018, 9, 16);
        LocalDate endDate = LocalDate.of(2018, 9, 20);
        //when
        List<RoomEntity> rooms = roomRepository.findAllByRoomsByParameters(priceMin, priceMax, city, startDate, endDate);
        //then
        assertThat(rooms).isEmpty();
    }


    @Test
    public void shouldNotFindAnyAvailableRoomInWarsawInGivenTimeRange2() {
        //given
        int priceMin = 100;
        int priceMax = 1000;
        String city = "Warszawa";
        LocalDate startDate = LocalDate.of(2018, 9, 15);
        //then
        LocalDate endDate = LocalDate.of(2018, 9, 21);
        //when
        List<RoomEntity> rooms = roomRepository.findAllByRoomsByParameters(priceMin, priceMax, city, startDate, endDate);
       assertThat(rooms).isEmpty();
    }

    @Test
    public void shouldNotFindAnyAvailableRoomInWarsawInGivenTimeRange3() {
        //given
        int priceMin = 100;
        int priceMax = 1000;
        String city = "Warszawa";
        LocalDate startDate = LocalDate.of(2018, 9, 15);
        //then
        LocalDate endDate = LocalDate.of(2018, 9, 20);
        //when
        List<RoomEntity> rooms = roomRepository.findAllByRoomsByParameters(priceMin, priceMax, city, startDate, endDate);
       assertThat(rooms).isEmpty();
    }

    @Test
    public void shouldFind1RoomsInWarsawFrom20thOfSeptember() {
        //given
        int expectedNumOfElements = 1;
        int priceMin = 100;
        int priceMax = 1000;
        String city = "Warszawa";
        LocalDate startDate = LocalDate.of(2018, 9, 20);
        LocalDate endDate = LocalDate.of(2018, 9, 22);
        //when
        List<RoomEntity> rooms = roomRepository.findAllByRoomsByParameters(priceMin, priceMax, city, startDate, endDate);
        //then
        assertThat(rooms).isNotEmpty();
        assertThat(rooms).size().isEqualTo(expectedNumOfElements);
    }

    @Test
    public void shouldNotFindAnyRoomInWarsawInDesiredPriceRange() {
        //given
        int priceMin = 100;
        int priceMax = 250;
        String city = "Warszawa";
        LocalDate startDate = LocalDate.of(2018, 8, 20);
        LocalDate endDate = LocalDate.of(2018, 8, 22);
        //when
        List<RoomEntity> rooms = roomRepository.findAllByRoomsByParameters(priceMin, priceMax, city, startDate, endDate);
        //then
        assertThat(rooms).isEmpty();
    }

    @Test
    public void shouldFind2RoomsInKielceInDesiredPriceRange() {
        //given
        int expectedNumOfElements = 2;
        int priceMin = 100;
        int priceMax = 200;
        String city = "Kielce";
        LocalDate startDate = LocalDate.of(2018, 8, 20);
        LocalDate endDate = LocalDate.of(2018, 8, 22);
        //when
        List<RoomEntity> rooms = roomRepository.findAllByRoomsByParameters(priceMin, priceMax, city, startDate, endDate);
        //then
        assertThat(rooms).isNotEmpty();
        assertThat(rooms).size().isEqualTo(expectedNumOfElements);
    }

}
