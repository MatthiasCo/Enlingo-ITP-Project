package wordleGame;
import database.DatabaseManager;
import shared.Question;

public class WordleGameModel {
    private DatabaseManager databaseManager;
    private String targetWord;
    private String targetQuestion;
    private Question question;

    public WordleGameModel() {
        databaseManager = new DatabaseManager();
        do {

            question = databaseManager.getRandomQuestion();
        } while (!question.isForWordle());

        this.targetWord = question.getAnswers()[0].toString();
        this.targetQuestion = question.getText();
    }

    public String getTargetQuestion() {
        return targetQuestion;
    }
}
