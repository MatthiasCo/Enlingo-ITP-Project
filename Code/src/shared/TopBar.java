package shared;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import mainMenu.MainMenuControl;


public class TopBar extends JPanel implements ActionListener{
    private MainMenuControl mainMenuControl;

    public TopBar(MainMenuControl control) {
        //if (control!=null){
            this.mainMenuControl = control;
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.GRAY)); // Bottom border
            setPreferredSize(new Dimension(900, 50));

            // Left-aligned label
            JLabel titleLabel = new JLabel("Enlingo-Deine Englischlernplattform");
            titleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 0));

            // Load and resize the home icon
            ImageIcon originalIcon = new ImageIcon("src/shared/HomeIcon.png"); // Replace with actual path
            Image scaledImage = originalIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
            ImageIcon resizedIcon = new ImageIcon(scaledImage);

            ImageIcon originalIcon2 = new ImageIcon("src/shared/ModeSwitchIcon.png");
            Image scaledImage2 = originalIcon2.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
            ImageIcon resizedIcon2 = new ImageIcon(scaledImage2);

            // Right-aligned home button
            JButton homeButton = new JButton(" HOME", resizedIcon);
            homeButton.addActionListener(this);
            homeButton.setActionCommand("home");
            homeButton.setFont(new Font("Arial", Font.BOLD, 14));
            homeButton.setFocusPainted(false);
            homeButton.setContentAreaFilled(false);

            JButton modeSwitchButton = new JButton(resizedIcon2);
            modeSwitchButton.addActionListener(this);
            modeSwitchButton.setActionCommand("mode");
            modeSwitchButton.setFont(new Font("Arial", Font.BOLD, 14));
            modeSwitchButton.setFocusPainted(false);
            modeSwitchButton.setContentAreaFilled(false);

            JPanel rightPanel = new JPanel();
            rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.X_AXIS)); // Vertical layout
            rightPanel.setOpaque(false);
            rightPanel.add(Box.createVerticalGlue());
            rightPanel.add(homeButton);
            rightPanel.add(Box.createVerticalGlue());
            add(titleLabel, BorderLayout.WEST);
            add(rightPanel, BorderLayout.EAST);
        //}
    }
    public void actionPerformed(ActionEvent e) {
        if ("home".equals(e.getActionCommand())) {
            this.mainMenuControl.display(true);
            Enlingo.hideAllViews(this.mainMenuControl);
        }
    }
}
