package wordleGame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class WordleGameController {
    private final WordleGameModel model;
    private final WordleGameView view;

    public WordleGameController(WordleGameModel model, WordleGameView view) {
        this.model = model;
        this.view = view;
    }

    public void ActionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        WordleGameModel model = new WordleGameModel();
        WordleGameView view = new WordleGameView();
        WordleGameController controller = new WordleGameController(model, view);
    }
}

