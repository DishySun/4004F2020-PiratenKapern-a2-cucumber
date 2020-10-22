package view;

import java.util.ArrayList;
import java.util.Scanner;

import entity.Dice;
import entity.Player;
import entity.FortuneCard.FortuneCard;

public class ServerView {

	public FortuneCard getFortuneCard() {
		while(true) {
			System.out.print("What Fortune Card will this player draw: ");
			String fc = new Scanner(System.in).nextLine();
			String arg[] = fc.trim().toLowerCase().split(" ");
			if(arg.length < 1) continue;
			switch (arg[0]) {
			case "ca":
				return new entity.FortuneCard.Captain();
			case "di":
				return new entity.FortuneCard.Diamond();
			case "go":
				return new entity.FortuneCard.Gold();
			case "mb":
				return new entity.FortuneCard.MonkeyBusiness();
			case "so":
				return new entity.FortuneCard.Sorceress();
			case "tc":
				return new entity.FortuneCard.TreasureChest();
			case "sb":
				if (arg.length != 2) {
					System.err.println("Format error: SB <int: sword require>");
					continue;
				}
				try {
					int i = Integer.parseInt(arg[1]);
					if (i > 4 || i < 2) {
						System.err.println("sb: sword required should be 2-4.");
						continue;
						}
					return new entity.FortuneCard.SeaBattle(i);
				}catch(NumberFormatException e) {
					System.err.println("need 2 integers after \"sb\"");
					continue;
				}
			case "sk":
				if (arg.length != 2) {
					System.err.println("Format error: SK <int: skulls>");
					continue;
				}
				try {
					int i = Integer.parseInt(arg[1]);
					return new entity.FortuneCard.Skull(i);
				}catch(NumberFormatException e) {
					System.err.println("need a integer after \"sk\"");
					continue;
				}
			default:
				System.err.println("Invalid Card");
				continue;
			}
		}
	}

	public ArrayList<Dice> getDice(int diceNumber) {
		ArrayList<Dice> dice = new ArrayList<Dice>();
		while (dice.size() != diceNumber) {
			System.out.println("What dice will this player roll (need " + (diceNumber - dice.size()) + " dice):");
			String diceString = new Scanner(System.in).nextLine();
			String arg[] = diceString.trim().toLowerCase().split(" ");
			for (String s : arg) {
				if (!s.equals("sk") && !s.equals("mo") && !s.equals("pa") && !s.equals("sw") & !s.equals("co") && !s.equals("di")) {
					System.err.println("Invalid die: "+ s);
					break;
				}
			}
			for (String s : arg) {
				Dice d = new Dice(s);
				if (d.getFace() == Dice.Face.SKULL) d.lock();
				dice.add(d);
			}
			if (dice.size() > diceNumber) {
				System.err.println("Dice number exceed. Enter again.");
				dice.clear();
			}
		}
		return dice;
	}

	public Boolean isServerControled() {
		String s = "";
		while (!s.equals("y") && !s.equals("n")) {
			System.out.print("Server Controlled Game?(Y/N)");
			s = new Scanner(System.in).nextLine().trim().toLowerCase();
		}
		if (s.contentEquals("y")) return true;
		else return false;
	}
}
