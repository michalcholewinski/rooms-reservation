package com.mybooking.customers;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @GetMapping
    public List<Customer> getCustomers(){
        return null;
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer room){
        return null;
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable("id") Long id, @RequestBody Customer room){
        return null;
    }

}
