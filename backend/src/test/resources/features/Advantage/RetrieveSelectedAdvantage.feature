Feature: retrieve selected advantage

  Background:
    Given a society name "Tisanerie"
    And a Advantage avaible at the same shop
    And a customer who is choosing an advantage from the shop

  Scenario: the customer retrieve the advantage
    Given a customer who retrieved his advantage
    Then the advantage is set to Retrived status


  Scenario: the customer retrieve the advantage not available
    Given a customer who retrieved his advantage one time
    Then a customer who retrieved his advantage two time and there is failure