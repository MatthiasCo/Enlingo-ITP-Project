// QuizGameController.java
package quizGame;

import mainMenu.MainMenuControl;

public class QuizGameController {
    private QuizGameModel model;
    private QuizGameView view;

    public QuizGameController() {
        this.model = new QuizGameModel();
        this.view = new QuizGameView(this);
    }

    public void display() {
        view.setVisible(true);
    }

    public void navigateBack() {
        MainMenuControl mainMenu = new MainMenuControl();
        mainMenu.display();
        view.setVisible(false);
    }
}