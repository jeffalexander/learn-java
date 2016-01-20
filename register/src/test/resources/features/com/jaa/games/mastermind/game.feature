Feature: Starting a game
  
  Background: New games
    Given a secret code
    When a new game is started
    
  Scenario: New games have a secret code
    Then the secret code is stored
    
  Scenario: New games have no history
    Then the game should have no history

  Scenario Outline: testing an attempt
    Given the secret code <secret>
    When the attempt is <attempt>
    Then the response has <responseRedCount> red and <responseWhiteCount> white key pins
    
  Scenarios:
    | secret 						| attempt 						| responseRedCount	| responseWhiteCount	|
    | BLUE, BLUE, BLUE, BLUE		| GREEN, GREEN, GREEN, GREEN	| 0					| 0						|
    | BLUE, BLUE, BLUE, BLUE		| BLUE, BLUE, BLUE, BLUE		| 4					| 0						|
    | BLUE, BLUE, GREEN, BLUE		| BLUE, GREEN, ORANGE, PURPLE	| 1 				| 1						|
    | PURPLE, BLUE, GREEN, ORANGE 	| BLUE, GREEN, ORANGE, PURPLE	| 0 				| 4						|
    