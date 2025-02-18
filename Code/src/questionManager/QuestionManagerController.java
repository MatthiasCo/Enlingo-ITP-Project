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
    }

    public void display() {
        view.init();
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

    public static void main(String[] args) {
        //test
        //MainMenuControl mainMenu = new MainMenuControl();
        //QuestionManagerController questionManager = new QuestionManagerController();
        //questionManager.display();
    }
}