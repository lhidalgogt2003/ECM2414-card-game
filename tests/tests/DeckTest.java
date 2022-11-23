package tests;

import ECM2414.Card;
import ECM2414.Deck;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
	static Deck deck;

	@BeforeAll
	static void init() throws FileNotFoundException {
		deck = new Deck(1);
	}

	@Test
	void testDeckConstruction() {
		assertEquals(1, deck.getId());
		assertTrue(deck.isEmpty());
		assertEquals(0, deck.size());
		assertNull(deck.draw());
	}

	@Test
	void testAddingCardsToDeck() {
		for (int i = 2; i < 5; i++) {
			deck.add(new Card(i));
		}
		assertFalse(deck.isEmpty());
		assertEquals(3, deck.size());
	}

	@Test
	void testDrawingCardsFromDeck() {
		for (int i = 2; i < 5; i++) {
			assertEquals(i, deck.draw().getFaceValue());
		}
		assertEquals(0, deck.size());
		assertTrue(deck.isEmpty());
	}

}
