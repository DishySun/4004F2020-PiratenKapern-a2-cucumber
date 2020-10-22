package command;

import java.util.HashSet;

import game.TurnControl;

public class Lock implements Command {
	private HashSet<Integer> index;
	
	public Lock(HashSet<Integer> i) {
		this.index = i;
	}

	@Override
	public Boolean execute(TurnControl t) {
		return t.lock(index);
	}

}
