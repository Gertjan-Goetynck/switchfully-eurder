package com.switchfully.eurder.domain.user;

import com.switchfully.eurder.infrastructure.utils.ValidationUtil;

import java.util.UUID;

public abstract class User {
    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final String address;
    private final String phoneNumber;
    private final UserRole userRole;

    public User(String firstName, String lastName, String email, String password, String address, String phoneNumber, UserRole userRole) {
        this(UUID.randomUUID(), firstName, lastName, email, password, address, phoneNumber, userRole);
    }

    public User(UUID id, String firstName, String lastName, String email, String password, String address, String phoneNumber, UserRole userRole) {
        this.id = id;
        ValidationUtil.throwExceptionIfBlankOrNullString(password, "Password");
        this.password = password;
        ValidationUtil.throwExceptionIfBlankOrNullString(firstName, "First name");
        this.firstName = firstName;
        ValidationUtil.throwExceptionIfBlankOrNullString(lastName, "Last name");
        this.lastName = lastName;
        ValidationUtil.throwExceptionIfIllegalEmail(email);
        this.email = email;
        ValidationUtil.throwExceptionIfBlankOrNullString(address, "Address");
        this.address = address;
        ValidationUtil.throwExceptionIfBlankOrNullString(phoneNumber, "Phone number");
        this.phoneNumber = phoneNumber;
        ValidationUtil.throwExceptionIfNullObject(userRole, "Role");
        this.userRole = userRole;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserRole getUserRole() {
        return this.userRole;
    }

    public String getPassword() {
        return this.password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}
