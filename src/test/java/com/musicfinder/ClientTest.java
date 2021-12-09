package com.musicfinder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.musicfinder.model.User;
import com.musicfinder.service.UserService;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;

public class ClientTest extends BaseTestClass {

    private Client client;

    @Mock
    private UserService userService;

    //Test data
    private final String email = "toto@gmail.com";
    private final String password = "azeaze";
    private final User user = new User(email,password);

    @Before
    public void setup() {
        client = new Client(userService);
    }   
    
    @Test
    public void register() throws Exception {
        String expected = "Thanks for joining MusicFinder! You can now login!";

        when(userService.register(any(User.class))).thenReturn(user);
        String actual = client.register(email, password);

        assertEquals(expected, actual);
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    
    @Test
    public void register_should_fail_when_invalid_email() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Registration failed : Invalid email");

        client.register("a", password);
    }

    @Test
    public void register_should_fail_when_too_short_password() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Registration failed : password has to contain at least 6 characters");

        client.register(email, "aze");
    }

    @Test
    public void register_should_fail_when_already_one_account() throws Exception {
        String expected = "An account already exists for this email";

        when(userService.register(any(User.class))).thenThrow(new Exception(expected));
        String actual = client.register(email, password);

        assertEquals(expected, actual);
    }

    @Test
    public void login() throws Exception{
        String expected = "Successfully logged in!";
        when(userService.login(any(String.class), any(String.class))).thenReturn(user);
        String actual = client.login(email, password);

        assertEquals(expected, actual);
    }

    @Test
    public void login_should_fail_with_wrong_credentials() throws Exception{
        String expected = "Invalid email or password...";
        when(userService.login(any(String.class), any(String.class))).thenThrow(new Exception(expected));
        String actual = client.login(email, password);

        assertEquals(expected, actual);
    }
    
    @Test
    public void testSearchWithoutParam(){
        client.search("");
        assertEquals(0, client.getFetchedSongs().size());
    }

    @Test
    public void testSearch(){
        client.search("Easy");
        assertNotEquals(0, client.getFetchedSongs().size());
    }
}
