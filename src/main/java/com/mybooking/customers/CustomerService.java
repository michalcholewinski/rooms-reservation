package com.mybooking.customers;

import com.mybooking.users.RoleRepository;
import com.mybooking.users.UserEntity;
import com.mybooking.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static com.mybooking.users.RoleNames.CUSTOMER;

@RequiredArgsConstructor
@Service
public class CustomerService {
    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CustomerMapper customerMapper;

    public List<Customer> getCustomers() {
        List<UserEntity> customers = userRepository.findAllByRoleName(CUSTOMER);
        return customerMapper.toCustomers(customers);
    }

    @Transactional
    public Customer createCustomer(Customer customer) {
        if(!isEmailCorrect(customer)){
            throw new ValidationException("Wrong email format");
        };
        if (userRepository.findOneByEmail(customer.getEmail()).isPresent()) {
            throw new CustomerAlreadyExistsException(customer.getEmail());
        }
        UserEntity userEntity = customerMapper.toUserEntity(customer);
        List<String> desiredRoles = new ArrayList<>();
        desiredRoles.add(CUSTOMER);
        userEntity.setRoles(roleRepository.findAllByNameIn(desiredRoles));
        return customerMapper.toCustomer(userRepository.save(userEntity));
    }

    private boolean isEmailCorrect(Customer customer) {
        return Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE).matcher(customer.getEmail()).matches();
    }

}
