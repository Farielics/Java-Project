import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class YuGiOhDuelGameGUI extends JFrame {
    private Player player1;
    private Player player2;
    private JLabel lifePointsLabel1;
    private JLabel lifePointsLabel2;
    private JTextArea gameLog;
    private JButton[] monsterZoneP1;
    private JButton[] monsterZoneP2;
    
    public YuGiOhDuelGameGUI() {
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");

        setTitle("Yu-Gi-Oh Duel!");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create the duel field layout (3x5 grid for each player)
        JPanel fieldPanel = new JPanel(new GridLayout(2, 1));

        // Player 1 Zone
        JPanel player1Zone = new JPanel(new BorderLayout());
        lifePointsLabel1 = new JLabel("Player 1 Life Points: " + player1.getLifePoints());
        player1Zone.add(lifePointsLabel1, BorderLayout.NORTH);

        JPanel player1MonsterZone = new JPanel(new GridLayout(1, 5));
        monsterZoneP1 = new JButton[5];  // Player 1 monster zones
        for (int i = 0; i < 5; i++) {
            monsterZoneP1[i] = new JButton("Empty");
            player1MonsterZone.add(monsterZoneP1[i]);
        }
        player1Zone.add(player1MonsterZone, BorderLayout.CENTER);
        fieldPanel.add(player1Zone);

        // Player 2 Zone
        JPanel player2Zone = new JPanel(new BorderLayout());
        lifePointsLabel2 = new JLabel("Player 2 Life Points: " + player2.getLifePoints());
        player2Zone.add(lifePointsLabel2, BorderLayout.NORTH);

        JPanel player2MonsterZone = new JPanel(new GridLayout(1, 5));
        monsterZoneP2 = new JButton[5];  // Player 2 monster zones
        for (int i = 0; i < 5; i++) {
            monsterZoneP2[i] = new JButton("Empty");
            player2MonsterZone.add(monsterZoneP2[i]);
        }
        player2Zone.add(player2MonsterZone, BorderLayout.CENTER);
        fieldPanel.add(player2Zone);

        // Game Log for Messages
        gameLog = new JTextArea();
        gameLog.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(gameLog);

        // Control Button
        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startDuel();
            }
        });

        // Layout the main frame
        getContentPane().add(fieldPanel, BorderLayout.CENTER);
        getContentPane().add(logScrollPane, BorderLayout.EAST);
        getContentPane().add(startButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void startDuel() {
        gameLog.append("The duel has started!\n");
        // Start with each player drawing 5 cards
        for (int i = 0; i < 5; i++) {
            player1.drawCard();
            player2.drawCard();
        }
        updateLifePoints();
        playTurn(player1);  // Player 1 starts
    }

    private void playTurn(Player player) {
        gameLog.append(player.getName() + "'s turn!\n");

        // Summon a monster from the player's hand
        if (!player.getHand().isEmpty()) {
            Card monster = player.getHand().get(0);  // Simplified: first card in hand
            if (monster.cardType.equals("monster")) {
                player.summonMonster(monster);
                placeMonsterOnBoard(player, monster);
                gameLog.append(player.getName() + " summoned " + monster.getName() + "\n");
            }
        }
        
        // Next, switch to the opponent's turn
        if (player == player1) {
            playTurn(player2);  // Switch to player 2
        } else {
            playTurn(player1);  // Switch to player 1
        }
    }

    private void placeMonsterOnBoard(Player player, Card monster) {
        JButton[] monsterZone = player == player1 ? monsterZoneP1 : monsterZoneP2;
        for (int i = 0; i < 5; i++) {
            if (monsterZone[i].getText().equals("Empty")) {
                monsterZone[i].setText(monster.getName() + " (ATK: " + monster.getAttackPoints() + ")");
                break;
            }
        }
    }

    private void updateLifePoints() {
        lifePointsLabel1.setText("Player 1 Life Points: " + player1.getLifePoints());
        lifePointsLabel2.setText("Player 2 Life Points: " + player2.getLifePoints());
    }

    public static void main(String[] args) {
        new YuGiOhDuelGameGUI();
    }
}