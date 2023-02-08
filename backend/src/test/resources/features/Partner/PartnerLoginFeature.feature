Feature: partner log in

  Scenario: login success
    Given a partner named "Serge", with email "serge@yahoo.fr" and password "ilovecats"
    When he sign in
    Then the log in is sucessful

  Scenario: login failure
    Given a partner named "Serge", with email "serge@yahoo.fr" and password "ilovecats"
    Then the log in unsucessful
