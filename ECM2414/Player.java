package ECM2414;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Player implements Runnable {
	private int id;
	private ArrayList<Card> hand;
	private Deck left;
	private Deck right;
	private GameState state;
	private PrintWriter logger;

	public Player(int id, GameState state) throws FileNotFoundException {
		this.id = id;
		this.hand = new ArrayList<Card>();
		this.state = state;
		logger = new PrintWriter(new File(String.format("player%d.txt", id)));
	}

	private String getHand() {
		StringBuilder sb = new StringBuilder();
		for (Card c : hand) {
			sb.append(c);
			sb.append(' ');
		}
		return sb.toString();
	}

	public void addCard(Card card) {
		hand.add(card);
	}

	public void setLeftDeck(Deck deck) {
		left = deck;
	}

	public void setRightDeck(Deck deck) {
		right = deck;
	}

	public Card drawCard() {
		Collections.shuffle(hand);
		Card card = hand.get(0);
		int removeIndex = 0;
		if (card.getFaceValue() == id) {
			for (int i = 1; i < 4; i++) {
				if (id != hand.get(i).getFaceValue()) {
					card = hand.get(i);
					removeIndex = i;
				}
			}
		}
		return hand.remove(removeIndex);
	}

	public boolean hasWon() {
		for (int i = 1; i < hand.size(); i++) {
			if (hand.get(i - 1).getFaceValue() != hand.get(i).getFaceValue()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void run() {
		logger.println(String.format("player %d initial hand %s", id, getHand()));
		while (!state.isOver()) {
			if (hasWon()) {
				logger.println(String.format("player %d wins ", id));
				state.setIsOver();
			} else {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Card newCard;
				do {
					newCard = left.draw();
				} while (newCard == null);
				
				Card toDiscard = drawCard();
				right.add(toDiscard);
				hand.add(newCard);
				logger.println(String.format("player %d draws a %d from deck %d ", id, newCard.getFaceValue(), left.getId()));
				logger.println(String.format("player %d discards a %d to deck %d ", id, toDiscard.getFaceValue(), right.getId()));
				logger.println(String.format("player %d current hand %s", id, getHand()));
			}
		}
		logger.println(String.format("player %d exits ", id));
		logger.println(String.format("player %d final hand %s", id, getHand()));
		logger.close();

	}
}
