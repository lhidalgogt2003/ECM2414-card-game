package ECM2414;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



/**
 * CardGame is a multi-threaded application that
 * uses multiple threads to create a card game of
 * the number of players choosen by the user
 * 
 * @authors George Hynes, Luis Hidalgo
 * @version 1.0
 */


public class CardGame {

    /**
	 * starts the Card game and asks the user to enter the number of players
     * and where to save the information of the game
	 * @param String[] args
 	*/  
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
	/**
	 * sets a deck to the left and to the right
     * to the player with index i
	 * @param Player[] players 
     * @param Deck[] decks
 	*/  
	public static void assignDecks(Player[] players, Deck[] decks) {
		for (int i = 0; i < players.length; i++) {
			players[i].setLeftDeck(decks[i]);
			players[i].setRightDeck(decks[(i + 1) % decks.length]);
		}
	}
	
    /**
	 * distributes the cards to each player
     * checking the number of players
     * @param Deck pack
	 * @param Player[] players
     * @param Deck[] decks
 	*/  
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

    /**
	 * starts the number of player in the game
     * @param int n
	 * @param GameState state
     * @throws FileNotFoundExeption
     * @return a list of players
 	*/  
	public static Player[] initializePlayers(int n, GameState state) throws FileNotFoundException {
		Player[] players = new Player[n];
		for (int i = 0; i < n; i++) {
			players[i] = new Player(i + 1, state);
		}
		return players;
	}

    /**
	 * starts the number of decks in the game
     * @param int n
     * @return a list of decks
 	*/ 
	public static Deck[] initializeDecks(int n) {
		Deck[] decks = new Deck[n];
		for (int i = 0; i < n; i++) {
			decks[i] = new Deck(i + 1);
		}
		return decks;
	}

    /**
	 * loads the pack to play the game
     * @param String filename
	 * @param int n
     * @throws InvalidPackException
     * @return the pack to play the game
 	*/ 
	public static Deck loadPack(String filename, int n) throws InvalidPackException {
		Deck pack = new Deck(0);
		try (Scanner in = new Scanner(new File(filename))) {
			while (in.hasNext()) {
				pack.add(new Card(in.nextInt()));
			}
		} catch (FileNotFoundException e) {
			throw new InvalidPackException("File not found");
		}
		if (pack.size() != 8 * n) {
			throw new InvalidPackException("Invalid length");
		}
		return pack;
	}

}
