package cucumberTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import cucumber.annotation.en.*;
import entity.Player;
import game.Game;
import game.OneTurnScoreChange;

public class NetworkedGameTest{
	private Game g;
	private ArrayList<Player> players;
	
	//106
	@Given("a game")
	public void newGame() {
		players = new ArrayList<Player>();
		players.add(new Player("P1"));
		players.add(new Player("P2"));
		players.add(new Player("P3"));
		g = new Game(players, false);
	}
	
	@When("current player score -500 in skull island")
	public void when_106_1() {
		OneTurnScoreChange a = new OneTurnScoreChange(OneTurnScoreChange.Range.OTHER,-500);
		g.scoreChange(a);
	}
	
	@Then("other players remain 0 score even their score is less than 500")
	public void then_106_1() {
		for(Player p: players) {
			assertEquals(0, p.getScore());
		}
	}
	
	//112
	@When("current player score -500 in sea battle")
	public void when_112_1(){
		OneTurnScoreChange a = new OneTurnScoreChange(OneTurnScoreChange.Range.SELF,-500);
		g.scoreChange(a);
	}
	
	@Then("current player remain 0 score even his/her score is less than 500")
	public void then_112_1() {
		assertEquals(0, g.getCurrentPlayer().getScore());
	}
	
	//some scores
	@When("current player score 500")
	public void score500() {
		OneTurnScoreChange a = new OneTurnScoreChange(OneTurnScoreChange.Range.SELF, 500);
		g.scoreChange(a);
	}
	
	@When("current player score 1000")
	public void score1000() {
		OneTurnScoreChange a = new OneTurnScoreChange(OneTurnScoreChange.Range.SELF, 1000);
		g.scoreChange(a);
	}
	
	@When("current player score 10000")
	public void score10000() {
		OneTurnScoreChange a = new OneTurnScoreChange(OneTurnScoreChange.Range.SELF, 10000);
		g.scoreChange(a);
	}
	
	@When("current player score -500")
	public void scoreN500() {
		OneTurnScoreChange a = new OneTurnScoreChange(OneTurnScoreChange.Range.SELF, -500);
		g.scoreChange(a);
	}
	
	@When("current player score -1000")
	public void scoreN1000() {
		OneTurnScoreChange a = new OneTurnScoreChange(OneTurnScoreChange.Range.SELF, -1000);
		g.scoreChange(a);
	}
	@When("current player score -10000")
	public void scoreN10000() {
		OneTurnScoreChange a = new OneTurnScoreChange(OneTurnScoreChange.Range.SELF, 10000);
		g.scoreChange(a);
	}
	
	//some player scores
	@Then("current player have 500 score total")
	public void curr500() {
		assertEquals(500,g.getCurrentPlayer().getScore());
	}
	
	@Then("current player have 1000 score total")
	public void curr1000() {
		assertEquals(1000,g.getCurrentPlayer().getScore());
	}
	
	@Then("current player have 1500 score total")
	public void curr1500() {
		assertEquals(1500,g.getCurrentPlayer().getScore());
	}
	
	@Then("current player have 2000 score total")
	public void curr2000() {
		assertEquals(2000,g.getCurrentPlayer().getScore());
	}
	
	@Then("current player have 2500 score total")
	public void curr2500() {
		assertEquals(2500,g.getCurrentPlayer().getScore());
	}
	
	@When("current player reach winning score")
	public void winningScore() {
		assertTrue(g.reachWinningScore());
	}
	
	@Then("no winner yet")
	public void noWinner() {
		assertNull(g.checkWinner());
	}
	
	@Then("have winner")
	public void haveWinner() {
		assertNotNull(g.checkWinner());
	}
	
	@When("next turn")
	public void nextTurn() {
		g.updateTurn();
	}
}