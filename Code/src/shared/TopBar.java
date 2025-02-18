package shared;
import javax.swing.*;
import java.awt.*;

public class TopBar extends JPanel {
    public TopBar() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK)); // Bottom border

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
        homeButton.setFont(new Font("Arial", Font.BOLD, 14));
        homeButton.setFocusPainted(false);
        homeButton.setBorderPainted(false);
        homeButton.setContentAreaFilled(false);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setOpaque(false);
        rightPanel.add(homeButton);

        add(titleLabel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
    }
}
