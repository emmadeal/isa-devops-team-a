Feature: update society informations

  Background:
    Given a society called "Fleuriste" opening at 8:00 and closing at 18:00, located in "Cannes" is created

    Scenario: update society's location
      When the partner change the location of the society to "Nice"
      Then the society "Fleuriste" is located in "Nice"

    Scenario: update society's opening time
      When the partner change the opening time of the society to 9:00
      Then the society "Fleuriste" is open at 9:00
      And a notification is sent

    Scenario: update society's closing time
      When the partner change the closing time of the society to 19:00
      Then the society "Fleuriste" is closed at 19:00
      And a notification is sent