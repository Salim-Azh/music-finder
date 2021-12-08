package com.musicfinder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.musicfinder.model.User;
import com.musicfinder.service.UserService;

public class Client {

    private UserService userService;

    public Client(UserService userService) {
        if (userService == null) {
            throw new IllegalArgumentException("User service cannot be null");
        }
        this.userService = userService;
    }

    private boolean isValidEmail(String email) {
        String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Matcher matcher = Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(email);
        return matcher.find();
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 6;
    }
    
    public String register(String email, String password) {
        if (email == null || password == null) {
            throw new IllegalArgumentException("Email and password cannot be null");
        }
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Registration failed : Invalid email");
        }
        if (!isValidPassword(password)) {
            throw new IllegalArgumentException("Registration failed : password has to contain at least 6 characters");
        }
        try {
            userService.register(new User(email, password));
            return "Thanks for joining MusicFinder! You can now login!";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
    
}
