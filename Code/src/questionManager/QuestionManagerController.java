package questionManager;

import mainMenu.MainMenuControl;

public class QuestionManagerController {
    public QuestionManagerController(QuestionManagerModel model, QuestionManagerView view) {
        this.model = model;
        this.view = view;
    }

    questionManager.QuestionManagerModel model = new QuestionManagerModel();
    questionManager.QuestionManagerView view = new QuestionManagerView();
    QuestionManagerController controller = new QuestionManagerController(model, view);

    public QuestionManagerController() {
    }

    public void display() {
    }
}
