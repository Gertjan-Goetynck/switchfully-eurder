package com.switchfully.eurder.domain.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {
    private final UserRepository userRepository = new UserRepository();

    @Test
    void givenNullUser_whenAddingUserToRepository_thenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> userRepository.addUser(null));
    }

    @Test
    void givenUser_whenAddingUserToRepository_thenThrowNoException(){
        assertDoesNotThrow(()->userRepository.addUser(new Customer("F", "F", "F@F.F", "F", "F")));
    }
}