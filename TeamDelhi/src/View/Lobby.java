package View;

import javax.swing.*;
import java.awt.*;
/**
 * 
 * @author Team Delhi
 */
public class Lobby extends JPanel {

    private Thread lobbyThread;
    private JList<String> playerList;
    private String selected;
    private boolean checkLobby = true;

    Lobby() {
        setLayout(null);
        setBackground(Color.BLACK);

        // Object[]
        playerList = new JList<>();
        DefaultListModel<String> players = new DefaultListModel<>();
        playerList.setModel(players);
        playerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        playerList.setLayoutOrientation(JList.VERTICAL);
        playerList.setVisibleRowCount(-1);
        playerList.setBackground(Color.BLACK);
        playerList.setForeground(new Color(80, 230, 250));
        playerList.setFont(new Font("Consolas", Font.BOLD, 26));

        JScrollPane status = new JScrollPane(playerList);
        status.setBounds(100, 150, 700, 650);
        add(status);

        JLabel lobbyTitle = new JLabel("Battleships Lobby", SwingConstants.CENTER);
        lobbyTitle.setForeground(new Color(80, 230, 250));
        lobbyTitle.setFont(new Font("Consolas", Font.BOLD, 48));
        lobbyTitle.setBounds(0, 0, 1600, 100);
        add(lobbyTitle);

        JButton invite = new JButton("Invite");
        invite.setBounds(900, 150, 200, 75);
        invite.setFont(new Font("Consolas", Font.BOLD, 32));
        invite.setBackground(Color.BLACK);
        invite.setForeground(new Color(80, 230, 250));
        invite.setEnabled(false);
        add(invite);

        JButton leave = new JButton("Leave");
        leave.setBounds(1200, 150, 200, 75);
        leave.setFont(new Font("Consolas", Font.BOLD, 32));
        leave.setBackground(Color.BLACK);
        leave.setForeground(new Color(80, 230, 250));
        add(leave);

        playerList.addListSelectionListener(e -> {
            selected = playerList.getSelectedValue();
            invite.setEnabled(true);
        });

        invite.addActionListener(e -> {
            // if invite already sent, disable button for 30 seconds
            GameLauncher.frame.client.invitePlayer(selected);
 
        });

        leave.addActionListener(e -> {
            stopCheckingLobby();
            GameLauncher.frame.client.signout(GameLauncher.frame.localPlayer.getUserName());

            GameLauncher.frame.setContentPane(GameLauncher.frame.home);
            (GameLauncher.frame.getContentPane()).revalidate();
            GameLauncher.frame.repaint();
        });

    }


    public void StartCheckingLobby() {

        lobbyThread = new Thread(() -> {

            while (checkLobby) {

                String[] response = GameLauncher.frame.client.getResponse();

                if (response[0].equals("update-lobby")) {

                    DefaultListModel<String> model = new DefaultListModel<>();

                    if (response.length != 1) {
                        for (int i = 1; i < response.length; i++) {
                            model.addElement(response[i]);
                        }
                    }

                    playerList.setModel(model);
                } else if (response[0].equals("send-invite") && response[1].equals("true") && response[2].equals("Match Started.")) {

                    GameLauncher.frame.setContentPane(GameLauncher.frame.setup);
                    (GameLauncher.frame.getContentPane()).revalidate();
                    GameLauncher.frame.repaint();

                    stopCheckingLobby();

                }
            }

        });

        lobbyThread.start();

    }

    private void stopCheckingLobby() {
        checkLobby = false;
    }

}
