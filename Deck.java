import java.util.ArrayList;
import java.util.Collections;

class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        // Add cards to the deck
        cards.add(new Card("Blue-Eyes White Dragon", 3000, 2500, "monster"));
        cards.add(new Card("Dark Magician", 2500, 2100, "monster"));
        // Add more cards here...
        Collections.shuffle(cards); // Shuffle the deck
    }

    public Card drawCard() {
        if (cards.size() > 0) {
            return cards.remove(0); // Remove the top card
        }
        return null; // No more cards to draw
    }

    public int remainingCards() {
        return cards.size();
    }
}
/*
class Deck {
    Card[] cards;

    public Deck(Card[] cards) {
        this.cards = cards;
    }

    public Card drawCard() {
        // Example of drawing the first card (simplified)
        return cards.length > 0 ? cards[0] : null;
    }
}
*/