package com.musicfinder.cucumber.steps;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import com.musicfinder.Client;
import com.musicfinder.cucumber.states.ExceptionHandler;
import com.musicfinder.model.User;
import com.musicfinder.service.UserService;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserStepDefinitions {

    private Client client;
    private String message;

    @Given("the MusicFinder app is started")
    public void the_MusicFinder_app_is_started() {
        UserService userService = new UserService(new UserRepositoryImpl());
        client = new Client(userService);
    }

    @When("the user registers with valid credentials")
    public void the_user_registers_with_valid_credentials() {
        message = client.register("toto@gmail.com", "azeaze");
    }

    @When("the user registers with an invalid email")
    public void the_user_registers_with_invalid_email() {
        message = client.register("toto", "azeaze");
    }

    @When("the user registers with a less than 6 characters password")
    public void the_user_registers_with_a_less_than_6_characters_password() {
        message = client.register("toto@gmail.com", "aze");
    }

    @DataTableType
    public User authorEntryTransformer(Map<String, String> entry) {
        return new User(entry.get("email"), entry.get("password"));
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

    @Then("the message {string} should be displayed")
    public void the_message_should_be_displayed(String msg) {
        assertEquals(msg, message);
    }
}
