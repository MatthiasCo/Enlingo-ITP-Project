package Code.src.questionManager;

import questionManager.QuestionManagerModel;
import questionManager.QuestionManagerView;

public class QuestionManagerController {
    public QuestionManagerController(QuestionManagerModel model, QuestionManagerView view) {
        this.model = model;
        this.view = view;
    }

    questionManager.QuestionManagerModel model = new QuestionManagerModel();
    questionManager.QuestionManagerView view = new QuestionManagerView();
    QuestionManagerController controller = new QuestionManagerController(model, view);

}
