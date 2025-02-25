package quizGame;

import mainMenu.MainMenuControl;
import shared.Question;
import shared.TopBar;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizGameController implements ActionListener {
    static int questionCount = 1;
    private final QuizGameModel model;
    private final QuizGameView view;
    private final MainMenuControl mainMenuControl;

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
            resetQuiz();
            showCompletionPanel();
        } else {
            model.startQuiz();
            Question<?> currentQuestion = model.getCurrentQuestion();
            view.displayQuestion(currentQuestion.getText());
            questionCount++;
        }
    }

    private void resetQuiz() {
        view.resetView();
    }

    private void showCompletionPanel() {
        JPanel completionPanel = new JPanel();
        completionPanel.setLayout(new BoxLayout(completionPanel, BoxLayout.Y_AXIS));
        JLabel messageLabel = new JLabel("Quiz complete! You have answered 10 questions.");
        JLabel correctLabel = new JLabel("Correct answers: " + model.getCorrectAnswers());
        JLabel incorrectLabel = new JLabel("Incorrect answers: " + model.getIncorrectAnswers());
        JLabel percentlabel;
        JLabel complimentLabel = new JLabel(model.getRandomCompliment());
        try {
            percentlabel = new JLabel("Quotia: " + 10 * model.getCorrectAnswers() + "%");
        } catch (ArithmeticException e) {
            percentlabel = new JLabel("Quotia: 0%");
        }
        completionPanel.add(complimentLabel);
        completionPanel.add(messageLabel);
        completionPanel.add(correctLabel);
        completionPanel.add(incorrectLabel);
        completionPanel.add(percentlabel);
        JOptionPane.showMessageDialog(view, completionPanel, "Quiz Complete", JOptionPane.INFORMATION_MESSAGE);
    }

    public void checkAnswer(String answer) {
        if (model.checkAnswer(answer)) {
            view.setQuestionBoolLabelColor(new Color(0, 255, 0));
            view.showResult("Correct!");
        } else {
            view.setQuestionBoolLabelColor(new Color(255, 0, 0));
            if (model.getCorrectAnswer().equals("true")) {
                view.showResult("Incorrect. The correct answer is: Yes!");
            } else if (model.getCorrectAnswer().equals("false")) {
                view.showResult("Incorrect. The correct answer is: No!");
            } else {
                view.showResult("Incorrect. The correct answer is: " + model.getCorrectAnswer());
            }
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
                questionCount = 1;
                model.resetCounters();
                startQuiz();
                break;
            case "submit":
                checkAnswer(view.getAnswerField().getText());
                break;
            case "next":
                view.clearAnswerField();
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
}