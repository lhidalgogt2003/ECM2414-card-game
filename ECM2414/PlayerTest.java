package ECM2414;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
	static GameState state;
	static Player player;

	@BeforeEach
	void init() {
		state = new GameState();
		try {
			player = new Player(1, state);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testPlayerConstruction() {
		assertEquals(1, player.getID());
	}

	@Test
	void testDeckAssign() {
		Deck left = new Deck(0);
		for (int i = 2; i < 5; i++) {
			left.add(new Card(i));
		}
		left.add(new Card(1));
		Deck right = new Deck(0);
		for (int i = 6; i < 9; i++) {
			right.add(new Card(i));
		}
		right.add(new Card(1));

		player.setLeftDeck(left);
		player.setRightDeck(right);
		assertEquals(left, player.getLeftDeck());
		assertEquals(right, player.getRightDeck());
	}

	@Test
	void testPlayerHandLogic() {
		player.addCard(new Card(3));
		player.addCard(new Card(1));
		player.addCard(new Card(2));
		player.addCard(new Card(1));

		assertEquals("3 1 2 1 ", player.getHand());
		assertFalse(player.hasWon());
	}

	@Test
	void testPlayerWinLogic() {
		player.addCard(new Card(1));
		player.addCard(new Card(1));
		player.addCard(new Card(1));
		player.addCard(new Card(1));
		assertTrue(player.hasWon());
	}

}
