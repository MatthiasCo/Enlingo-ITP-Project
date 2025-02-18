package mainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuView extends JFrame {

    public MainMenuView(MainMenuControl control) {
        setTitle("shared.Enlingo Menu");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));

        JButton quizGameButton = new JButton("Quiz Game");
        quizGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.navigateToQuizGame();
            }
        });

        JButton wordleGameButton = new JButton("Wordle Game");
        wordleGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.navigateToWordleGame();
            }
        });

        JButton questionManagerButton = new JButton("Question Manager");
        questionManagerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.navigateToQuestionManager();
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(quizGameButton);
        add(wordleGameButton);
        add(questionManagerButton);
        add(exitButton);
    }

    public void display(boolean b) {
        setVisible(b);
    }
}