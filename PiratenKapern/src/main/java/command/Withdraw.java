package command;

import java.util.HashSet;

import game.TurnControl;

public class Withdraw implements Command {
	private HashSet<Integer> index;
	
	public Withdraw(HashSet<Integer> i) {
		this.index = i;
	}

	@Override
	public Boolean execute(TurnControl t) {
		return t.withdraw(index);
	}

}
