package wordleGame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import mainMenu.*;
import shared.TopBar;

import javax.swing.*;



public class WordleGameController implements ActionListener {
    private MainMenuControl mainMenuControl;

    private WordleGameModel model;
    private WordleGameView view;
    private int attempt = 0;
    boolean buttonEnabled = true;

    public WordleGameController(MainMenuControl mainMenuControl) {
        this.model = new WordleGameModel();
        this.view = new WordleGameView(this);
        this.mainMenuControl = mainMenuControl;

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
                    view.setWinScreen(model.getTargetWord(), attempt);
                } else {
                    view.setStatusMessage("Incorrect! You have " + model.getAttemptsLeft() + " attempts left");
                    view.disableRow(attempt);
                    int[] colorControll = model.checkColor(guess);
                    view.setColors(attempt, colorControll);
                    attempt++;
                    if (model.getAttemptsLeft()==0) {
                        view.setStatusMessage("Game over! The word was " + model.getTargetWord());
                        view.disableInput();
                    }
                }
            }
        } else if (command.equals("restart")) {
                model = new WordleGameModel();
                view.resetView();
                attempt = 0;
        } else if (command.equals("home")) {
            this.display(false);
            mainMenuControl.display(true);
        }
    }

    protected void submit(){
        if(buttonEnabled){ actionPerformed(new ActionEvent(this, 0, "submit"));}
    }

    public void display (boolean show){
        view.display(show);
    }

    public void setButtonEnabled(boolean enabled){
        this.buttonEnabled = enabled;
    }

    public JPanel topPanel(){
        return (new TopBar(this.mainMenuControl));
    }
}