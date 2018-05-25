package View;

import Model.Player;

import javax.swing.*;
import java.awt.*;
import java.net.InetAddress;
import java.util.ArrayList;

/**
 * Class to launch the Game and set-up the required windows
 *
 * @author Team Delhi
 */
public class GameLauncher extends JFrame {
    private static final long serialVersionUID = -4649786985472599318L;
    public static GameLauncher frame = null;
    public Setup setup = new Setup();
    public Battle battle = new Battle();
    public Win win = new Win();
    public Lose lose = new Lose();
    public Home home = new Home();
    public Rules rules = new Rules();
    public Lobby lobby = new Lobby();
    public Client client = new Client();
    public ArrayList<String> statusLog = new ArrayList<String>();
    public Player localPlayer;
    public Player otherPlayer;
    public int[] ships;

    /**
     *
     */
    public GameLauncher() {

        // Set size of the frame to 1600 pixels wide and 900 pixels height
        setSize(1600, 900);
        // Set location of the frame to centre of the screen
        setLocationRelativeTo(null);
        // Give the frame a title to be displayed at the top of the window
        setTitle("Battleships");
        // Make sure players can't resize the window as all buttons/labels will
        // then be in wrong place
        setResizable(false);
        // Enable the exit button in top right to close the window
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Set dialog window colours
        new UIManager();
        UIManager.put("OptionPane.background", Color.BLACK);
        UIManager.put("OptionPane.messageForeground", new Color(80, 230, 250));
        UIManager.put("Panel.background", Color.BLACK);

        // Make the frame visible when the program is run
        setVisible(true);

        add(home);
        add(rules);
        add(lobby);
        add(battle);
        add(setup);
        add(win);
        add(lose);
        setContentPane(home);

        localPlayer = new Player("");
        otherPlayer = new Player("");
        this.ships = new int[100];

    }


    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            try {

                frame = new GameLauncher();
                frame.setVisible(true);

                if (args.length < 1) {
                    frame.client.setHost(InetAddress.getLocalHost().getHostAddress());
                    frame.client.setPort(8080);
                } else {
                    frame.client.setHost(args[0]);
                    frame.client.setPort(Integer.parseInt(args[1]));
                }

                frame.client.start();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }
}
