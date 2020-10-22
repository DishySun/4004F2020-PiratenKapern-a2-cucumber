package command;

import game.TurnControl;

public interface Command {
	public Boolean execute(TurnControl tc);
}
