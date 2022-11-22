package ECM2414;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardTest {

	@Test
	void test() {
		Card card = new Card(8);
		assertEquals(8, card.getFaceValue());
		assertEquals("8", card.toString());
	}

}
