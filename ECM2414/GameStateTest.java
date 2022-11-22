package ECM2414;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameStateTest {

	@Test
	void test() {
		GameState state = new GameState();
		assertFalse(state.isOver());
		state.setIsOver();
		assertTrue(state.isOver());
	}

}
