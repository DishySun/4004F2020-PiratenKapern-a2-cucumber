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
	Scenario: die with 1 skulls on second reroll
		Given a turn
		When fortune card is coin
		And first roll: 1 skull, 4 parrots, 3 swords
		And hold parrot
		And reroll: 1 skull, 2 monkey
		And lock all and reroll monkey
		And reroll: 1 skull, 1 monkey
		Then disqulified: 3 or more skulls
		
	@tag43
	Scenario: firstroll score 800 with captain, 2 diamon, 2 coin,
		Given a turn
		When fortune card is captain
		And first roll: 2 diamond, 2 coin, 4 others with no score
		And end turn
		Then score: 800
		
	@tag44
	Scenario: coin, firstroll: 2 monkey, reroll: 1 monkey
		Given a turn
		When fortune card is coin
		When first roll: 2 monkey, 6 others no score
		And hold monkey
		And reroll: 1 monkey, 2 parrot, 1 sword
		And end turn
		Then score: 200
		
	@tag45
	Scenario: coin, score 2 sets of 3 monkey, swords in RTS on first roll
		Given a turn
		When fortune card is coin
		And first roll: 3 monkey, 3 swords, 2 skull
		And end turn
		Then score: 300
		
	@tag46
	Scenario: score 2 sets of 3 monkey, parrots in RTS using 2 rolls
		Given a turn
		When fortune card is coin
		And first roll: 2 monkey, 2 parrot, 2 sword, 2 skull
		And hold monkey
		And hold parrot
		And reroll: 1 monkey, 1 parrot
		And end turn
		Then score: 300
		
	@tag47
	Scenario: score a set of 3 diamonds correctly
		Given a turn
		When fortune card is coin
		And first roll: 3 diamonds, 2 skull, 1 sword, 2 parrot
		And end turn
		Then score: 500
	
	@tag48
	Scenario: score a set of 4 coins correctly with FC is a diamond
		Given a turn
		When fortune card is diamond
		And first roll: 4 coin, 2 skull, 2 parrot
		And end turn
		Then score: 700
		
	@tag49
	Scenario: score set of 3 swords and set of 4 parrots correctly on first roll
		Given a turn
		When fortune card is coin
		And first roll: 3 sword, 4 parrot, 1 monkey
		And end turn
		Then score: 400
		
	@tag50
	Scenario: score set of 3 coins+ FC and set of 4 swords correctly over several rolls
		Given a turn
		When fortune card is coin
		And first roll: 2 coin, 2 sword, 2 parrot, 2 monkey
		And hold coin
		And hold sword
		And reroll: 1 coin, 1 monkey, 2 parrot
		And hold coin
		And hold sword
		And reroll: 1 sword, 2 monkey
		And hold coin
		And hold sword
		And reroll: 1 sword, 1 skull
		And end turn
		Then score: 800
		
	@tag51
	Scenario: same as previous row but with captain fortune card
	Given a turn
		When fortune card is captain
		And first roll: 2 coin, 2 sword, 2 parrot, 2 monkey
		And hold coin
		And hold sword
		And reroll: 1 coin, 1 monkey, 2 parrot
		And hold coin
		And hold sword
		And reroll: 1 sword, 2 monkey
		And hold coin
		And hold sword
		And reroll: 1 sword, 1 skull
		And end turn
		Then score: 1200
		
	@tag52
	Scenario: score set of 5 swords over 3 rolls
		Given a turn
		When fortune card is coin
		And first roll: 2 sword, 6 monkey
		And hold sword
		And reroll: 1 sword, 5 monkey
		And hold sword
		And reroll: 1 sword, 4 monkey
		And hold sword
		And reroll: 1 sword, 2 monkey, 1 parrot
		And end turn;
		Then score: 600
	
	@tag53
	Scenario: score set of 6 monkeys on first roll
		Given a turn
		When fortune card is coin
		And first roll: 2 sword, 6 monkey
		And end turn
		Then score: 1100
		
	@tag54
	Scenario: score set of 7 parrots on first roll
		Given a turn
		When fortune card is coin
		And first roll: 7 parrot, 1 skull
		And end turn
		Then score: 2100
		
	@tag55
	Scenario: score set of 8 coins on first roll
		Given a turn
		When fortune card is coin
		And first roll: 8 coin
		And end turn
		Then score: 5400
		
	@tag56
	Scenario: score set of 8 coins on first roll and FC is diamond 
		Given a turn
		When fortune card is diamond
		And first roll: 8 coin
		And end turn
		Then score: 5400
		
	@tag57
	Scenario: score set of 8 swords on first roll and FC is captain
		Given a turn
		When fortune card is captain
		And first roll: 8 sword
		And end turn
		Then score: 9000
		
	@tag58
	Scenario: score set of 8 monkeys over several rolls
		Given a turn
		When fortune card is coin
		And first roll: 4 monkey, 4 sword
		And hold monkey
		And reroll: 2 monkey, 2 parrot
		And hold monkey
		And reroll: 2 monkey
		And end turn
		Then score: 4600
		
	@tag59
	Scenario: score a set of 2 diamonds over 2 rolls with FC is diamond
		Given a turn
		When fortune card is diamond
		And first roll: 4 monkey, 4 sword
		And reroll: 2 diamond, 2 parrot, 2 skull, 2 monkey
		And end turn
		Then score: 400
		
	@tag60
	Scenario: score a set of 3 diamonds over 2 rolls
		Given a turn
		When fortune card is coin
		And first roll: 4 monkey, 4 sword
		And reroll: 3 diamond, 2 skull, 1 parrot, 2 monkey
		And end turn
		Then score: 500
		
	@tag61
	Scenario: score a set of 3 coins over 2 rolls
		Given a turn
		When fortune card is coin
		And first roll: 4 monkey, 4 sword
		And reroll: 3 coin, 2 skull, 1 parrot, 2 monkey
		And end turn
		Then score: 600
		
	@tag62
	Scenario: score a set of 3 coins over 2 rolls  with FC is diamond
		Given a turn
		When fortune card is diamond
		And first roll: 4 monkey, 4 sword
		And reroll: 3 coin, 2 skull, 1 parrot, 2 monkey
		And end turn
		Then score: 500
		
	@tag63
	Scenario: score a set of 4 monkeys and a set of 3 coins
		Given a turn
		When fortune card is coin
		And first roll: 4 monkey, 3 coin, 1 skull
		And end turn
		Then score: 800
		
	@tag65
	Scenario: get 7 swords on first roll, try to roll the 8 die by itself
		Given a turn
		When fortune card is coin
		And first roll: 7 sword, 1 monkey
		And hold sword
		Then reroll fail: roll at least 2 dice
		
	@tag70
	Scenario: roll 2 skulls, reroll one of them due to sorceress, then go to next round of turn
		Given a turn
		When fortune card is sorceress
		And first roll: 2 skull, 6 coin
		And unlock one of skulls
		And reroll: 7 sward
		Then only 1 skull in hand
		
	@tag71
	Scenario: roll no skulls, then next round roll 1 skull and reroll for it, then score 
		Given a turn
		When fortune card is sorceress
		And first roll: 2 sword, 6 monkey
		And hold monkey
		And reroll: 1 skull,1 coin
		And unlock one of skulls
		And reroll: 2 parrot
		And end turn
		Then score: 1000
		
	@tag72
	Scenario: roll no skulls, then next round roll 1 skull and reroll for it, then go to next round 
		Given a turn
		When fortune card is sorceress
		And first roll: 2 sword, 6 monkey
		And hold monkey
		And reroll: 1 skull,1 coin
		And unlock one of skulls
		And reroll: 2 parrot
		Then no skull in hand
		
	@tag75
	Scenario: first roll gets 3 monkeys 3 parrots  1 skull 1 coin
		Given a turn
		When fortune card is monkey business
		And first roll: 3 monkey, 3 parrot,  1 skull, 1 coin
		And end turn
		Then score: 1100
		
	@tag76
	Scenario: over several rolls: 2 monkeys, 1 parrot, 2 coins, 1 diamond, 2 swords
		Given a turn
		When fortune card is monkey business
		And first roll: 4 monkey, 4 sword
		And reroll: 2 monkey, 1 parrot, 2 coin, 1 diamond, 2 sword
		And end turn
		Then score: 400
	
	@tag77
	Scenario: over several rolls get 3 monkeys, 4 parrots, 1 sword
		Given a turn
		When fortune card is monkey business
		And first roll: 4 monkey, 4 sword
		And reroll: 2 monkey, 1 parrot, 2 coin, 1 diamond, 2 sword
		And reroll: 3 monkey, 4 parrot, 1 sword
		And end turn
		Then score: 2000
		
	@tag80
	Scenario: treasure chest scenario 1
		Given a turn
		When fortune card is treasure chest
		And first roll: 3 parrot, 2 sword, 2 diamond, 1 coin
		And stash: index 5 6 7
		And hold parrot
		And reroll: 2 parrot
		And stash: index 0 1 2 3 4
		And withdraw: index 0 1 2
		And reroll: 1 skull, 1 coin, 1 parrot
		And end turn
		Then score: 1100
		
	@tag85
	Scenario: treasure chest scenario 2
		Given a turn
		When fortune card is treasure chest
		And first roll:  2 skull, 3 parrot, 3 coin
		And stash: index 5 6 7
		And reroll: 2 diamond, 1 coin
		And stash: index 4
		And reroll: 1 skull, 1 coin
		And end turn;
		Then score: 600
		