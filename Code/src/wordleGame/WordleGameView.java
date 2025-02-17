package wordleGame;
import javax.swing.*;
import java.awt.*;

public class WordleGameView {
    private JFrame frame;

    private JPanel containerPanel;

    private JLabel statusLabel;

    private JLabel welcomeLabel;
    private JButton startButton;

    private JPanel buttonPanel;

    private WordleGameController controller;
    private JTextField[][] inputField;
    private JButton submitButton;
    private JButton restartButton;
    private JButton homeButton;

    public WordleGameView(WordleGameController controller) {
        frame = new JFrame("Wordle Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        this.controller = controller;


        welcomeView();
        frame.setVisible(true);
    }

    public void welcomeView(){
        containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout()); // Set mainPanel layout to BorderLayout;

        this.welcomeLabel = new JLabel("Willkommen zu Wordle!", SwingConstants.CENTER); // Center the text
        this.startButton = new JButton("Start");

        startButton.setActionCommand("start");
        startButton.addActionListener(controller);

        containerPanel.add(welcomeLabel, BorderLayout.CENTER);
        containerPanel.add(startButton, BorderLayout.SOUTH);

        frame.add(containerPanel, BorderLayout.CENTER); // Add containerPanel to mainPanel
    }


    public void mainView() {
        frame.getContentPane().removeAll(); // Ensure all components are removed
        frame.revalidate(); // Refresh the layout before adding new components

        containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout());

        this.statusLabel = new JLabel("Enter a 5-letter word", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 18));
        containerPanel.add(statusLabel, BorderLayout.NORTH);
        
        JPanel inputPanel = new JPanel();
        this.inputField = new JTextField[5][5];
        inputPanel.setLayout(new GridLayout(5, 5));
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                this.inputField[i][j] = new JTextField();
                inputField[i][j].setFont(new Font("Arial", Font.BOLD, 24));
                inputField[i][j].setHorizontalAlignment(JTextField.CENTER);
                inputPanel.add(this.inputField[i][j]);
                inputField[i][j].setEditable(false);
            }
        }
        for (int j = 0; j < 5; j++) {
            inputField[0][j].setEditable(true);
        }
        containerPanel.add(inputPanel, BorderLayout.CENTER);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));

        homeButton = new JButton("Home");
        homeButton.setActionCommand("home");
        homeButton.addActionListener(controller);
        buttonPanel.add(homeButton);

        submitButton = new JButton("Submit");
        submitButton.setActionCommand("submit");
        submitButton.addActionListener(controller);
        buttonPanel.add(submitButton);

        restartButton = new JButton("Restart");
        restartButton.setActionCommand("restart");
        restartButton.addActionListener(controller);
        buttonPanel.add(restartButton);

        containerPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(containerPanel);
        frame.revalidate(); // Refresh layout after adding components
        frame.repaint(); // Redraw the frame
    }

    public String getCurrentGuess(int attempt) {
        StringBuilder guess = new StringBuilder();
        for (int j = 0; j < 5; j++) {
            guess.append(inputField[attempt][j].getText().toUpperCase());
        }
        return guess.toString();
    }
    public void setStatusMessage(String message) {
        statusLabel.setText(message);
    }

    public void setColor(int attempt, int index, Color color) {
        inputField[attempt][index].setBackground(color);
    }
    public void disableRow(int attempt) {
        for (int j = 0; j < 5; j++) {
            inputField[attempt][j].setEditable(false);
        }
        for (int j = 0; j < 5; j++) {
            inputField[attempt+1][j].setEditable(true);
        }
    }

    public void disableInput(){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                inputField[i][j].setEditable(false);
            }
        }
        submitButton.setEnabled(false);
    }


}
