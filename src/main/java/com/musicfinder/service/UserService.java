package com.musicfinder.service;

import com.musicfinder.model.User;
import com.musicfinder.repository.UserRepository;

public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        if (userRepository == null) {
            throw new IllegalArgumentException("UserRepository cannot be null");
        }
        this.userRepository = userRepository;
    }

    public User register(User user) throws Exception {
        if (user == null) {
            throw new IllegalArgumentException("user cannot be null");
        }
        
        boolean exists =userRepository.getUserByEmail(user.getEmail()).isPresent();
        if (exists) {
            throw new Exception("An account already exists for this email");
        }    

        return userRepository.save(user).orElseThrow(() -> new Exception("An issue occured when saving the user"));
    }
}
