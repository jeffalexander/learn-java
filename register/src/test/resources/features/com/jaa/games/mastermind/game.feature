Feature: Starting a game
  
  Scenario: New games have a secret code
    Given a secret code
    When a new game is started
    Then the secret code is stored
    
  Scenario: New games have no history
    Given a secret code
    When a new game is started
    Then the game should have no history
