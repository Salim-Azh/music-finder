package com.musicfinder.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.musicfinder.BaseTestClass;
import com.musicfinder.model.User;
import com.musicfinder.repository.UserRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

public class UserServiceTest extends BaseTestClass {
    
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    //Test data
    private final User user = new User("toto@gmail.com", "azeaze");

    @Before
    public void setup() {
        userService = new UserService(userRepository);
    }

    @Test
    public void register() throws Exception {
        when(userRepository.save(any(User.class))).thenReturn(Optional.of(user));
        userService.register(user);
        
        ArgumentCaptor<User> uArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(uArgumentCaptor.capture());

        User userThatWasSaved = uArgumentCaptor.getValue();
        assertEquals(userThatWasSaved,user);
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    
    @Test
    public void register_fails_when_persistance_failure() throws Exception {
        when(userRepository.save(any(User.class))).thenReturn(Optional.empty());
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("An issue occured when saving the user");
        userService.register(user);
        verify(userRepository, times(0)).save(any(User.class));
    }

    @Test
    public void register_fails_when_already_one_account() throws Exception {
        when(userRepository.getUserByEmail(any(String.class))).thenReturn(Optional.of(user));
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("An account already exists for this email");

        userService.register(user);
        verify(userRepository, times(0)).save(any(User.class));
    }

    @Test
    public void login() throws Exception {
        when(userRepository.getUserByEmail("toto@gmail.com")).thenReturn(Optional.of(user));

        assertEquals(user, userService.login("toto@gmail.com","azeaze"));
    }

    @Test
    public void login_should_fail_with_wrong_email() throws Exception {
        when(userRepository.getUserByEmail("toto@gmail.com")).thenReturn(Optional.empty());

        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Invalid email or password...");
        userService.login("toto@gmail.com","azeaze");
    }

    @Test
    public void login_should_fail_with_wrong_password() throws Exception {
        when(userRepository.getUserByEmail("toto@gmail.com")).thenReturn(Optional.of(user));

        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Invalid email or password...");
        userService.login("toto@gmail.com","a");
    }
}
