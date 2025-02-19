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
        boolean[] matched = new boolean[5]; // Track which target letters are matched

        // First pass: Mark green and track exact matches
        for (int i = 0; i < 5; i++) {
            char c = guess.charAt(i);
            if (c == this.targetWord.charAt(i)) {
                colors[i] = 1; // Green
                matched[i] = true; // Mark this position as used
            }
        }

        // Second pass: Mark yellow only if the letter appears elsewhere and is not already matched
        for (int i = 0; i < 5; i++) {
            if (colors[i] == 0) { // If not already marked as green
                char c = guess.charAt(i);
                for (int j = 0; j < 5; j++) {
                    if (!matched[j] && this.targetWord.charAt(j) == c) {
                        colors[i] = 2; // Yellow
                        matched[j] = true; // Mark this occurrence as used
                        break; // Only mark one occurrence
                    }
                }
                if (colors[i] == 0) {
                    colors[i] = 3; // Grey if not found in the word
                }
            }
        }

        return colors;

    }
}
