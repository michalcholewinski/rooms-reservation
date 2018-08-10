package com.mybooking.users;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = NONE)
@AutoConfigureEmbeddedDatabase
@DataJpaTest
@Sql("classpath:sql/findAllRolesByNameInRepositoryTest/loadDB.sql")
public class FindAllRolesByNameInRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;


    @Test
    public void shouldFindOneRoleExactlyWhichWeAreLookingFor() {
        //given
        int expectedNumOfElements = 1;
        ArrayList<String> desiredRoles = Lists.newArrayList("TEST_OWNER");

        //when
        Set<RoleEntity> roles = roleRepository.findAllByNameIn(desiredRoles);
        //then
        assertThat(roles).isNotEmpty();
        assertThat(roles).size().isEqualTo(expectedNumOfElements);
        RoleEntity role = roles.stream().findFirst().orElse(null);
        assertThat(role).isNotNull();
        assertThat(role.getName()).isEqualTo("TEST_OWNER");
    }

    @Test
    public void shouldFindOnlyOneRoleFromDesiredList() {
        //given
        int expectedNumOfElements = 1;
        ArrayList<String> desiredRoles = Lists.newArrayList("TEST_OWNER","TEST_MICHAL");

        //when
        Set<RoleEntity> roles = roleRepository.findAllByNameIn(desiredRoles);
        //then
        assertThat(roles).isNotEmpty();
        assertThat(roles).size().isEqualTo(expectedNumOfElements);
        RoleEntity role = roles.stream().findFirst().orElse(null);
        assertThat(role).isNotNull();
        assertThat(role.getName()).isEqualTo("TEST_OWNER");
    }


    @Test
    public void shouldFind2RolesFromDesiredList() {
        //given
        int expectedNumOfElements = 2;
        ArrayList<String> desiredRoles = Lists.newArrayList("TEST_OWNER","TEST_CUSTOMER");

        //when
        Set<RoleEntity> roles = roleRepository.findAllByNameIn(desiredRoles);
        //then
        assertThat(roles).isNotEmpty();
        assertThat(roles).size().isEqualTo(expectedNumOfElements);
        List<String> roleNames = roles.stream().map(roleEntity -> roleEntity.getName()).collect(Collectors.toList());
        assertThat(roleNames).isNotEmpty(); //just in case
        assertThat(roleNames).contains("TEST_OWNER", "TEST_CUSTOMER");
    }


    @Test
    public void shouldFindOnly2RolesFromDesiredList() {
        //given
        int expectedNumOfElements = 2;
        ArrayList<String> desiredRoles = Lists.newArrayList("TEST_OWNER","TEST_CUSTOMER","TEST_MICHAL");

        //when
        Set<RoleEntity> roles = roleRepository.findAllByNameIn(desiredRoles);
        //then
        assertThat(roles).isNotEmpty();
        assertThat(roles).size().isEqualTo(expectedNumOfElements);
        List<String> roleNames = roles.stream().map(roleEntity -> roleEntity.getName()).collect(Collectors.toList());
        assertThat(roleNames).isNotEmpty(); //just in case
        assertThat(roleNames).contains("TEST_OWNER", "TEST_CUSTOMER");
    }
}
