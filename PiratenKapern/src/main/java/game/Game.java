package game;

import java.util.ArrayList;

import entity.Player;
import entity.FortuneCard.Deck;
import entity.FortuneCard.FortuneCard;
import view.PlayerView;
import view.ServerView;

public class Game {
	private final int WINNING_SCORE=6000;
	
	private ArrayList<Player> players;
	private final Boolean isServerControledGame;
	
	private TurnControl currTurn;
	private ArrayList<TurnControl> turns;
	private Deck deck;
	private int currPlayer;
	private int winnerRound;
	
	
	public Game(ArrayList<Player> players, Boolean serverControl) {
		this.players = players;
		this.isServerControledGame = serverControl;
		
		
		turns = new ArrayList<TurnControl>();
		deck = new Deck();
		currPlayer = 0;
		winnerRound = -1;
	}
	
	public FortuneCard drawCard() {
		return deck.draw();
	}
	
	public void newTurn(ServerView sv, PlayerView currPlayerView, ArrayList<PlayerView> otherPlayers) {
		String currName = players.get(currPlayer).getName();
		currTurn = new TurnControl(sv, currPlayerView,currName, otherPlayers,isServerControledGame);
		turns.add(currTurn);
	}
	
	public OneTurnScoreChange startTurn(FortuneCard c) {
		OneTurnScoreChange a = currTurn.turnStart(c);
		currTurn = null;
		return a;
	}
	
	public void scoreChange(OneTurnScoreChange delta) {
		if (delta.getRange() == OneTurnScoreChange.Range.SELF) {
			players.get(currPlayer).scoreChange(delta.getChange());
		}else {
			for (int i = 0; i < players.size(); i++) {
				if (i == currPlayer) continue;
				players.get(i).scoreChange(delta.getChange());
			}
		}
	}
	
	public Player checkWinner() {
		if (this.winnerRound == -1) {
			if (players.get(currPlayer).getScore() >= this.WINNING_SCORE) {
				this.winnerRound = players.size()-2;
			}
		}else if (this.winnerRound == 0) {
			int max = 0;
			Player winner = null;
			for (Player p: players) {
				int s = p.getScore();
				if (s > max) {
					max = s;
					winner = p;
				}
			}
			if (max >= this.WINNING_SCORE) return winner;
			else this.winnerRound = -1;
		}else this.winnerRound--;
		return null;
	}
	
	public Boolean reachWinningScore() {
		if (winnerRound == players.size()-2) return true;
		else return false;
	}
	
	public void updateTurn() {
		currPlayer = (currPlayer + 1) % players.size();
	}
	
	public Player getCurrentPlayer() {return players.get(currPlayer);}
	public int getWinningScore() {return this.WINNING_SCORE;}
	public String reportScore() {
		String result = "";
		for (Player p : players) 
			result += p.getName() + ": " + p.getScore() +"\n";
		return result;
	}
}
