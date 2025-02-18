package questionManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import shared.Question;

public class QuestionManagerView extends JFrame {
    private JList<Question<Object>> questionList;
    private DefaultListModel<Question<Object>> listModel;
    private JTextArea questionText;
    private JButton saveButton;
    private JPanel topPanel;
    private QuestionManagerController controller;

    public QuestionManagerView(QuestionManagerController controller) {
        this.controller = controller;
        listModel = new DefaultListModel<>();
    }

    public void init() {
        setTitle("Question Manager");
        setSize(1000, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        questionList = new JList<>(listModel);
        questionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        questionList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Question<Object> selectedQuestion = questionList.getSelectedValue();
                if (selectedQuestion != null) {
                    questionText.setText(selectedQuestion.getText());
                }
            }
        });

        JScrollPane listScrollPane = new JScrollPane(questionList);
        add(listScrollPane, BorderLayout.WEST);

        questionText = new JTextArea();
        JScrollPane textScrollPane = new JScrollPane(questionText);
        add(textScrollPane, BorderLayout.CENTER);

        saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            Question<Object> selectedQuestion = questionList.getSelectedValue();
            if (selectedQuestion != null) {
                selectedQuestion.setText(questionText.getText());
                controller.updateQuestion(selectedQuestion);
            }
        });

        topPanel = controller.topPanel();
        add(topPanel, BorderLayout.NORTH);
        add(saveButton, BorderLayout.SOUTH);
        setVisible(true);
    }

    public void setQuestions(List<Question<Object>> questions) {
        listModel.clear();
        for (Question<Object> question : questions) {
            listModel.addElement(question);
        }
    }
}