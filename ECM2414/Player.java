package ECM2414;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Player is a class that implements runnable
 * gets the card from the card class
 * gives a hand to each player
 * and determines how has won the game
 * @author George Hynes, Luis Hidalgo
 * @version 1.0
 *
 */
public class Player implements Runnable {
	private final int id;
	private final ArrayList<Card> hand;
	private Deck left;
	private Deck right;
	private final GameState state;
	private final PrintWriter logger;

    /**
	 * gets the id, hand and state of the game of the player
     * and logs to the file
	 * @param id of the player to construct
     * @param state GameState of game
 	*/
	public Player(int id, GameState state) throws FileNotFoundException {
		this.id = id;
		this.hand = new ArrayList<>();
		this.state = state;
		logger = new PrintWriter(String.format("player%d.txt", id));
	}

	/**
	 * gets hand the player
	 * @return the hand of the player
	 */
	String getHand() {
		StringBuilder sb = new StringBuilder();
		for (Card c : hand) {
			sb.append(c);
			sb.append(' ');
		}
		return sb.toString();
	}

    /**
	 * adds a card to the hand of the player
	 * @param card to add
 	*/
	public void addCard(Card card) {
		hand.add(card);
	}

    /**
	 * sets the left deck
	 * @param deck to be the left deck
 	*/
	public void setLeftDeck(Deck deck) {
		left = deck;
	}

    /**
	 * sets the right deck
	 * @param deck to be the right deck
 	*/
	public void setRightDeck(Deck deck) {
		right = deck;
	}

    /**
	 * the method shuffles the cards, draws
     * the card from the left deck
     * and removes a card from the player hand
     * to the right deck
	 * @return the card removes
 	*/
	public Card drawCard() {
		Collections.shuffle(hand);
		Card card = hand.get(0);
		int removeIndex = 0;
		if (card.getFaceValue() == id) {
			for (int i = 1; i < 4; i++) {
				if (id != hand.get(i).getFaceValue()) {
					removeIndex = i;
				}
			}
		}
		return hand.remove(removeIndex);
	}
    /**
	 * checks if all the cards have the same face value
     * and if so returns true stating the player has won
     * the game
	 * @return if player has won
 	*/
	public boolean hasWon() {
		for (int i = 1; i < hand.size(); i++) {
			if (hand.get(i - 1).getFaceValue() != hand.get(i).getFaceValue()) {
				return false;
			}
		}
		return true;
	}
    /**
	 * Starts all the threads for the player
     * and stars the player movements in the game
     * drawing and discarding the cards from each deck
     * depending on the player Id
     * whilst checking if the player has won the game
 	*/
	@Override
	public void run() {
		logger.println(String.format("player %d initial hand %s", id, getHand()));
		while (!state.isOver()) {
			if (hasWon()) {
				logger.println(String.format("player %d wins ", id));
				state.setIsOver();
				state.setWonBy(id);
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
		int wonBy = state.getWonBy();
		if (wonBy != id) {
			logger.println(String.format("player %d has informed player %d that player %d has won", wonBy, id, wonBy));
		}
		logger.println(String.format("player %d exits ", id));
		logger.println(String.format("player %d final hand %s", id, getHand()));
		logger.close();
	}

    public int getID() {
		return id;
    }

	public Deck getLeftDeck() {
		return left;
	}

	public Deck getRightDeck() {
		return right;
	}
}
