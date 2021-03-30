package com.switchfully.eurder.infrastructure.utils;

import com.switchfully.eurder.infrastructure.exceptions.IllegalEmailException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {

    private static final String NULL_OBJECT_MESSAGE = " cannot be null.";
    private static final String BLANK_OBJECT_MESSAGE = " cannot be empty";

    /* EMAIL */
    private static final String EMAIL_REGEX = "^(.+)@(.+)$";
    private static final String NULL_EMAIL_MESSAGE = "Email" + NULL_OBJECT_MESSAGE;
    private static final String BLANK_EMAIL_MESSAGE = "Email" + BLANK_OBJECT_MESSAGE;
    private static final String INVALID_EMAIL_MESSAGE = "Email must be of format XX@XX.XX";

    public static void throwExceptionIfNullObject(Object object, String objectName) {
        if (isNullObject(object)) {
            throw new IllegalArgumentException(objectName + NULL_OBJECT_MESSAGE);
        }
    }

    public static void throwExceptionIfBlankString(String string, String stringName) {
        if (string.isBlank()) {
            throw new IllegalArgumentException(stringName + BLANK_OBJECT_MESSAGE);
        }
    }

    public static void throwExceptionIfBlankOrNullString(String string, String stringName) {
        throwExceptionIfNullObject(string, stringName);
        throwExceptionIfBlankString(string, stringName);
    }

    public static void throwExceptionIfInvalidEmail(String email) {
        if (isNullObject(email)) {
            throw new IllegalEmailException(NULL_EMAIL_MESSAGE);
        }
        if (email.isBlank()) {
            throw new IllegalEmailException(BLANK_EMAIL_MESSAGE);
        }
        if (!isValidEmail(email)) {
            throw new IllegalEmailException(INVALID_EMAIL_MESSAGE);
        }
    }

    private static boolean isValidEmail(String email) {
        return Pattern.compile(EMAIL_REGEX).matcher(email).matches();
    }

    public static boolean isNullObject(Object object) {
        return object == null;
    }


}
