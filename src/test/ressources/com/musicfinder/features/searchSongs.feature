@SearchSongs
Feature: Search Songs
    As a user
    In order to add songs to my playlist
    I want to be able to search songs given their title or artist name
    
    Background: Search Mode
        Given the user is signed in
        And the user hits the search song button
    
    Scenario: No Matching Songs Found
        Given the available songs are 
        | Song | Title | Artist |
        | Song1  | Test | Test  |
        | Song2  | Easy | Troye  |
        | Song3  | Easy | OtherArtist  |
        When the user researched for "Hello"
        Then the user should see a "No matching song found" warning

    Scenario: Matching Songs Found
        Given the available songs are 
        | Song | Title | Artist |
        | Song1  | Test | Test  |
        | Song2  | Easy | Troye  |
        | Song3  | Easy | OtherArtist  |
        When the user researched for "Easy"
        Then the user should see "Matching song found: Easy by Troye, Easy by OtherArtist"

        