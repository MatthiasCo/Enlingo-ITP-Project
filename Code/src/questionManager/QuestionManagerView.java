package questionManager;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import shared.Question;

public class QuestionManagerView extends JFrame {
    private JTable questionTable;
    private DefaultTableModel tableModel;
    private JTextField questionTextField;
    private JTextField answerTextField;
    private JButton saveButton;
    private JPanel topPanel;
    private QuestionManagerController controller;

    public QuestionManagerView(QuestionManagerController controller) {
        this.controller = controller;
        init();
    }

    public void init() {
        setTitle("Question Manager");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        topPanel = controller.topPanel();
        saveButton = new JButton("Save");
        answerTextField = new JTextField();
        questionTextField = new JTextField();


        tableModel = new DefaultTableModel(new Object[]{"ID", "Question", "Answer"}, 0);
        questionTable = new JTable(tableModel);
        questionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        questionTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = questionTable.getSelectedRow();
                if (selectedRow != -1) {
                    questionTextField.setText((String) tableModel.getValueAt(selectedRow, 1));
                    answerTextField.setText((String) tableModel.getValueAt(selectedRow, 2));
                }
            }
        });

        JScrollPane tableScrollPane = new JScrollPane(questionTable);
        add(tableScrollPane, BorderLayout.WEST);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Question:"));
        inputPanel.add(questionTextField);

        inputPanel.add(new JLabel("Answer:"));
        inputPanel.add(answerTextField);

        saveButton.addActionListener(e -> {
            int selectedRow = questionTable.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) tableModel.getValueAt(selectedRow, 0);
                String questionText = questionTextField.getText();
                String answerText = answerTextField.getText();
                Question<Object> question = new Question<>(id, questionText, answerText, Object.class);
                controller.updateQuestion(question);
            }
        });

        inputPanel.add(saveButton);
        add(inputPanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        setVisible(true);
    }

    public void setQuestions(List<Question<?>> questions) {
        tableModel.setRowCount(0);
        for (Question<?> question : questions) {
            tableModel.addRow(new Object[]{question.getId(), question.getText(), question.getAnswers()});
        }
    }
}