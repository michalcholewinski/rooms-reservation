package com.mybooking.reservations;

import com.mybooking.MyBookingApplication;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = MyBookingApplication.class
)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = NONE)
@AutoConfigureEmbeddedDatabase
@Sql(value = "classpath:sql/findAllRoomsByParametersRepositoryTest/loadDB.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "classpath:sql/findAllRoomsByParametersRepositoryTest/clearDB.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class CreateNewReservationIntegrationTest {


    public static final String API_RESERVATIONS = "/api/reservations";


    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mvc;

    @Before
    public void setup() throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void shouldCreateNewReservation() throws Exception {
        mvc.perform(post(API_RESERVATIONS)
                .content(new String("{\n" +
                        "\t\"roomId\":\"113\", \n" +
                        "\t\"userId\":\"101\",\n" +
                        "\t\"startDate\": \"2018-12-04\",\n" +
                        "\t\"endDate\": \"2018-12-07\"\n" +
                        "}"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("fromTime", is("2018-12-04")))
                .andExpect(jsonPath("toTime", is("2018-12-07")))
                .andExpect(jsonPath("room.number", is(1)))
                .andExpect(jsonPath("customer.email", is("karpinski@onet.eu")));
    }

    @Test
    public void shouldResultWithBadRequestDueToWrongDateRange() throws Exception {
        mvc.perform(post(API_RESERVATIONS)
                .content(new String("{\n" +
                        "\t\"roomId\":\"113\", \n" +
                        "\t\"userId\":\"101\",\n" +
                        "\t\"startDate\": \"2018-12-07\",\n" +
                        "\t\"endDate\": \"2018-12-02\"\n" +
                        "}"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errorCode", is(2)));
    }

    @Test
    public void shouldResultWithBadRequestForNotExistingUser() throws Exception {
        mvc.perform(post(API_RESERVATIONS)
                .content(new String("{\n" +
                        "\t\"roomId\":\"113\", \n" +
                        "\t\"userId\":\"1011212\",\n" +
                        "\t\"startDate\": \"2018-12-02\",\n" +
                        "\t\"endDate\": \"2018-12-08\"\n" +
                        "}"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errorCode", is(6)));
    }

    @Test
    public void shouldResultWithBadRequestForNotExistingRoom() throws Exception {
        mvc.perform(post(API_RESERVATIONS)
                .content(new String("{\n" +
                        "\t\"roomId\":\"1131212\", \n" +
                        "\t\"userId\":\"101\",\n" +
                        "\t\"startDate\": \"2018-12-02\",\n" +
                        "\t\"endDate\": \"2018-12-08\"\n" +
                        "}"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errorCode", is(5)));
    }

}
