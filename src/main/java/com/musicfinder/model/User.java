package com.musicfinder.model;

public class User {

    private String email;
    private String password;

    public User(String email, String password) {
        if (email == null || password == null) {
            throw new IllegalArgumentException("Email and password cannot be null");
        }
        this.email = email;
        this.password = password;
    }
    
}
