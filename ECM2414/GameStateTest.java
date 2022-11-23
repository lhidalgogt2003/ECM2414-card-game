package ECM2414;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameStateTest {
	static GameState state;

	@BeforeAll
	static void init() {
		state = new GameState();
	}

	@Test
	void testStateConstruction() {
		assertFalse(state.isOver());
	}

	@Test
	void testStateChange() {
		state.setIsOver();
		assertTrue(state.isOver());
	}

}
