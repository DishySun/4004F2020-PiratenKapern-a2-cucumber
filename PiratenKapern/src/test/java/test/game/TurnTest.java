package test.game;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.ArrayList;

import org.junit.Test;

import entity.Dice;
import entity.FortuneCard.*;
import game.ChestException;
import game.Turn;
import game.Theme.*;

public class TurnTest {

	@Test
	public void test_Constructor() {
		//initialize a turn, all attributes should be set to default
		Turn t = new Turn();
		assertEquals(t.getscoreMultiplier(), 1); 		//scoreMultiplier = 1
		assertEquals(t.getskullRerollCount(), 0); 		//skullRerollCounter = 0
		assertEquals(t.getTreasureInHand().size(), 6); 	//6 faces
		for (Dice.Face f : Dice.Face.values()) {
			//every face should be counted as 0 at the beginning of the turn
			assertEquals((int)t.getTreasureInHand().get(f), 0);
		}
		assertNull(t.getFortuneCard()); //Fortune Card should be set now
		assertTrue(t.getTheme() instanceof game.Theme.Normal);
		assertEquals(t.getHand().size(), 8);  //8 Dice
		for(Dice d : t.getHand()) {
			// Face = SKULL, unlocked at initialization
			assertTrue(d.getFace() == Dice.Face.SKULL);
			assertFalse(d.isLock());
		}
		assertNull(t.getChest()); //chest cannot be accessed unless get Treasure Chest fortune card
	}
	
	@Test
	public void test_SetFortuneCard_TreasureChest() {
		Turn t;
		//test for treasure chest fortune card
		t = new Turn();
		FortuneCard card = new TreasureChest();
		t.setCard(card);
		assertEquals(t.getChest().size(), 0); 			//get access to chest
		assertTrue(t.getFortuneCard() instanceof TreasureChest);
			//others should not change
		assertEquals(t.getscoreMultiplier(), 1); 		//scoreMultiplier = 1
		assertEquals(t.getskullRerollCount(), 0); 		//skullRerollCounter = 0
		assertEquals(t.getTreasureInHand().size(), 6); 	//6 faces
		for (Dice.Face f : Dice.Face.values()) {
			//every face should be counted as 0 at the beginning of the turn
			assertEquals((int)t.getTreasureInHand().get(f), 0);
		} 
		assertTrue(t.getTheme() instanceof game.Theme.Normal);
		assertEquals(t.getHand().size(), 8);  //8 Dice
		for(Dice d : t.getHand()) {
			// Face = SKULL, unlocked at initialization
			assertTrue(d.getFace() == Dice.Face.SKULL);
			assertFalse(d.isLock());
		}
	}
	
	@Test
	public void test_SetFortuneCard_Captain() {
		Turn t;
		//test for treasure chest fortune card
		t = new Turn();
		FortuneCard card = new Captain();
		t.setCard(card);
		assertEquals(t.getscoreMultiplier(), 2);		//scoreMultiplier = 2
		assertTrue(t.getFortuneCard() instanceof Captain);
			//others should not change
		assertEquals(t.getskullRerollCount(), 0); 		//skullRerollCounter = 0
		assertEquals(t.getTreasureInHand().size(), 6); 	//6 faces
		for (Dice.Face f : Dice.Face.values()) {
			//every face should be counted as 0 at the beginning of the turn
			assertEquals((int)t.getTreasureInHand().get(f), 0);
		}
		assertTrue(t.getTheme() instanceof game.Theme.Normal);
		assertEquals(t.getHand().size(), 8);  //8 Dice
		for(Dice d : t.getHand()) {
			// Face = SKULL, unlocked at initialization
			assertTrue(d.getFace() == Dice.Face.SKULL);
			assertFalse(d.isLock());
		}
		assertNull(t.getChest()); 
	}
	
	@Test
	public void test_SetFortuneCard_Sorceress() {
		Turn t;
		//test for treasure chest fortune card
		t = new Turn();
		FortuneCard card = new Sorceress();
		t.setCard(card);
		assertEquals(t.getskullRerollCount(), 1);
		assertTrue(t.getFortuneCard() instanceof Sorceress); //skullRerollCounter = 0
			//others should not change
		assertEquals(t.getscoreMultiplier(), 1); 		//scoreMultiplier = 1
		 		
		assertEquals(t.getTreasureInHand().size(), 6); 	//6 faces
		for (Dice.Face f : Dice.Face.values()) {
			//every face should be counted as 0 at the beginning of the turn
			assertEquals((int)t.getTreasureInHand().get(f), 0);
		} 
		assertTrue(t.getTheme() instanceof game.Theme.Normal);
		assertEquals(t.getHand().size(), 8);  //8 Dice
		for(Dice d : t.getHand()) {
			// Face = SKULL, unlocked at initialization
			assertTrue(d.getFace() == Dice.Face.SKULL);
			assertFalse(d.isLock());
		}
		assertNull(t.getChest());
	}
	
	@Test
	public void test_SetFortuneCard_SeaBattle() {
		Turn t;
		//test for treasure chest fortune card
		t = new Turn();
		FortuneCard card = new entity.FortuneCard.SeaBattle(2);
		t.setCard(card);
		assertTrue(t.getTheme() instanceof game.Theme.SeaBattle);
		assertTrue(t.getFortuneCard() instanceof entity.FortuneCard.SeaBattle);
			//others should not change
		assertEquals(t.getscoreMultiplier(), 1); 		//scoreMultiplier = 1
		assertEquals(t.getskullRerollCount(), 0);		//skullRerollCounter = 0
		assertEquals(t.getTreasureInHand().size(), 6); 	//6 faces
		for (Dice.Face f : Dice.Face.values()) {
			//every face should be counted as 0 at the beginning of the turn
			assertEquals((int)t.getTreasureInHand().get(f), 0);
		} 
		assertEquals(t.getHand().size(), 8);  //8 Dice
		for(Dice d : t.getHand()) {
			// Face = SKULL, unlocked at initialization
			assertTrue(d.getFace() == Dice.Face.SKULL);
			assertFalse(d.isLock());
		}
		assertNull(t.getChest());
	}
	
	@Test
	public void test_SetFortuneCard_Gold() {
		Turn t;
		//test for treasure chest fortune card
		t = new Turn();
		FortuneCard card = new Gold();
		t.setCard(card);
		for (Dice.Face f : Dice.Face.values()) {
			if (f == Dice.Face.COIN) assertEquals((int)t.getTreasureInHand().get(f), 1);
			else assertEquals((int)t.getTreasureInHand().get(f), 0);
		} 
		assertTrue(t.getFortuneCard() instanceof Gold);
			//others should not change
		assertEquals(t.getscoreMultiplier(), 1); 		//scoreMultiplier = 1
		assertEquals(t.getskullRerollCount(), 0);		//skullRerollCounter = 0
		assertEquals(t.getTreasureInHand().size(), 6); 	//6 faces
		assertTrue(t.getTheme() instanceof game.Theme.Normal);
		assertTrue(t.getHand().size() == 8);  //8 Dice
		for(Dice d : t.getHand()) {
			// Face = SKULL, unlocked at initialization
			assertTrue(d.getFace() == Dice.Face.SKULL);
			assertFalse(d.isLock());
		}
		assertNull(t.getChest());
	}
	
	@Test
	public void test_SetFortuneCard_Diamond() {
		Turn t;
		//test for treasure chest fortune card
		t = new Turn();
		FortuneCard card = new Diamond();
		t.setCard(card);
		for (Dice.Face f : Dice.Face.values()) {
			if (f == Dice.Face.DIAMOND) assertEquals((int)t.getTreasureInHand().get(f),1);
			else assertEquals((int)t.getTreasureInHand().get(f), 0);
		} 
		assertTrue(t.getFortuneCard() instanceof Diamond);
			//others should not change
		assertEquals(t.getscoreMultiplier(), 1); 		//scoreMultiplier = 1
		assertEquals(t.getskullRerollCount(), 0);		//skullRerollCounter = 0
		assertEquals(t.getTreasureInHand().size(), 6); 	//6 faces
		assertTrue(t.getTheme() instanceof game.Theme.Normal);
		assertEquals(t.getHand().size(), 8);  //8 Dice
		for(Dice d : t.getHand()) {
			// Face = SKULL, unlocked at initialization
			assertTrue(d.getFace() == Dice.Face.SKULL);
			assertFalse(d.isLock());
		}
		assertNull(t.getChest());
	}
	
	@Test
	public void test_SetFortuneCard_MonkeyBusiness() {
		Turn t;
		//test for treasure chest fortune card
		t = new Turn();
		FortuneCard card = new entity.FortuneCard.MonkeyBusiness();
		t.setCard(card);
		assertTrue(t.getTheme() instanceof game.Theme.MonkeyBusiness);
		assertTrue(t.getFortuneCard() instanceof entity.FortuneCard.MonkeyBusiness);
			//others should not change
		assertEquals(t.getscoreMultiplier(), 1); 		//scoreMultiplier = 1
		assertEquals(t.getskullRerollCount(), 0);		//skullRerollCounter = 0
		assertEquals(t.getTreasureInHand().size(), 6); 	//6 faces
		for (Dice.Face f : Dice.Face.values()) {
			//every face should be counted as 0 at the beginning of the turn
			assertEquals((int)t.getTreasureInHand().get(f), 0);
		} 
		assertEquals(t.getHand().size(), 8);  //8 Dice
		for(Dice d : t.getHand()) {
			// Face = SKULL, unlocked at initialization
			assertTrue(d.getFace() == Dice.Face.SKULL);
			assertFalse(d.isLock());
		}
		assertNull(t.getChest());
	}
	
	@Test
	public void test_SetFortuneCard_Skulls() {
		Turn t;
		//test for treasure chest fortune card
		t = new Turn();
		FortuneCard card = new Skull(1);
		t.setCard(card);
		for (Dice.Face f : Dice.Face.values()) {
			if (f == Dice.Face.SKULL) assertEquals((int)t.getTreasureInHand().get(f),1);
			else assertEquals((int)t.getTreasureInHand().get(f), 0);
		} 
		assertTrue(t.getFortuneCard() instanceof Skull);
			//others should not change
		assertEquals(t.getscoreMultiplier(), 1); 		//scoreMultiplier = 1
		assertEquals(t.getskullRerollCount(), 0);		//skullRerollCounter = 0
		assertEquals(t.getTreasureInHand().size(), 6); 	//6 faces
		assertTrue(t.getTheme() instanceof game.Theme.Normal);
		assertEquals(t.getHand().size(), 8);  //8 Dice
		for(Dice d : t.getHand()) {
			// Face = SKULL, unlocked at initialization
			assertTrue(d.getFace() == Dice.Face.SKULL);
			assertFalse(d.isLock());
		}
		assertNull(t.getChest());
	}
	
	/*4 case after first roll
		1. 2 or less skulls - No change i.e. theme still Normal, continue
		2. 3 skulls - disqualified and end turn
		3. 4 or more skulls - go to Skull Island
		4. 4 or more skulls but already engaged in SeaBattle - no change i.e. disqualified
	*/
	@Test 
	public void test_firstRoll_1() {
		//no change and continue
		while(true) {
			Turn t = new Turn();
			FortuneCard card = new Diamond();
			t.setCard(card);
			int i = 0;
			Boolean bool = t.firstRoll(null);
			//count for skulls
			for (Dice d : t.getHand()) {
				if (d.getFace() == Dice.Face.SKULL) i++;
			}
			if (i > 2) continue;
			//test case:
			assertTrue(t.getTheme() instanceof Normal);
			assertFalse(bool);
			break;
		}
	}
	
	@Test
	public void test_fitstRoll_2() {
		// exact 3 skulls - disqualified from turn
		while(true) {
			Turn t = new Turn();
			FortuneCard c = new Gold();
			t.setCard(c);
			int i = 0;
			Boolean bool = t.firstRoll(null);
			for (Dice d : t.getHand()) {
				if (d.getFace() == Dice.Face.SKULL) i++;
			}
			if (i != 3) continue;
			//test case:
			assertTrue(t.getTheme() instanceof Normal);
			assertTrue(bool);
			break;
		}
	}
	
	@Test
	public void test_fitstRoll_3() {
		// 4 or more skulls - theme change to Skull Island can continue
		while(true) {
			Turn t = new Turn();
			FortuneCard c = new Skull(2);
			t.setCard(c);
			int i = 2;
			Boolean bool = t.firstRoll(null);
			for (Dice d : t.getHand()) {
				if (d.getFace() == Dice.Face.SKULL) i++;
			}
			if (i < 4) continue;
			//test case:
			assertTrue(t.getTheme() instanceof SkullIsland);
			assertFalse(bool);
			break;
		}
	}
	
	@Test
	public void test_fitstRoll_4() {
		// 4 or more skulls, but SeaBattle Card before - end turn
		while(true) {
			Turn t = new Turn();
			FortuneCard c = new entity.FortuneCard.SeaBattle(2);
			t.setCard(c);
			int i = 0;
			Boolean bool = t.firstRoll(null);
			for (Dice d : t.getHand()) {
				if (d.getFace() == Dice.Face.SKULL) i++;
			}
			if (i < 4) continue;
			//test case:
			assertTrue(t.getTheme() instanceof game.Theme.SeaBattle);
			assertTrue(bool);
			break;
		}
	}
	
	@Test
	public void test_endTurn() {
		//score calculation was tested in Theme tests
		//this if for test correctly mapping
		Turn t = new Turn();
		FortuneCard c = new entity.FortuneCard.Captain();
		t.setCard(c);
		int skull = 0,monkey = 0, parrot = 0, sword = 0, coin = 0, diamond = 0;
		ArrayList<Dice> ds = new ArrayList<Dice>();
		while (ds.size() < 8) {
			Dice d = new Dice();
			d.roll();
			switch (d.getFace()) {
			case SKULL: 
				skull++;
				break;
			case MONKEY:
				monkey++;
				break;
			case PARROT:
				parrot++;
				break;
			case SWORD:
				sword++;
				break;
			case COIN:
				coin++;
				break;
			case DIAMOND:
				diamond++;
				break;
			default: break;
			}
			ds.add(d);
		}
		int b = t.reroll(ds);
		t.endTurn();
		if (b == 0) {
			assertTrue(t.getTreasureInHand().get(Dice.Face.SKULL) == skull);
			assertTrue(t.getTreasureInHand().get(Dice.Face.MONKEY) == monkey);
			assertTrue(t.getTreasureInHand().get(Dice.Face.PARROT) == parrot);
			assertTrue(t.getTreasureInHand().get(Dice.Face.SWORD) == sword);
			assertTrue(t.getTreasureInHand().get(Dice.Face.COIN) == coin);
			assertTrue(t.getTreasureInHand().get(Dice.Face.DIAMOND) == diamond);
			t.setCard(new entity.FortuneCard.Gold());
			assertTrue(t.getTreasureInHand().get(Dice.Face.COIN) == coin+1);
			t.setCard(new entity.FortuneCard.Diamond());
			assertTrue(t.getTreasureInHand().get(Dice.Face.DIAMOND)==diamond+1);
			t.setCard(new entity.FortuneCard.Skull(2));
			assertTrue(t.getTreasureInHand().get(Dice.Face.SKULL) == skull+2);
		}else {
			assertTrue(t.getTreasureInHand().get(Dice.Face.SKULL) == skull);
			assertTrue(t.getTreasureInHand().get(Dice.Face.MONKEY) == 0);
			assertTrue(t.getTreasureInHand().get(Dice.Face.PARROT) == 0);
			assertTrue(t.getTreasureInHand().get(Dice.Face.SWORD) == 0);
			assertTrue(t.getTreasureInHand().get(Dice.Face.COIN) == 0);
			assertTrue(t.getTreasureInHand().get(Dice.Face.DIAMOND) == 0);
		}
	}
	
	@Test
	public void test_Chest_Feature() {
		Turn t = new Turn();
		HashSet<Integer> index = new HashSet<Integer>();
		index.add(8);
		index.add(-1);
		index.add(0);
		index.add(1);
		index.add(2);
		index.add(3);
		index.add(4);
		index.add(5);
		index.add(6);
		try {
			t.moveToChest(index);
			fail("no Chest now");
		}catch(game.ChestException e) {
			assertEquals(e.getCode(), 1);
			assertEquals(t.getHand().size(), 8);
			assertNull(t.getChest());
		}
		try {
			t.moveToHand(index);
			fail("no Chest now");
		}catch(game.ChestException e) {
			assertEquals(e.getCode(), 1);
			assertEquals(t.getHand().size(), 8);
			assertNull(t.getChest());
		}
		
		t.setCard(new TreasureChest());
		try {
			t.moveToChest(index);
			fail("exceed Hand size");
		}catch(game.ChestException e) {
			assertEquals(e.getCode(), 2);
			assertEquals(t.getHand().size(), 8);
			assertEquals(t.getChest().size(), 0);
		};
		try {
			t.moveToHand(index);
			fail("exceed Chest size");
		}catch(game.ChestException e) {
			assertEquals(e.getCode(), 2);
			assertEquals(t.getHand().size(), 8);
			assertEquals(t.getChest().size(), 0);
		};
		
		index.remove(6);
		try {
			t.moveToChest(index);
			fail("invalid index");
		}catch(game.ChestException e) {
			assertEquals(e.getCode(), 3);
			assertEquals(t.getHand().size(), 8);
			assertEquals(t.getChest().size(), 0);
		};
		index.remove(8);
		try {
			t.moveToChest(index);
			fail("invalid index");
		}catch(game.ChestException e) {
			assertEquals(e.getCode(), 3);
			assertEquals(t.getHand().size(),8);
			assertEquals(t.getChest().size() , 0);
		};
		
		index.remove(-1);
		try {
			t.moveToChest(index);
			fail("moving skulls");
		}catch(game.ChestException e) {
			assertEquals(e.getCode(), 4);
			assertEquals(t.getHand().size(),8);
			assertEquals(t.getChest().size(),0);
		};
		
		index.clear();
		ArrayList<Dice> ds = new ArrayList<Dice>();
		for (int i =0; i < 8;i++) {
			Dice d = new Dice();
			d.roll();
			if (d.getFace() != Dice.Face.SKULL) index.add(i);
			ds.add(d);
		}
		t.reroll(ds);
		try {
			t.moveToChest(index);
		} catch (ChestException e) {
			fail("Should not get Exception");
		}
		assertEquals(t.getChest().size(),index.size());
		assertEquals(t.getHand().size(), 8-index.size());
		
		index.clear();
		for (int i = 0; i < t.getChest().size()-1; i++) {
			index.add(i);
		}
		try {
			t.moveToHand(index);
		}catch(ChestException e) {
			fail("Should not get Exception");
		}
		//have "i < size - 1"
		assertEquals(t.getChest().size(),1);
		assertEquals(t.getHand().size(), 7);
	}
	
	@Test
	public void test_Lock_Feature() {
		Turn t = new Turn();
		HashSet<Integer> skullIndex = new HashSet<Integer>();
		HashSet<Integer> nonSkullIndex = new HashSet<Integer>();
		do {
			//to make sure at least 2 skulls or at least 2 non-skulls
			skullIndex.clear();
			nonSkullIndex.clear();
			t.reroll(null);
			for (int i = 0; i < 8; i++) {
				Dice d = t.getHand().get(i);
				if (d.isLock()) skullIndex.add(i);
				else nonSkullIndex.add(i);
			}
		}while(skullIndex.size() < 2 || nonSkullIndex.size() < 2);
		assertEquals(skullIndex.size()+nonSkullIndex.size(),8);
		
		//test auto filtering invalid indexes
		skullIndex.add(10);
		skullIndex.add(-1);
		nonSkullIndex.add(10);
		nonSkullIndex.add(-1);
		int lockedNum, unlockedNum;
		//lock all non-skull dice
		t.lock(nonSkullIndex);
		lockedNum=0;
		unlockedNum=0;
		for (Dice d:t.getHand()) {
			if (d.isLock()) lockedNum++;
			else unlockedNum++;
		}
		assertEquals(lockedNum, 8);
		assertEquals(unlockedNum, 0);
		
		//lock all skull dice -> nothing will be changed
		t.lock(skullIndex);
		lockedNum=0;
		unlockedNum=0;
		for (Dice d:t.getHand()) {
			if (d.isLock()) lockedNum++;
			else unlockedNum++;
		}
		assertEquals(lockedNum, 8);
		assertEquals(unlockedNum, 0);
		
		//unlock all non-skull dice
		t.unlock(nonSkullIndex);
		lockedNum=0;
		unlockedNum=0;
		for (Dice d:t.getHand()) {
			if (d.isLock()) lockedNum++;
			else unlockedNum++;
		}
		assertEquals(lockedNum, skullIndex.size()-2);
		assertEquals(unlockedNum, nonSkullIndex.size()-2);
		
		//unlock all skull dice (Fortune Card != Sorceress)
		//nothing will be changed
		t.unlock(skullIndex);
		lockedNum=0;
		unlockedNum=0;
		for (Dice d:t.getHand()) {
			if (d.isLock()) lockedNum++;
			else unlockedNum++;
		}
		assertEquals(lockedNum, skullIndex.size()-2);
		assertEquals(unlockedNum, nonSkullIndex.size()-2);
		
		//set to Sorceress Fortune Card
		t.setCard(new Sorceress());
		assertEquals(t.getskullRerollCount(), 1);
		
		//unlock more than one skulls -> only one skull will be unlocked
		t.unlock(skullIndex);
		assertEquals(t.getskullRerollCount(), 0);
		lockedNum=0;
		unlockedNum=0;
		for (Dice d:t.getHand()) {
			if (d.isLock()) lockedNum++;
			else unlockedNum++;
		}
		assertEquals(lockedNum, skullIndex.size()-2-1);
		assertEquals(unlockedNum, nonSkullIndex.size()-2+1);
		
		//lock a unlocked skull back
		t.lock(skullIndex);
		assertEquals(t.getskullRerollCount(), 1);
		lockedNum=0;
		unlockedNum=0;
		for (Dice d:t.getHand()) {
			if (d.isLock()) lockedNum++;
			else unlockedNum++;
		}
		assertEquals(lockedNum, skullIndex.size()-2);
		assertEquals(unlockedNum, nonSkullIndex.size()-2);
	}
}
