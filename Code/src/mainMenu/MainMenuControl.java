package mainMenu;

import com.formdev.flatlaf.FlatDarkLaf;
import quizGame.*;
import wordleGame.*;
import questionManager.*;

import javax.swing.*;

public class MainMenuControl {
    private MainMenuView view;

    public MainMenuControl() {
        this.view = new MainMenuView();
    }

    public MainMenuView getView() {
        return view;
    }

    public void setView(MainMenuView view) {
        this.view = view;
    }

    public void navigateToQuizGame() {
        this.view.setVisible(false);
        QuizGameController quizGame = new QuizGameController();
        quizGame.display();
    }

    public void navigateToWordleGame() {
        this.view.setVisible(false);
        WordleGameController wordleGame = new WordleGameController(this);
    }

    public void navigateToQuestionManager() {
        this.view.setVisible(false);
        QuestionManagerController questionManager = new QuestionManagerController(this);
        questionManager.display();
    }

    public static void main(String[] args) {
        // Set FlatLaf Dark theme
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        MainMenuView view = new MainMenuView();
        MainMenuControl control = new MainMenuControl();
        view.setControl(control);
        view.display();
    }

    public void display() {
        this.view = new MainMenuView();
        this.view.setControl(this);
        this.view.display();
    }
}