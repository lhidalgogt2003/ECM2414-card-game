package ECM2414;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;


/**
 * Player is a class that implements runnable 
 * gets the card from the card class 
 * gives a hand to each player
 * and determines how has won the game
 * @authors George Hynes, Luis Hidalgo
 * @version 1.0
 * 
 */

public class Player implements Runnable {
	private int id;
	private ArrayList<Card> hand;
	private Deck left;
	private Deck right;
	private GameState state;
	private PrintWriter logger;

    /**
	 * gets the id, hand and state of the game of the player
     * and logs to the file
	 * @param int id
     * @param GameState state
     * @throws FileNotFoundException
 	*/
	public Player(int id, GameState state) throws FileNotFoundException {
		this.id = id;
		this.hand = new ArrayList<Card>();
		this.state = state;
		logger = new PrintWriter(new File(String.format("player%d.txt", id)));
	}

    /**
	 * gets hand the player
	 * @return the hand of the player
 	*/    
	private String getHand() {
		StringBuilder sb = new StringBuilder();
		for (Card c : hand) {
			sb.append(c);
			sb.append(' ');
		}
		return sb.toString();
	}
    /**
	 * adds a card to the hand of the player
	 * @param Card card
 	*/  
	public void addCard(Card card) {
		hand.add(card);
	}
    /**
	 * sets the left deck
	 * @param Deck deck
 	*/  
	public void setLeftDeck(Deck deck) {
		left = deck;
	}
    /**
	 * sets the right deck
	 * @param Deck deck
 	*/  
	public void setRightDeck(Deck deck) {
		right = deck;
	}
    /**
	 * the method suffles the cards, draws
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
					card = hand.get(i);
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
}
