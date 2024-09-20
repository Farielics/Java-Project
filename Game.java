import java.util.Scanner;

class Game {
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Player opponent;

    public Game(String player1Name, String player2Name) {
        player1 = new Player(player1Name);
        player2 = new Player(player2Name);
        currentPlayer = player1;
        opponent = player2;
    }

    public void startGame() {
        System.out.println("Duel Start: " + player1.getName() + " vs " + player2.getName());
        // Draw initial cards
        for (int i = 0; i < 5; i++) {
            player1.drawCard();
            player2.drawCard();
        }

        // Start turn loop
        while (player1.getLifePoints() > 0 && player2.getLifePoints() > 0) {
            playTurn(currentPlayer);
            switchPlayers();  // Alternate turns
        }

        // Declare winner
        if (player1.getLifePoints() <= 0) {
            System.out.println(player2.getName() + " wins the duel!");
        } else {
            System.out.println(player1.getName() + " wins the duel!");
        }
    }

    private void playTurn(Player player) {
        System.out.println(player.getName() + "'s turn!");

        // Draw Phase
        player.drawCard();
        System.out.println("Draw Phase: " + player.getName() + " draws a card.");

        // Main Phase
        Scanner scanner = new Scanner(System.in);
        System.out.println("Your hand:");
        for (int i = 0; i < player.getHand().size(); i++) {
            System.out.println((i + 1) + ": " + player.getHand().get(i).getName());
        }
        System.out.println("Choose a monster to summon (enter number): ");
        int choice = scanner.nextInt() - 1;

        if (choice >= 0 && choice < player.getHand().size()) {
            Card monster = player.getHand().get(choice);
            if (monster.cardType.equals("monster")) {
                player.summonMonster(monster);
                System.out.println(monster.getName() + " has been summoned!");
            }
        }

        // Battle Phase
        if (!opponent.getMonsterZone().isEmpty()) {
            Card opponentMonster = opponent.getMonsterZone().get(0);
            System.out.println("You attack " + opponentMonster.getName());
            if (player.getMonsterZone().get(0).getAttackPoints() > opponentMonster.getAttackPoints()) {
                System.out.println("Attack successful! " + opponentMonster.getName() + " is destroyed.");
                opponent.getMonsterZone().remove(0);
                opponent.decreaseLifePoints(500); // Simplified damage
                System.out.println(opponent.getName() + " loses 500 life points.");
            } else {
                System.out.println("Attack failed.");
            }
        }

        // End Phase
        System.out.println("End of " + player.getName() + "'s turn.\n");
    }

    private void switchPlayers() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
            opponent = player1;
        } else {
            currentPlayer = player1;
            opponent = player2;
        }
    }
}
/*
class Game {
    Player player1;
    Player player2;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void startGame() {
        System.out.println("Duel Start: " + player1.getName() + " vs " + player2.getName());
        // Additional game logic (simplified)
    }
}
    */