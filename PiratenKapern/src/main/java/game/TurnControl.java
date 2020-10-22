package game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import command.Command;
import command.CommandFactory;
import entity.FortuneCard.FortuneCard;
import view.PlayerView;
import view.ServerView;

public class TurnControl {
	private Turn currTurn;
	private CommandFactory cf;
	private ArrayList<Command> commandList;
	private ServerView serverView;
	private PlayerView currPlayer;
	private final String currName;
	private ArrayList<PlayerView> otherPlayer;
	private final Boolean isServerControledGame;

	public TurnControl(ServerView sv, PlayerView curr,String currName, ArrayList<PlayerView> others, Boolean isServerControledGame) {
		commandList = new ArrayList<Command>();
		this.serverView = sv;
		this.currPlayer = curr;
		this.currName = currName;
		this.otherPlayer = others;
		this.isServerControledGame = isServerControledGame;
	}

	private void sendToCurrentPlayer(String msg){
		currPlayer.send(msg);
	}

	private void sendToOtherPlayers(String msg) {
		for (PlayerView pv: otherPlayer) {
			pv.send(msg);
		}
	}
	
	private void announce(String msg) {
		this.sendToCurrentPlayer(msg);
		this.sendToOtherPlayers(msg);
	}

	public OneTurnScoreChange turnStart(FortuneCard card) {
		//init turn
		currTurn = new Turn();
		cf = new CommandFactory(currTurn);
		this.sendToCurrentPlayer("\n============Your Turn============");
		this.sendToOtherPlayers("\n============" + currName + "'s Turn============");

		//draw Card
		currTurn.setCard(card);
		this.sendToCurrentPlayer("\n*You have drawn " + card);
		this.sendToOtherPlayers("\n*" + currName + " has drawn " + card);

		//first roll
		Boolean first;
		if (isServerControledGame){
			first = currTurn.firstRoll(serverView.getDice(currTurn.getDiceNumber()));
		}else{
			first = currTurn.firstRoll(null);
		}
		String statString = currTurn.statString();
		this.sendToCurrentPlayer("\n*Your first roll:\n" + statString);
		this.sendToOtherPlayers("\n*" + currName + "'s first roll:\n" + statString);
		if (first) {
			this.sendToCurrentPlayer("You have rolled 3 or more skulls, your turn ends.");
			this.sendToOtherPlayers(currName + " has rolled 3 or more skulls, turn ends.");
			this.endTurn();
			return currTurn.getDelta();
		}else{
			if (currTurn.getTheme() instanceof game.Theme.SkullIsland){
				this.sendToCurrentPlayer("\n*Welcome to Skull Island! Get as much skulls as you can to reduce others' score!");
				this.sendToOtherPlayers("\n*" + currName + " has entered Skull Island. You score gonna be... :(");
			}
		}
		
		//main phase: wait actions
		while (true) {
			//turn loop
			Command command;
			while (true) {
				//get command loop
				try {
					command = this.getCommand();
					if (command != null) break;
					else {
						this.sendToCurrentPlayer("\n*Captain, I can't understand your command."+
												 "\n    Please enter again.");
					}
				} catch (IOException e) {
					System.err.println("io error");
					this.sendToCurrentPlayer("Connection error, please enter again. ");
				}
			}
			commandList.add(command);
			if (command.execute(this)) break;
		}
		
		//end turn
		this.endTurn();
		return currTurn.getDelta();
	}
	
	//player actions
	public Boolean end() {
		this.announce("\n*Action: end");
		return true;
	}

	public Boolean stash(HashSet<Integer> index) {
		try {
			currTurn.moveToChest(index);
			this.announce("\n*Action: stash");
		} catch (ChestException e) {
			this.sendToCurrentPlayer("\n*You do not have a Treasure Cheast.");
		}
		return false;
	}
	
	public Boolean withdraw(HashSet<Integer> index) {
		try {
			currTurn.moveToHand(index);
			this.announce("\n*Action: withdraw");
		} catch (ChestException e) {
			this.sendToCurrentPlayer("\n*You do not have a Treasure Cheast.");
		}
		return false;
	}

	public Boolean unlock(HashSet<Integer> index) {
		currTurn.unlock(index);
		this.announce("\n*Action: unlcok");
		return false;
	}

	public Boolean lock(HashSet<Integer> index) {
		currTurn.lock(index);
		this.announce("\n*lock");
		return false;
	}

	public Boolean roll() {
		int flag;
		if (isServerControledGame) {
			flag = currTurn.reroll(serverView.getDice(currTurn.howManyToReroll()));
		}
		else flag = currTurn.reroll(null);
		if (flag == -1) {
			this.sendToCurrentPlayer("\n*You need to roll at least 2 dice.");
			return false;
		}
		this.sendToCurrentPlayer("\n*You have rerolled:\n"+currTurn.statString());
		this.sendToOtherPlayers("\n*" + currName + " has rerolled:\n" + currTurn.statString());
		switch (flag) {
		case 0:
			return false;
		case 1:
			this.sendToCurrentPlayer("\n*You have beed disqulified from the turn because you got 3 or more skulls.");
			this.sendToOtherPlayers("\n*" + currName + " has beed disqulified from the turn because got 3 or more skulls.");
			return true;
		case 2:
			this.sendToCurrentPlayer("\n*You have beend disqulified from the turn because you didn't roll any skulls this reroll in Skull Island.");
			this.sendToOtherPlayers("\n*" + currName + " have beend disqulified from the turn because didn't roll any skulls this reroll in Skull Island.");
			return true;
		default:
			this.announce("\n*Error, turn ends");
			return true;
		}
	}
	
	//private method
	private Command getCommand() throws IOException {
		this.sendToCurrentPlayer("\n*Captain, it's our time!\n"+ cf.getPrompt() +"What are we doing next?");
		String s = this.currPlayer.getCommand();
		return this.cf.creatCommand(s);
	}
	
	private void endTurn() {
		currTurn.endTurn();
		this.sendToCurrentPlayer("\n^^^^^^^Your Turn Ends^^^^^^^");
		this.sendToOtherPlayers("\n^^^^^^^" + currName + "'s Turn Ends^^^^^^^");
		
	}
}
