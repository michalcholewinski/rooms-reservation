//package com.mybooking.customers;
//
//import com.mybooking.MyBookingApplication;
//import com.mybooking.users.UsersRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(
//    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
//    classes = MyBookingApplication.class
//)
//@AutoConfigureMockMvc
//@AutoConfigureTestDatabase
////@Sql(value="classpath:sql/getCustomersRestControllerIntegrationTest/loadCustomers.sql",  executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
////@Sql(value="classpath:sql/getCustomersRestControllerIntegrationTest/deleteCustomersAndOrganizations.sql",  executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
//public class GetCustomersRestControllerIntegrationTest {
//
//
//    public static final String API_CUSTOMERS = "/api/customers";
//    @Autowired
//    protected WebApplicationContext wac;
//
//    protected MockMvc mvc;
//
//    @Autowired
//    private UsersRepository usersRepository;
//
//
//    //    @After
//    //    public void resetDb() {
//    //        customerRepository.deleteAll();
//    //    }
//
//    @Test
//    public void shouldFindAllCustomers() throws Exception {
////        mvc.perform(get(API_CUSTOMERS + "?phoneNumber=+48789456123")
//            //                .contentType(MediaType.APPLICATION_JSON))
//            //                .andExpect(status().isOk())
//            //                .andExpect(content()
//            //                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//            //                .andExpect(jsonPath("$[0].firstName", is("Michal")))
//            //                .andExpect(jsonPath("$[0].lastName", is("Cholewinski")))
//            //                .andExpect(jsonPath("$[0].phoneNumber", is("+48789456123")));
//    }
//
//
//}
