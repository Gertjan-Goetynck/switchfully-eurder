package com.switchfully.eurder.domain.user;

import com.switchfully.eurder.infrastructure.exceptions.IllegalEmailException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    @Test
    void whenCreatingCustomerWithNullFirstName_thenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Customer(null, "f", "f@f.f", "f", "f"));
    }

    @Test
    void whenCreatingCustomerWithBlankFirstName_thenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Customer("", "f", "f@f.f", "f", "f"));
    }

    @Test
    void whenCreatingCustomerWithNullLastName_thenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Customer("F", null, "f@f.f", "f", "f"));
    }

    @Test
    void whenCreatingCustomerWithBlankLastName_thenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Customer("F", "", "f@f.f", "f", "f"));
    }

    @Test
    void whenCreatingCustomerWithNullEmail_thenThrowIllegalEmailException() {
        assertThrows(IllegalEmailException.class, () -> new Customer("F", "f", null, "f", "f"));
    }

    @Test
    void whenCreatingCustomerWithBlankEmail_thenThrowIllegalEmailException() {
        assertThrows(IllegalEmailException.class, () -> new Customer("F", "f", "", "f", "f"));
    }

    @Test
    void whenCreatingCustomerWithInvalidEmail_thenThrowIllegalEmailException() {
        assertThrows(IllegalEmailException.class, () -> new Customer("F", "f", "f", "f", "f"));
    }

    @Test
    void whenCreatingCustomerWithNullAddress_thenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Customer("F", "f", "f@f.f", null, "f"));
    }

    @Test
    void whenCreatingCustomerWithBlankAddress_thenThrowIllegalEmailException() {
        assertThrows(IllegalArgumentException.class, () -> new Customer("F", "f", "f@f.f", "", "f"));
    }

    @Test
    void whenCreatingCustomerWithNullPhonenumber_thenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Customer("F", "f", "f@f.f", "f", null));
    }

    @Test
    void whenCreatingCustomerWithBlankPhonenumber_thenThrowIllegalEmailException() {
        assertThrows(IllegalArgumentException.class, () -> new Customer("F", "f", "f@f.f", "f", ""));
    }

    @Test
    void whenCreatingValidCustomer_thenThrowNoException(){
        assertDoesNotThrow(() -> new Customer("F", "f", "f@f.f", "f", "F"));
    }
}