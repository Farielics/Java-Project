import java.util.ArrayList;

class Player {
    private String name;
    private int lifePoints;
    private ArrayList<Card> hand;
    private Deck deck;
    private ArrayList<Card> monsterZone;

    public Player(String name) {
        this.name = name;
        this.lifePoints = 8000;
        this.hand = new ArrayList<>();
        this.deck = new Deck();
        this.monsterZone = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public void decreaseLifePoints(int amount) {
        this.lifePoints -= amount;
    }

    public void drawCard() {
        Card drawnCard = deck.drawCard();
        if (drawnCard != null) {
            hand.add(drawnCard);
        }
    }

    public void summonMonster(Card monster) {
        if (monsterZone.size() < 5) {  // Limit to 5 monsters
            monsterZone.add(monster);
        }
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public ArrayList<Card> getMonsterZone() {
        return monsterZone;
    }

    public Deck getDeck() {
        return deck;
    }
}
/*
class Player {
    String name;
    Deck deck;

    public Player(String name, Deck deck) {
        this.name = name;
        this.deck = deck;
    }

    public String getName() {
        return name;
    }

    public Deck getDeck() {
        return deck;
    }
}
    */