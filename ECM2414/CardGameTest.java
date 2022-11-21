package ECM2414;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

class CardGameTest {

	@Test
	void test1() {
		Deck pack;
		try {
			pack = CardGame.loadPack("four.txt", 4);
			assertEquals(32, pack.size());
		} catch (InvalidPackException e) {
			e.printStackTrace();
		}
	}

	@Test
	void test2() {
		Deck[] decks = CardGame.initializeDecks(4);
		assertEquals(4, decks.length);
	}

	@Test
	void test3() {
		Player[] players;
		try {
			players = CardGame.initializePlayers(4, new GameState());
			assertEquals(4, players.length);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	void test4() {
		int n = 4;
		Deck pack;
		try {
			pack = CardGame.loadPack("four.txt", n);
			GameState state = new GameState();
			Player[] players = CardGame.initializePlayers(n, state);
			Deck[] decks = CardGame.initializeDecks(n);

			CardGame.distributeCards(pack, players, decks);
			CardGame.assignDecks(players, decks);

			ExecutorService executorService = Executors.newFixedThreadPool(n);
			for (int i = 0; i < players.length; i++) {
				executorService.execute(players[i]);
			}
			executorService.shutdown();
			executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
			boolean hasWon = false;
			for (Player p : players) {
				hasWon = (hasWon || p.hasWon());
			}
			
			assertTrue(hasWon);
		} catch (InvalidPackException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	void test5() {
		InvalidPackException thrown = assertThrows(
				InvalidPackException.class,
		           () -> CardGame.loadPack("invalid.txt", 4),
		           "Expected loadPack() to throw an Invalid Pack Exception"
		    );
		    assertTrue(thrown.getMessage().contains(""));
	}
}
