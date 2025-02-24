package quizGame;

import database.DatabaseManager;
import shared.Question;

public class QuizGameModel {
    private final DatabaseManager databaseManager;
    private Question<?> currentQuestion;
    private Question<?> lastQuestion;
    private int correctAnswers = 0;
    private int incorrectAnswers = 0;

    public QuizGameModel() {
        this.databaseManager = new DatabaseManager(Object.class);
        currentQuestion = databaseManager.getRandomQuestion();
    }

    public Question<?> getCurrentQuestion() {
        return currentQuestion;
    }

    public void startQuiz() {
        do {
            currentQuestion = databaseManager.getRandomQuestion();
        } while (currentQuestion != null && currentQuestion.equals(lastQuestion) && QuizGameController.getQuestionCount() <= 10);

        if (currentQuestion != null) {
            lastQuestion = currentQuestion;
        }
    }

    public boolean isQuizComplete() {
        return QuizGameController.getQuestionCount() > 10;
    }

    public boolean checkAnswer(String answer) {
        answer = answer.toLowerCase();
        if (answer.equals("ja") || answer.equals("richtig") || answer.equals("wahr") || answer.equals("korrekt") || answer.equals("yes") || answer.equals("yea") || answer.equals("yez") || answer.equals("ye") || answer.equals("yep") || answer.equals("uhuh")) {
            answer = "true";
        } else if (answer.equals("nein") || answer.equals("falsch") || answer.equals("unkorrekt") || answer.equals(
                "no") || answer.equals("nuh uh")) {
            answer = "false";
        }
        boolean isCorrect = false;
        if (currentQuestion != null) {
            for (Object correctAnswer : currentQuestion.getAnswers()) {
                if (correctAnswer.toString().equalsIgnoreCase(answer)) {
                    isCorrect = true;
                    break;
                }
            }
        }

        if (isCorrect) {
            correctAnswers++;
        } else {
            incorrectAnswers++;
        }
        return isCorrect;
    }

    public Object getCorrectAnswer() {
        return currentQuestion != null ? currentQuestion.getAnswers()[0] : null;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void resetCounters() {
        correctAnswers = 0;
        incorrectAnswers = 0;
    }

    public String getRandomCompliment() {
        String[] compliments = {"You are doing great!", "You are learning!", "You are improving!", "Don't stop now!",
                "Legendary!", "Winner Winner Chicken Dinner!", "Keep grinding!", "Impressive!", "Nice job!"};
        int random = (int)(Math.random() * compliments.length);
        return compliments[random];
    }
}