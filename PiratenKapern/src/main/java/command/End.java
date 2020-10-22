package command;

import game.TurnControl;

public class End implements Command {

	@Override
	public Boolean execute(TurnControl t) {
		return t.end();
	}

}
