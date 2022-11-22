package ECM2414;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PlayerTest {

	@Test
	void test() {
		GameState state = new GameState();
		try {
			Player player = new Player(1, state);
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
			
			player.addCard(new Card(3));
			player.addCard(new Card(1));
			player.addCard(new Card(2));
			player.addCard(new Card(1));
			
			assertEquals("3 1 2 1 ", player.getHand());
			assertFalse(player.hasWon());
			

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}

}
