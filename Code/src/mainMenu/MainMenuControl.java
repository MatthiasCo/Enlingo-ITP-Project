package Code.src.mainMenu;

import Code.src.quizGame.QuizGameController;
import Code.src.wordleGame.WordleGameController;
import Code.src.questionManager.QuestionManagerController;

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
        QuizGame quizGame = new QuizGame(this);
        quizGame.display();
        view.setVisible(false);
    }

    public void navigateToWordleGame() {
        WordleGame wordleGame = new WordleGame(this);
        wordleGame.display();
        view.setVisible(false);
    }

    public void navigateToQuestionManager() {
        QuestionManager questionManager = new QuestionManager(this);
        questionManager.display();
        view.setVisible(false);
    }
}