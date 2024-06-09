package com.dauphine.manager.service.impl;


import com.dauphine.manager.entity.User;
import com.dauphine.manager.repository.UserRepository;
import com.dauphine.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getOrCreateUser(String username) {
        Optional<User> existingUser = userRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            return existingUser.get();
        } else {
            User newUser = new User();
            newUser.setUsername(username);
            return userRepository.save(newUser);
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}