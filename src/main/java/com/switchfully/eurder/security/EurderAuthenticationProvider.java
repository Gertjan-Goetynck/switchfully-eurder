package com.switchfully.eurder.security;

import com.switchfully.eurder.api.controllers.ItemController;
import com.switchfully.eurder.domain.user.User;
import com.switchfully.eurder.domain.user.UserRole;
import com.switchfully.eurder.infrastructure.exceptions.UserNotFoundException;
import com.switchfully.eurder.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EurderAuthenticationProvider implements AuthenticationProvider {
    private final AuthenticationService authenticationService;
    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);


    public EurderAuthenticationProvider(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return authenticationService.getUserFromEmailPassword(authentication.getName(), authentication.getCredentials().toString())
                .map(this::mapToAuthenticationToken)
                .orElseThrow(UserNotFoundException::new);
    }

    private UsernamePasswordAuthenticationToken mapToAuthenticationToken(User user) {
        return new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(), mapRoleToGrantedAuthorities(user.getUserRole()));
    }

    private List<SimpleGrantedAuthority> mapRoleToGrantedAuthorities(UserRole role) {
        return role.getFeatures().stream()
                .map(Enum::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
