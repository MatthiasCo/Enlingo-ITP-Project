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
    static int questionCount = 1;

    public QuizGameController(MainMenuControl mainMenuControl) {
        this.mainMenuControl = mainMenuControl;
        this.model = new QuizGameModel();
        this.view = new QuizGameView(this);
    }

    public static int getQuestionCount() {
        return questionCount;
    }

    public void display(boolean b) {
        view.setVisible(b);
    }

    public void startQuiz() {
        if (model.isQuizComplete()) {
            showCompletionPanel();
            questionCount = 0;
            model.resetCounters();
            model.startQuiz();
        } else {
            model.startQuiz();
            Question<String> currentQuestion = model.getCurrentQuestion();
            view.displayQuestion(currentQuestion.getText());
            questionCount++;
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
            case "start":
                view.mainGameView();
                startQuiz();
                break;
            case "submit":
                checkAnswer(view.getAnswerField().getText());
                break;
            case "next":
                startQuiz();
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

    private void showCompletionPanel() {
        JPanel completionPanel = new JPanel();
        completionPanel.setLayout(new BoxLayout(completionPanel, BoxLayout.Y_AXIS));
        JLabel messageLabel = new JLabel("Quiz complete! You have answered 10 questions.");
        JLabel correctLabel = new JLabel("Correct answers: " + model.getCorrectAnswers());
        JLabel incorrectLabel = new JLabel("Incorrect answers: " + model.getIncorrectAnswers());
        completionPanel.add(messageLabel);
        completionPanel.add(correctLabel);
        completionPanel.add(incorrectLabel);
        JOptionPane.showMessageDialog(view, completionPanel, "Quiz Complete", JOptionPane.INFORMATION_MESSAGE);
    }
}