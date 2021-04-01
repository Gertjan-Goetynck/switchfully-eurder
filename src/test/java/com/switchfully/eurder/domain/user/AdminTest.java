package com.switchfully.eurder.domain.user;

import com.switchfully.eurder.infrastructure.exceptions.IllegalEmailException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminTest {
    @Test
    void whenCreatingAdminWithNullFirstName_thenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Admin(null, "f", "f@f.f", "f", "f"));
    }

    @Test
    void whenCreatingAdminWithBlankFirstName_thenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Admin("", "f", "f@f.f", "f", "f"));
    }

    @Test
    void whenCreatingAdminWithNullLastName_thenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Admin("F", null, "f@f.f", "f", "f"));
    }

    @Test
    void whenCreatingAdminWithBlankLastName_thenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Admin("F", "", "f@f.f", "f", "f"));
    }

    @Test
    void whenCreatingAdminWithNullEmail_thenThrowIllegalEmailException() {
        assertThrows(IllegalEmailException.class, () -> new Admin("F", "f", null, "f", "f"));
    }

    @Test
    void whenCreatingAdminWithBlankEmail_thenThrowIllegalEmailException() {
        assertThrows(IllegalEmailException.class, () -> new Admin("F", "f", "", "f", "f"));
    }

    @Test
    void whenCreatingAdminWithInvalidEmail_thenThrowIllegalEmailException() {
        assertThrows(IllegalEmailException.class, () -> new Admin("F", "f", "f", "f", "f"));
    }

    @Test
    void whenCreatingAdminWithNullAddress_thenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Admin("F", "f", "f@f.f", null, "f"));
    }

    @Test
    void whenCreatingAdminWithBlankAddress_thenThrowIllegalEmailException() {
        assertThrows(IllegalArgumentException.class, () -> new Admin("F", "f", "f@f.f", "", "f"));
    }

    @Test
    void whenCreatingAdminWithNullPhonenumber_thenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Admin("F", "f", "f@f.f", "f", null));
    }

    @Test
    void whenCreatingAdminWithBlankPhonenumber_thenThrowIllegalEmailException() {
        assertThrows(IllegalArgumentException.class, () -> new Admin("F", "f", "f@f.f", "f", ""));
    }

    @Test
    void whenCreatingValidAdmin_thenThrowNoException() {
        assertDoesNotThrow(() -> new Admin("F", "f", "f@f.f", "f", "F"));
    }

    @Test
    void whenCreatingValidAdmin_thenUserRoleIsAdmin() {
        //WHEN
        Admin admin = new Admin("F", "f", "f@f.f", "f", "F");

        //THEN
        assertEquals(admin.getUserRole(), UserRole.ADMIN);
    }

}