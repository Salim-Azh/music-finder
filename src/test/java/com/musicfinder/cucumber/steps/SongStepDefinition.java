package com.musicfinder.cucumber.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.musicfinder.Client;
import com.musicfinder.services.fetch.ItunesSongFetcher;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SongStepDefinition {

    Client client;
    ItunesSongFetcher sf;

    @Given("the user is signed in")
    public void the_user_is_signed_in() {
        client = new Client();
        //client.login("","");
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
