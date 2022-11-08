package ECM2414;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
    List<DeckCollection.Deck.Card> hand = new ArrayList<>();
    private final int playerNumber;

    Player(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public void receive(DeckCollection.Deck.Card c) {
        hand.add(c);
    }

    public synchronized void main() {

    }

    private boolean checkIfWon() {
        return hand.isEmpty() || Collections.frequency(hand, hand.get(0)) == hand.size();
    }

}
