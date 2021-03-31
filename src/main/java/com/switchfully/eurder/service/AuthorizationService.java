package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.user.User;
import com.switchfully.eurder.domain.user.UserRepository;
import com.switchfully.eurder.domain.user.UserRole;
import com.switchfully.eurder.infrastructure.utils.ValidationUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    private final UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void throwExceptionIfNotAdmin(String id) throws IllegalAccessException {
        if (!isAdmin(id)) {
            throw new IllegalAccessException("Only admins can visit this page");
        }

    }

    public void throwExceptionIfNotMember(String id) throws IllegalAccessException {
        if (!isMember(id)) {
            throw new IllegalAccessException("Only registered users can visit this page");
        }

    }

    private boolean isAdmin(String id) {
        User user = userRepository.getUserById(ValidationUtil.convertStringToUUID(id));
        return user != null && user.getUserRole() == UserRole.ADMIN;
    }

    private boolean isMember(String id) {
        User user = userRepository.getUserById(ValidationUtil.convertStringToUUID(id));
        return user != null;
    }
}
