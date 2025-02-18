package quizGame;

import database.DatabaseManager;
import shared.Classes;
import shared.Question;

public class QuizGameModel {
    private DatabaseManager databaseManager;
    private Question<String> currentQuestion;
    private Question<String> lastQuestion;

    public QuizGameModel() {
        this.databaseManager = new DatabaseManager(Classes.STRING);
    }

    public Question<String> getCurrentQuestion() {
        return currentQuestion;
    }

    public void startQuiz() {
        do {
            currentQuestion = databaseManager.getRandomQuestion();
        } while (currentQuestion != null && currentQuestion.equals(lastQuestion));

        if (currentQuestion != null) {
            lastQuestion = currentQuestion;
        }
    }

    public boolean checkAnswer(String answer) {
        return currentQuestion != null && currentQuestion.getAnswers()[0].equalsIgnoreCase(answer);
    }

    public String getCorrectAnswer() {
        return currentQuestion != null ? currentQuestion.getAnswers()[0] : null;
    }
}