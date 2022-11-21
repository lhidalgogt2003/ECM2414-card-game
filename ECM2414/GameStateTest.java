package ECM2414;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameStateTest {

	@Test
	void test() {
		GameState state = new GameState();
		assertEquals(false, state.isOver());
		state.setIsOver();
		assertEquals(true, state.isOver());
	}

}
