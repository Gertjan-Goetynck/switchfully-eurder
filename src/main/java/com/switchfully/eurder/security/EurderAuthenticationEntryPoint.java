package com.switchfully.eurder.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class EurderAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
    public static final String NAME_OF_REALM = "all-authenticated-users";

    public EurderAuthenticationEntryPoint() {
        setRealmName(NAME_OF_REALM);
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.addHeader("WWW-Authenticate", "Basic realm = " + getRealmName());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        writer.println("HTTP Status 401 (UNAUTHORIZED)- " + authException.getMessage());
    }
}
