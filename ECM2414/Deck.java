package ECM2414;

import java.util.ArrayList;

public class Deck {
	private int id;
	private ArrayList<Card> cards;
	
	public Deck(int id) {
		this.id = id;
		cards = new ArrayList<Card>();
	}
	
	public int getId() {
		return id;
	}
	
	public boolean isEmpty() {
		return cards.isEmpty();
	}
	
	public void add(Card card) {
		synchronized (cards) {
			cards.add(card);
		}
	}
	
	public Card draw() {
		synchronized (cards) {
			if (cards.isEmpty()) {
				return null;
			}
			return cards.remove(0);
		}
	}

	public int size() {
		return cards.size();
	}
}
