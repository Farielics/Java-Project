class Card {
    String name;
    int attackPoints;
    int defensePoints;
    String cardType; // monster, spell, etc.
    boolean isInAttackMode = true;

    public Card(String name, int attackPoints, int defensePoints, String cardType) {
        this.name = name;
        this.attackPoints = attackPoints;
        this.defensePoints = defensePoints;
        this.cardType = cardType;
    }

    public String getName() {
        return name;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public int getDefensePoints() {
        return defensePoints;
    }

    public boolean isInAttackMode() {
        return isInAttackMode;
    }

    public void toggleMode() {
        this.isInAttackMode = !this.isInAttackMode;
    }
}
/*
class Card {
    String name;
    String type;
    String imagePath;

    public Card(String name, String type, String imagePath) {
        this.name = name;
        this.type = type;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getImagePath() {
        return imagePath;
    }
}
*/