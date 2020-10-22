package command;

import java.util.HashSet;

import game.TurnControl;

public class Stash implements Command {
	private HashSet<Integer> index;
	
	public Stash(HashSet<Integer> i) {
		this.index = i;
	}

	@Override
	public Boolean execute(TurnControl t) {
		return t.stash(index);
	}

}
