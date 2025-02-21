package questionManager;

import mainMenu.MainMenuControl;
import shared.Question;
import shared.TopBar;

import javax.swing.JPanel;
import java.util.List;

public class QuestionManagerController {
    private MainMenuControl mainMenu;
    private QuestionManagerModel model;
    private QuestionManagerView view;

    public QuestionManagerController(MainMenuControl mainMenu) {
        this.mainMenu = mainMenu;
        this.model = new QuestionManagerModel();
        this.view = new QuestionManagerView(this);
        loadQuestions();
        view.init();
    }

    public void display(boolean b) {
        view.setVisible(b);
    }

    public void loadQuestions() {
        List<Question<?>> questions = model.getAllQuestions();
        view.setQuestions(questions);
    }

    public void updateQuestion(Question<Object> question) {
        model.updateQuestion(question);
        loadQuestions();
    }

    public void display() {
        view.setVisible(true);
    }

    public JPanel topPanel(){
        return (new TopBar(this.mainMenu));
    }
}