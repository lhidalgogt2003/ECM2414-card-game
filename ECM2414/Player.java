package ECM2414;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Each Player object stores the player's hand, which deck is on its 'left' and 'right'
 * and keeps track of the GameState to enable multi-threaded execution
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
	 * Constructs a Player object with the given id and GameState and
     * prepares a log file
	 * @param id of the player to construct
     * @param state GameState of game
 	*/
	public Player(int id, GameState state) throws FileNotFoundException {
		this.id = id;
		this.hand = new ArrayList<>();
		this.state = state;
		logger = new PrintWriter(String.format("player%d_output.txt", id));

	}

	/**
	 * get the Player's hand as String
	 * @return String hand of the player
	 */
	public String getHand() {
		StringBuilder sb = new StringBuilder();
		for (Card c : hand) {
			sb.append(c);
			sb.append(" ");
		}
		return sb.toString();
	}

    /**
	 * add a card to the Player's hand
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
	 * the method shuffles the hand, draws
     * the card from the left deck
     * and removes a card from the player hand
     * to the right deck
	 * @return the card removes
 	*/
	public Card drawCard() {
		Collections.shuffle(hand);
		Card card = hand.get(0);
		int removeIndex = 0;
		// don't discard a preferred card (ie. same as id or a card you already hold)
		if (card.getFaceValue() == id || doesHandContainThis(card)) {
			for (int i = 1; i < 4; i++) {
				// if you have to discard a card, make sure it's not the same as id
				if (id != hand.get(i).getFaceValue()) {
					removeIndex = i;
				}
			}
		}
		return hand.remove(removeIndex);
	}

    /**
	 * checks if all the cards have the same face value
     * and if so returns true indicating that the player has won the game
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
	 * Starts the player within the game
     * Logic for drawing and discarding the cards into and from the player's hand
     * whilst checking if the player has won the game and whether someone else has won the game
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
				hand.add(newCard);
				right.add(toDiscard);
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

	/**
	 * @return id of player
	 */
    public int getID() {
		return id;
    }

	/**
	 * @return deck to the 'left' of the player
	 */
	public Deck getLeftDeck() {
		return left;
	}

	/**
	 * @return deck to the 'right' of the player
	 */
	public Deck getRightDeck() {
		return right;
	}

	/**
	 * @return true if a card of the same face value is within the Player's hand
	 */
	private boolean doesHandContainThis(Card c) {
		for (Card card_in_hand: hand) {
			if (c.getFaceValue() == card_in_hand.getFaceValue()) {
				return true;
			}
		}
		return false;
	}
}
