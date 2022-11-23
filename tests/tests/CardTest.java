package tests;

import ECM2414.Card;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardTest {

	@Test
	void testCardConstruction() {
		Card card = new Card(8); 				// create Card with value 8
		assertEquals(8, card.getFaceValue());	// check value is 8
		assertEquals("8", card.toString());	// check value is converted
	}

}
