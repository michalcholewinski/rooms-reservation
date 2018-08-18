package com.mybooking.users;

import javax.validation.constraints.NotNull;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(@NotNull Long userId) {
        super("User with id: "+userId+" cannot be found");
    }
}
