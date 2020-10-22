package game;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import entity.Player;
import entity.FortuneCard.FortuneCard;
import view.PlayerView;
import view.ServerView;

public class GameControl extends Thread{
	private final int PLAYER_NUMBER = 3;
	
	private HashMap<Player,PlayerView> players;
	private ServerView sv;
	
	private Game g;
	
	public GameControl() {
		players = new HashMap<Player,PlayerView>();
		sv = new ServerView();
	}
	
	private void announce(String msg) {
		for (Player p : players.keySet()) {
			players.get(p).send(msg);
		}
	}
	
	public Boolean playerConnect(Socket s) throws IOException {
		PlayerView pv = new PlayerView(s);
		String name = pv.askName();
		Player p = new Player(name);
		players.put(p, pv);
		if(players.size() < PLAYER_NUMBER) return false;
		else {
			this.start();
			return true;
		}
	}
	
	@Override
	public void run() {
		Boolean serverControled = sv.isServerControled();
		ArrayList<Player> p = new ArrayList<Player>();
		p.addAll(players.keySet());
		g = new Game(p,serverControled);
		
		this.gameStart();
		Player winner = null;
		while (true) {
			//turn loop
			
			//new turn
			Player currPlayer = g.getCurrentPlayer();
			PlayerView currPlayerView = players.get(currPlayer);
			ArrayList<PlayerView> otherPlayers = new ArrayList<PlayerView>();
			for (Player pp : players.keySet()) {
				if (pp == currPlayer) continue;
				otherPlayers.add(players.get(pp));
			}
			g.newTurn(sv, currPlayerView, otherPlayers);
			
			//draw fortune card
			FortuneCard fc;
			if (serverControled) fc = sv.getFortuneCard();
			else fc = g.drawCard();
			
			//start turn
			OneTurnScoreChange delta = g.startTurn(fc);
			
			//score
			g.scoreChange(delta);
			this.showScore(delta);
			
			//check winner
			winner = g.checkWinner();
			if (winner != null) break;
			else {
				if (g.reachWinningScore()) this.announce(currPlayer.getName() + " has reach winning point "+ g.getWinningScore() +"\nUse your next turn to catch him/her, or you lose!");
			}
			
			//update turn
			g.updateTurn();
		}
		
		this.announceWinner(winner);
		for (Player pp : players.keySet()) {
			players.get(pp).shutdown();
		}
	}
	
	private void gameStart() {
		String yousoro;
		try {
			yousoro = "*      "+new String("全速前進ヨーソロー！".getBytes(),"UTF-8")+"            *";
		} catch (UnsupportedEncodingException e) {
			yousoro = "";
		}
		this.announce("******************************");
		this.announce("*         GAME START         *");
		this.announce("*      Full Sail Ahead       *");
		this.announce(yousoro);
		this.announce("******************************");
	}
	
	private void showScore(OneTurnScoreChange delta) {
		Player currPlayer = g.getCurrentPlayer();
		int change = delta.getChange();
		OneTurnScoreChange.Range range = delta.getRange();
		String gainNlost;
		if (change > 0 ) gainNlost = "GAIN " + change + " points";
		else if (change < 0) gainNlost = "LOST " + Math.abs(change) + " points";
		else gainNlost = "no change on score this turn";
		if (delta.getRange() == OneTurnScoreChange.Range.SELF) {
			for (Player p : players.keySet()) {
				if (p == currPlayer) players.get(p).send("\n*You have " + gainNlost);
				else players.get(p).send("\n*" + currPlayer.getName() + " has " + gainNlost);
			}
		}else {
			for (Player p : players.keySet()) {
				if (p == currPlayer) players.get(p).send("\n*Other players have " + gainNlost);
				else players.get(p).send("\n*You have " + gainNlost);
			}
		}
		
		this.announce(g.reportScore());
		
	}
	
	private void announceWinner(Player winner) {
		for (Player p : players.keySet()) {
			if (p == winner) players.get(p).send("You have won the game!");
			else players.get(p).send(winner.getName()+ " has won the game!");
		}
	}
}
