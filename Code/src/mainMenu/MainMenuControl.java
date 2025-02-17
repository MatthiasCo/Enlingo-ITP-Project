package mainMenu;

import quizGame.*;
import wordleGame.*;
import questionManager.*;

public class MainMenuControl {
    private MainMenuView view;

    public MainMenuControl () {
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
        MainMenuView view = new MainMenuView();
        MainMenuControl control = new MainMenuControl();
        view.setControl(control);
        view.display();
    }

    public void display() {
    this.view = new MainMenuView();
    }
}