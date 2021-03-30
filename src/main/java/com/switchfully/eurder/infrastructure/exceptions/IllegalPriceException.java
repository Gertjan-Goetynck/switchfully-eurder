package com.switchfully.eurder.infrastructure.exceptions;

public class IllegalPriceException extends RuntimeException {
    public IllegalPriceException(String message) {
        super("Illegal price: " + message);
    }
}
