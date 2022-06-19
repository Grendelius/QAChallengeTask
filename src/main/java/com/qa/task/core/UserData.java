package com.qa.task.core;

import com.qa.task.model.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserData {

    private final List<User> users;

    public User userForName(String name) {
        return users.stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown '" + name + "' user"));
    }

}
