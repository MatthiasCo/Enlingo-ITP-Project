package wordleGame;
import javax.swing.*;
import java.awt.*;

public class WordleGameView {
    private JPanel containerPanel;
    private JFrame frame;

    private String question;
    private JLabel questionLabel;

    private JLabel welcomeLabel;
    private JButton startButton;

    private WordleGameController controller;
    private JTextField[][] inputField;
    private JButton submitButton;

    public WordleGameView(WordleGameController controller) {
        frame = new JFrame("Wordle Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        this.controller = controller;

        containerPanel = new JPanel();

        welcomeView();
        frame.add(containerPanel);
        frame.setVisible(true);

        frame.setVisible(true);
    }

    public void welcomeView(){
        containerPanel.setLayout(new BorderLayout()); // Set mainPanel layout to BorderLayout

        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BorderLayout());

        this.welcomeLabel = new JLabel("Willkommen zu Wordle!", SwingConstants.CENTER); // Center the text
        this.startButton = new JButton("Start");

        startButton.setActionCommand("start");
        startButton.addActionListener(controller);

        welcomePanel.add(welcomeLabel, BorderLayout.CENTER);
        welcomePanel.add(startButton, BorderLayout.SOUTH);

        containerPanel.add(welcomePanel, BorderLayout.CENTER); // Add welcomePanel to mainPanel
    }

    public void mainView(String question){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        this.inputField = new JTextField[5][5];
        if(question != null){
            this.question = question;
            JLabel questionLabel = new JLabel(this.question);
            mainPanel.add(questionLabel, BorderLayout.NORTH);
        }


        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 5));
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                this.inputField[i][j] = new JTextField();
                inputPanel.add(this.inputField[i][j]);
            }
        }
        mainPanel.add(inputPanel, BorderLayout.CENTER);

        submitButton = new JButton("Submit");
        submitButton.setActionCommand("submit");
        submitButton.addActionListener(controller);

        mainPanel.add(submitButton, BorderLayout.SOUTH);

        this.containerPanel.add(mainPanel);
    }

}
