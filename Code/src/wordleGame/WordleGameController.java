package wordleGame;

import mainMenu.MainMenuControl;

public class WordleGameController {
    private WordleGameModel model;
    private WordleGameView view;
    private MainMenuControl mainMenuControl;

    public WordleGameController(MainMenuControl mainMenuControl) {
        this.mainMenuControl = mainMenuControl;
        this.model = new WordleGameModel();
        this.view = new WordleGameView(this);
    }

    public void display() {
        view.setVisible(true);
    }

    public void navigateBack() {
        view.setVisible(false);
        mainMenuControl.showMainMenu();
    }
}