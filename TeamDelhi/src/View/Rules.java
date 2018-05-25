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
public class Rules extends JPanel {
    private BufferedImage rulesBG;

    public Rules() {
        setLayout(null);
        try {
            rulesBG = ImageIO.read(getClass().getResource("/rulesBG.jpg"));
        } catch (IOException e1) {
            setBackground(Color.BLACK);
        }

        JButton back = new JButton("Back");
        back.setFont(new Font("Arial", Font.BOLD, 28));
        back.setBounds(700, 805, 200, 50);
        back.setBackground(Color.BLACK);
        back.setForeground(new Color(80, 230, 250));
        add(back);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameLauncher.frame.setContentPane(GameLauncher.frame.home);
                ((JPanel) GameLauncher.frame.getContentPane()).revalidate();
                GameLauncher.frame.repaint();
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(rulesBG, 0, 0, null);
    }

}
