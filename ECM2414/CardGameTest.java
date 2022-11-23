package ECM2414;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class CardGameTest {

	@Test
	void testPackLoad() {
		Deck pack;
		try {
			pack = CardGame.loadPack("four.txt", 4);
			assertEquals(32, pack.size());
		} catch (InvalidPackException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testDecksInitialisation() {
		Deck[] decks = CardGame.initialiseDecks(4);
		assertEquals(4, decks.length);
	}

	@Test
	void testPlayersInitialisation() {
		Player[] players;
		try {
			players = CardGame.initialisePlayers(4, new GameState());
			assertEquals(4, players.length);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testGameFinishesWithWinner() {
		int n = 4;
		Deck pack;
		try {
			pack = CardGame.loadPack("four.txt", n);
			GameState state = new GameState();
			Player[] players = CardGame.initialisePlayers(n, state);
			Deck[] decks = CardGame.initialiseDecks(n);

			CardGame.distributeCards(pack, players, decks);
			CardGame.assignDecks(players, decks);

			ExecutorService executorService = Executors.newFixedThreadPool(n);
			for (Player player : players) {
				executorService.execute(player);
			}
			executorService.shutdown();
			executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
			boolean hasWon = false;
			for (Player p : players) {
				hasWon = (hasWon || p.hasWon());
			}
			
			assertTrue(hasWon);
		} catch (InvalidPackException | InterruptedException | FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	void testFileNotFoundHandled() {
		InvalidPackException thrown = assertThrows(
				InvalidPackException.class,
		           () -> CardGame.loadPack("invalid.txt", 4),
		           "Expected loadPack() to throw an Invalid Pack Exception"
		    );
		assertTrue(thrown.getMessage().contains("File not found"));
	}
}
