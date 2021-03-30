package com.switchfully.eurder.domain.user;

public class Admin extends User {
    public Admin(String firstName, String lastName, String email, String address, String phoneNumber) {
        super(firstName, lastName, email, address, phoneNumber, UserRole.ADMIN);
    }
}
