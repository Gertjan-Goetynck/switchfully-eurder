package com.switchfully.eurder.infrastructure.utils;

import com.switchfully.eurder.infrastructure.exceptions.IllegalPriceException;
import com.switchfully.eurder.infrastructure.exceptions.IllegalUUIDException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;


import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ValidationUtilTest {

    @Test
    void givenNullObject_whenCallingThrowExceptionIfNullObject_thenThrowIllegalArgumentException() {
        //GIVEN
        Object object = null;

        //WHEN
        Executable executable = () -> ValidationUtil.throwExceptionIfNullObject(object, "Object");

        //THEN
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, executable);
        assertEquals("Object cannot be null", illegalArgumentException.getMessage());

    }

    @Test
    void givenNotNullObject_whenCallingThrowExceptionIfNullObject_thenThrowNoException() {
        //GIVEN
        Object object = new Object();

        //WHEN
        Executable executable = () -> ValidationUtil.throwExceptionIfNullObject(object, "Object");

        //THEN
        assertDoesNotThrow(executable);

    }

    @Test
    void givenNullObject_whenCallingIsNullObject_thenReturnTrue() {
        //GIVEN
        Object object = null;

        //WHEN
        Boolean isNullObject = ValidationUtil.isNullObject(object);

        //THEN
        assertEquals(true, isNullObject);
    }

    @Test
    void givenNotNullObject_whenCallingIsNullObject_thenReturnFalse() {
        //GIVEN
        Object object = new Object();

        //WHEN
        Boolean isNullObject = ValidationUtil.isNullObject(object);

        //THEN
        assertEquals(false, isNullObject);
    }

    @Test
    void givenBlankString_whenCallingThrowExceptionIfBlankString_thenThrowIllegalArgumentException() {
        //GIVEN
        String string = "";

        //WHEN
        Executable executable = () -> ValidationUtil.throwExceptionIfBlankString(string, "String");

        //THEN
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, executable);
        assertEquals("String cannot be empty", illegalArgumentException.getMessage());
    }

    @Test
    void givenNonBlankString_whenCallingThrowExceptionIfBlankString_thenThrowIllegalArgumentException() {
        //GIVEN
        String string = "String";

        //WHEN
        Executable executable = () -> ValidationUtil.throwExceptionIfBlankString(string, "String");

        //THEN
        assertDoesNotThrow(executable);
    }

    @Test
    void givenBlankString_whenCallingThrowExceptionIfBlankOrNullString_thenThrowIllegalArgumentException() {
        //GIVEN
        String string = "";

        //WHEN
        Executable executable = () -> ValidationUtil.throwExceptionIfBlankOrNullString(string, "String");

        //THEN
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, executable);
        assertEquals("String cannot be empty", illegalArgumentException.getMessage());
    }

    @Test
    void givenNullString_whenCallingThrowExceptionIfBlankOrNullString_thenThrowIllegalArgumentException() {
        //GIVEN
        String string = null;

        //WHEN
        Executable executable = () -> ValidationUtil.throwExceptionIfBlankOrNullString(string, "String");

        //THEN
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, executable);
        assertEquals("String cannot be null", illegalArgumentException.getMessage());
    }


    @Test
    void givenNonBlankString_whenCallingThrowExceptionIfBlankOrNullString_thenThrowNoException() {
        //GIVEN
        String string = "String";

        //WHEN
        Executable executable = () -> ValidationUtil.throwExceptionIfBlankOrNullString(string, "String");

        //THEN
        assertDoesNotThrow(executable);
    }

    @Test
    void givenNullEmail_whenCallingThrowExceptionIfInvalidEmail_thenThrowIllegalEmailException() {
        //GIVEN
        String string = null;

        //WHEN
        Executable executable = () -> ValidationUtil.throwExceptionIfIllegalEmail(string);

        //THEN
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, executable);
        assertEquals("Illegal email: Email cannot be null", illegalArgumentException.getMessage());
    }

    @Test
    void givenBlankEmail_whenCallingThrowExceptionIfInvalidEmail_thenThrowIllegalEmailException() {
        //GIVEN
        String string = "";

        //WHEN
        Executable executable = () -> ValidationUtil.throwExceptionIfIllegalEmail(string);

        //THEN
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, executable);
        assertEquals("Illegal email: Email cannot be empty", illegalArgumentException.getMessage());
    }

    @Test
    void givenInvalidEmail_whenCallingThrowExceptionIfInvalidEmail_thenThrowIllegalEmailException() {
        //GIVEN
        String string = "A";

        //WHEN
        Executable executable = () -> ValidationUtil.throwExceptionIfIllegalEmail(string);

        //THEN
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, executable);
        assertEquals("Illegal email: Email must be of format XX@XX.XX", illegalArgumentException.getMessage());
    }

    @Test
    void givenValidEmail_whenCallingThrowExceptionIfInvalidEmail_thenThrowIllegalEmailException() {
        //GIVEN
        String string = "Test@test.test";

        //WHEN
        Executable executable = () -> ValidationUtil.throwExceptionIfIllegalEmail(string);

        //THEN
        assertDoesNotThrow(executable);
    }

    @Test
    void givenNegativePrice_whenCallingthrowExceptionIfNegativePrice_thenThrowIllegalPriceException() {
        //GIVEN
        double price = -1;

        //WHEN
        Executable executable = () -> ValidationUtil.throwExceptionIfNegativePrice(price);

        //THEN
        IllegalPriceException illegalPriceException = assertThrows(IllegalPriceException.class, executable);
        assertEquals("Illegal price: Price cannot be negative", illegalPriceException.getMessage());
    }

    @Test
    void given0Price_whenCallingthrowExceptionIfNegativePrice_thenNoException() {
        //GIVEN
        double price = 0;

        //WHEN
        Executable executable = () -> ValidationUtil.throwExceptionIfNegativePrice(price);

        //THEN
        assertDoesNotThrow(executable);
    }

    @Test
    void givenPositivePrice_whenCallingthrowExceptionIfNegativePrice_thenNoException() {
        //GIVEN
        double price = 1;

        //WHEN
        Executable executable = () -> ValidationUtil.throwExceptionIfNegativePrice(price);

        //THEN
        assertDoesNotThrow(executable);
    }

    @Test
    void givenNegativeNumber_whenCallingthrowExceptionIfNegativeNumber_thenThrowIllegalArgumentException() {
        //GIVEN
        int number = -1;

        //WHEN
        Executable executable = () -> ValidationUtil.throwExceptionIfNegativeNumber(number, "Number");

        //THEN
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, executable);
        assertEquals("Number can not be negative", illegalArgumentException.getMessage());
    }

    @Test
    void given0Number_whenCallingthrowExceptionIfNegativeNumber_thenNoException() {
        //GIVEN
        int number = 0;

        //WHEN
        Executable executable = () -> ValidationUtil.throwExceptionIfNegativeNumber(number, "Number");

        //THEN
        assertDoesNotThrow(executable);
    }

    @Test
    void givenPositiveNumber_whenCallingthrowExceptionIfNegativeNumber_thenNoException() {
        //GIVEN
        int number = 1;

        //WHEN
        Executable executable = () -> ValidationUtil.throwExceptionIfNegativeNumber(number, "Number");

        //THEN
        assertDoesNotThrow(executable);
    }

    @Test
    void givenNullUUIDString_whenCallingThrowExceptionIfInvalidUUID_thenThrowIllegalArgumentException() {
        //GIVEN
        String uuid = null;

        //WHEN
        Executable executable = () -> ValidationUtil.throwExceptionIfInvalidUUID(uuid);

        //THEN
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, executable);
        assertEquals("Id cannot be null", illegalArgumentException.getMessage());

    }

    @Test
    void givenBlankUUIDString_whenCallingThrowExceptionIfInvalidUUID_thenThrowIllegalArgumentException() {
        //GIVEN
        String uuid = "";

        //WHEN
        Executable executable = () -> ValidationUtil.throwExceptionIfInvalidUUID(uuid);

        //THEN
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, executable);
        assertEquals("Id cannot be empty", illegalArgumentException.getMessage());

    }

    @Test
    void givenWrongLengthUUIDString_whenCallingThrowExceptionIfInvalidUUID_thenThrowIllegalArgumentException() {
        //GIVEN
        String uuid = "5";

        //WHEN
        Executable executable = () -> ValidationUtil.throwExceptionIfInvalidUUID(uuid);

        //THEN
        IllegalUUIDException illegalUUIDException = assertThrows(IllegalUUIDException.class, executable);
        assertEquals("Illegal UUID: The id is not of a valid length", illegalUUIDException.getMessage());
    }

    @Test
    void givenValidUUIDString_whenCallingThrowExceptionIfInvalidUUID_thenThrowNoException() {
        //GIVEN
        String uuid = "123e4567-e89b-12d3-a456-556642440000";

        //WHEN
        Executable executable = () -> ValidationUtil.throwExceptionIfInvalidUUID(uuid);

        //THEN
        assertDoesNotThrow(executable);
    }

    @Test
    void givenBlankUUIDString_whenCallingIsValidUuid_thenReturnFalse() {
        //GIVEN
        String uuid = "";

        //WHEN
        Boolean isValidUUID = ValidationUtil.isValidUUID(uuid);

        //THEN
        assertEquals(false, isValidUUID);

    }

    @Test
    void givenNullUUIDString_whenCallingIsValidUuid_thenReturnFalse() {
        //GIVEN
        String uuid = null;

        //WHEN
        Boolean isValidUUID = ValidationUtil.isValidUUID(uuid);

        //THEN
        assertEquals(false, isValidUUID);

    }

    @Test
    void givenInvalidLengthUUIDString_whenCallingIsValidUuid_thenReturnFalse() {
        //GIVEN
        String uuid = "A";

        //WHEN
        Boolean isValidUUID = ValidationUtil.isValidUUID(uuid);

        //THEN
        assertEquals(false, isValidUUID);

    }

    @Test
    void givenValidUUIDString_whenCallingIsValidUuid_thenReturnTrue() {
        //GIVEN
        String uuid = "123e4567-e89b-12d3-a456-556642440000";

        //WHEN
        Boolean isValidUUID = ValidationUtil.isValidUUID(uuid);

        //THEN
        assertEquals(true, isValidUUID);

    }

    @Test
    void givenNullUUIDString_whenCallingConvertStringToUUID_thenThrowIllegalArgumentException() {
        //GIVEN
        String uuid = null;

        //WHEN
        Executable executable = () -> ValidationUtil.convertStringToUUID(uuid);

        //THEN
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, executable);
        assertEquals("Id cannot be null", illegalArgumentException.getMessage());

    }

    @Test
    void givenBlankUUIDString_whenCallingConvertStringToUUID_thenThrowIllegalArgumentException() {
        //GIVEN
        String uuid = "";

        //WHEN
        Executable executable = () -> ValidationUtil.convertStringToUUID(uuid);

        //THEN
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, executable);
        assertEquals("Id cannot be empty", illegalArgumentException.getMessage());

    }

    @Test
    void givenWrongLengthUUIDString_whenCallingConvertStringToUUID_thenThrowIllegalArgumentException() {
        //GIVEN
        String uuid = "5";

        //WHEN
        Executable executable = () -> ValidationUtil.convertStringToUUID(uuid);

        //THEN
        IllegalUUIDException illegalUUIDException = assertThrows(IllegalUUIDException.class, executable);
        assertEquals("Illegal UUID: The id is not of a valid length", illegalUUIDException.getMessage());
    }

    @Test
    void givenValidUUIDString_whenCallingConvertStringToUUID_thenReturnUUID() {
        //GIVEN
        String uuid = "123e4567-e89b-12d3-a456-556642440000";

        //WHEN
        UUID uuidConverted = ValidationUtil.convertStringToUUID(uuid);

        //THEN

        assertEquals(UUID.fromString(uuid), uuidConverted);
    }

}