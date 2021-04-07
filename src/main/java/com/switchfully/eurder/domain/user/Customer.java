package com.switchfully.eurder.domain.user;

import java.util.UUID;

public class Customer extends User {
    public Customer(String firstName, String lastName, String email, String password, String address, String phoneNumber) {
        this(UUID.randomUUID(), firstName, lastName, email, password, address, phoneNumber);
    }

    public Customer(UUID id, String firstName, String lastName, String email, String password, String address, String phoneNumber) {
        super(id, firstName, lastName, email, password, address, phoneNumber, UserRole.CUSTOMER);
    }
}
