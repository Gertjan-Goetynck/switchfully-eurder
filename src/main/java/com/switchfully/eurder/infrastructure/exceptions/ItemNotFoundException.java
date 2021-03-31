package com.switchfully.eurder.infrastructure.exceptions;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException() {
        super("The item you wish to purchase was not found");
    }
}
