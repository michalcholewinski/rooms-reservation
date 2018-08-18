package com.mybooking.customers;

import com.mybooking.users.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toCustomer(UserEntity user);

    List<Customer> toCustomers(List<UserEntity> users);

    UserEntity toUserEntity(Customer customer);
}
