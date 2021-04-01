package com.switchfully.eurder.domain.user;

import com.switchfully.eurder.infrastructure.exceptions.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {
    private final UserRepository userRepository = new UserRepository();

    @Test
    void givenNullUser_whenAddingUserToRepository_thenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> userRepository.addUser(null));
    }

    @Test
    void givenUser_whenAddingUserToRepository_thenThrowNoException() {
        assertDoesNotThrow(() -> userRepository.addUser(new Customer("F", "F", "F@F.F", "F", "F")));
    }

    @Test
    void givenUserInRepository_whenCallingGetUserById_thenReturnUser() {
        Customer customer = new Customer("F", "f", "f@f.f", "f", "F");
        userRepository.addUser(customer);

        Customer returnedCustomer = userRepository.getCustomerById(customer.getId());

        assertEquals(customer, returnedCustomer);
    }


    @Test
    void giveCustomerInRepository_whenCallingGetCustomerById_thenReturnUser() {
        Customer customer = new Customer("F", "f", "f@f.f", "f", "F");
        userRepository.addUser(customer);

        Customer returnedCustomer = userRepository.getCustomerById(customer.getId());

        assertEquals(customer, returnedCustomer);
    }

    @Test
    void giveCustomerInRepository_whenCallingGetCustomerById_thenReturnedUserRoleIsCustomer() {
        Customer customer = new Customer("F", "f", "f@f.f", "f", "F");
        userRepository.addUser(customer);

        Customer returnedCustomer = userRepository.getCustomerById(customer.getId());

        assertEquals(UserRole.CUSTOMER, returnedCustomer.getUserRole());
    }

}