package com.switchfully.eurder.domain.user;

public class Customer extends User {
    public Customer(String firstName, String lastName, String email, String address, String phoneNumber) {
        super(firstName, lastName, email, address, phoneNumber, UserRole.CUSTOMER);
    }
}
