package com.musicfinder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.musicfinder.model.Playlist;
import com.musicfinder.model.Song;
import com.musicfinder.model.User;
import com.musicfinder.service.PlaylistService;
import com.musicfinder.service.UserService;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;

public class ClientTest extends BaseTestClass {

    private Client client;

    @Mock
    private UserService userService;

    @Mock
    private PlaylistService playlistService;

    //Test data
    private final String email = "toto@gmail.com";
    private final String password = "azeaze";
    private final User user = new User(email,password);
    private final Song song = new Song(new ObjectId(), "toto", "tata", "titi");

    @Before
    public void setup() {
        client = new Client(userService, playlistService);
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

    @Test
    public void saveSong() throws Exception {
        //given
        String expected = "Song saved to playlist";
        client.setConnectedUser(user);
        client.search("Easy");
        List<Song> songs = new ArrayList<>(); 
        songs.add(song);
        Playlist pl = new Playlist(songs);
        user.setPlaylist(pl);
        when(playlistService.saveSong(any(User.class), any(Song.class))).thenReturn(user);

        //when
        String actual = client.saveSong(0);

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void saveSongShouldFailWhenNoSearchResult() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("No results from search you must search for a song before saving it to playlist");
        client.setConnectedUser(user);
        client.saveSong(0);
    }

    @Test
    public void saveSongShouldFailWhenInvalidIndex() {
        //given
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("No such song exist the index shoud be between 0 and 9");
        client.search("Easy");
        //when
        client.saveSong(10);
    }
}
