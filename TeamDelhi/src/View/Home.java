package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 
 * @author Team Delhi
 */
public class Home extends JPanel {
    private BufferedImage homeBG;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton login;


    Home() {
        setLayout(null);
        try {
            homeBG = ImageIO.read(getClass().getResource("/homeBG.jpg"));
        } catch (IOException e1) {
            setBackground(Color.BLACK);
        }

        // Username Label
        JLabel username = new JLabel("Username", SwingConstants.CENTER);
        username.setForeground(Color.BLACK);
        username.setFont(new Font("Arial", Font.BOLD, 22));
        username.setBounds(1150, 300, 300, 50);
        add(username);

        // Password Label
        JLabel password = new JLabel("Password", SwingConstants.CENTER);
        password.setForeground(Color.BLACK);
        password.setFont(new Font("Arial", Font.BOLD, 22));
        password.setBounds(1150, 400, 300, 50);
        add(password);

        // Username Input
        usernameField = new JTextField(" Enter Username");
        usernameField.setBounds(1150, 350, 300, 50);
        usernameField.setBackground(Color.BLACK);
        usernameField.setForeground(new Color(80, 230, 250));
        usernameField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                JTextField source = (JTextField) e.getComponent();
                source.setText("");
                source.removeFocusListener(this);
            }
        });
        add(usernameField);

        // Password Input
        passwordField = new JPasswordField();
        passwordField.setBounds(1150, 450, 300, 50);
        passwordField.setBackground(Color.BLACK);
        passwordField.setForeground(new Color(80, 230, 250));
        passwordField.addActionListener(e -> login.doClick());
        passwordField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                JTextField source = (JTextField) e.getComponent();
                source.setText("");
                source.removeFocusListener(this);
            }
        });
        add(passwordField);

        JPasswordField dummyFocus = new JPasswordField();
        dummyFocus.setBounds(1, 1, 1, 1);
        add(dummyFocus);

        // Register Button
        JButton register = new JButton("Join The Fleet");
        register.setFont(new Font("Arial", Font.BOLD, 30));
        register.setBounds(1150, 550, 300, 75);
        register.setBackground(Color.BLACK);
        register.setForeground(new Color(80, 230, 250));
        register.addActionListener((ActionEvent actionEvent) -> {
            String username1 = usernameField.getText().trim();
            char[] password1 = passwordField.getPassword();

            if (username1.equals("")) {
                JOptionPane.showMessageDialog(this, "Invalid username.", "Error message",
                        JOptionPane.ERROR_MESSAGE);
            } else if (username1.length() < 4) {
                JOptionPane.showMessageDialog(this, "Username must be at least 4 characters.", "Error message",
                        JOptionPane.ERROR_MESSAGE);
            } else if (password1.length < 5) {
                JOptionPane.showMessageDialog(this, "Password must be at least 5 characters.", "Error message",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                String[] response = GameLauncher.frame.client.signup(username1, new String(password1));
                assert response != null;
                JOptionPane.showMessageDialog(this, response[2], "Message from server",
                        JOptionPane.INFORMATION_MESSAGE);
                usernameField.setText("");
                passwordField.setText("");
            }
        });
        add(register);


        // Login Button
        login = new JButton("Start War");
        login.setFont(new Font("Arial", Font.BOLD, 30));
        login.setBounds(1150, 650, 300, 75);
        login.setBackground(Color.BLACK);
        login.setForeground(new Color(80, 230, 250));
        login.addActionListener((ActionEvent actionEvent) -> {
            String username1 = usernameField.getText().trim();
            char[] password1 = passwordField.getPassword();

            if (username1.equals("") || username1.length() < 4 || password1.length < 5) {
                JOptionPane.showMessageDialog(this, "Invalid information.", "Error message",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                String[] response = GameLauncher.frame.client.signin(username1, new String(password1));
                assert response != null;

                if (response[1].equals("true")) {
                    JOptionPane.showMessageDialog(this, response[2]);
                    GameLauncher.frame.localPlayer.setUserName(username1);
                    GameLauncher.frame.setContentPane(GameLauncher.frame.lobby);
                    (GameLauncher.frame.getContentPane()).revalidate();
                    GameLauncher.frame.repaint();
                    GameLauncher.frame.lobby.StartCheckingLobby();
                } else {
                    JOptionPane.showMessageDialog(this, response[2], "Error message",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(login);


        // Instructions Button
        JButton instructions = new JButton("Instructions");
        instructions.setFont(new Font("Arial", Font.BOLD, 30));
        instructions.setBounds(1150, 750, 300, 75);
        instructions.setBackground(Color.BLACK);
        instructions.setForeground(new Color(80, 230, 250));
        instructions.addActionListener((ActionEvent e) -> {
            GameLauncher.frame.setContentPane(GameLauncher.frame.rules);
            (GameLauncher.frame.getContentPane()).revalidate();
            GameLauncher.frame.repaint();
        });
        add(instructions);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(homeBG, 0, 0, null);
    }

}
