package questionManager;

import mainMenu.MainMenuControl;

public class QuestionManagerController {
    private MainMenuControl mainMenu;

    private QuestionManagerModel model;
    private QuestionManagerView view;

    public QuestionManagerController(MainMenuControl mainMenu) {
        this.model = new QuestionManagerModel();
        this.view = new QuestionManagerView(this);
        this.mainMenu = mainMenu;
    }

    public void display() {
        view.init();
    }

    public void navigateBack() {
        mainMenu.show(true);
        view.setVisible(false);
    }

    public static void main(String[] args) {
        //test
        //MainMenuControl mainMenu = new MainMenuControl();
        //QuestionManagerController questionManager = new QuestionManagerController();
        //questionManager.display();

    }
}
