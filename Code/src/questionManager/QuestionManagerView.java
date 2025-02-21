package questionManager;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import shared.Question;

public class QuestionManagerView extends JFrame {
    private JTable questionTable;
    private DefaultTableModel tableModel;
    private JButton removeButton;
    private JButton addButton;
    private JPanel topPanel;
    private QuestionManagerController controller;

    public QuestionManagerView(QuestionManagerController controller) {
        this.controller = controller;
    }

    public void init() {
        setTitle("Question Manager");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        topPanel = controller.topPanel();
        removeButton = new JButton("Remove");
        addButton = new JButton("Add");

        tableModel = new DefaultTableModel(new Object[]{"ID", "Question", "Answer Type", "Answers"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0 && column != 2; // Make all columns except ID and Answer Type editable
            }
        };
        questionTable = new JTable(tableModel);
        questionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        controller.loadQuestions();
        // Add a listener to handle cell updates
        tableModel.addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                if (column == 3) { // If Answers column is updated
                     int id = (int) tableModel.getValueAt(row, 0);
                    String questionText = (String) tableModel.getValueAt(row, 1);
                    String answersJoined = (String) tableModel.getValueAt(row, 3);
                    String[] answers = answersJoined.isEmpty() ? new String[0] : answersJoined.split(";");
                    String answerType = answers.length > 1 ? answers[0].getClass().getSimpleName() + "-array" : answers[0].getClass().getSimpleName();
                    tableModel.setValueAt(answerType, row, 2);
                    Question<Object> question = new Question<>(id, questionText, answers, Object.class);
                    controller.updateQuestion(question);
                    controller.loadQuestions();
                }
            }
        });

        JScrollPane tableScrollPane = new JScrollPane(questionTable);
        add(tableScrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        addButton.addActionListener(e -> {
            int newId = tableModel.getRowCount() + 1; // Generate a new ID
            tableModel.addRow(new Object[]{newId, "New Question", "String", ""});
        });

        removeButton.addActionListener(e -> {
            int selectedRow = questionTable.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) tableModel.getValueAt(selectedRow, 0);
                controller.removeQuestion(id);
                tableModel.removeRow(selectedRow);
            }
        });

        add(buttonPanel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);
        setVisible(true);
    }

    public void setQuestions(List<Question<?>> questions) {
        tableModel.setRowCount(0);
        for (Question<?> question : questions) {
            String answerType = question.getAnswers().getClass().getComponentType().getSimpleName();
            if (question.getAnswers().length > 1) {
                answerType += "-array";
            }
            String[] answers = Arrays.stream(question.getAnswers())
                    .map(Object::toString)
                    .toArray(String[]::new);
            String answersJoined = String.join(";", answers);
            tableModel.addRow(new Object[]{question.getId(), question.getText(), answerType, answersJoined});
        }
    }
}