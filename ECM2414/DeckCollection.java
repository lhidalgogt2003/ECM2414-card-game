package ECM2414;

import java.util.ArrayList;
import java.util.List;

public class DeckCollection {
     static class Deck {
        static class Card {
            private final int value;

            Card(int value){
                this.value = value;
            }

            public int getValue() {
                return value;
            }
        }
        static List<Card> cards = new ArrayList<>();
        public Deck() {

        }
        public Card draw() {
            return cards.remove(0);
        }
        public void discard(Card card) {
            cards.add(card);
        }
    }
    static List<Deck> decks = new ArrayList<>();
    public static Deck.Card draw(int playerNumber) {
        return decks.get(playerNumber).draw();
    }
    public void discard(int playerNumber, Deck.Card card) {
        if (playerNumber == decks.size()) {
            decks.get(0).discard(card);
        } else {
            decks.get(playerNumber+1).discard(card);
        }
    }

}
