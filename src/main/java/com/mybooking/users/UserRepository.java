package com.mybooking.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("Select distinct u from UserEntity u join fetch u.roles r where r.name=:role")
    List<UserEntity> findAllByRoleName(@Param("role") String role);
}
