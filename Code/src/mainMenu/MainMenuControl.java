package mainMenu;

import quizGame.*;
import wordleGame.*;
import questionManager.*;

public class MainMenuControl {
    private MainMenuView view;

    public MainMenuControl(MainMenuView view) {
        this.view = view;
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
        view.setVisible(false);
    }

    public void navigateToWordleGame() {
        WordleGameController wordleGame = new WordleGameController();
        wordleGame.display();
        view.setVisible(false);
    }

    public void navigateToQuestionManager() {
        QuestionManagerController questionManager = new QuestionManagerController();
        questionManager.display();
        view.setVisible(false);
    }

    public static void main(String[] args) {
        MainMenuView view = new MainMenuView();
        MainMenuControl control = new MainMenuControl(view);
        view.setControl(control);
        view.display();
    }
}