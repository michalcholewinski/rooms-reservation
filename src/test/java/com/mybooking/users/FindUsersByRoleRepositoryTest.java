package com.mybooking.users;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = NONE)
@AutoConfigureEmbeddedDatabase
@DataJpaTest
@Sql("classpath:sql/findUsersByRoleRepositoryTest/loadDB.sql")
public class FindUsersByRoleRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    @Test
    public void shouldFindOneUser() {
        //given
        int expectedNumOfElements = 1;
        String desiredRole = "TEST_OWNER";

        //when
        List<UserEntity> users = userRepository.findAllByRoleName(desiredRole);
        //then
        assertThat(users).isNotEmpty();
        assertThat(users).size().isEqualTo(expectedNumOfElements);

    }

    @Test
    public void shouldFind3Users() {
        //given
        int expectedNumOfElements = 3;
        String desiredRole = "TEST_CUSTOMER";

        //when
        List<UserEntity> users = userRepository.findAllByRoleName(desiredRole);
        //then
        assertThat(users).isNotEmpty();
        assertThat(users).size().isEqualTo(expectedNumOfElements);

    }

}
