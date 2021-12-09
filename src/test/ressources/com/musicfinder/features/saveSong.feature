@AddSongs
Feature: Add songs
    As a user
    I want to be able to add a song to my playlist
    After i have searched for it

    Background: Adding a song
        Given the user is signed in
        When the user researched for "Easy" 

    Scenario: Success save to playlist
        When the user saves a song to his playlist
        Then the song should appear in the playlist
    
    Scenario: Out of range index
        When the user saves the song at an out of range index
        Then the message should the
    
