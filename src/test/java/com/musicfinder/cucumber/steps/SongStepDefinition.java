package com.musicfinder.cucumber.steps;

import static org.junit.Assert.assertEquals;

import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SongStepDefinition {
    @Given("the user is signed in")
    public void the_user_is_signed_in() {
        //User.login("TEST","TEST");
        //throw new io.cucumber.java.PendingException();
    }

    @Given("the user hits the search song button")
    public void the_user_hits_search_button() {
        //SongFinder sf = new SongFinder();
    }

    @Given("the user researched for \"Hello\"")
    public void the_user_researched_for_hello() {
        //assertEquals(null, sf.find("Hello"));
    }

    @Given("the user researched for \"Easy\"")
    public void the_user_researched_for_easy() {
        //assertEquals(null, sf.find("Easy"));
    }

    @Given("the available songs are")
    public void the_available_songs_are(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        
        List<String> songList = dataTable.column(2);
        throw new io.cucumber.java.PendingException();
    }

    @Then("the user should see a \"No matching song found\" warning")
    public void the_user_should_see_no_songs_found_warning(){
        System.out.println("No matching sound found");
    }

    @Then("the user should see \"Matching song found: Easy by Troye, Easy by OtherArtist\"")
    public void the_user_should_see_found_songs(){
        System.out.println("Matching song found: Easy by Troye, Easy by OtherArtist");
    }

}
