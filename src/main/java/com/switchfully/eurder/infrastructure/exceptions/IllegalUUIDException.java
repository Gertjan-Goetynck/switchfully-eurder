package com.switchfully.eurder.infrastructure.exceptions;

public class IllegalUUIDException extends RuntimeException {
    public IllegalUUIDException(String message) {
        super("Illegal UUID: " + message);
    }
}
