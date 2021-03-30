package com.switchfully.eurder.domain.user;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private final List<User> userList = new ArrayList<>();

    public UserRepository() {
    }

    public void addUser(User user) {
        userList.add(user);
    }
}
