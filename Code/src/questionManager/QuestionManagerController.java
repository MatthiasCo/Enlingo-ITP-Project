package questionManager;

import mainMenu.MainMenuControl;

public class QuestionManagerController {
    QuestionManagerModel model;
    QuestionManagerView view;

    public QuestionManagerController() {
        this.model = new QuestionManagerModel();
        this.view = new QuestionManagerView(this);

    }

    public void display() {
        view.init();
    }

    public void navigateBack() {
        MainMenuControl mainMenu = new MainMenuControl();
        mainMenu.display();
        view.setVisible(false);
    }
}
