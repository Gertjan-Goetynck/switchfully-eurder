package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.user.User;
import com.switchfully.eurder.domain.user.UserRepository;
import com.switchfully.eurder.domain.user.UserRole;
import com.switchfully.eurder.infrastructure.utils.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthorizationService {
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(AuthorizationService.class);


    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void throwExceptionIfNotAdmin(String userId) throws IllegalAccessException {
        if (!ValidationUtil.isValidUUID(userId) || !isAdmin(userId)) {
            logger.warn("A user with UUID " + (!ValidationUtil.isNullObject(userId) ? userId : "UNKNOWN") + " tried to access an admin only page without permission");
            throw new IllegalAccessException("Only admins can visit this page");
        }

    }

    public void throwExceptionIfNotCustomer(String userId) throws IllegalAccessException {
        if (!ValidationUtil.isValidUUID(userId) || !isCustomer(userId)) {
            logger.warn("A user with UUID " + (!ValidationUtil.isNullObject(userId) ? userId : "UNKNOWN") + " tried to access a customer only page without permission");
            throw new IllegalAccessException("Only customers can visit this page");
        }
    }

    public void throwExceptionIfNotOwnProfile(String authorizationId, String customerId) throws IllegalAccessException {
        if (ValidationUtil.isNullObject(authorizationId) || !authorizationId.equals(customerId) && !isCustomer(customerId)) {
            logger.warn("A user with UUID " + (authorizationId != null ? authorizationId : "UNKOWN") + " tried to access a page owned by the user with ID " + customerId + " without permission");
            throw new IllegalAccessException("You need to be logged in and owner of this profile to visit this page");
        }
    }

    public void throwExceptionIfNotMember(String userId) throws IllegalAccessException {
        if (!ValidationUtil.isValidUUID(userId) || !isMember(userId)) {
            logger.warn("A user with UUID " + (!ValidationUtil.isNullObject(userId) ? userId : "UNKNOWN") + " tried to access a member only page without permission");
            throw new IllegalAccessException("Only registered users can visit this page");
        }

    }

    private boolean isAdmin(String id) {
        User user = userRepository.getUserById(UUID.fromString(id));
        return user != null && user.getUserRole() == UserRole.ADMIN;
    }

    private boolean isCustomer(String userId) {
        User user = userRepository.getUserById(UUID.fromString(userId));
        return user != null && user.getUserRole() == UserRole.CUSTOMER;
    }

    private boolean isMember(String id) {
        User user = userRepository.getUserById(UUID.fromString(id));
        return user != null;
    }
}
