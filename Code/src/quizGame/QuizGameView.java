package quizGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizGameView extends JFrame {
    private QuizGameController controller;
    private JLabel questionLabel;
    private JTextField answerField;
    private JButton homeButton;
    private String lastAnswer = "";

    public QuizGameView(QuizGameController controller) {
        this.controller = controller;
        setTitle("Quiz Game");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Question label in the center
        questionLabel = new JLabel("Question goes here", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(questionLabel, BorderLayout.CENTER);

        // Answer input field at the bottom
        JPanel bottomPanel = new JPanel(new BorderLayout());
        answerField = new JTextField();
        answerField.setFont(new Font("Arial", Font.PLAIN, 18));
        answerField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String answer = answerField.getText();
                if (!answer.equalsIgnoreCase(lastAnswer)) {
                    controller.checkAnswer(answer);
                    lastAnswer = answer;
                } else {
                    showResult("Duplicate answer. Please try again.");
                }
            }
        });
        bottomPanel.add(answerField, BorderLayout.CENTER);

        // Home button at the bottom
        homeButton = new JButton("Home");
        homeButton.setHorizontalAlignment(SwingConstants.CENTER);
        homeButton.addActionListener(e -> controller.navigateBack());
        bottomPanel.add(homeButton, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);

        setResizable(false); // Make the window non-resizable
    }

    public void displayQuestion(String question) {
        questionLabel.setText(question);
    }

    public void clearAnswerField() {
        answerField.setText("");
    }

    public void showResult(String result) {
        JOptionPane.showMessageDialog(this, result);
    }
}