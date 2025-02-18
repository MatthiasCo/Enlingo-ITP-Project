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
        this.model = new QuizGameModel();
        this.view = new QuizGameView(this);
        this.mainMenuControl = mainMenuControl;
    }

    public void display(boolean b) {
        view.setVisible(b);
    }

    public void startQuiz() {
        model.startQuiz();
        Question<String> currentQuestion = model.getCurrentQuestion();
        if (currentQuestion != null) {
            view.displayQuestion(currentQuestion.getText());
        } else {
            view.displayQuestion("No questions available.");
        }
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
            case "submit":
                checkAnswer(view.getAnswerField().getText());
                break;
        }
    }

    public JPanel topPanel(){
        return (new TopBar(this.mainMenuControl));
    }
}