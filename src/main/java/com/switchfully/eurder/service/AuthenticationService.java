package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.user.User;
import com.switchfully.eurder.domain.user.UserRepository;
import com.switchfully.eurder.infrastructure.exceptions.UserNotFoundException;
import com.switchfully.eurder.security.IAuthenticationFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final IAuthenticationFacade authenticationFacade;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    public AuthenticationService(UserRepository userRepository, IAuthenticationFacade authenticationFacade) {
        this.userRepository = userRepository;
        this.authenticationFacade = authenticationFacade;
    }

    public User getAuthenticatedUser() {
        return userRepository.getUserByEmail(authenticationFacade.getAuthentication().getPrincipal().toString());
    }

    public Optional<User> getUserFromEmailPassword(String email, String password) {
        return userRepository.getUserByEmailAndPassword(email, password);
    }

    public void throwExceptionIfNotOwnProfile(String customerId) throws IllegalAccessException {
        if (!isAuthenticatedUserProfile(customerId)) {
            logger.warn("A user with ID " + getAuthenticatedUser().getId() + " tried to access a page owned by the user with ID " + customerId + " without permission");
            throw new IllegalAccessException("You need to be logged in and owner of this profile to visit this page");
        }
    }

    private boolean isAuthenticatedUserProfile(String userId) {
        if (!isUser(userId)) {
            logger.warn("User with ID " + userId + " not found");
            throw new UserNotFoundException(UUID.fromString(userId));
        }
        return getAuthenticatedUser().getEmail().equals(userRepository.getUserById(UUID.fromString(userId)).getEmail());
    }

    private boolean isUser(String userId) {
        return userRepository.getUserById(UUID.fromString(userId)) != null;
    }

}
