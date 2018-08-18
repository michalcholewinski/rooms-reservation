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
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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


    public static final String API_ROOMS = "/api/rooms";


    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mvc;

    @Before
    public void setup() throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void shouldRequestFailDueToLackOfRequiredParameters() throws Exception {
        mvc.perform(get(API_ROOMS)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldFind3Rooms() throws Exception {
        mvc.perform(get(getUrl(100, 1000, "Kielce", "2018-09-20", "2018-09-22"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void shouldReturnBadRequestBecauseOfWrongDateRange() throws Exception {
        mvc.perform(get(getUrl(100, 1000, "Kielce", "2018-09-23", "2018-09-22"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("errorCode", is(2)));
    }

    @Test
    public void shouldReturnBadRequestBecauseOfWrongDateRangeSameDate() throws Exception {
        mvc.perform(get(getUrl(100, 1000, "Kielce", "2018-09-22", "2018-09-22"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("errorCode", is(2)));
    }

    @Test
    public void shouldReturnBadRequestBecauseOfWrongPriceRange() throws Exception {
        mvc.perform(get(getUrl(1000, 100, "Kielce", "2018-09-20", "2018-09-22"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("errorCode", is(3)));
    }

    private String getUrl(int priceMin, int priceMax, final String city, final String startDate, final String endDate) {
        return API_ROOMS + "?priceMin=" + priceMin + "&priceMax=" + priceMax + "&city=" + city + "&startDate=" + startDate + "&endDate=" + endDate;
    }
}
