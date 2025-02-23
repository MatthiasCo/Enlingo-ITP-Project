// QuizGameView.java
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
    private String lastAnswer = "";
    private JLabel nameLabel;
    private JPanel topBar;
    private JPanel containerPanel;
    private JLabel welcomeLabel;
    private JPanel buttonPanel;
    private JButton startButton;
    private JPanel topPanel;
    private JLabel questionBoolLabel;
    private JButton nextButton;
    private JLabel questionCountLabel;

    public QuizGameView(QuizGameController controller) {
        this.controller = controller;
        setTitle("Quiz Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    public void welcomeView() {
        getContentPane().removeAll(); // Ensure all components are removed
        revalidate();
        containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout()); // Set mainPanel layout to BorderLayout;

        welcomeLabel = new JLabel(
                "<html><div style='text-align: center;'>" +
                        "<span style='font-family: Arial; font-size: 40px; font-weight: bold; font-style: italic;'>QUIZ GAME</span><br><br>" +
                        "<span style='font-family: Arial; font-size: 24px; font-weight: normal;'>Welcome to the Quiz Game!<br>Press START to begin!</span>" +
                        "</div></html>"
        );
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 50)); // Center the button, with vertical gap

        startButton = new JButton("Start");
        startButton.setFont(new Font("Arial", Font.BOLD, 36));
        startButton.setActionCommand("start");
        startButton.addActionListener(controller);

        startButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Change cursor to hand on hover

        startButton.setPreferredSize(new Dimension(400, 100)); // Adjust width as needed (200px here)

        startButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                startButton.setBackground(new Color(85, 88, 90, 255)); // Darken teal on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                startButton.setBackground(new Color(78, 80, 82, 255)); // Reset to original teal
            }
        });

        buttonPanel.add(startButton);

        topPanel = controller.topPanel();

        containerPanel.add(topPanel, BorderLayout.NORTH);
        containerPanel.add(welcomeLabel, BorderLayout.CENTER);
        containerPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(containerPanel, BorderLayout.CENTER); // Add containerPanel to mainPanel
        revalidate(); // Refresh layout after adding components
        repaint(); // Redraw the frame
    }

    public void mainGameView() {
        getContentPane().removeAll(); // Ensure all components are removed
        revalidate();
        containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout()); // Set mainPanel layout to BorderLayout;

        topBar = controller.topPanel();
        containerPanel.add(topBar, BorderLayout.NORTH);

        // Question label in the center
        questionLabel = new JLabel("Question goes here", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        questionBoolLabel = new JLabel("", SwingConstants.CENTER);
        questionBoolLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        questionBoolLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 85, 0));

        // Answer input field, question count, and next button at the bottom
        JPanel centerPanel = new JPanel(new BorderLayout());
        JPanel bottomPanel = new JPanel(new BorderLayout());
        answerField = new JTextField();
        answerField.setFont(new Font("Arial", Font.PLAIN, 18));
        answerField.setPreferredSize(new Dimension(0, 60));
        answerField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String answer = answerField.getText();
                    controller.checkAnswer(answer);
            }
        });

        nextButton = new JButton("Next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 18));
        nextButton.setActionCommand("next");
        nextButton.addActionListener(controller);

        questionCountLabel = new JLabel(controller.getQuestionCount() + "/10");
        questionCountLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        questionCountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        questionCountLabel.setPreferredSize(new Dimension(80, 60));

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(questionCountLabel, BorderLayout.WEST);
        inputPanel.add(answerField, BorderLayout.CENTER);
        inputPanel.add(nextButton, BorderLayout.EAST);

        bottomPanel.add(inputPanel, BorderLayout.CENTER);

        centerPanel.add(questionLabel, BorderLayout.CENTER);
        centerPanel.add(questionBoolLabel, BorderLayout.SOUTH);

        nameLabel = new JLabel("Quiz Game", SwingConstants.CENTER);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0)); // Add padding to nameLabel
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        centerPanel.add(nameLabel, BorderLayout.NORTH);

        containerPanel.add(bottomPanel, BorderLayout.SOUTH);
        containerPanel.add(centerPanel, BorderLayout.CENTER);

        add(containerPanel, BorderLayout.CENTER); // Add containerPanel to mainPanel
        revalidate(); // Refresh layout after adding components
        repaint(); // Redraw the frame
    }

    public void displayQuestion(String question) {
        questionLabel.setText(question);
        questionCountLabel.setText(controller.getQuestionCount() + "/10");
    }

    public void clearAnswerField() {
        answerField.setText("");
    }

    public JTextField getAnswerField() {
        return answerField;
    }

    public void resetView() {
        questionLabel.setText("");
        answerField.setText("");
        questionCountLabel.setText("0/10");
        controller.startWelcome();
    }

    public void showResult(String result) {
        questionBoolLabel.setText(result);
    }

    public void setQuestionBoolLabelColor(Color color) {
        questionBoolLabel.setForeground(color);
    }
}