package questionManager;

import database.DatabaseManager;
import mainMenu.MainMenuControl;
import shared.Question;
import shared.TopBar;

import javax.swing.JPanel;
import java.util.List;

/**
 * Enlingo: QuestionManager Module (Matthias) - Controller
 * @author Matthias Pagler
 * @version 1.0
 */
public class QuestionManagerController {
    private MainMenuControl mainMenu;
    private QuestionManagerModel model;
    private QuestionManagerView view;

    public QuestionManagerController(MainMenuControl mainMenu) {
        this.mainMenu = mainMenu;
        this.model = new QuestionManagerModel();
        this.view = new QuestionManagerView(this);
        view.init();
    }

    public void display(boolean b) {
        view.setVisible(b);
    }

    public void loadQuestions() {
        List<Question<?>> questions = model.getAllQuestions();
        view.setQuestions(questions);
    }

    public void updateQuestion(Question<?> question) {
        model.updateQuestion(question);
        loadQuestions();
    }

    public JPanel topPanel(){
        return (new TopBar(this.mainMenu));
    }

    public void removeQuestion(int id) {
        model.removeQuestion(id);
        loadQuestions();
    }

    public DatabaseManager<Object> getDB() {
        return new DatabaseManager<>(Object.class);
    }
}