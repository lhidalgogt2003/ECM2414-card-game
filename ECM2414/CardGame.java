package ECM2414;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



/**
 * CardGame is a multi-threaded application that
 * uses multiple threads to create a card game of
 * the number of players chosen by the user
 * 
 * @author George Hynes, Luis Hidalgo
 * @version 1.0
 */


public class CardGame {

    /**
	 * starts the Card game and asks the user to enter the number of players
     * and where to save the information of the game
	 * @param args runtime arguments
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
			Player[] players = initialisePlayers(n, state);
			Deck[] decks = initialiseDecks(n);
			
			distributeCards(pack, players, decks);
			assignDecks(players, decks);
			
			ExecutorService executorService = Executors.newFixedThreadPool(n);
			for (Player player : players) {
				executorService.execute(player);
			}
			executorService.shutdown();

			while (!state.isOver()) {
				Thread.sleep(1000);
			}

			System.out.printf("player %d wins%n", state.getWonBy());

			for (Deck d: decks) {
				if (d.size() > 0) {
					d.logHand();
				}
			}
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
	 * @param players 	players to be assigned left and right decks
     * @param decks		decks to assign to players
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
     * @param pack 		of cards to be distributed to players and to form decks
	 * @param players	to be given cards from the pack
     * @param decks		to receive remaining cards
 	*/  
	public static void distributeCards(Deck pack, Player[] players, Deck[] decks) {
		// distribute cards to players first in round-robin fashion
		for (int i = 0; i < 4; i++) {
			for (Player player : players) {
				player.addCard(pack.draw());
			}
		}

		// distribute remaining cards amongst all decks
		while (!pack.isEmpty()) {
			for (Deck deck : decks) {
				deck.add(pack.draw());
			}
		}
	}

    /**
	 * starts the number of player in the game
     * @param n			number of players
	 * @param state		GameState for players to use
     * @return a list of players
 	*/  
	public static Player[] initialisePlayers(int n, GameState state) throws FileNotFoundException {
		Player[] players = new Player[n];
		for (int i = 0; i < n; i++) {
			players[i] = new Player(i + 1, state);
		}
		return players;
	}

    /**
	 * starts the number of decks in the game
     * @param n players :. n decks
     * @return an array of decks
 	*/ 
	public static Deck[] initialiseDecks(int n) throws FileNotFoundException {
		Deck[] decks = new Deck[n];
		for (int i = 0; i < n; i++) {
			decks[i] = new Deck(i + 1);
		}
		return decks;
	}

    /**
	 * loads the pack to play the game
     * @param filename to load pack from
	 * @param n number of players the pack should have cards
     * @return the pack to play the game
 	*/ 
	public static Deck loadPack(String filename, int n) throws InvalidPackException, FileNotFoundException {
		Deck pack = new Deck(0);
		try (Scanner in = new Scanner(new File(filename))) {
			while (in.hasNext()) {
				pack.add(new Card(in.nextInt()));
			}
		} catch (FileNotFoundException e) {
			throw new InvalidPackException("File not found");
		} catch (InputMismatchException e) {
			throw new InvalidPackException("Invalid file contents");
		} catch (NullPointerException e) {
			throw new InvalidPackException("No filename provided");
		}
		if (pack.size() != 8 * n) {
			throw new InvalidPackException("Invalid length");
		}
		return pack;
	}

}
