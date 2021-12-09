package com.musicfinder.cucumber.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.musicfinder.Client;
import com.musicfinder.repository.UserRepositoryImpl;
import com.musicfinder.service.UserService;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PlaylistStepDefinitions {

    private Client client;
  

    @When("the user adds a song to his playlist")
    public void the_user_add_a_song_to_is_playlist() {
        client.saveSong(0);
    }

    @Then("the playlist should not be empty")
    public void the_song_should_appear_in_the_playlist() {
       assertTrue(!client.getConnectedUserPlaylist().isEmpty());
    }
    
}
