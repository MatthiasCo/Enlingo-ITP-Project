package questionManager;

import mainMenu.MainMenuControl;
import shared.Question;

import java.util.List;

public class QuestionManagerController {
    private MainMenuControl mainMenu;
    private QuestionManagerModel model;
    private QuestionManagerView view;

    public QuestionManagerController(MainMenuControl mainMenu) {
        this.model = new QuestionManagerModel();
        this.view = new QuestionManagerView(this);
        this.mainMenu = mainMenu;
        loadQuestions();
        view.init();
    }

    public void display(boolean b) {
        view.setVisible(b);
    }

    public void loadQuestions() {
        List<Question<Object>> questions = model.getAllQuestions();
        view.setQuestions(questions);
    }

    public void updateQuestion(Question<Object> question) {
        model.updateQuestion(question);
        loadQuestions();
    }

    public void navigateBack() {
        mainMenu.display();
        view.setVisible(false);
    }
}