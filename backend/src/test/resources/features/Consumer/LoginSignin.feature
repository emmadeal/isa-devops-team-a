Feature: Consumer login and signin

  Background:
    Given there is a consumer with email "email@gmail.com" and password "password" in database

  Scenario: consumer try to signin
    When the consumer signin
    Then there is 1 consumer in the database

  Scenario: new consumer try to login
    When a new consumer login with email "newEmail@gmail.com" and password "newPassword"
    Then there is 1 consumer in the database

  Scenario: new consumer signin
    When a new consumer signin with email "newEmail@gmail.com" and password "newPassword"
    Then there is 2 consumer in the database

