package shared;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import mainMenu.MainMenuControl;

public class TopBar extends JPanel implements ActionListener{
    private MainMenuControl mainMenuControl;

    public TopBar() {
        this.mainMenuControl = new MainMenuControl();
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.GRAY)); // Bottom border

        // Left-aligned label
        JLabel titleLabel = new JLabel("Enlingo-Deine Englischlernplattform");
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 0));

        // Load and resize the home icon
        ImageIcon originalIcon = new ImageIcon("src/shared/HomeIcon.png"); // Replace with actual path
        Image scaledImage = originalIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        // Right-aligned home button
        JButton homeButton = new JButton(" HOME", resizedIcon);
        homeButton.addActionListener(this);
        homeButton.setActionCommand("home");
        homeButton.setFont(new Font("Arial", Font.BOLD, 14));
        homeButton.setFocusPainted(false);
        homeButton.setContentAreaFilled(false);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setOpaque(false);
        rightPanel.add(homeButton);

        add(titleLabel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
    }
    public void actionPerformed(ActionEvent e) {
        if ("home".equals(e.getActionCommand())) {
            mainMenuControl.display();
        }
    }
}
