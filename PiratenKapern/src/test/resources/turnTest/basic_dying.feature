@tag
Feature: basic dying test

  @tag38
  Scenario: die with 3 skulls on first roll
    Given a turn
    When fortune card is coin
    And first roll: 3 skulls on firstroll
    Then disqulified after firstroll
    
	@tag39
  Scenario: die with 2 skulls on second roll
  	Given a turn
  	When fortune card is coin
  	And first roll: 1 skull, 4 parrots, 3 swords
  	And hold parrot
  	And reroll: 2 skull, 1 sword
  	Then disqulified: 3 or more skulls
  	
	@tag40
	Scenario: die with 1 skulls on second roll
		Given a turn
		When fortune card is coin
		And first roll: 2 skulls, 4 parrots, 2 swords
		And hold parrot
		And reroll: 1 skull, 1 sword
		Then disqulified: 3 or more skulls
		
	@tag41
	Scenario: die with 1 skulls on second roll