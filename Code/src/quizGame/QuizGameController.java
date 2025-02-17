package quizGame;

import mainMenu.MainMenuControl;

public class QuizGameController {
    public QuizGameController(quizGame.QuizGameModel model, quizGame.QuizGameView view) {
        this.model = model;
        this.view = view;
    }

    quizGame.QuizGameModel model = new QuizGameModel();
    quizGame.QuizGameView view = new QuizGameView();
    QuizGameController controller = new QuizGameController(model, view);

    public QuizGameController() {
    }

    public void display() {
    }
}