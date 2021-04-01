package com.switchfully.eurder.api.exceptions;

import com.switchfully.eurder.infrastructure.exceptions.IllegalEmailException;
import com.switchfully.eurder.infrastructure.exceptions.IllegalUUIDException;
import com.switchfully.eurder.infrastructure.exceptions.ItemNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    private void illegalArgumentException(IllegalArgumentException exception, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(IllegalEmailException.class)
    private void illegalEmailException(IllegalEmailException exception, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(IllegalAccessException.class)
    private void illegalAccessException(IllegalAccessException exception, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, exception.getMessage());
    }

    @ExceptionHandler(ItemNotFoundException.class)
    private void itemNotFoundException(ItemNotFoundException exception, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, exception.getMessage());
    }

    @ExceptionHandler(IllegalUUIDException.class)
    private void illegalUUIDException(IllegalUUIDException exception, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, exception.getMessage());
    }
}
