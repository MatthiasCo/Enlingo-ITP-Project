package wordleGame;

public class wordleGameController {

}
public static void main(String[] args) {
    // TODO Auto-generated method stub
    WordleGameModel model = new WordleGameModel();
    WordleGameView view = new WordleGameView();
    wordleGameController controller = new wordleGameController(model, view);
    view.setVisible(true);
}
