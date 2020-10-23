@tag
Feature: basic dying and scoring test

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
		And first roll: 2 skulls, 4 parrot, 2 sword
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
	Scenario: sorceress test: roll 2 skulls, reroll one of them due to sorceress, then go to next round of turn
		Given a turn
		When fortune card is sorceress
		And first roll: 2 skull, 6 coin
		And unlock one of skulls
		And reroll: 7 sward
		Then only 1 skull in hand
		
	@tag71
	Scenario: sorceress test: roll no skulls, then next round roll 1 skull and reroll for it, then score 
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
	Scenario: sorceress test: roll no skulls, then next round roll 1 skull and reroll for it, then go to next round 
		Given a turn
		When fortune card is sorceress
		And first roll: 2 sword, 6 monkey
		And hold monkey
		And reroll: 1 skull,1 coin
		And unlock one of skulls
		And reroll: 2 parrot
		Then no skull in hand
		
	@tag75
	Scenario: monkey business test: first roll gets 3 monkeys 3 parrots  1 skull 1 coin
		Given a turn
		When fortune card is monkey business
		And first roll: 3 monkey, 3 parrot,  1 skull, 1 coin
		And end turn
		Then score: 1100
		
	@tag76
	Scenario: monkey business test: over several rolls: 2 monkeys, 1 parrot, 2 coins, 1 diamond, 2 swords
		Given a turn
		When fortune card is monkey business
		And first roll: 4 monkey, 4 sword
		And reroll: 2 monkey, 1 parrot, 2 coin, 1 diamond, 2 sword
		And end turn
		Then score: 400
	
	@tag77
	Scenario: monkey business test: over several rolls get 3 monkeys, 4 parrots, 1 sword
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
		
	@tag91
	Scenario: full chest 1: 3 monkeys, 3 swords, 1 diamond, 1 parrot
		Given a turn
		When fortune card is coin
		And first roll: 3 monkey, 3 sword, 1 diamond, 1 parrot
		And end turn
		Then score: 400
		
	@tag92
	Scenario: full chest 2: 3 monkeys, 3 swords, 2 coins FC: captain
		Given a turn
		When fortune card is captain
		And firstroll: 3 monkey, 3 sword, 2 coin
		And end turn
		Then score: 1800
		
	@tag93
	Scenario: full chest 3: 3 monkeys, 4 swords, 1 diamond, FC: coin
		Given a turn
		When fortune card is coin
		And first roll: 3 monkey, 4 sword, 1 diamond
		And end turn
		Then score: 1000
		
	@tag94
	Scenario: full chest 4
		Given a turn
		When fortune card is sea battle 2
		And first roll: 4 monkey, 1 sword, 2 parrot, 1 coin
		And hold monkey
		And hold sword
		And hold coin
		And reroll: 1 sword, 1 coin
		And end turn
		Then score: 1200
		
	@tag97
	Scenario: full chest 5: FC: monkey business and RTS: 2 monkeys, 1 parrot, 2 coins, 3 diamonds
		Given a turn
		When fortune card is monkey business
		And first roll: 2 monkey, 1 parrot, 2 coin, 3 diamond
		And end turn
		Then score: 1200
		
	@tag100
	Scenario: die by rolling one skull and having a FC with two skulls
		Given a turn
		When fortune card is skull 2
		And first roll: 1 skull, 4 parrots, 3 swords
		Then disqulified: 3 or more skulls
		
	@tag101
	Scenario: die by rolling 2 skulls and having a FC with 1 skull
		Given a turn
		When fortune card is skull 1
		And first roll: 2 skulls, 6 coin
		Then disqulified: 3 or more skulls
		
	@tag102
	Scenario: roll 5 skulls with FC: Captain
		Given a turn
		When fortune card is captain
		And first roll: 5 skull, 3 coin
		And end turn
		Then -1000 for all other players
		
	@103
	Scenario: roll 2 skulls AND have a FC with two skulls: roll 2 skulls next roll, then 1 skull
		Given a turn
		When fortune card is skull 2
		And first roll: 2 skulls, 4 parrot, 2 sword
		And reroll: 2 skull, 4 parrot
		And reroll: 1 skull, 3 sword
		And end turn
		Then other score
		And score: -700
		
	@tag104
	Scenario: roll 3 skulls AND have a FC with two skulls: roll no skulls next roll
		Given a turn
		When fortune card is skull 2
		And first roll: 3 skulls on firstroll
		Then continue on first roll
		When reroll: 5 coin
		Then disqulified: did not roll skull in skull island
		When end turn
		Then other score
		And score: -500
		
	@tag105
	Scenario: roll 3 skulls AND have a FC with 1 skull: roll 1 skull next roll then none
		Given a turn
		When fortune card is skull 1
		And first roll: 3 skulls on firstroll
		Then continue on first roll
		When reroll: 1 skull, 4 parrot
		And reroll: 4 monkey
		Then disqulified: did not roll skull in skull island
		When end turn
		Then other score
		And score: -500
		
	@tag109
	Scenario: FC 2 swords, die on first roll
		Given a turn
		When fortune card is sea battle 2
		And first roll: 3 skulls on firstroll
		Then disqulified after firstroll
		When end turn
		Then self score
		And score: -300
		
	@tag110
	Scenario: FC 3 swords, die on first roll
		Given a turn
		When fortune card is sea battle 3
		And first roll: 3 skulls on firstroll
		Then disqulified after firstroll
		When end turn
		Then self score
		And score: -500
		
	@tag111
	Scenario: FC 4 swords, die on first roll
		Given a turn
		When fortune card is sea battle 4
		And first roll: 3 skulls on firstroll
		Then disqulified after firstroll
		When end turn
		Then self score
		And score: -1000

	@tag113
	Scenario: FC 2 swords, roll 3 monkeys 2 swords, 1 coin, 2 parrots  SC = 100 + 100 + 300 = 500
		Given a turn
		When fortune card is sea battle 2
		And first roll: 3 monkey, 2 sword, 1 coin, 2 parrot
		And end turn
		Then self score
		And score: 500
		
	@tag114
	Scenario: FC 2 swords with reroll
		Given a turn
		When fortune card is sea battle 2
		And first roll: 4 monkey, 1 sword, 1 skull, 2 parrot
		And hold monkey
		And hold sword
		And reroll: 1 sword, 1 skull
		And end turn
		Then self score
		And score: 500
		
	@tag116
	Scenario: FC 3 swords, roll 3 monkeys 4 swords  SC = 100 + 200 + 500 = 800
		Given a turn
		When fortune card is sea battle 3
		And first roll: 3 monkey, 4 sword, 1 skull
		And end turn
		Then self score
		And score: 800
		
	@tag117
	Scenario: FC 3 swords with reroll
		Given a turn
		When fortune card is sea battle 3
		And first roll: 4 monkey, 2 sword, 2 skull
		And hold sword
		And reroll: 2 sword, 2 skull
		Then disqulified: 3 or more skulls
		When end turn
		Then self score
		And score: -500
		
	@tag119
	Scenario: FC 4 swords, roll 3 monkeys 4 swords 1 skull  SC = 100 +200 + 1000 = 1300
		Given a turn
		When fortune card is sea battle 4
		And first roll: 3 monkey, 4 sword, 1 skull
		And end turn
		Then self score
		And score: 1300
		
	@tag120
	Scenario: FC 4 swords with reroll
		Given a turn
		When fortune card is sea battle 4
		And first roll: 3 monkey, 1 sword, 1 skull, 1 diamond, 2 parrot
		And hold monkey
		And hold sword
		And hold diamond
		And reroll: 2 sword
		And unhold all
		And hold sword
		And hold diamond
		And reroll: 1 sword, 2 parrot
		And end turn
		Then self score
		And score: 1300