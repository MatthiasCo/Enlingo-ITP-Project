package questionManager;
import database.DatabaseManager;
import shared.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionManagerModel {
    private DatabaseManager<Object> dbController;

    public QuestionManagerModel() {
        this.dbController = new DatabaseManager<>(Object.class);
    }

    public List<Question<Object>> getAllQuestions() {
        List<Question<Object>> allQuestions = new ArrayList<>();
        allQuestions.addAll(fetchQuestionsOfType(String.class));
        allQuestions.addAll(fetchQuestionsOfType(Integer.class));
        allQuestions.addAll(fetchQuestionsOfType(Double.class));
        allQuestions.addAll(fetchQuestionsOfType(Boolean.class));
        return allQuestions;
    }

    private <T> List<Question<Object>> fetchQuestionsOfType(Class<T> type) {
        DatabaseManager<T> specificDbController = new DatabaseManager<>(type);
        List<Question<T>> specificQuestions = specificDbController.getAllQuestions();
        List<Question<Object>> questions = new ArrayList<>();
        for (Question<T> question : specificQuestions) {
            questions.add(new Question<>(question.getId(), question.getText(), (Object) question.getAnswers(), Object.class));
        }
        return questions;
    }

    public boolean updateQuestion(Question<Object> question) {
        List<Question<Object>> allQuestions = getAllQuestions();
        for (Question<Object> q : allQuestions) {
            if (q.getId() == question.getId()) {
                q.setText(question.getText());
                q.setAnswers(question.getAnswers());
                dbController.removeQuestion(q.getId());
                dbController.addQuestion(q);
                return true;
            }
        }
        return false;
    }
}