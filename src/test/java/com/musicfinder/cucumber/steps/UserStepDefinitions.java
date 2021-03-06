package com.musicfinder.cucumber.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;
import java.util.Map;

import com.musicfinder.Client;
import com.musicfinder.cucumber.state.ExceptionHandler;
import com.musicfinder.model.Playlist;
import com.musicfinder.model.Song;
import com.musicfinder.model.User;
import com.musicfinder.repository.UserRepositoryImpl;
import com.musicfinder.service.PlaylistService;
import com.musicfinder.service.UserService;

import org.bson.types.ObjectId;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserStepDefinitions {

    private Client client;
    private String message;
    private ExceptionHandler exceptionHandler = new ExceptionHandler();

    @Given("the MusicFinder app is started")
    public void the_MusicFinder_app_is_started() {
        UserService userService = new UserService(new UserRepositoryImpl());
        PlaylistService playlistService = new PlaylistService(new UserRepositoryImpl());
        client = new Client(userService, playlistService);
    }

    @When("the user registers with valid credentials")
    public void the_user_registers_with_valid_credentials() {
        message = client.register("tata@gmail.com", "azeaze");
    }

    @When("the user registers with an invalid email")
    public void the_user_registers_with_invalid_email() {
        exceptionHandler.expectException();
        try {
            client.register("toto.com", "azeaze");
        } catch (IllegalArgumentException e) {
            exceptionHandler.set(e);
        }
    }

    @When("the user registers with a less than 6 characters password")
    public void the_user_registers_with_a_less_than_6_characters_password() {
        exceptionHandler.expectException();
        try {
            client.register("toto@gmail.com", "aze");
        } catch (IllegalArgumentException e) {
            exceptionHandler.set(e);
        }
    }

    @Given("the following user exists :")
    public void the_following_user_exists(DataTable dataTable) throws Exception {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        for (Map<String,String> columns : rows) {
            client.register(columns.get("email"), columns.get("password"));
        }
    }

    @When("the user registers with {string} for the email")
    public void the_user_registers_with_for_email(String email) {
        message = client.register(email, "azeaze");
    }

    @When("the user try to log in with the following credentials: {string} and {string}")
    public void the_user_try_to_log_in_with_right_credentials(String email, String password){
        message = client.login(email, password);
    }

    @When("the user try to log in with the following wrong credentials: {string} and {string}")
    public void the_user_try_to_log_in_with_wrong_credentials(String email, String password){
        message = client.login(email, password);
    }
    
    @Then("the message {string} should be returned")
    public void the_message_should_be_displayed(String msg) {
        assertEquals(msg, message);
    }

    @Then("an exception with message {string} should be thrown")
    public void the_error_message_should_be_thrown(String msg) {
        assertEquals(msg, exceptionHandler.getException().getMessage());
    }

    @Given("the user is logged in")
    public void theUserIsLoggedIn() {
        client.setConnectedUser(new User(new ObjectId(), "tata@gmail.com", "azeaze"));
    }

    @When("the user searched for Easy")
    public void the_user_searched_for_easy(){
        client.search("Easy");
    }

    @When("the user saves a song to his playlist with no search results before")
    public void searchForEasyEmpty(){
        client.search("");
        exceptionHandler.expectException();
        try {
            client.saveSong(0);
        } catch (Exception e) {
            exceptionHandler.set(e);
        }

    }

    @When("the user saves a song to his playlist")
    public void the_user_add_a_song_to_is_playlist() {
        message = client.saveSong(0);
    }

    @When("the user saves the song with an out of range index")
    public void the_user_saves_with_an_out_of_range_index() {
        exceptionHandler.expectException();
        try {
            client.saveSong(10);
        } catch (Exception e) {
            exceptionHandler.set(e);
        }
        
    }

    @Given("the user's playlist is not empty")
    public void the_user_playlist_is_not_empty(){
        User tmp = client.getConnectedUser();
        tmp.setPlaylist(new Playlist());
        tmp.getPlaylist().add(new Song(new ObjectId(), "Easy", "Test", "test"));
        client.setConnectedUser(tmp);
    }

    @When("the user asks to see his playlist")
    public void the_user_asks_to_see_his_playlist(){
        message = client.displayPlaylist();
    }

    @Then("the user should see his playlist")
    public void the_user_should_see_his_playlist(){
        assertFalse(client.isConnectedUserPlaylistEmpty());
    }

    
  
}
