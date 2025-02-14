package wordleGame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;



public class WordleGameController implements ActionListener {
    private WordleGameModel model;
    private WordleGameView view;

    public WordleGameController() {
        this.model = new WordleGameModel();
        this.view = new WordleGameView(this);

    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("start")) {
            view.mainView(this.model.getTargetQuestion());
        }
    }

    public static void main(String[] args) {
        WordleGameController controller = new WordleGameController();
    }
}

