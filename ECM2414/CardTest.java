package ECM2414;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CardTest {

	@Test
	void test() {
		Card card = new Card(8);
		assertEquals(8, card.getFaceValue());
		assertEquals("8", card.toString());
	}

}
