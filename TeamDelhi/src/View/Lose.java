package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 
 * 
 * @author Team Delhi
 */
@SuppressWarnings("serial")
public class Lose extends JPanel {

    private BufferedImage loseBG;


    public Lose() {
        setLayout(null);
        try {
            loseBG = ImageIO.read(getClass().getResource("/loseBG.jpg"));
        } catch (IOException e1) {
            setBackground(Color.BLACK);
        }

        // High Scores Label
        JLabel highScores = new JLabel("High Scores");
        highScores.setForeground(Color.BLACK);
        highScores.setFont(new Font("Arial", Font.BOLD, 30));
        highScores.setBounds(150, 450, 300, 50);
        add(highScores);

        // Profile Label
        JLabel profile = new JLabel("Profile");
        profile.setForeground(Color.BLACK);
        profile.setFont(new Font("Arial", Font.BOLD, 30));
        profile.setBounds(150, 600, 300, 50);
        add(profile);

        // High Scores display
        JTextField mockHSDisplay = new JTextField();
        mockHSDisplay.setBounds(150, 520, 700, 60);
        mockHSDisplay.setBackground(Color.BLACK);
        add(mockHSDisplay);

        // Profile display
        JPasswordField mockPDisplay = new JPasswordField();
        mockPDisplay.setBounds(150, 670, 700, 60);
        mockPDisplay.setBackground(Color.BLACK);
        add(mockPDisplay);


        // Fight Again Button
        JButton replay = new JButton("Fight Again");
        replay.setFont(new Font("Arial", Font.BOLD, 40));
        replay.setBounds(1100, 500, 350, 100);
        replay.setBackground(Color.BLACK);
        replay.setForeground(new Color(80, 230, 250));
        replay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameLauncher.frame.setContentPane(GameLauncher.frame.lobby);
                ((JPanel) GameLauncher.frame.getContentPane()).revalidate();
                GameLauncher.frame.repaint();
            }
        });
        add(replay);

        // Login Button - Need to be logged out/delete player etc
        JButton quit = new JButton("End War");
        quit.setFont(new Font("Arial", Font.BOLD, 40));
        quit.setBounds(1100, 650, 350, 100);
        quit.setBackground(Color.BLACK);
        quit.setForeground(new Color(80, 230, 250));
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameLauncher.frame.setContentPane(GameLauncher.frame.home);
                ((JPanel) GameLauncher.frame.getContentPane()).revalidate();
                GameLauncher.frame.repaint();
            }
        });
        add(quit);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(loseBG, 0, 0, null);
    }

}
