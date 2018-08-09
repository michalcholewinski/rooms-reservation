package com.mybooking.customers;

import com.mybooking.users.UserEntity;
import com.mybooking.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mybooking.users.RoleNames.CUSTOMER;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final UserRepository userRepository;

    public List<Customer> getCustomers() {
        List<UserEntity> customers = userRepository.findAllByRoleName(CUSTOMER);
        //map to customers here
        return null;
    }

    public Customer createCustomer(Customer customer) {
        return null;
    }

}
