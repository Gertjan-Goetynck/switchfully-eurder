package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.user.User;
import com.switchfully.eurder.domain.user.UserRepository;
import com.switchfully.eurder.domain.user.UserRole;
import com.switchfully.eurder.infrastructure.utils.ValidationUtil;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthorizationService {
    private final UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void throwExceptionIfNotAdmin(String id) throws IllegalAccessException {
        if (!ValidationUtil.isValidUUID(id) || !isAdmin(id)) {
            throw new IllegalAccessException("Only admins can visit this page");
        }

    }

    public void throwExceptionIfNotMember(String id) throws IllegalAccessException {
        if (!ValidationUtil.isValidUUID(id) || !isMember(id)) {
            throw new IllegalAccessException("Only registered users can visit this page");
        }

    }

    private boolean isAdmin(String id) {
        User user = userRepository.getUserById(UUID.fromString(id));
        return user != null && user.getUserRole() == UserRole.ADMIN;
    }

    private boolean isMember(String id) {
        User user = userRepository.getUserById(UUID.fromString(id));
        return user != null;
    }

    public void throwExceptionIfNotCustomer(String userId) throws IllegalAccessException {
        if (!ValidationUtil.isValidUUID(userId) || !isCustomer(userId)) {
            throw new IllegalAccessException("Only customers can visit this page");
        }
    }

    private boolean isCustomer(String userId) {
        User user = userRepository.getUserById(UUID.fromString(userId));
        return user != null && user.getUserRole() == UserRole.CUSTOMER;
    }
}
