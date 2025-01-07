package wordleGame;

public class WordleGameModel {
    private String targetWord;

    public WordleGameModel() {
        targetWord = questionManager.getRandomFrage();
    }
}
