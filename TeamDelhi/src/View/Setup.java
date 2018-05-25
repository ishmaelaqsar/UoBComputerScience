package View;

import Model.Player;
import Model.ship;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

/**
 * 
 * 
 *
 * @author Team Delhi
 */
@SuppressWarnings("serial")
public class Setup extends JPanel {
    // private LoginRegister user;

    private String playerName = "Your Name";
    private String enemyName = "Enemy Name";
    private int currentShip;
    private Thread setUpThread;
    // Variables for the battle screen
    private BufferedImage battleBG, ocean, ship;
    private ImageIcon ocean2, ship2;
    private int i = 0, j = 0;// Used multiple times so easier to declare here
    private JButton[] b1Button, b2Button;// Array of buttons representing each grid in the battle screen
    private JLabel[] b1Top, b1Side, b2Top, b2Side;// Array of labels for the top and side of each grid in the battle screen
    private String[] labelTop = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};// Array of labels so each is added in correct place in battleScreen method
    private String[] labelSide = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};// Array of labels so each is added in correct place in battleScreen method
    private String[] options = {"Horizontal", "Vertical"};
    private String[] coordStrings = {"a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8", "a9", "a10", "b1", "b2", "b3", "b4", "b5",
            "b6", "b7", "b8", "b9", "b10", "c1", "c2", "c3", "c4", "c5", "c6", "c7", "c8", "c9", "c10", "d1", "d2", "d3",
            "d4", "d5", "d6", "d7", "d8", "d9", "d10", "e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9", "e10", "f1",
            "f2", "f3", "f4", "f5", "f6", "f7", "f8", "f9", "f10", "g1", "g2", "g3", "g4", "g5", "g6", "g7", "g8", "g9",
            "g10", "h1", "h2", "h3", "h4", "h5", "h6", "h7", "h8", "h9", "h10", "i1", "i2", "i3", "i4", "i5", "i6", "i7",
            "i8", "i9", "i10", "j1", "j2", "j3", "j4", "j5", "j6", "j7", "j8", "j9", "j10"};
    // Variables for in-game chat
    private JTextArea chatLogArea;
    private JTextArea gameStatus;
    private JButton msg1, msg2, msg3, msg4, msg5, msg6;
	private boolean gameSetUp = false;
    public JLabel pName;
    public JLabel eName;


    Setup() {
        setLayout(null);
        currentShip = 0;
        try {
            battleBG = ImageIO.read(getClass().getResource("/battleBG.jpg"));
            ocean = ImageIO.read(getClass().getResource("/ocean.jpg"));
            ship = ImageIO.read(getClass().getResource("/ship.jpg"));
        } catch (IOException e1) {
            setBackground(Color.BLACK);
        }

        ocean2 = new ImageIcon(ocean);
        ship2 = new ImageIcon(ship);

        this.gameStatus = new JTextArea(5, 20);
        gameStatus.setEditable(false);
        gameStatus.setLineWrap(true);
        gameStatus.setBackground(Color.BLACK);
        gameStatus.setForeground(new Color(80, 230, 250));
        gameStatus.setFont(new Font("Arial", Font.BOLD, 16));
        gameStatus.setText("Place your patrol boat");
        JScrollPane status = new JScrollPane(gameStatus);
        status.setBounds(0, 700, 800, 165);// Sets the x and y coordinate of the text field, and then the horizontal and vertical pixel length
        add(status);

        JButton rules = new JButton("Rules");
        rules.setBounds(740, 575, 120, 80);
        rules.setFont(new Font("Arial", Font.BOLD, 16));
        rules.setForeground(new Color(80, 230, 250));
        rules.setBackground(Color.BLACK);
        rules.addActionListener(e -> JOptionPane.showMessageDialog(null, "Each player must first place their ships by clicking on the left grid\nand choosing a horizontal or vertical alignment.\r\n" +
                "\r\n" +
                "There are 5 ships in your fleet:\r\n" +
                "Patrol Boat (Size 2)\r\n" +
                "Destroyer (Size 3)\r\n" +
                "Submarine (Size 3)\r\n" +
                "Battleship (Size 4)\r\n" +
                "Aircraft Carrier (Size 5)\r\n" +
                "\r\n" +
                "Once all ships are placed, take it in turns to attack\nyour opponent by clicking on the right grid. \r\n" +
                "\r\n" +
                "You must sink all of your opponents ships in order\nto win the game. Good Luck!\r\n"));
        add(rules);

        // Chat Window
        chatLogArea = new JTextArea(5, 20);
        chatLogArea.setEditable(false);
        chatLogArea.setLineWrap(true);
        chatLogArea.setBackground(Color.BLACK);
        chatLogArea.setForeground(new Color(80, 230, 250));
        chatLogArea.setFont(new Font("Arial", Font.BOLD, 16));
        chatLogArea.setText("Chat disabled until the war begins");
        JScrollPane message = new JScrollPane(chatLogArea);
        message.setBounds(800, 700, 400, 165);
        add(message);

        msg1 = new JButton("Good Match!");
        msg1.setBounds(1200, 700, 200, 55);
        msg1.setBackground(Color.BLACK);
        msg1.setForeground(new Color(80, 230, 250));
        msg1.setFont(new Font("Arial", Font.BOLD, 16));
        add(msg1);

        msg2 = new JButton("Lucky Hit!");
        msg2.setBounds(1400, 700, 200, 55);
        msg2.setBackground(Color.BLACK);
        msg2.setForeground(new Color(80, 230, 250));
        msg2.setFont(new Font("Arial", Font.BOLD, 16));
        add(msg2);

        msg3 = new JButton("Haha!");
        msg3.setBounds(1200, 755, 200, 55);
        msg3.setBackground(Color.BLACK);
        msg3.setForeground(new Color(80, 230, 250));
        msg3.setFont(new Font("Arial", Font.BOLD, 16));
        add(msg3);

        msg4 = new JButton("Don't Kill Please");
        msg4.setBounds(1400, 755, 200, 55);
        msg4.setBackground(Color.BLACK);
        msg4.setForeground(new Color(80, 230, 250));
        msg4.setFont(new Font("Arial", Font.BOLD, 16));
        add(msg4);

        msg5 = new JButton("One More Match?");
        msg5.setBounds(1200, 810, 200, 55);
        msg5.setBackground(Color.BLACK);
        msg5.setForeground(new Color(80, 230, 250));
        msg5.setFont(new Font("Arial", Font.BOLD, 16));
        add(msg5);

        msg6 = new JButton("Loser");
        msg6.setBounds(1400, 810, 200, 55);
        msg6.setBackground(Color.BLACK);
        msg6.setForeground(new Color(80, 230, 250));
        msg6.setFont(new Font("Arial", Font.BOLD, 16));
        add(msg6);

        disable();


        // Player Title
        this.pName = new JLabel(playerName, SwingConstants.CENTER);// Creates a label which will eventually display the players username -> also centres the text between the boundaries it is given
        pName.setForeground(Color.BLACK);// Sets the colour of the text to white
        pName.setFont(new Font("Arial", Font.BOLD, 28));// Sets the font to Arial, makes it bold and sets it to size 28
        pName.setBounds(0, 25, 800, 50);// Sets x coordinate to 0 and width to 800, which is half the size of the overall window -> this will ensure the player name text is centred on the left side of the window
        add(pName);// Adds the label to the window

        // Enemy Title
        this.eName = new JLabel(enemyName, SwingConstants.CENTER);// Creates a label which will eventually display the enemy's username -> also centres the text between the boundaries it is given
        eName.setForeground(Color.BLACK);// Sets the colour of the text to white
        eName.setFont(new Font("Arial", Font.BOLD, 28));// Sets the font to Arial, makes it bold and sets it to size 28
        eName.setBounds(800, 25, 800, 50);// Sets x coordinate to 800 and width to 800, which is half the size of the overall window -> this will ensure the player name text is centred on the right side of the window
        add(eName);// Adds the label to the window


        // Left board button grid
        i = 0;// Ensures that i starts at 0
        b1Button = new JButton[100];// Creates an array of size 100
        for (int x = 175; x < 675; x += 50) {// For x coordinates between 175 and 675, incrementing by 50 pixels each time and...
            for (int y = 150; y < 650; y += 50) {// ..for y coordinates between 150 and 650, incrementing by 50 pixels at a time...
                b1Button[i] = new JButton();// ...create a new JButton...
                b1Button[i].setBounds(x, y, 50, 50);// ...in the location of the current value of x and y, with dimensions 50 pixels by 50 pixels...
                b1Button[i].setRolloverEnabled(true);
                b1Button[i].setIcon(ocean2);// ...set the colour of the button to blue...
                b1Button[i].setRolloverIcon(ship2);
                b1Button[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (currentShip < 4) {

                            int index = Arrays.asList(b1Button).indexOf(e.getSource());
                            String coords = coordStrings[index];

                            int response = JOptionPane.showOptionDialog(null, "How should the ship be aligned?", "Alignment",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

                            if (response == 0) {
                                //"horizontal"
                                GameLauncher.frame.localPlayer.playerBoard.ships.get(currentShip).vertical = false;
                            } else if (response == 1) {
                                //"vertical";
                                GameLauncher.frame.localPlayer.playerBoard.ships.get(currentShip).vertical = true;
                            }


                            if (GameLauncher.frame.localPlayer.playerBoard.placeShip(GameLauncher.frame.localPlayer.playerBoard.ships.get(currentShip), coords)) {

                                shipArrPlacement(GameLauncher.frame.localPlayer.playerBoard.ships.get(currentShip), index);

                                if (currentShip == 0) {

                                    GameLauncher.frame.statusLog.add("Place your Patrol Boat!\n");
                                    GameLauncher.frame.statusLog.add("Patrol Boat placed successfully!\nPlace your Destroyer\n");
                                    updateStatusLog();

                                } else if (currentShip == 1) {

                                    GameLauncher.frame.statusLog.add("Destroyer placed successfully!\nPlace your Submarine\n");
                                    updateStatusLog();

                                } else if (currentShip == 2) {

                                    GameLauncher.frame.statusLog.add("Submarine placed successfully!\nPlace your Battleship\n");
                                    updateStatusLog();

                                } else if (currentShip == 3) {

                                    GameLauncher.frame.statusLog.add("Battleship placed successfully!\nPlace your Aircraft Carrier\n");
                                    updateStatusLog();

                                }


                                currentShip += 1;

                            } else {

                                GameLauncher.frame.statusLog.add("Ship did not place successfully!\n");
                                updateStatusLog();

                            }

                            GameLauncher.frame.localPlayer.playerBoard.printBoard();


                        } 
                        
                        else if (currentShip == 4) {

                            int index = Arrays.asList(b1Button).indexOf(e.getSource());
                            String coords = coordStrings[index];

                            int response = JOptionPane.showOptionDialog(null, "How should the ship be aligned?", "Alignment",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

                            if (response == 0) {
                                //"horizontal"
                                GameLauncher.frame.localPlayer.playerBoard.ships.get(currentShip).vertical = false;
                            } else if (response == 1) {
                                //"vertical";
                                GameLauncher.frame.localPlayer.playerBoard.ships.get(currentShip).vertical = true;
                            }


                            if (GameLauncher.frame.localPlayer.playerBoard.placeShip(GameLauncher.frame.localPlayer.playerBoard.ships.get(currentShip), coords)) {

                                shipArrPlacement(GameLauncher.frame.localPlayer.playerBoard.ships.get(currentShip), index);

                                GameLauncher.frame.statusLog.add("Aircraft Carrier placed successfully!\nLet's go to war!\n");
                                updateStatusLog();
                                GameLauncher.frame.battle.updateStatusLog();

                                currentShip += 1;
                                updatePlayerBoard();

                                StartCheckingSetUp();

                            } else {

                                GameLauncher.frame.statusLog.add("Ship placement was blocked!\n");
                                updateStatusLog();

                            }

                            GameLauncher.frame.localPlayer.playerBoard.printBoard();
                            System.out.println(currentShip);

                        }

                        updatePlayerBoard();

                    }
                });

                add(b1Button[i]);// ...and add the button to the window
                i++;// Then increment i so each button is stored from indexes 0-99 in the array
            }
        }

        // Right board button grid
        i = 0;// same as above
        b2Button = new JButton[100];// Same as above...
        for (int x = 975; x < 1475; x += 50) {// ...this time for right hand side of board so new x coordinates...
            for (int y = 150; y < 650; y += 50) {// ...but y coordinates stay the same
                b2Button[i] = new JButton();// same as above
                b2Button[i].setBounds(x, y, 50, 50);// same as above
                b2Button[i].setIcon(ocean2);// same as above
                b2Button[i].setDisabledIcon(ocean2);
                b2Button[i].setEnabled(false);
                add(b2Button[i]);// same as above
                i++;// same as above
            }
        }


        // Left board top labels
        i = 0;
        j = 0;
        b1Top = new JLabel[10];
        for (int x = 175; x < 675; x += 50) {
            b1Top[i] = new JLabel(labelTop[j], SwingConstants.CENTER);
            b1Top[i].setForeground(Color.BLACK);
            b1Top[i].setFont(new Font("Arial", Font.BOLD, 20));
            b1Top[i].setBounds(x, 100, 50, 50);
            add(b1Top[i]);
            i++;
            j++;
        }

        // Left board side labels
        i = 0;
        j = 0;
        b1Side = new JLabel[10];
        for (int y = 150; y < 650; y += 50) {
            b1Side[i] = new JLabel(labelSide[j], SwingConstants.CENTER);
            b1Side[i].setForeground(Color.BLACK);
            b1Side[i].setFont(new Font("Arial", Font.BOLD, 20));
            b1Side[i].setBounds(125, y, 50, 50);
            add(b1Side[i]);
            i++;
            j++;
        }

        // Right board top labels
        i = 0;
        j = 0;
        b2Top = new JLabel[10];
        for (int x = 975; x < 1475; x += 50) {
            b2Top[i] = new JLabel(labelTop[j], SwingConstants.CENTER);
            b2Top[i].setForeground(Color.BLACK);
            b2Top[i].setFont(new Font("Arial", Font.BOLD, 20));
            b2Top[i].setBounds(x, 100, 50, 50);
            add(b2Top[i]);
            i++;
            j++;
        }

        // Right board side labels
        i = 0;
        j = 0;
        b2Side = new JLabel[10];
        for (int y = 150; y < 650; y += 50) {
            b2Side[i] = new JLabel(labelSide[j], SwingConstants.CENTER);
            b2Side[i].setForeground(Color.BLACK);
            b2Side[i].setFont(new Font("Arial", Font.BOLD, 20));
            b2Side[i].setBounds(925, y, 50, 50);
            add(b2Side[i]);
            i++;
            j++;
        }
    }


    private void updatePlayerBoard() {//Remove for loop and replace k with index for individual button changes -> more efficient than checking entire array

        for (int k = 0; k < 100; k++) {

            if (GameLauncher.frame.ships[k] == 1) {

                b1Button[k].setIcon(ship2);
                b1Button[k].setEnabled(false);
                b1Button[k].setDisabledIcon(ship2);

                GameLauncher.frame.battle.b1Button[k].setIcon(ship2);
                GameLauncher.frame.battle.b1Button[k].setEnabled(false);
                GameLauncher.frame.battle.b1Button[k].setDisabledIcon(ship2);

            }
        }
    }


    public boolean shipsPlaced(int[] ships) {//shipsPlaced method is different from...

        for (Model.ship ship : GameLauncher.frame.localPlayer.playerBoard.ships) {

            if (!ship.placed) {

                return false;

            }
        }

        return true;

    }


    private void shipArrPlacement(ship ship, int index) {

        int size = ship.getSize();
        boolean vertical = ship.vertical;

        if (vertical) {

            for (int i = 0; i < size; i++) {

                GameLauncher.frame.ships[(index + i)] = 1;

            }
        } else {

            for (int i = 0; i < size; i++) {

                GameLauncher.frame.ships[index + (i * 10)] = 1;

            }
        }
    }


    private void updateStatusLog() {

        String message = "";
        for (String a : GameLauncher.frame.statusLog) {
            message += a;
            gameStatus.setText(message);
        }

    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(battleBG, 0, 0, null);
    }



    private void StartCheckingSetUp() {
        setUpThread = new Thread(() -> {
            while (!gameSetUp ) {
                String[] response = GameLauncher.frame.client.shipsPlaced();
                Object player;

                if (response[1].equals("true")) {
                    try {
                        if ((player = GameLauncher.frame.client.sendShips()) instanceof Player) {
                            GameLauncher.frame.localPlayer = (Player) player;

                            JOptionPane.showMessageDialog(null, "Ship placement completed.\nTake turns to attack by clicking on the right grid.");
                            GameLauncher.frame.setContentPane(GameLauncher.frame.battle);
                            GameLauncher.frame.getContentPane().revalidate();
                            GameLauncher.frame.repaint();
                            
                            stopCheckingLobby();
                            GameLauncher.frame.battle.StartCheckingBattle();
                        }
                    } catch (HeadlessException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        setUpThread.start();
    }

    public void stopCheckingLobby() {
        gameSetUp = true;
    }

    public void disable() {
        msg1.setEnabled(false);
        msg2.setEnabled(false);
        msg3.setEnabled(false);
        msg4.setEnabled(false);
        msg5.setEnabled(false);
        msg6.setEnabled(false);
    }

}
