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

    public List<Question<?>> getAllQuestions() {
        Set<Question<?>> allQuestions = new HashSet<>();
        allQuestions.addAll(fetchQuestionsOfType(Classes.STRING));
        allQuestions.addAll(fetchQuestionsOfType(Classes.INTEGER));
        allQuestions.addAll(fetchQuestionsOfType(Classes.DOUBLE));
        allQuestions.addAll(fetchQuestionsOfType(Classes.BOOLEAN));
        allQuestions.addAll(fetchQuestionsOfType(Classes.FLOAT));
        allQuestions.addAll(fetchQuestionsOfType(Classes.LONG));
        allQuestions.addAll(fetchQuestionsOfType(Classes.SHORT));
        allQuestions.addAll(fetchQuestionsOfType(Classes.BYTE));
        allQuestions.addAll(fetchQuestionsOfType(Classes.CHARACTER));
        List<Question<?>> sortedQuestions = new ArrayList<>(allQuestions);
        sortedQuestions.sort(Comparator.comparingInt(Question::getId));
        return sortedQuestions;
    }

    private <T> List<Question<?>> fetchQuestionsOfType(Class<T> type) {
        DatabaseManager<T> specificDbController = new DatabaseManager<>(type);
        return new ArrayList<>(specificDbController.getAllQuestions());
    }

    public <T> boolean updateQuestion(Question<?> question) {
        List<Question<?>> allQuestions = getAllQuestions();
        for (Question<?> q : allQuestions) {
            if (q.getId() == question.getId()) {
                q.setText(question.getText());
                setQuestionAnswers((Question<T>) q, (T[]) question.getAnswers());
                dbController.removeQuestion(q.getId());
                dbController.addQuestion((Question<Object>) q);
                return true;
            }
        }
        return false;
    }

    private <T> void setQuestionAnswers(Question<T> question, T[] answers) {
        question.setAnswers(answers);
    }
}