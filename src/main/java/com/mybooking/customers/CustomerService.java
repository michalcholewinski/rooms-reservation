package com.mybooking.customers;

import com.mybooking.users.RoleRepository;
import com.mybooking.users.UserEntity;
import com.mybooking.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.mybooking.users.RoleNames.CUSTOMER;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CustomerMapper customerMapper;

    public List<Customer> getCustomers() {
        List<UserEntity> customers = userRepository.findAllByRoleName(CUSTOMER);
        return customerMapper.toCustomers(customers);
    }

    public Customer createCustomer(Customer customer) {
        if (userRepository.findOneByEmail(customer.getEmail()).isPresent()) {
            throw new CustomerAlreadyExistsException(customer.getEmail());
        }
        UserEntity userEntity = customerMapper.toUserEntity(customer);
        List<String> desiredRoles = new ArrayList<>();
        desiredRoles.add(CUSTOMER);
        userEntity.setRoles(roleRepository.findAllByNameIn(desiredRoles));
        return customerMapper.toCustomer(userRepository.save(userEntity));
    }

}
