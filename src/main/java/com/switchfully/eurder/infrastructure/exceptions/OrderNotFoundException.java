package com.switchfully.eurder.infrastructure.exceptions;

import java.util.UUID;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(UUID id) {
        super("The order with ID "+ id + " was not found");
    }
}
