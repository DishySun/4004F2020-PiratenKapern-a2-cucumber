package command;

import game.TurnControl;

public class Roll implements Command {

	@Override
	public Boolean execute(TurnControl t) {
		return t.roll();
	}

}
