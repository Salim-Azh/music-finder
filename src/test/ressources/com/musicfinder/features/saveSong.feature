@AddSongs
Feature: Add songs
    As a user
    I want to be able to add a song to my playlist
    in order to save it

    Background: Adding a song
        Given the MusicFinder app is started
        And the user registers with valid credentials
        And the user is logged in

    Scenario: Success save to playlist
        When the user search for Easy
        And the user saves a song to his playlist
        Then the playlist should not be empty
        And the message "Song saved to playlist" should be returned
    
    Scenario: Out of range index
        When the user searched for Easy
        And the user saves the song with an out of range index
        Then an exception with message "No such song exist the index shoud be between 0 and 9" should be thrown
    
    Scenario: No result from search
        When the user searched with an empty string
        And the user saves the song with an out of range index
        Then an exception with message "No results from search you must search for a song before saving it to playlist" should be thrown

    
