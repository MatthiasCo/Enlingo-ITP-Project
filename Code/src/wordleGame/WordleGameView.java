package wordleGame;
import javax.swing.*;
import java.awt.*;

public class WordleGameView {
    private JPanel mainPanel;
    private JFrame frame;

    private JLabel welcomeMessage;
    private JButton startButton;

    private WordleGameController controller;
    private JTextField[][] inputField;
    private JButton submitButton;

    public WordleGameView() {
        frame = new JFrame("Wordle Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        mainPanel = new JPanel();

        welcomeView();
        frame.add(mainPanel);
        frame.setVisible(true);

        frame.setVisible(true);
    }

    private void welcomeView(){
        JPanel welcomePanel = new JPanel();

        welcomePanel.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Willkommen zu Wordle!");
        JButton startButton = new JButton("Start");

        startButton.setActionCommand("start");
        startButton.addActionListener();

        welcomePanel.add(welcomeLabel, BorderLayout.CENTER);
        welcomePanel.add(startButton, BorderLayout.SOUTH);

        mainPanel.add(welcomePanel);
    }

    public void mainView(){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 5));
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                inputField[i][j] = new JTextField();
                inputPanel.add(inputField[i][j]);
            }
        }
        mainPanel.add(inputPanel, BorderLayout.CENTER);

        submitButton = new JButton("Submit");
        submitButton.setActionCommand("submit");
        submitButton.addActionListener();

        mainPanel.add(submitButton, BorderLayout.SOUTH);
    }

}
