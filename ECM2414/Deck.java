package ECM2414;

import java.util.ArrayList;


/**
 * Deck is a class that 
 * gets the id of each card and stores them
 * in an arraylist and checks if there
 * are no cards in the deck
 * @author George Hynes, Luis Hidalgo
 * @version 1.0
 * 
 */

public class Deck {
	private final int id;
	private final ArrayList<Card> cards;
	
	/**
	 * gets the id of each card into an array list
	 * @param id of deck for construction
 	*/  
	public Deck(int id) {
		this.id = id;
		cards = new ArrayList<>();
	}

	/**
	 * gets the id of the card
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
	 * synchronises the arraylist of cards
	 * and adds a card to the deck
	 * @param card to add to deck
 	*/  
	public void add(Card card) {
		synchronized (cards) {
			cards.add(card);
		}
	}
	/**
	 * removes a card to the deck to the right
	 * @return the index of the card removed
 	*/  
	public Card draw() {
		synchronized (cards) {
			if (cards.isEmpty()) {
				return null;
			}
			return cards.remove(0);
		}
	}
	/**
	 * checks the size of the arraylist 
	 *  of cards
	 * @return size of the arraylist cards
 	*/  
	public int size() {
		return cards.size();
	}
}
