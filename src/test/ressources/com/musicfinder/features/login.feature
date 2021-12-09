Feature: Log in
  In order to use MusicFinder application as a user 
  I want to be able to log into the system

  Background: Login command
  Given the MusicFinder app is started

  Scenario: User successfully logs in
  Given the following user exists :
  |email           | password |
  |salim@gmail.com | azeaze   |
  When the user try to log in with the following credentials: "salim@gmail.com" and "azeaze"
  Then the message "Successfully logged in!" should be returned

  Scenario: User enters an invalid password
  Given the following user exists :
  |email           | password |
  |salim@gmail.com | azeaze   |
  When the user try to log in with the following wrong credentials: "salim@gmail.com" and "a"
  Then an exception with message "Invalid email or password..." should be thrown
  
  Scenario: User enters an invalid email
  Given the following user exists :
  |email           | password |
  |salim@gmail.com | azeaze   |
  When the user try to log in with the following wrong credentials: "salim@gmail" and "azeaze"
  Then an exception with message "Invalid email or password..." should be thrown
