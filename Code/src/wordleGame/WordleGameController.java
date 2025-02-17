package wordleGame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
//import mainMenu.*;



public class WordleGameController implements ActionListener {
    private WordleGameModel model;
    private WordleGameView view;
    private int attempt = 0;

    public WordleGameController() {
        this.model = new WordleGameModel();
        this.view = new WordleGameView(this);

    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("start")) {
            view.mainView();
        } else if (command.equals("submit")) {
            String guess = view.getCurrentGuess(attempt);
            if (guess.length() != 5) {
                view.setStatusMessage("Please enter exactly 5 letters");
            } else {
                if (model.checkAnswer(guess)) {
                    view.setStatusMessage("Correct! The word was " + model.getTargetWord());
                    for (int i = 0; i < 5; i++) {
                        view.setColor(attempt, i, Color.GREEN);
                    }
                    view.disableInput();
                } else {
                    view.setStatusMessage("Incorrect! You have " + model.getAttemptsLeft() + " attempts left");
                    view.disableRow(attempt);
                    int[] colors = model.checkColor(guess);
                    for (int i = 0; i < 5; i++) {
                        if (colors[i] == 1) {
                            view.setColor(attempt, i, Color.GREEN);
                        } else if (colors[i] == 2) {
                            view.setColor(attempt, i, Color.YELLOW);
                        } else {
                            view.setColor(attempt, i, Color.GRAY);
                        }
                    }
                    attempt++;
                    if (model.getAttemptsLeft() == 0) {
                        view.setStatusMessage("Game over! The word was " + model.getTargetWord());
                        view.disableInput();
                    }
                }
            } //else if (command.equals("restart")) {
                model = new WordleGameModel();
                //view.resetView();
                attempt = 0;
           // } else if (command.equals("home")) {
                //mainMenu.
                //model = new WordleGameModel();
                //attempt = 0;
        }
    }

    public static void main(String[] args) {
        WordleGameController controller = new WordleGameController();
    }
}

