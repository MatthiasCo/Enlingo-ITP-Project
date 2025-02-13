package quizGame;

import mainMenu.MainMenuControl;

public class QuizGameController {
    private QuizGameModel model;
    private QuizGameView view;
    private MainMenuControl mainMenuControl;

    public QuizGameController(MainMenuControl mainMenuControl) {
        this.mainMenuControl = mainMenuControl;
        this.model = new QuizGameModel();
        this.view = new QuizGameView(this);
    }

    public void display() {
        view.setVisible(true);
    }

    public void navigateBack() {
        view.setVisible(false);
        mainMenuControl.showMainMenu();
    }
}