package com.mybooking.customers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

//Probably not needed
//    @PutMapping("/{id}")
//    public Customer updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer){
//        return customerService.updateCustomer(id, customer);
//    }

}
