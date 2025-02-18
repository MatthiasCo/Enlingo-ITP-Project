package quizGame;

import shared.TopBar;

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
    private JLabel nameLabel;
    private JPanel topBar;

    public QuizGameView(QuizGameController controller) {
        this.controller = controller;
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        topBar = new TopBar();
        add(topBar, BorderLayout.NORTH);

        // Question label in the center
        questionLabel = new JLabel("Question goes here", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        // Answer input field at the bottom
        JPanel centerPanel = new JPanel(new BorderLayout());
        JPanel bottomPanel = new JPanel(new BorderLayout());
        answerField = new JTextField();
        answerField.setFont(new Font("Arial", Font.PLAIN, 18));
        answerField.setPreferredSize(new Dimension(400, 60));
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

        centerPanel.add(questionLabel, BorderLayout.CENTER);


        nameLabel = new JLabel("Quiz Game", SwingConstants.CENTER);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0)); // Add padding to nameLabel
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        centerPanel.add(nameLabel, BorderLayout.NORTH);

        add(bottomPanel, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);
        setResizable(false); // Make the window non-resizable
    }

    public void displayQuestion(String question) {
        questionLabel.setText(question);
    }

    public void clearAnswerField() {
        answerField.setText("");
    }

    public JTextField getAnswerField() {
        return answerField;
    }

    public void showResult(String result) {
        JOptionPane.showMessageDialog(this, result);
    }
}