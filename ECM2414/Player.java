package ECM2414;

import java.util.ArrayList;
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

    public void play() {
        DeckCollection.draw(playerNumber);
    }

    private boolean checkIfWon() {
        for (DeckCollection.Deck.Card c : hand) {
            if (! (c.getValue() == hand.get(0).getValue())) {
                return false;
            }
        }
        return true;
    }

}
