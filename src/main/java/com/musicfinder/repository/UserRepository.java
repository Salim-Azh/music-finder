package com.musicfinder.repository;

import java.util.Optional;

import com.musicfinder.model.User;

public interface UserRepository extends Repository<User> {

    Optional<User> getUserByEmail(String email);
}
