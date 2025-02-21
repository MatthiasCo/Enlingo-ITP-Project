package quizGame;

import mainMenu.MainMenuControl;
import shared.Question;
import shared.TopBar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizGameController implements ActionListener {
    private QuizGameModel model;
    private QuizGameView view;
    private MainMenuControl mainMenuControl;

    public QuizGameController(MainMenuControl mainMenuControl) {
        this.mainMenuControl = mainMenuControl;
        this.model = new QuizGameModel();
        this.view = new QuizGameView(this);
    }

    public void display(boolean b) {
        view.setVisible(b);
    }

    public void startQuiz() {
        model.startQuiz();
        Question<String> currentQuestion = model.getCurrentQuestion();
        view.displayQuestion(currentQuestion.getText());
    }

    public void checkAnswer(String answer) {
        if (model.checkAnswer(answer)) {
            view.showResult("Correct!");
        } else {
            view.showResult("Incorrect. The correct answer is: " + model.getCorrectAnswer());
        }
        view.clearAnswerField();
        startQuiz();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "start":
                view.mainGameView();
                startQuiz();
                break;
            case "submit":
                checkAnswer(view.getAnswerField().getText());
                break;
        }
    }

    public void startWelcome() {
        view.welcomeView();
        view.setVisible(true);
    }

    public JPanel topPanel() {
        return new TopBar(this.mainMenuControl);
    }
}