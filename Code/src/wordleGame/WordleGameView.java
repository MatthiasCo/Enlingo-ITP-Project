package wordleGame;
import shared.TopBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.*;

public class WordleGameView {
    private JFrame frame;

    private JPanel containerPanel;
    private JPanel topPanel;

    private JLabel statusLabel;

    private JLabel welcomeLabel;
    private JButton startButton;

    private JPanel buttonPanel;

    private JPanel midPanel;

    private WordleGameController controller;
    private JTextField[][] inputField;
    private JButton submitButton;
    private JButton restartButton;
    private JButton homeButton;

    public WordleGameView(WordleGameController controller) {
        frame = new JFrame("Wordle Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        this.controller = controller;


        welcomeView();
        frame.setVisible(true);
    }

    public void welcomeView(){
        frame.getContentPane().removeAll(); // Ensure all components are removed
        frame.revalidate();
        containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout()); // Set mainPanel layout to BorderLayout;

        this.welcomeLabel = new JLabel("Willkommen zu Wordle!", SwingConstants.CENTER); // Center the text
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30)); // Center the button, with vertical gap

        homeButton = new JButton("Home");
        homeButton.setFont(new Font("Arial", Font.BOLD, 20)); // (Font Name, Style, Size)
        homeButton.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40)); // (top, left, bottom, right)
        homeButton.setActionCommand("home");
        homeButton.addActionListener(controller);
        buttonPanel.add(homeButton);

        this.startButton = new JButton("Start");
        startButton.setFont(new Font("Arial", Font.BOLD, 20)); // (Font Name, Style, Size)
        startButton.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40)); // (top, left, bottom, right)
        startButton.setActionCommand("start");
        startButton.addActionListener(controller);


        startButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Change cursor to hand on hover

        startButton.setPreferredSize(new Dimension(200, 50)); // Adjust width as needed (200px here)


        startButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                startButton.setBackground(new Color(85,88,90,255)); // Darken teal on hover
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                startButton.setBackground(new Color(78,80,82,255)); // Reset to original teal
            }
        });

        buttonPanel.add(startButton);

        JPanel topPanel = new TopBar();

        containerPanel.add(topPanel, BorderLayout.NORTH);
        containerPanel.add(welcomeLabel, BorderLayout.CENTER);
        containerPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(containerPanel, BorderLayout.CENTER); // Add containerPanel to mainPanel
        frame.revalidate(); // Refresh layout after adding components
        frame.repaint(); // Redraw the frame

    }


    public void mainView() {
        frame.getContentPane().removeAll(); // Ensure all components are removed
        frame.revalidate(); // Refresh the layout before adding new components

        containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout());

        midPanel = new JPanel();
        midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));

        this.statusLabel = new JLabel("Enter a 5-letter word", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 18));
        midPanel.add(statusLabel);

        JPanel inputPanel = new JPanel();
        this.inputField = new JTextField[5][5];
        inputPanel.setLayout(new GridLayout(5, 5));
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                this.inputField[i][j] = new JTextField();
                inputField[i][j].setFont(new Font("Arial", Font.BOLD, 24));
                inputField[i][j].setHorizontalAlignment(JTextField.CENTER);
                addInputFieldListener(inputField[i][j], i, j);
                inputPanel.add(this.inputField[i][j]);
                inputField[i][j].setEditable(false);
            }
        }
        for (int j = 0; j < 5; j++) {
            inputField[0][j].setEditable(true);
        }

        midPanel.add(inputPanel);
        containerPanel.add(midPanel, BorderLayout.CENTER);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));

        submitButton = new JButton("Submit");
        submitButton.setActionCommand("submit");
        submitButton.addActionListener(controller);
        submitButton.setPreferredSize(new Dimension(200, 25));
        buttonPanel.add(submitButton);

        restartButton = new JButton("Restart");
        restartButton.setActionCommand("restart");
        restartButton.addActionListener(controller);
        restartButton.setPreferredSize(new Dimension(200, 25));
        buttonPanel.add(restartButton);

        topPanel = new TopBar();

        containerPanel.add(buttonPanel, BorderLayout.SOUTH);
        containerPanel.add(topPanel, BorderLayout.NORTH);

        frame.add(containerPanel);
        frame.revalidate(); // Refresh layout after adding components
        frame.repaint(); // Redraw the frame
    }

    // Reset the view to its initial state
    public void resetView(){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                inputField[i][j].setText("");
                inputField[i][j].setBackground(new Color(60,63,65,255));
            }
            for (int j = 0; j < 5; j++) {
                inputField[i][j].setEditable(false);
            }
        }
        for (int j = 0; j < 5; j++) {
            inputField[0][j].setEditable(true);
            inputField[0][j].setBackground(new Color(70,73,75,255));
        }
        statusLabel.setText("Enter a 5-letter word");
        submitButton.setEnabled(true);
    }

    private void addInputFieldListener(JTextField field, int row, int col) {
        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (field.getText().length() >= 1) {    // Limit the input to 1 character
                    e.consume();    // Ignore the key press
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (Character.isLetter(e.getKeyChar())) {   // Detect if the key pressed is a letter
                    field.setText(field.getText().toUpperCase()); // Convert the letter to uppercase
                    moveToNextField(row, col); // Move to the next field
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER) { // Detect the Enter key press
                    submitButton.doClick(); // Simulate pressing the submit button
                }
            }
        });
    }

    private void moveToNextField(int row, int col) {
        if (col < 4) {
            inputField[row][col + 1].requestFocus();
        }
    }

    public String getCurrentGuess(int attempt) {
        StringBuilder guess = new StringBuilder();
        for (int j = 0; j < 5; j++) {
            guess.append(inputField[attempt][j].getText().toUpperCase());
        }
        return guess.toString();
    }

    public void show(boolean show) {
        frame.setVisible(show);

    }

    // Set the status message
    public void setStatusMessage(String message) {
        statusLabel.setText(message);
    }

    // Set the color of the input field
    public void setColor(int attempt, int index, Color color) {
        inputField[attempt][index].setBackground(color);
    }

    // Set the colors of the input fields based on the feedback
    // Right Letter Right Position = Green
    // Right Letter Wrong Position = Yellow
    // Wrong Letter = Gray
    public void setColors(int attempt, int[] colors){
        for (int i = 0; i < 5; i++) {
            if (colors[i] == 1) {
                setColor(attempt, i, Color.GREEN);
            } else if (colors[i] == 2) {
                setColor(attempt, i, Color.YELLOW);
            } else {
                setColor(attempt, i, Color.GRAY);
            }
        }
    }
    // Disable the input fields for the current row
    public void disableRow(int attempt) {
        for (int j = 0; j < 5; j++) {
            inputField[attempt][j].setEditable(false);
            inputField[attempt+1][j].setBackground(new Color(70,73,75,255));
        }

        // Only enable the next row if there is one
        if (attempt < 4) {
            for (int j = 0; j < 5; j++) {
                inputField[attempt + 1][j].setEditable(true);
            }
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

    public void setWinScreen(String targetWord, int attempt) {
        setStatusMessage("Correct! The word was " + targetWord);
        for (int i = 0; i < 5; i++) {
            setColor(attempt, i, Color.GREEN);
        }
        disableInput();    }

}
