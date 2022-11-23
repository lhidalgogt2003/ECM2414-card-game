package ECM2414;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;


/**
 * Deck is a class with the primary function of storing an array of cards
 * @author George Hynes, Luis Hidalgo
 * @version 1.0
 * 
 */

public class Deck {
	private final int id;
	private final ArrayList<Card> cards;
	private PrintWriter logger = null;
	
	/**
	 * Constructs a deck object with given id and prepares a log file
	 * @param id of deck for construction
 	*/  
	public Deck(int id) throws FileNotFoundException {
		this.id = id;
		cards = new ArrayList<>();

		if (id > 0) {
			// because of zero-indexing, this deck won't need a logfile
			logger = new PrintWriter(String.format("deck%d_output.txt", id));
		}
	}

	/**
	 * @return id of the card
 	*/  
	public int getId() {
		return id;
	}

	/**
	 * checks if the deck of cards is empty
	 * @return if player has won
 	*/  
	public boolean isEmpty() {
		return cards.isEmpty();
	}

	/**
	 * adds a card to the deck
	 * @param card to add to deck
 	*/  
	public void add(Card card) {
		synchronized (cards) {
			cards.add(card);
		}
	}

	/**
	 * removes a card from the deck
	 * @return the card removed
 	*/  
	public Card draw() {
		synchronized (cards) {
			if (cards.isEmpty()) {
				return null;
			}
			return cards.remove(0); }
	}

	/**
	 * @return number of cards in deck
 	*/  
	public int size() {
		return cards.size();
	}

	/**
	 * logs deck to file
	 */
	public void logHand() {
		if (logger != null) {
			StringBuilder sb = new StringBuilder();
			for (Card c : cards) {
				sb.append(c.toString());
				sb.append(" ");
			}
			logger.println(String.format("deck %d contents: %s", id, sb));
			logger.close();
		}
	}
}
