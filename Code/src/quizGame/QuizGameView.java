package quizGame;

import javax.swing.*;
import java.awt.*;

public class QuizGameView extends JFrame {
    private QuizGameController controller;

    public QuizGameView(QuizGameController controller) {
        this.controller = controller;
        setTitle("Quiz Game");
        setSize(960, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1)); // 3 rows, 1 column

        JButton startQuizButton = new JButton("Start Quiz");
        startQuizButton.setHorizontalAlignment(SwingConstants.CENTER);
        startQuizButton.addActionListener(e -> startQuiz());

        JButton infoButton = new JButton("Info");
        infoButton.setHorizontalAlignment(SwingConstants.CENTER);
        infoButton.addActionListener(e -> showInfo());

        JButton backButton = new JButton("Back");
        backButton.setHorizontalAlignment(SwingConstants.CENTER);
        backButton.addActionListener(e -> controller.navigateBack());

        add(startQuizButton);
        add(infoButton);
        add(backButton);
        setResizable(false); // Make the window non-resizable
    }

    private void startQuiz() {
        // Logic to start the quiz
        JOptionPane.showMessageDialog(this, "Quiz started!");
    }

    private void showInfo() {
        // Show a popup window with an explanation
        JOptionPane.showMessageDialog(this, "This is a quiz game. Answer the questions to the best of your ability.");
    }
}