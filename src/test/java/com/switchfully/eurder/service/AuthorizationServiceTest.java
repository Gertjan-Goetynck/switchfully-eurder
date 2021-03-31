package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.user.Customer;
import com.switchfully.eurder.domain.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class AuthorizationServiceTest {
    private final UserRepository userRepository = new UserRepository();
    private final AuthorizationService authorizationService = new AuthorizationService(userRepository);

    @Test
    void givingNullId_whenValidatingAdmin_thenThrowIllegalAccessException() {
        Executable executable = () -> authorizationService.throwExceptionIfNotAdmin(null);
        assertThrows(IllegalAccessException.class, executable);
    }

    @Test
    void givingWrongSizedId_whenValidatingAdmin_thenThrowIllegalAccessException() {
        Executable executable = () -> authorizationService.throwExceptionIfNotAdmin("karel");
        assertThrows(IllegalAccessException.class, executable);
    }

    @Test
    void givingWrongId_whenValidatingAdmin_thenThrowIllegalAccessException() {
        Executable executable = () -> authorizationService.throwExceptionIfNotAdmin("123e4567-e89b-12d3-a456-556642449600");
        assertThrows(IllegalAccessException.class, executable);
    }

    @Test
    void givingCorrectId_whenValidatingAdmin_thenLogIn() {
        Executable executable = () -> authorizationService.throwExceptionIfNotAdmin("123e4567-e89b-12d3-a456-556642440000");
        assertDoesNotThrow(executable);
    }

    @Test
    void givingNullId_whenValidatingMember_thenThrowIllegalAccessException() {
        Executable executable = () -> authorizationService.throwExceptionIfNotMember(null);
        assertThrows(IllegalAccessException.class, executable);
    }

    @Test
    void givingWrongSizedId_whenValidatingMember_thenThrowIllegalAccessException() {
        Executable executable = () -> authorizationService.throwExceptionIfNotMember("karel");
        assertThrows(IllegalAccessException.class, executable);
    }

    @Test
    void givingWrongId_whenValidatingMember_thenThrowIllegalAccessException() {
        Executable executable = () -> authorizationService.throwExceptionIfNotMember("123e4567-e89b-12d3-a456-556642440007");
        assertThrows(IllegalAccessException.class, executable);
    }

    @Test
    void givingCorrectId_whenValidatingMember_thenLogIn() {
        Customer customer = new Customer("Test", "Test", "test@dummy.com", "test", "test");
        userRepository.addUser(customer);
        Executable executable = () -> authorizationService.throwExceptionIfNotMember(customer.getId().toString());
        assertDoesNotThrow(executable);
    }
}