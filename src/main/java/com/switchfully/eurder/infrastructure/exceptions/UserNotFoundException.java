package com.switchfully.eurder.infrastructure.exceptions;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(UUID uuid) {
        super("User not found: there was no user with ID " + uuid);
    }
}
