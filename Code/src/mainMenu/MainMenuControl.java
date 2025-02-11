package Code.src.mainMenu;

import Code.src.quizGame.*;
import Code.src.wordleGame.*;
import Code.src.questionManager.*;

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
        QuizGameController quizGame = new QuizGameController(this);
        quizGame.display();
        view.setVisible(false);
    }

    public void navigateToWordleGame() {
        WordleGameController wordleGame = new WordleGameController(this);
        wordleGame.display();
        view.setVisible(false);
    }

    public void navigateToQuestionManager() {
        QuestionManagerController questionManager = new QuestionManagerController(this);
        questionManager.display();
        view.setVisible(false);
    }
}