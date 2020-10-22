package turnTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import cucumber.annotation.en.*;
import entity.Dice;
import game.Turn;

public class BasicDyingAndScoringTest {
	Turn turn;
	ArrayList<Dice> dice;
	
	@Given("a turn")
	public void newTurn() {
		turn = new Turn();
		turn.setCard(new entity.FortuneCard.Gold());
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
	
	//38
	@When("roll 3 skulls on firstroll")
	public void roll3SkullsOnFirstRoll() {
		
		dice.add(new Dice("sk"));
		dice.add(new Dice("sk"));
		dice.add(new Dice("sk"));
		dice.add(new Dice("mo"));
		dice.add(new Dice("mo"));
		dice.add(new Dice("pa"));
		dice.add(new Dice("pa"));
		dice.add(new Dice("mo"));
	}
	
	@Then("disqulified after firstroll")
	public void disqulifiedAfterFirstRoll38() {
		assertTrue(turn.firstRoll(dice));
	}
	
	//39
	@When("first roll: 1 skull, 4 parrots, 3 swords")
	public void firstRoll_39() {
		dice.add(new Dice("sk"));
		dice.add(new Dice("pa"));
		dice.add(new Dice("pa"));
		dice.add(new Dice("pa"));
		dice.add(new Dice("pa"));
		dice.add(new Dice("sw"));
		dice.add(new Dice("sw"));
		dice.add(new Dice("sw"));
		turn.firstRoll(dice);
	}
	
	@When("reroll: 2 skull, 1 sword")
	public void reroll_39_1() {
		dice.clear();
		dice.add(new Dice("sk"));
		dice.add(new Dice("sk"));
		dice.add(new Dice("sw"));
	}
	
	@Then("disqulified: 3 or more skulls")
	public void then_39() {
		assertEquals(1,turn.reroll(dice));
	}
	
	//40
	@When("first roll: 2 skulls, 4 parrots, 2 swords")
	public void firstRoll_40() {
		dice.add(new Dice("sk"));
		dice.add(new Dice("sk"));
		dice.add(new Dice("pa"));
		dice.add(new Dice("pa"));
		dice.add(new Dice("pa"));
		dice.add(new Dice("pa"));
		dice.add(new Dice("pa"));
		dice.add(new Dice("sw"));
		dice.add(new Dice("sw"));
		turn.firstRoll(dice);
	}
	
	@When("reroll: 1 skull, 1 sword")
	public void reroll_40_1() {
		dice.clear();
		dice.add(new Dice("sk"));
		dice.add(new Dice("sw"));
	}
	
	//41
}