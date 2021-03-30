package com.switchfully.eurder.api.exceptions;

import com.switchfully.eurder.infrastructure.exceptions.IllegalEmailException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    private void illegalArgumentException(IllegalArgumentException exception, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST,exception.getMessage());
    }

    @ExceptionHandler(IllegalEmailException.class)
    private void illegalEmailException(IllegalEmailException exception, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, exception.getMessage());
    }
}
