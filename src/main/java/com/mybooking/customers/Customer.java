package com.mybooking.customers;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Customer {
    private Long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String email;
}
