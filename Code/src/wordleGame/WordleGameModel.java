package wordleGame;
import database.DatabaseManager;
import shared.Classes;
import shared.Question;

public class WordleGameModel {
    private DatabaseManager databaseManager;
    private String targetWord;
    private Question question;
    private int attemptsleft = 5;

    public WordleGameModel() {
        databaseManager = new DatabaseManager(Classes.STRING);
        question = databaseManager.getRandomWordleQuestion();
        this.targetWord = question.getAnswers()[0].toString().toUpperCase();
    }


    public boolean checkAnswer(String answer) {
        if (answer.equalsIgnoreCase(targetWord)) {
            return true;
        } else {
            attemptsleft--;
            return false;
        }
    }
    public String getTargetWord() {
        return targetWord;
    }

    public int getAttemptsLeft() {
        return attemptsleft;
    }

    public int[] checkColor(String guess){
        int[] colors = new int[5];
        for(int i=0; i<5;i++) {
            char c = guess.charAt(i);
            if (c == this.targetWord.charAt(i)) {
                colors[i] = 1; //green
            } else if (this.targetWord.contains(String.valueOf(c))) {
                colors[i] = 2;   //yellow
            } else {
                colors[i] = 3 ;  //grey
            }
        }
        return colors;
    }
}
