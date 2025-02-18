package quizGame;

import mainMenu.MainMenuControl;
import shared.Classes;
import shared.Question;
import database.DatabaseManager;

public class QuizGameController {
    private QuizGameModel model;
    private QuizGameView view;
    private MainMenuControl mainMenuControl;
    private DatabaseManager databaseManager;
    private Question<String> currentQuestion;
    private Question<String> lastQuestion;

    public QuizGameController() {
        this.model = new QuizGameModel();
        this.view = new QuizGameView(this);
        this.mainMenuControl = mainMenuControl;
        this.databaseManager = new DatabaseManager(Classes.STRING);
    }

    public void display() {
        view.setVisible(true);
        startQuiz();
    }

    public void navigateBack() {
        view.setVisible(false);
        mainMenuControl.display();
    }

    public void startQuiz() {
        do {
            currentQuestion = databaseManager.getRandomQuestion();
        } while (currentQuestion != null && currentQuestion.equals(lastQuestion));

        if (currentQuestion != null) {
            view.displayQuestion(currentQuestion.getText());
            lastQuestion = currentQuestion;
        } else {
            view.displayQuestion("No questions available.");
        }
    }

    public void checkAnswer(String answer) {
        if (currentQuestion != null && currentQuestion.getAnswers()[0].equalsIgnoreCase(answer)) {
            view.showResult("Correct!");
        } else {
            view.showResult("Incorrect. The correct answer is: " + currentQuestion.getAnswers()[0]);
        }
        view.clearAnswerField();
        startQuiz();
    }
}