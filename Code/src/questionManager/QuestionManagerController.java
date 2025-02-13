package questionManager;

import mainMenu.MainMenuControl;

public class QuestionManagerController {
    private QuestionManagerModel model;
    private QuestionManagerView view;
    private MainMenuControl mainMenuControl;

    public QuestionManagerController(MainMenuControl mainMenuControl) {
        this.mainMenuControl = mainMenuControl;
        this.model = new QuestionManagerModel();
        this.view = new QuestionManagerView(this);
    }

    public void display() {
        view.setVisible(true);
    }

    public void navigateBack() {
        view.setVisible(false);
        mainMenuControl.showMainMenu();
    }
}