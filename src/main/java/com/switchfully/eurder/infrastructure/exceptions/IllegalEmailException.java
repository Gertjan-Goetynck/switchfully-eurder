package com.switchfully.eurder.infrastructure.exceptions;

public class IllegalEmailException extends IllegalArgumentException {
    public IllegalEmailException(String message) {
        super("Illegal email: " + message);
    }
}
