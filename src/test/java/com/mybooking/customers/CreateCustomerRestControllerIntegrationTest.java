package com.mybooking.customers;

import com.mybooking.MyBookingApplication;
import com.mybooking.users.RoleEntity;
import com.mybooking.users.UserEntity;
import com.mybooking.users.UserRepository;
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

import static com.mybooking.users.RoleNames.CUSTOMER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = MyBookingApplication.class
)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = NONE)
@AutoConfigureEmbeddedDatabase
//@Sql(value="classpath:sql/createCustomerRestControllerIntegrationTest/loadDB.sql",  executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(value="classpath:sql/getCustomersRestControllerIntegrationTest/deleteCustomersAndOrganizations.sql",  executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class CreateCustomerRestControllerIntegrationTest {


    public static final String API_CUSTOMERS = "/api/customers";


    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    private UserRepository userRepository;

    protected MockMvc mvc;

    @Before
    public void setup() throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void shouldCreateCustomer() throws Exception {
        mvc.perform(post(API_CUSTOMERS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new String("{\"firstName\":\"Michal\", \"lastName\":\"Cholewinski\",\"email\":\"email@moj.com\"}")))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("firstName", is("Michal")))
                .andExpect(jsonPath("lastName", is("Cholewinski")))
                .andExpect(jsonPath("email", is("email@moj.com")));

        UserEntity userEntity = userRepository.findOneByEmail("email@moj.com").orElse(null);
        assertThat(userEntity).isNotNull();
        assertThat(userEntity.getRoles()).isNotEmpty();
        RoleEntity role = userEntity.getRoles().stream().findFirst().orElse(null);
        assertThat(role).isNotNull();
        assertThat(role.getName()).isEqualTo(CUSTOMER);
    }

    @Test
    @Sql(value="classpath:sql/createCustomerRestControllerIntegrationTest/loadDB.sql",  executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value="classpath:sql/createCustomerRestControllerIntegrationTest/clearDB.sql",  executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void shouldNotCreateCustomerWithExistingEmail() throws Exception {
        mvc.perform(post(API_CUSTOMERS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new String("{\"firstName\":\"Michal\", \"lastName\":\"Cholewinski\",\"email\":\"cholewinski@onet.eu\"}")))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("errorCode", is(1)));
    }
}
