package questionManager;
import database.DatabaseManager;
import shared.Classes;
import shared.Question;

import java.util.*;

public class QuestionManagerModel {
    private DatabaseManager<Object> dbController;

    public QuestionManagerModel() {
        this.dbController = new DatabaseManager<>(Object.class);
    }

    public List<Question<Object>> getAllQuestions() {
        Set<Question<Object>> allQuestions = new HashSet<>();
        allQuestions.addAll(fetchQuestionsOfType(Classes.STRING));
        allQuestions.addAll(fetchQuestionsOfType(Classes.INTEGER));
        allQuestions.addAll(fetchQuestionsOfType(Classes.DOUBLE));
        allQuestions.addAll(fetchQuestionsOfType(Classes.BOOLEAN));
        allQuestions.addAll(fetchQuestionsOfType(Classes.FLOAT));
        allQuestions.addAll(fetchQuestionsOfType(Classes.LONG));
        allQuestions.addAll(fetchQuestionsOfType(Classes.SHORT));
        allQuestions.addAll(fetchQuestionsOfType(Classes.BYTE));
        allQuestions.addAll(fetchQuestionsOfType(Classes.CHARACTER));
        List<Question<Object>> sortedQuestions = new ArrayList<>(allQuestions);
        sortedQuestions.sort(Comparator.comparingInt(Question::getId));
        return sortedQuestions;
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