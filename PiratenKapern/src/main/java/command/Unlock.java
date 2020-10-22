package command;

import java.util.HashSet;

import game.TurnControl;

public class Unlock implements Command {
	private HashSet<Integer> index;
	
	public Unlock(HashSet<Integer> i) {
		this.index = i;
	}

	@Override
	public Boolean execute(TurnControl t) {
		return t.unlock(index);
	}

}
