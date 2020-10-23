package cucumberTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;

import cucumber.annotation.en.*;
import entity.Dice;
import game.ChestException;
import game.OneTurnScoreChange;
import game.Turn;

public class BasicDyingAndScoringTest {
	private Turn turn;
	private ArrayList<Dice> dice;
	
	private void add(String diceStr, int number) {
		for (int i = 0; i < number; i++) {
			dice.add(new Dice(diceStr));
		}
	}
	
	@Given("a turn")
	public void newTurn() {
		turn = new Turn();
		dice = new ArrayList<Dice>();
	}
	
	//fortune card
	@When("fortune card is treasure chest")
	public void cardTC() {
		turn.setCard(new entity.FortuneCard.TreasureChest());
	}
	
	@When("fortune card is captain")
	public void cardCaptain() {
		turn.setCard(new entity.FortuneCard.Captain());
	}
	
	@When("fortune card is sorceress")
	public void cardSorceress() {
		turn.setCard(new entity.FortuneCard.Sorceress());
	}
	
	@When("fortune card is sea battle 2")
	public void cardSB2() {
		turn.setCard(new entity.FortuneCard.SeaBattle(2));
	}
	
	@When("fortune card is sea battle 3")
	public void cardSB3() {
		turn.setCard(new entity.FortuneCard.SeaBattle(3));
	}
	
	@When("fortune card is sea battle 4")
	public void cardSB4() {
		turn.setCard(new entity.FortuneCard.SeaBattle(4));
	}
	
	@When("fortune card is coin")
	public void cardCoin() {
		turn.setCard(new entity.FortuneCard.Gold());
	}
	
	@When("fortune card is diamond")
	public void cardDiamond() {
		turn.setCard(new entity.FortuneCard.Diamond());
	}
	
	@When("fortune card is monkey business")
	public void cardMB() {
		turn.setCard(new entity.FortuneCard.MonkeyBusiness());
	}
	
	@When("fortune card is skull 1")
	public void cardSkull1() {
		turn.setCard(new entity.FortuneCard.Skull(1));
	}
	
	@When("fortune card is skull 2")
	public void cardSkull2() {
		turn.setCard(new entity.FortuneCard.Skull(2));
	}
	
	//hold
	@When("hold monkey")
	public void holdMonkey() {
		turn.hold(Dice.Face.MONKEY);
	}
	
	@When("hold parrot")
	public void holdParrot() {
		turn.hold(Dice.Face.PARROT);
	}
	
	@When("hold sword")
	public void holdSword() {
		turn.hold(Dice.Face.SWORD);
	}
	
	@When("hold coin")
	public void holdCoin() {
		turn.hold(Dice.Face.COIN);
	}
	
	@When("hold diamond")
	public void holdDiamond() {
		turn.hold(Dice.Face.DIAMOND);
	}
	
	//actions
	@When("end turn")
	public void endTurn() {
		turn.endTurn();
	}
	
	//38
	@When("first roll: 3 skulls on firstroll")
	public void roll3SkullsOnFirstRoll() {
		add("sk", 3);
		add("mo", 3);
		add("pa", 2);
	}
	
	@Then("disqulified after firstroll")
	public void disqulifiedAfterFirstRoll38() {
		assertTrue(turn.firstRoll(dice));
	}
	
	//39
	@When("first roll: 1 skull, 4 parrots, 3 swords")
	public void firstRoll_39() {
		dice.add(new Dice("sk"));
		add("pa", 4);
		add("sw",3);
		turn.firstRoll(dice);
	}
	
	@When("reroll: 2 skull, 1 sword")
	public void reroll_39_1() {
		dice.clear();
		add("sk",2);
		dice.add(new Dice("sw"));
	}
	
	@Then("disqulified: 3 or more skulls")
	public void then_39() {
		assertEquals(1,turn.reroll(dice));
	}
	
	//40
	@When("first roll: 2 skulls, 4 parrot, 2 sword")
	public void firstRoll_40() {
		add("sk", 2);
		add("pa", 4);
		add("sw", 2);
		turn.firstRoll(dice);
	}
	
	@When("reroll: 1 skull, 1 sword")
	public void reroll_40_1() {
		dice.clear();
		dice.add(new Dice("sk"));
		dice.add(new Dice("sw"));
	}
	
	//41
	@When("reroll: 1 skull, 2 monkey")
	public void reroll_41_1() {
		dice.clear();
		dice.add(new Dice("sk"));
		dice.add(new Dice("mo"));
		dice.add(new Dice("mo"));
		assertEquals(0,turn.reroll(dice));
	}
	
	@When("lock all and reroll monkey")
	public void lockAllRerollMonkey() {
		turn.lockAll();
		turn.unhold(Dice.Face.MONKEY);
	}
	
	@When("reroll: 1 skull, 1 monkey")
	public void reroll_41_2() {
		dice.clear();
		dice.add(new Dice("sk"));
		dice.add(new Dice("mo"));
	}
	
	//43
	@When("first roll: 2 diamond, 2 coin, 4 others with no score")
	public void firstRoll_42() {
		dice.add(new Dice("di"));
		dice.add(new Dice("di"));
		dice.add(new Dice("co"));
		dice.add(new Dice("co"));
		dice.add(new Dice("pa"));
		dice.add(new Dice("pa"));
		dice.add(new Dice("mo"));
		dice.add(new Dice("mo"));
		turn.firstRoll(dice);
	}
	
	@Then("score: 800")
	public void score800() {
		assertEquals(OneTurnScoreChange.Range.SELF, turn.getDelta().getRange());
		assertEquals(800, turn.getDelta().getChange());
	}
	
	//44
	@When("first roll: 2 monkey, 6 others no score")
	public void firstRoll_44() {
		dice.add(new Dice("mo"));
		dice.add(new Dice("mo"));
		dice.add(new Dice("sk"));
		dice.add(new Dice("sk"));
		dice.add(new Dice("pa"));
		dice.add(new Dice("pa"));
		dice.add(new Dice("sw"));
		dice.add(new Dice("sw"));
		turn.firstRoll(dice);
	}
	
	@When("reroll: 1 monkey, 2 parrot, 1 sword")
	public void reroll_44_1(){
		dice.clear();
		dice.add(new Dice("mo"));
		dice.add(new Dice("pa"));
		dice.add(new Dice("pa"));
		dice.add(new Dice("sw"));
		assertEquals(0, turn.reroll(dice));
	}
	
	@Then("score: 200")
	public void score200() {
		assertEquals(OneTurnScoreChange.Range.SELF, turn.getDelta().getRange());
		assertEquals(200, turn.getDelta().getChange());
	}
	
	//45
	@When("first roll: 3 monkey, 3 swords, 2 skull")
	public void firstRoll_45() {
		add("mo", 3);
		add("sw", 3);
		add("sk", 2);
		turn.firstRoll(dice);
	}
	
	@Then("score: 300")
	public void score300() {
		assertEquals(OneTurnScoreChange.Range.SELF, turn.getDelta().getRange());
		assertEquals(300, turn.getDelta().getChange());
	}
	
	//46
	@When("first roll: 2 monkey, 2 parrot, 2 sword, 2 skull")
	public void firstRoll_46() {
		dice.add(new Dice("mo"));
		dice.add(new Dice("mo"));
		dice.add(new Dice("pa"));
		dice.add(new Dice("pa"));
		dice.add(new Dice("sw"));
		dice.add(new Dice("sw"));
		dice.add(new Dice("sk"));
		dice.add(new Dice("sk"));
		turn.firstRoll(dice);
	}
	
	@When("reroll: 1 monkey, 1 parrot")
	public void reroll_46_1() {
		dice.clear();
		dice.add(new Dice("mo"));
		dice.add(new Dice("pa"));
		assertEquals(0, turn.reroll(dice));
	}
	
	//47
	@When("first roll: 3 diamonds, 2 skull, 1 sword, 2 parrot")
	public void firstRoll_47() {
		add("di", 3);
		add("sk", 2);
		add("sw", 1);
		add("pa", 2);
		turn.firstRoll(dice);
	}
	
	@Then("score: 500")
	public void score500() {
		assertEquals(OneTurnScoreChange.Range.SELF, turn.getDelta().getRange());
		assertEquals(500, turn.getDelta().getChange());
	}
	
	//48
	@When("first roll: 4 coin, 2 skull, 2 parrot")
	public void firstRoll_48() {
		add("co", 4);
		add("sk", 2);
		add("pa", 2);
		turn.firstRoll(dice);
	}
	
	@Then("score: 700")
	public void score700() {
		assertEquals(OneTurnScoreChange.Range.SELF, turn.getDelta().getRange());
		assertEquals(700, turn.getDelta().getChange());
	}
	
	//49
	@When("first roll: 3 sword, 4 parrot, 1 monkey")
	public void firstRoll_49() {
		add("sw", 3);
		add("pa", 4);
		add("mo", 1);
		turn.firstRoll(dice);
	}
	
	@Then("score: 400")
	public void score400() {
		assertEquals(OneTurnScoreChange.Range.SELF, turn.getDelta().getRange());
		assertEquals(400, turn.getDelta().getChange());
	}
	
	//50
	@When("first roll: 2 coin, 2 sword, 2 parrot, 2 monkey")
	public void firstRoll_50() {
		add("co", 2);
		add("sw", 2);
		add("pa", 2);
		add("mo", 2);
		turn.firstRoll(dice);
	}
	
	@When("reroll: 1 coin, 1 monkey, 2 parrot")
	public void reroll_50_1() {
		dice.clear();
		add("co", 1);
		add("mo", 1);
		add("pa", 2);
		assertEquals(0, turn.reroll(dice));
	}
	
	@When("reroll: 1 sword, 2 monkey")
	public void reroll_50_2() {
		dice.clear();
		add("sw", 1);
		add("mo", 2);
		assertEquals(0, turn.reroll(dice));
	}
	
	@When("reroll: 1 sword, 1 skull")
	public void reroll_50_3() {
		dice.clear();
		add("sw", 1);
		add("sk", 1);
		assertEquals(0, turn.reroll(dice));
	}
	
	//52
	@When("first roll: 2 sword, 6 monkey")
	public void firstRoll_52() {
		add("sw", 2);
		add("mo", 6);
		turn.firstRoll(dice);
	}
	
	@When("reroll: 1 sword, 5 monkey")
	public void reroll_52_1() {
		dice.clear();
		add("sw", 1);
		add("mo", 5);
		assertEquals(0, turn.reroll(dice));
	}
	
	@When("reroll: 1 sword, 4 monkey")
	public void reroll_52_2() {
		dice.clear();
		add("sw", 1);
		add("mo", 4);
		assertEquals(0, turn.reroll(dice));
	}

	@When("reroll: 1 sword, 2 monkey, 1 parrot")
	public void reroll_52_3() {
		dice.clear();
		add("sw", 1);
		add("mo", 2);
		add("pa", 1);
		assertEquals(0, turn.reroll(dice));
	}
	
	@Then("score: 600")
	public void score600() {
		assertEquals(OneTurnScoreChange.Range.SELF, turn.getDelta().getRange());
		assertEquals(600, turn.getDelta().getChange());
	}
	
	//53
	@Then("score: 1100")
	public void score1100() {
		assertEquals(OneTurnScoreChange.Range.SELF, turn.getDelta().getRange());
		assertEquals(1100,turn.getDelta().getChange());
	}
	
	//54
	@When("first roll: 7 parrot, 1 skull")
	public void firstRoll_54() {
		add("pa", 7);
		add("sk" ,1);
		turn.firstRoll(dice);
	}
	
	@Then("score: 2100")
	public void score2100() {
		assertEquals(OneTurnScoreChange.Range.SELF, turn.getDelta().getRange());
		assertEquals(2100, turn.getDelta().getChange());
	}
	
	//55
	@When("first roll: 8 coin")
	public void firstRoll_55() {
		add("co", 8);
		turn.firstRoll(dice);
	}
	
	@Then("score: 5400")
	public void score5400() {
		assertEquals(OneTurnScoreChange.Range.SELF, turn.getDelta().getRange());
		assertEquals(5400, turn.getDelta().getChange());
	}
	
	//57
	@When("first roll: 8 sword")
	public void firstRoll_57() {
		add("sw", 8);
		turn.firstRoll(dice);
	}
	
	@Then("score: 9000")
	public void score9000() {
		assertEquals(OneTurnScoreChange.Range.SELF, turn.getDelta().getRange());
		assertEquals(9000, turn.getDelta().getChange());
	}
	
	//58
	@When("first roll: 4 monkey, 4 sword")
	public void firstRoll_58() {
		add("mo", 4);
		add("sw", 4);
		turn.firstRoll(dice);
	}
	
	@When("reroll: 2 monkey, 2 parrot")
	public void reroll_58_1() {
		dice.clear();
		add("mo", 2);
		add("pa", 2);
		assertEquals(0, turn.reroll(dice));
	}
	
	@When("reroll: 2 monkey")
	public void reroll_58_2() {
		dice.clear();
		add("mo", 2);
		assertEquals(0, turn.reroll(dice));
	}
	
	@Then("score: 4600")
	public void score4600() {
		assertEquals(OneTurnScoreChange.Range.SELF, turn.getDelta().getRange());
		assertEquals(4600, turn.getDelta().getChange());
	}
	
	//59
	@When("reroll: 2 diamond, 2 parrot, 2 skull, 2 monkey")
	public void reroll_59() {
		dice.clear();
		add("di", 2);
		add("pa", 2);
		add("sk", 2);
		add("mo", 2);
		assertEquals(0, turn.reroll(dice));
	}
	
	//60
	@When("reroll: 3 diamond, 2 skull, 1 parrot, 2 monkey")
	public void reroll_60() {
		dice.clear();
		add("di", 3);
		add("sk", 2);
		add("pa", 1);
		add("mo", 2);
		assertEquals(0, turn.reroll(dice));
	}
	
	//61
	@When("reroll: 3 coin, 2 skull, 1 parrot, 2 monkey")
	public void reroll_61() {
		dice.clear();
		add("co", 3);
		add("sk", 2);
		add("pa", 1);
		add("mo", 2);
		assertEquals(0, turn.reroll(dice));
	}
	
	//63
	@When("first roll: 4 monkey, 3 coin, 1 skull")
	public void firstRoll_63() {
		add("mo", 4);
		add("co", 3);
		add("sk", 1);
		turn.firstRoll(dice);
	}
	
	//65
	@When("And first roll: 7 sword, 1 monkey")
	public void firstRoll_65() {
		add("sw", 7);
		add("mo", 1);
		turn.firstRoll(dice);
	}
	
	@Then("reroll fail: roll at least 2 dice")
	public void reroll_65() {
		assertEquals(-1, turn.reroll(null));
	}
	
	//70
	@When("first roll: 2 skull, 6 coin")
	public void firstRoll_70() {
		add("sk", 2);
		add("co", 6);
		turn.firstRoll(dice);
	}
	
	@When("unlock one of skulls")
	public void unlock_70() {
		HashSet<Integer> set = new HashSet<Integer>();
		set.add(0);
		turn.unlock(set);
	}
	
	@When("reroll: 7 sward")
	public void reroll_70() {
		dice.clear();
		add("sw", 7);
		assertEquals(0, turn.reroll(dice));
	}
	
	@Then("only 1 skull in hand")
	public void then_70() {
		int skull = 0;
		for(Dice d: turn.getHand()) {
			if (d.getFace() == Dice.Face.SKULL) skull++;
		}
		assertEquals(1, skull);
	}
	
	//71
	@When("reroll: 1 skull,1 coin")
	public void reroll_71_1() {
		dice.clear();
		add("sk", 1);
		add("co", 1);
		assertEquals(0, turn.reroll(dice));
	}
	
	@When("reroll: 2 parrot")
	public void reroll_71_2() {
		dice.clear();
		add("pa", 2);
		assertEquals(0, turn.reroll(dice));
	}
	
	@Then("score: 1000")
	public void score1000() {
		assertEquals(OneTurnScoreChange.Range.SELF, turn.getDelta().getRange());
		assertEquals(1000, turn.getDelta().getChange());
	}
	
	//72
	@Then("no skull in hand")
	public void then_72() {
		int skull = 0;
		for (Dice d : turn.getHand()) {
			if (d.getFace() == Dice.Face.SKULL) skull++;
		}
		assertEquals(0, skull);
	}
	
	//75
	@When("first roll: 3 monkey, 3 parrot,  1 skull, 1 coin")
	public void firstRoll_75() {
		add("mo", 3);
		add("pa", 3);
		add("sk", 1);
		add("co", 1);
		turn.firstRoll(dice);
	}
	
	//76
	@When("reroll: 2 monkey, 1 parrot, 2 coin, 1 diamond, 2 sword")
	public void reroll_76() {
		dice.clear();
		add("mo", 2);
		add("pa", 1);
		add("co", 2);
		add("di", 1);
		add("sw", 2);
		assertEquals(0, turn.reroll(dice));
	}
	
	@When("reroll: 3 monkey, 4 parrot, 1 sword")
	public void reroll_77() {
		dice.clear();
		add("mo", 3);
		add("pa", 4);
		add("sw", 1);
		assertEquals(0, turn.reroll(dice));
	}
	
	@Then("score: 2000")
	public void score2000() {
		assertEquals(OneTurnScoreChange.Range.SELF, turn.getDelta().getRange());
		assertEquals(2000, turn.getDelta().getChange());
	}
	
	//80
	@When("first roll: 3 parrot, 2 sword, 2 diamond, 1 coin")
	public void firstRoll_80() {
		add("pa", 3);
		add("sw", 2);
		add("di", 2);
		add("co", 1);
		turn.firstRoll(dice);
	}
	
	@When("stash: index 5 6 7")
	public void stash_80_1() {
		HashSet<Integer> set = new HashSet<Integer>();
		set.add(5);
		set.add(6);
		set.add(7);
		try {
			turn.moveToChest(set);
		} catch (ChestException e) {
			fail("Should have treasure chest");
		}
	}
	
	@When("stash: index 0 1 2 3 4")
	public void stash_80_2() {
		HashSet<Integer> set = new HashSet<Integer>();
		set.add(0);
		set.add(1);
		set.add(2);
		set.add(3);
		set.add(4);
		try {
			turn.moveToChest(set);
		} catch (ChestException e) {
			fail("Should have treasure chest");
		}
	}
	
	@When("withdraw: index 0 1 2")
	public void withdraw_80() {
		HashSet<Integer> set = new HashSet<Integer>();
		set.add(0);
		set.add(1);
		set.add(2);
		try {
			turn.moveToHand(set);
		} catch (ChestException e) {
			fail("Should have treasure chest");
		}
	}
	
	@When("reroll: 1 skull, 1 coin, 1 parrot")
	public void reroll_80_2() {
		dice.clear();
		add("sk", 1);
		add("co", 1);
		add("pa", 1);
		assertEquals(0, turn.reroll(dice));
	}
	
	//85
	@When("first roll:  2 skull, 3 parrot, 3 coin")
	public void firstRoll_85() {
		add("sk",2);
		add("pa",3);
		add("co",3);
		turn.firstRoll(dice);
	}
	
	@When("reroll: 2 diamond, 1 coin")
	public void reroll_85_1() {
		dice.clear();
		add("di", 2);
		add("co", 1);
		assertEquals(0,turn.reroll(dice));
	}
	
	@When("stash: index 4")
	public void stash_85_2() {
		HashSet<Integer> set = new HashSet<Integer>();
		set.add(4);
		try {
			turn.moveToChest(set);
		} catch (ChestException e) {
			fail("Should have treasure chest");
		}
	}
	
	@When("reroll: 1 skull, 1 coin")
	public void reroll_85_2() {
		dice.clear();
		add("sk", 1);
		add("co", 1);
		assertEquals(1,turn.reroll(dice));
	}
	
	//91
	@When("first roll: 3 monkey, 3 sword, 1 diamond, 1 parrot")
	public void firstRoll_91() {
		add("mo",3);
		add("sw",3);
		add("di",1);
		add("pa",1);
		turn.firstRoll(dice);
	}
	
	//92
	@When("firstroll: 3 monkey, 3 sword, 2 coin")
	public void firstroll_92() {
		add("mo", 3);
		add("sw", 3);
		add("co", 2);
		turn.firstRoll(dice);
	}
	
	@Then("score: 1800")
	public void score1800() {
		assertEquals(OneTurnScoreChange.Range.SELF, turn.getDelta().getRange());
		assertEquals(1800, turn.getDelta().getChange());
	}
	
	//93
	@When("first roll: 3 monkey, 4 sword, 1 diamond")
	public void firstRoll_93() {
		add("mo",3);
		add("sw",4);
		add("di",1);
		turn.firstRoll(dice);
	}
	
	//94
	@When("first roll: 4 monkey, 1 sword, 2 parrot, 1 coin")
	public void firstRoll_94() {
		add("mo", 4);
		add("sw", 1);
		add("pa", 2);
		add("co", 1);
		turn.firstRoll(dice);
	}
	
	@When("reroll: 1 sword, 1 coin")
	public void reroll_94_1() {
		dice.clear();
		add("sw", 1);
		add("co", 1);
		assertEquals(0, turn.reroll(dice));
	}
	
	@Then("score: 1200")
	public void score1200() {
		assertEquals(OneTurnScoreChange.Range.SELF, turn.getDelta().getRange());
		assertEquals(1200, turn.getDelta().getChange());
	}
	
	//97
	@When("first roll: 2 monkey, 1 parrot, 2 coin, 3 diamond")
	public void firstRoll_97() {
		add("mo", 2);
		add("pa", 1);
		add("co", 2);
		add("di", 3);
		turn.firstRoll(dice);
	}
	
	//101
	@When("first roll: 2 skulls, 6 coin")
	public void firstRoll_101() {
		add("sk", 2);
		add("co", 6);
		turn.firstRoll(dice);
	}
	
	//102
	@When("first roll: 5 skull, 3 coin")
	public void firstRoll_102() {
		add("sk", 5);
		add("co", 3);
		turn.firstRoll(dice);
	}
	
	@Then("-1000 for all other players")
	public void then_102() {
		assertEquals(OneTurnScoreChange.Range.OTHER, turn.getDelta().getRange());
		assertEquals(-1000, turn.getDelta().getChange());
	}
	
	//103
	@When("reroll: 2 skull, 4 parrot")
	public void reroll_103_1() {
		dice.clear();
		add("sk", 2);
		add("pa", 4);
		turn.reroll(dice);
	}
	
	@When("reroll: 1 skull, 3 sword")
	public void reroll_103_2() {
		dice.clear();
		add("sk", 1);
		add("sw", 3);
		turn.reroll(dice);
	}
	
	@Then("score: -700")
	public void then_103() {
		assertEquals(-700, turn.getDelta().getChange());
	}
	
	
	//104
	@When("reroll: 5 coin")
	public void reroll_104() {
		dice.clear();
		add("co", 5);
	}
	
	@Then("continue on first roll")
	public void then_104_0() {
		assertFalse(turn.firstRoll(dice));
	}
	
	@Then("disqulified: did not roll skull in skull island")
	public void then_104_1() {
		assertEquals(2, turn.reroll(dice));
	}
	
	@Then("score: -500")
	public void scoreNegative500() {
		assertEquals(-500, turn.getDelta().getChange());
	}
	
	//105
	@When("reroll: 1 skull, 4 parrot")
	public void reroll_105_1() {
		dice.clear();
		add("sk", 1);
		add("pa", 4);
		turn.reroll(dice);
	}
	
	@When("reroll: 4 monkey")
	public void reroll_105_2() {
		dice.clear();
		add("mo", 4);
	}
	
	//109
	@Then("score: -300")
	public void scoreNegative300(){
		assertEquals(-300, turn.getDelta().getChange());
	}
	
	@Then("self score")
	public void selfScore() {
		assertEquals(OneTurnScoreChange.Range.SELF, turn.getDelta().getRange());
	}
	
	@Then("other score")
	public void otherScore() {
		assertEquals(OneTurnScoreChange.Range.OTHER, turn.getDelta().getRange());
	}
	
	//113
	@When("first roll: 3 monkey, 2 sword, 1 coin, 2 parrot")
	public void firstRoll_113() {
		add("mo", 3);
		add("sw", 2);
		add("co", 1);
		add("pa", 2);
		turn.firstRoll(dice);
	}
	
	//114
	@When("first roll: 4 monkey, 1 sword, 1 skull, 2 parrot")
	public void firstRoll() {
		add("mo", 4);
		add("sw", 1);
		add("sk", 1);
		add("pa", 2);
		turn.firstRoll(dice);
	}
	
	//116
	@When("first roll: 3 monkey, 4 sword, 1 skull")
	public void firstRoll_116() {
		add("mo", 3);
		add("sw", 4);
		add("sk", 1);
		turn.firstRoll(dice);
	}
	
	//117
	@When("first roll: 4 monkey, 2 sword, 2 skull")
	public void firstRoll_117() {
		add("mo",4);
		add("sw",2);
		add("sk",2);
		turn.firstRoll(dice);
	}
	
	@When("reroll: 2 sword, 2 skull")
	public void reroll_117_1() {
		dice.clear();
		add("sw",2);
		add("sk",2);
	}
	
	//119
	@Then("score: 1300")
	public void score1300() {
		assertEquals(1300, turn.getDelta().getChange());
	}
	
	//120
	@When("first roll: 3 monkey, 1 sword, 1 skull, 1 diamond, 2 parrot")
	public void firstRoll_120() {
		add("mo", 3);
		add("sw", 1);
		add("sk", 1);
		add("di", 1);
		add("pa", 2);
		turn.firstRoll(dice);
	}
	
	@When("reroll: 2 sword")
	public void reroll_120_1() {
		dice.clear();
		add("sw", 2);
		turn.reroll(dice);
	}
	
	@When("unhold all")
	public void unholdAll() {
		turn.unlockAll();
	}
	
	@When("reroll: 1 sword, 2 parrot")
	public void reroll_120_2() {
		dice.clear();
		add("sw", 1);
		add("pa", 2);
		turn.reroll(dice);
	}
	
}