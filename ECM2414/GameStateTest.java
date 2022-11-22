package ECM2414;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameStateTest {

	@Test
	void test() {
		GameState state = new GameState();
		assertFalse(state.isOver());
		state.setIsOver();
		assertTrue(state.isOver());
	}

}
