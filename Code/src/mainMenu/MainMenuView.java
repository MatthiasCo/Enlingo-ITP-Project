package mainMenu;

import javax.swing.*;
import java.awt.*;

public class MainMenuView extends JFrame {
    private MainMenuControl control;

    public MainMenuView(MainMenuControl control) {
        this.control = control;
        setTitle("Main Menu");
        setSize(960, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1)); // 4 rows, 1 column

        JButton quizGameButton = new JButton("Quiz Game");
        quizGameButton.setHorizontalAlignment(SwingConstants.CENTER);
        quizGameButton.addActionListener(e -> control.navigateToQuizGame());

        JButton wordleGameButton = new JButton("Wordle Game");
        wordleGameButton.setHorizontalAlignment(SwingConstants.CENTER);
        wordleGameButton.addActionListener(e -> control.navigateToWordleGame());

        JButton questionManagerButton = new JButton("Question Manager");
        questionManagerButton.setHorizontalAlignment(SwingConstants.CENTER);
        questionManagerButton.addActionListener(e -> control.navigateToQuestionManager());

        JButton exitButton = new JButton("Exit");
        exitButton.setHorizontalAlignment(SwingConstants.CENTER);
        exitButton.addActionListener(e -> System.exit(0));

        add(quizGameButton);
        add(wordleGameButton);
        add(questionManagerButton);
        add(exitButton);

        setResizable(false); // Make the window non-resizable
    }

    public void setControl(MainMenuControl control) {
        this.control = control;
    }

    public void display() {
        setVisible(true);
    }
}