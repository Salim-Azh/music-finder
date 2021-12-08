package com.musicfinder.repository;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import com.musicfinder.model.User;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import io.cucumber.java.After;

public class UserRepositoryImplTest {
    
    private UserRepositoryImpl userRepositoryImpl;

    //Test data
    private final User user = new User("toto@gmail.com", "azeaze");

    @Before
    public void onSetup() {
        this.userRepositoryImpl = new UserRepositoryImpl();
        userRepositoryImpl.deleteAll();
    }

    @After
    public void onTeardown() {
        this.userRepositoryImpl = new UserRepositoryImpl();
        userRepositoryImpl.deleteAll();
    }

    @Test
    public void save() {
        Optional<User> savedUser = userRepositoryImpl.save(user);
        assertTrue(savedUser.isPresent());
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void save_should_fail_when_null_user() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("user cannot be null");
        
        Optional<User> savedUser = userRepositoryImpl.save(null);
        assertTrue(savedUser.isEmpty());
    }

    @Test
    public void save_fail_when_non_unique_email() {
        userRepositoryImpl.save(user);
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("An account already exists for this email");
        userRepositoryImpl.save(user);
    }

    @Test
    public void getUserByEmail() {
        userRepositoryImpl.save(user);

        Optional<User> retrievedUser = userRepositoryImpl.getUserByEmail(user.getEmail());
        assertTrue(retrievedUser.isPresent());
    }

    @Test
    public void getUserByEmail_should_fail_when_null_email() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("email cannot be null");
        Optional<User> retrievedUser = userRepositoryImpl.getUserByEmail(null);
        assertTrue(retrievedUser.isEmpty());
    }
}