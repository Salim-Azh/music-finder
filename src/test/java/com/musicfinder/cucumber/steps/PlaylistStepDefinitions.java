package com.musicfinder.cucumber.steps;

import com.musicfinder.Client;
import com.musicfinder.repository.UserRepositoryImpl;
import com.musicfinder.service.UserService;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PlaylistStepDefinitions {

    private Client client;
    
    @Given("the result of the search")
    public void the_result_of_the_search() throws IllegalAccessException {
        UserService userService = new UserService(new UserRepositoryImpl());
        client = new Client(userService);
        //client.login("","");
        client.search("Easy");
        if(client.getFetchedSongs().size()==0){
            throw new IllegalAccessException("There's no song");
        }
    }

    @When("the user add a song to is playlist")
    public void the_user_add_a_song_to_is_playlist() {
        client.saveSong(client.getFetchedSongs().get(0));
    }

    @Then("the song should appear in the playlist")
    public void the_song_should_appear_in_the_playlist() {
        client.showPlaylist(user);
    } 
}
