@tag
Feature: networked game test

  @tag106
  Scenario: deduction from skull island will not make others score negative
    Given a game
    When current player score -500 in skull island
    Then other players remain 0 score even their score is less than 500
    
  @tag112
  Scenario: deduction from sea battle will not make others score negative
  	Given a game
  	When current player score -500 in sea battle
  	Then current player remain 0 score even his/her score is less than 500
  	
	@tag125
	Scenario: game starts, each player plays a turn with scores being updated correctly 
		Given a game
		When current player score 500
		Then current player have 500 score total
		When current player score 500
		Then current player have 1000 score total
		When current player score -500
		Then current player have 500 score total
		When current player score -500 in skull island
		Then current player have 500 score total
		When current player score 1000
		Then current player have 1500 score total
		When current player score 1000
		Then current player have 2500 score total
		
		@127-131
		Scenario: game starts, each player plays a turn with scores being updated correctly 
		Given a game
		When current player score 500
		Then current player have 500 score total
		And no winner yet
		When current player score 500
		Then current player have 1000 score total
		And no winner yet
		When current player score -500
		Then current player have 500 score total
		And no winner yet
		When current player score -500 in skull island
		Then current player have 500 score total
		And no winner yet
		When current player score 1000
		Then current player have 1500 score total
		And no winner yet
		When current player score 1000
		Then current player have 2500 score total
		And no winner yet
		When current player score 10000
		Then no winner yet
		And current player reach winning score
		When next turn
		Then no winner yet
		When next turn
		Then have winner