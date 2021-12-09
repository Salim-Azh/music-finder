@AddSongs
Feature: Add songs
    As a user
    I want to be able to add a song to my playlist
    After i have searched for it

    Background: Adding a song
        Given the user is signed in
        And the user researched for "Easy" 

    Scenario: Song added to playlist
        Given the result of the search
        When the user add a song to is playlist
        Then the song should appear in the playlist 