Feature: create a partner with his society

  Scenario: creation partner
    When the mayor creates a new partner "Starbucks" with email "s@gmail.com"
    Then the partner "Starbucks" is present in the repository
    Then the mayor creates a new partner "Starbucks" with email "marie@gmail.com" and thie is failure


  Scenario: creation partner impossible because already email exists
    When the mayor creates a new partner "Starbucks" with email "s@gmail.com"
    Then the mayor doesnt creates the new partner "Starbucks2" with email "s@gmail.com"
    Then the partner "Starbucks2" is not present in the repository

  Scenario: creation society for already existing partner
    Given a partner "Starbucks"
    When the mayor creates a new society called "S1" for the partner "Starbucks"
    Then the society is present in the repository
    Then the society is linked to the partner
    Then the mayor creates a new society called "S1" for the partner "Starbucks" there is failure