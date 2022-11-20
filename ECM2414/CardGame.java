package ECM2414;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CardGame {

	public static void main(String[] args) {
		try {
			Scanner in = new Scanner(System.in);
			System.out.println("Please enter the number of players: ");
			int n = in.nextInt();
			in.nextLine();
			System.out.println("Please enter location of pack to load: ");
			String filename = in.nextLine();
			Deck pack = loadPack(filename, n);
			GameState state = new GameState();
			Player[] players = initializePlayers(n, state);
			Deck[] decks = initializeDecks(n);
			
			distributeCards(pack, players, decks);
			assignDecks(players, decks);
			
			 ExecutorService executorService = Executors.newFixedThreadPool(n);
			 for (int i = 0; i < players.length; i++) {
				 executorService.execute(players[i]);
			 }
			 executorService.shutdown();
			 while (!state.isOver()) {
			 	Thread.sleep(1000);
			 }
			 System.out.printf("player %d wins%n", state.getWonBy());
			 in.close();
		} catch (InvalidPackException | FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void assignDecks(Player[] players, Deck[] decks) {
		for (int i = 0; i < players.length; i++) {
			players[i].setLeftDeck(decks[i]);
			players[i].setRightDeck(decks[(i + 1) % decks.length]);
		}
	}
	
	public static void distributeCards(Deck pack, Player[] players, Deck[] decks) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < players.length; j++) {
				players[j].addCard(pack.draw());
			}
		}
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < decks.length; j++) {
				decks[j].add(pack.draw());
			}
		}
	}

	public static Player[] initializePlayers(int n, GameState state) throws FileNotFoundException {
		Player[] players = new Player[n];
		for (int i = 0; i < n; i++) {
			players[i] = new Player(i + 1, state);
		}
		return players;
	}

	public static Deck[] initializeDecks(int n) {
		Deck[] decks = new Deck[n];
		for (int i = 0; i < n; i++) {
			decks[i] = new Deck(i + 1);
		}
		return decks;
	}

	public static Deck loadPack(String filename, int n) throws InvalidPackException {
		Deck pack = new Deck(0);
		try (Scanner in = new Scanner(new File(filename))) {
			while (in.hasNext()) {
				pack.add(new Card(in.nextInt()));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (pack.size() != 8 * n) {
			throw new InvalidPackException();
		}
		return pack;
	}

}
