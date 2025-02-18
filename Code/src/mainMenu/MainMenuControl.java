package mainMenu;

import com.formdev.flatlaf.FlatDarkLaf;
import quizGame.*;
import wordleGame.*;
import questionManager.*;

import javax.swing.*;

public class MainMenuControl {
    private MainMenuView view;
    QuizGameController quizGame;
    WordleGameController wordleGame;
    QuestionManagerController questionManager;

    public MainMenuControl() {
        this.view = new MainMenuView(this);
        quizGame = new QuizGameController(this);
        quizGame.display(false);
        wordleGame = new WordleGameController(this);
        wordleGame.display(false);
        questionManager = new QuestionManagerController(this);
        questionManager.display(false);
    }

    public MainMenuView getView() {
        return view;
    }

    public void setView(MainMenuView view) {
        this.view = view;
    }

    public void navigateToQuizGame() {
        this.view.setVisible(false);
        quizGame.display(true);
        quizGame.startQuiz();
    }

    public void navigateToWordleGame() {
        this.view.setVisible(false);
        wordleGame.display(true);
    }

    public void navigateToQuestionManager() {
        this.view.setVisible(false);
        questionManager.display(true);
    }

    public static void main(String[] args) {
        // Set FlatLaf Dark theme
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        MainMenuControl control = new MainMenuControl();
        MainMenuView view = new MainMenuView(control);
        view.display();
    }

    public void display() {
        this.view.display();
    }
}