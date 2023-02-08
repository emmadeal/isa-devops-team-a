Feature: showSocieties

  Background:
    Given a client logged in as "jean@hotmail.fr" with password "mdp"

  Scenario: show the list of society not empty
    Given a partner and his society named "Boulangerie"
    When the client go to the society list
    Then the client see "Boulangerie"
    Then there is 1 society

  Scenario: show the list of society empty
    When the client go to the society list
    Then there is 0 society
