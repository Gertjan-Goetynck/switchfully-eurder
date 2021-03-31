package com.switchfully.eurder.domain.user;

import com.switchfully.eurder.infrastructure.utils.ValidationUtil;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class UserRepository {
    private final Map<UUID, User> userMap;

    public UserRepository() {
        userMap = new HashMap<>();
        Admin rootAdmin = new Admin(UUID.fromString("123e4567-e89b-12d3-a456-556642440000"), "Root", "Admin", "Root@Admin.com", "Root", "Root");
        userMap.put(rootAdmin.getId(), rootAdmin);

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
        if (userMap.get(uuid) == null || userMap.get(uuid).getUserRole() != UserRole.CUSTOMER) {
            throw new IllegalArgumentException("Customer not found");
        }
        return (Customer) userMap.get(uuid);
    }
}
