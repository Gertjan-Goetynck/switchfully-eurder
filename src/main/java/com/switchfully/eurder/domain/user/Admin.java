package com.switchfully.eurder.domain.user;

import java.util.UUID;

public class Admin extends User {
    public Admin(String firstName, String lastName, String email, String address, String phoneNumber) {
        this(UUID.randomUUID(), firstName, lastName, email, address, phoneNumber);
    }

    public Admin(UUID id, String firstName, String lastName, String email, String address, String phoneNumber) {
        super(id, firstName, lastName, email, address, phoneNumber, UserRole.ADMIN);
    }
}
