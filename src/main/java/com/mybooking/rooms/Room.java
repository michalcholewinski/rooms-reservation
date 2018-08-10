package com.mybooking.rooms;

import lombok.Data;

@Data
public class Room {
    private Long id;
    private int number;
    private String details;
    private int price; //should be decimal, String or even number in smallest unit (1zl - 100gr) but to accelerate working on project it will be just int
}
