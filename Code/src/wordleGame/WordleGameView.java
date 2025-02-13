package wordleGame;

import javax.swing.*;
import java.awt.*;

public class WordleGameView extends JFrame {
    private WordleGameController controller;

    public WordleGameView(WordleGameController controller) {
        this.controller = controller;
        setTitle("Wordle Game");
        setSize(960, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 1)); // 1 row, 1 column

        JButton backButton = new JButton("Back");
        backButton.setHorizontalAlignment(SwingConstants.CENTER);
        backButton.addActionListener(e -> controller.navigateBack());

        add(backButton);
        setResizable(false); // Make the window non-resizable
    }
}