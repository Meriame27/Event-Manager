package com.dauphine.manager.service;


import com.dauphine.manager.entity.User;

import java.util.List;
import java.util.Optional;


public interface UserService {
    Optional<User> getUserById(Long id);
    User getOrCreateUser(String username);
}