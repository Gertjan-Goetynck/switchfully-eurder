package com.switchfully.eurder.domain.user;

import com.switchfully.eurder.infrastructure.exceptions.UserNotFoundException;
import com.switchfully.eurder.infrastructure.utils.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class UserRepository {
    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);
    private final Map<UUID, User> userMap;
    private static final Admin ROOT_ADMIN = new Admin(UUID.fromString("123e4567-e89b-12d3-a456-556642440000"), "Root", "Admin", "Root@Admin.com", "root", "Root", "Root");

    public UserRepository() {
        userMap = new HashMap<>();
        userMap.put(ROOT_ADMIN.getId(), ROOT_ADMIN);

    }

    public Optional<User> getUserByEmailAndPassword(String email, String password) {
        return userMap.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .filter(user -> user.getPassword().equals(password))
                .limit(1)
                .findFirst();
    }

    public User getUserByEmail(String email) {
        return userMap.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .limit(1)
                .findFirst().orElseThrow(UserNotFoundException::new);
    }

    public void addUser(User user) {
        ValidationUtil.throwExceptionIfNullObject(user, "User");
        userMap.put(user.getId(), user);
    }

    public User getUserById(UUID uuid) {
        return userMap.get(uuid);
    }

    public List<Customer> getAllCustomers() {
        return userMap.values().stream()
                .filter(user -> user.getUserRole() == UserRole.CUSTOMER)
                .map(customerUser -> (Customer) customerUser)
                .collect(Collectors.toList());
    }

    public Customer getCustomerById(UUID uuid) {
        return (Customer) getUserById(uuid);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(userMap.values());
    }
}
