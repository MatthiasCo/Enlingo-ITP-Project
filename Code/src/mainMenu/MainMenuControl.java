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
        QuizGameController quizGame = new QuizGameController();
        quizGame.display();
        this.view = null;
    }

    public void navigateToWordleGame() {
        WordleGameController wordleGame = new WordleGameController(this);
        wordleGame.display();
        this.view = null;
    }


    public void navigateToQuestionManager() {
        QuestionManagerController questionManager = new QuestionManagerController(this);
        questionManager.display();
        this.view = null;
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