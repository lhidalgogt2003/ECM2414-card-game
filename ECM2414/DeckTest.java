package ECM2414;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DeckTest {

	@Test
	void test() {
		Deck deck = new Deck(1);
		assertEquals(1, deck.getId());
		assertTrue(deck.isEmpty());
		assertEquals(0, deck.size());

		for (int i = 2; i < 5; i++) {
			deck.add(new Card(i));
		}
		assertFalse(deck.isEmpty());
		assertEquals(3, deck.size());
		for (int i = 2; i < 5; i++) {
			assertEquals(i, deck.draw().getFaceValue());
		}
		assertEquals(0, deck.size());
		assertTrue(deck.isEmpty());
		
	}

}
