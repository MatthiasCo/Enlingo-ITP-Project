package wordleGame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
        buttonPanel.setLayout(new GridLayout(1, 2));

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
        buttonPanel.add(startButton);


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
                addInputFieldListener(inputField[i][j], i, j);
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
    public void resetView(){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                inputField[i][j].setText("");
                inputField[i][j].setBackground(Color.WHITE);
            }
        }
        for (int j = 0; j < 5; j++) {
            inputField[0][j].setEditable(true);
        }
        for (int j = 0; j < 5; j++) {
            inputField[1][j].setEditable(false);
        }
        statusLabel.setText("Enter a 5-letter word");
        submitButton.setEnabled(true);
    }

    private void addInputFieldListener(JTextField field, int row, int col) {
        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (field.getText().length() >= 1) {
                    e.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (Character.isLetter(e.getKeyChar())) {
                    field.setText(field.getText().toUpperCase());
                    moveToNextField(row, col);
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
    public void setStatusMessage(String message) {
        statusLabel.setText(message);
    }

    public void setColor(int attempt, int index, Color color) {
        inputField[attempt][index].setBackground(color);
    }

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
