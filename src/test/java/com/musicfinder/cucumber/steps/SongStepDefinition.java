package com.musicfinder.cucumber.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.musicfinder.Client;
import com.musicfinder.repository.UserRepositoryImpl;
import com.musicfinder.service.PlaylistService;
import com.musicfinder.service.UserService;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SongStepDefinition {

    private Client client;

    @Given("the user is signed in")
    public void the_user_is_signed_in() {
        UserService userService = new UserService(new UserRepositoryImpl());
        PlaylistService playlistService = new PlaylistService(new UserRepositoryImpl());
        client = new Client(userService, playlistService);
        client.login("","");
    }

    @When("the user researched with an empty string")
    public void the_user_researched_for_hello() {
        client.search("");
    }

    @When("the user researched for \"Easy\"")
    public void the_user_researched_for_easy() {
        client.search("Easy");
    }

    @Then("the list of songs should be empty")
    public void the_user_should_see_no_songs(){
        assertEquals(0,client.getFetchedSongs().size());
    }

    @Then("the list of songs should not be empty")
    public void the_user_should_see_found_songs(){
        assertNotEquals(0, client.getFetchedSongs().size());
    }

}
