Feature: Sign up
  In order to use MusicFinder application as a user 
  I want to be able to register

  Background: Register command
  Given the MusicFinder app is started

  Scenario: User successful sign up
  When the user hits the command register with valid credentials
  Then the message "Thanks for joining MusicFinder! You can now login!" should be displayed

  Scenario: Invalid email
  When the user registers with an invalid email
  Then the message "Registration failed : Invalid email" should be displayed

  Scenario: Short password
  When the user registers with a less than 6 characters password
  Then the message "Registration failed : password has to contain at least 6 characters" should be displayed

  Scenario: Only one user account per email
  Given the following user exists :
  |email           | password |
  |salim@gmail.com | azeaze   |
  When the user registers with "salim@gmail.com" for the email
  Then the message "An account already exists for this email" should be displayed
