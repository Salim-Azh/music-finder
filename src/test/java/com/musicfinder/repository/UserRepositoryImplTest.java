package com.musicfinder.repository;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import com.musicfinder.model.Song;
import com.musicfinder.model.User;

import org.bson.types.ObjectId;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import io.cucumber.java.After;

public class UserRepositoryImplTest {
    
    private UserRepositoryImpl userRepositoryImpl;

    //Test data
    private final User user = new User("toto@gmail.com", "azeaze");
    private final Song song = new Song(new ObjectId(), "toto", "tata", "titi");

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

    
    @AfterClass
    public static void teardown(){
        UserRepository userRepository = new UserRepositoryImpl();
        userRepository.deleteAll();
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

    @Test
    public void  addSongToPlaylist() {
        Optional<User> savedUser = userRepositoryImpl.save(user);
        Optional<User> updatedUser = userRepositoryImpl.addSongToPlaylist(savedUser.get(), song);
        assertTrue(updatedUser.isPresent());
    }

    @Test
    public void  addSongToPlaylistShouldFailWhenUserNull() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("user and song cannot be null");
        
        userRepositoryImpl.addSongToPlaylist(null, song);
        
    }

    @Test
    public void  addSongToPlaylistShouldFailWhenSongNull() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("user and song cannot be null");
        
        userRepositoryImpl.addSongToPlaylist(user, null);
    }

}
