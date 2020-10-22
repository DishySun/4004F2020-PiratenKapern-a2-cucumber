package command;

import java.util.HashSet;

import game.Turn;

public class CommandFactory {
	private Turn t;
	
	public CommandFactory(Turn turn) {
		this.t = turn;
	}
	
	public Command creatCommand(String s) {
		String str[] = s.trim().toLowerCase().split(" ");
		int len = str.length;
		HashSet<Integer> index = new HashSet<Integer>();
		for (int i = 1; i < len; i++) {
			try {
				int j = Integer.parseInt(str[i]);
				index.add(j);
			}catch(NumberFormatException e) {
				return null;
			}
		}
		Command c;
		switch(str[0]) {
		case "roll": 
			c = new command.Roll();
			break;
		case "end": 
			c = new command.End();
			break;
		case "lock":
			c = new command.Lock(index);
			break;
		case "unlock":
			c = new command.Unlock(index);
			break;
		case "stash":
			c = new command.Stash(index);
			break;
		case "withdraw":
			c = new command.Withdraw(index);
			break;
		default:
			return null;
		}
		return c;
	}

	public String getPrompt() {
		String s= "    roll: to reroll all UNLOCKED dice in your hand.\n"
				+"    lock/unlock <indexes>: to lock/unlock <indexes>(split with a space) dice in your hand.\n"
				+"    end: to end your turn and calculate score.\n";
		if (t.getChest() != null) 
			s += "Captain, we found a Treasure Chest. At least we will get what's in the chest even we have too many skulls this turn. How luck!\n"
				+ "    stash <indexes>: to move <indexes>(split with a space) dice to the chest. Attention, skulls can not be moved to chest.\n"
				+ "    withdraw <indexes>: to move <indexes>(split with a space) dice to your hand.\n";
		return s;
	}
}
