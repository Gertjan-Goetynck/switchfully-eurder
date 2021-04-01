package com.switchfully.eurder.infrastructure.utils;

import com.switchfully.eurder.infrastructure.exceptions.IllegalEmailException;
import com.switchfully.eurder.infrastructure.exceptions.IllegalPriceException;
import com.switchfully.eurder.infrastructure.exceptions.IllegalUUIDException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.regex.Pattern;

public class ValidationUtil {

    private static final Logger logger = LoggerFactory.getLogger(ValidationUtil.class);

    /* EXCEPTION MESSAGES */
    public static final String NEGATIVE_PRICE_MESSAGE = "Price cannot be negative";
    public static final String NEGATIVE_NUMBER_MESSAGE = " can not be negative";
    public static final String INVALID_UUID_MESSAGE = "The id is not of a valid length";
    private static final String NULL_OBJECT_MESSAGE = " cannot be null";
    private static final String BLANK_OBJECT_MESSAGE = " cannot be empty";

    /* EMAIL */
    private static final String EMAIL_REGEX = "^(.+)@(.+)$";
    private static final String NULL_EMAIL_MESSAGE = "Email" + NULL_OBJECT_MESSAGE;
    private static final String BLANK_EMAIL_MESSAGE = "Email" + BLANK_OBJECT_MESSAGE;
    private static final String INVALID_EMAIL_MESSAGE = "Email must be of format XX@XX.XX";

    public static void throwExceptionIfNullObject(Object object, String objectName) {
        if (isNullObject(object)) {
            logger.warn("There was an attempt to create a " + objectName + " null object");
            throw new IllegalArgumentException(objectName + NULL_OBJECT_MESSAGE);
        }
    }

    public static boolean isNullObject(Object object) {
        return object == null;
    }

    public static void throwExceptionIfBlankString(String string, String stringName) {
        if (string.isBlank()) {
            logger.warn("There was an attempt to create a " + string + " blank string");
            throw new IllegalArgumentException(stringName + BLANK_OBJECT_MESSAGE);
        }
    }

    public static void throwExceptionIfBlankOrNullString(String string, String stringName) {
        throwExceptionIfNullObject(string, stringName);
        throwExceptionIfBlankString(string, stringName);
    }

    private static boolean isValidEmail(String email) {
        return Pattern.compile(EMAIL_REGEX).matcher(email).matches();
    }

    private static void throwExceptionIfInvalidEmail(String email) {
        if (!isValidEmail(email)) {
            logger.warn("There was an attempt to create an invalid email");
            throw new IllegalEmailException(INVALID_EMAIL_MESSAGE);
        }
    }

    private static void throwExceptionIfBlankEmail(String email) {
        if (email.isBlank()) {
            logger.warn("There was an attempt to create a blank email");
            throw new IllegalEmailException(BLANK_EMAIL_MESSAGE);
        }
    }

    private static void throwExceptionIfNullEmail(String email) {
        if (isNullObject(email)) {
            logger.warn("There was an attempt to create a null email");
            throw new IllegalEmailException(NULL_EMAIL_MESSAGE);
        }
    }

    public static void throwExceptionIfIllegalEmail(String email) {
        throwExceptionIfNullEmail(email);
        throwExceptionIfBlankEmail(email);
        throwExceptionIfInvalidEmail(email);
    }


    public static void throwExceptionIfNegativePrice(double amount) {
        if (amount < 0) {
            logger.warn("There was an attempt to create a negative price (" + amount + ")");
            throw new IllegalPriceException(NEGATIVE_PRICE_MESSAGE);
        }
    }

    public static void throwExceptionIfNegativeNumber(int number, String numberName) {
        if (number < 0) {
            logger.warn("There was an attempt to create a negative " + numberName + " (" + number + ")");
            throw new IllegalArgumentException(numberName + NEGATIVE_NUMBER_MESSAGE);
        }
    }

    public static void throwExceptionIfInvalidUUID(String id) {
        throwExceptionIfBlankOrNullString(id, "Id");
        if (!isValidUUID(id)) {
            logger.warn("There was an attempt to create an invalid ID (" + id + ")");
            throw new IllegalUUIDException(INVALID_UUID_MESSAGE);
        }
    }

    private static boolean isValidUUIDLength(String id) {
        return id.length() == 36;
    }

    public static boolean isValidUUID(String id) {
        return !isNullObject(id) && isValidUUIDLength(id);
    }

    public static UUID convertStringToUUID(String id) {
        throwExceptionIfInvalidUUID(id);
        return UUID.fromString(id);
    }
}
