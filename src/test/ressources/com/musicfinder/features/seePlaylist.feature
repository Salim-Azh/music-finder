@SeePlaylist
Feature: See Playlist
    As a user
    I want to be able to see what is in my playlist
    
    Background: Connected
        Given the MusicFinder app is started
        And the user is logged in
    
    Scenario: Playlist is not empty
        Given the user's playlist is not empty
        When the user asks to see his playlist
        Then the user should see his playlist

    Scenario: Playlist is empty
        When the user asks to see his playlist
        Then the message "Your playlist is empty..." should be returned

        