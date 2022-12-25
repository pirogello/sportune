package com.project.sportune.sportune.service;

import com.project.sportune.sportune.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User register(User user);
    List<User> getAll();
    User findByEmail(String email);
    User findByUsername(String username);
    User findById(UUID id);
    void delete(UUID id);
}
