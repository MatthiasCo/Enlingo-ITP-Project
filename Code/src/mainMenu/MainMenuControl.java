package mainMenu;

import quizGame.QuizGameController;
import shared.TopBar;
import wordleGame.WordleGameController;
import questionManager.QuestionManagerController;

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
        view.display(true);
    }

    public MainMenuView getView() {
        return view;
    }

    public void setView(MainMenuView view) {
        this.view = view;
    }

    public void navigateToQuizGame() {
        quizGame.startWelcome();
        this.view.setVisible(false);
    }

    public void navigateToWordleGame() {
        wordleGame.display(true);
        this.view.setVisible(false);
    }

    public void navigateToQuestionManager() {
        questionManager.display(true);
        this.view.setVisible(false);
    }

    public void display(boolean b) {
        this.view.display(b);
    }

    public void hideAllViews() {
        quizGame.display(false);
        wordleGame.display(false);
        questionManager.display(false);
    }

    public JPanel topPanel() {
        return new TopBar(this);
    }
}