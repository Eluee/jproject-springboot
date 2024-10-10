package com.jsoft.Jproject.service;

import com.jsoft.Jproject.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
    Optional<User> getUserByEmail(String email);
}