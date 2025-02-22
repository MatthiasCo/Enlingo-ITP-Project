package questionManager;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import database.DatabaseManager;
import shared.Classes;
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
                if (column == 1 || column == 3) { // If Question or Answers column is updated
                    int id = (int) tableModel.getValueAt(row, 0);
                    String questionText = (String) tableModel.getValueAt(row, 1);
                    String answersJoined = (String) tableModel.getValueAt(row, 3);
                    String[] answers = answersJoined.isEmpty() ? new String[0] : answersJoined.split(",\\s*");

                    String[] typedAnswers = Arrays.stream(answers)
                            .filter(Objects::nonNull) // Filter out null values
                            .filter(answer -> !answer.isEmpty()) // Filter out empty strings`
                            .map(String::trim)
                            .toArray(String[]::new);

                    ArrayList<Object> fixedAnswers = new ArrayList<>();
                    for (String answer : typedAnswers) {
                        Object typedAnswer;
                        if (answer.equalsIgnoreCase("true") || answer.equalsIgnoreCase("false")) {
                            typedAnswer = Boolean.parseBoolean(answer);
                        } else {
                            typedAnswer = controller.getDB().convertToSmallest(answer);
                        }
                        fixedAnswers.add(typedAnswer);
                    }

                    if (fixedAnswers.size() > 1 && fixedAnswers.stream().anyMatch(answer -> answer instanceof Boolean)) {
                        JOptionPane.showMessageDialog(null, "You can't have multiple answers with a boolean.", "Error", JOptionPane.ERROR_MESSAGE);
                        controller.loadQuestions();
                        return;
                    }
                    if(fixedAnswers.size() > 1) {
                        Class<?> type = fixedAnswers.getFirst().getClass();
                        for (Object answer : fixedAnswers) {
                            if (!type.equals(answer.getClass())) {
                                JOptionPane.showMessageDialog(this, "All answers must be of the same type", "Error", JOptionPane.ERROR_MESSAGE);
                                controller.loadQuestions();
                                return;
                            }
                        }
                    }

                    // Create a new Question object with an array of answers
                    Question<Object> question = new Question<>(id, questionText, fixedAnswers.toArray());

                    controller.updateQuestion(question);
                }
            }
        });

        JScrollPane tableScrollPane = new JScrollPane(questionTable);
        add(tableScrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        addButton.addActionListener(e -> {
            int newId = 0; // Default ID if the table is empty
            int rowCount = tableModel.getRowCount();
            if (rowCount > 0) {
                int lastId = (int) tableModel.getValueAt(rowCount - 1, 0);
                newId = lastId + 1;
            }
            //tableModel.addRow(new Object[]{newId, "New Question", "String", ""});
            controller.getDB().addQuestion(new Question<>(newId, "New Question", new Object[]{"Answer"}));
            controller.loadQuestions();

            // Highlight the new row
            int newRow = tableModel.getRowCount() - 1;
            questionTable.setRowSelectionInterval(newRow, newRow);
            questionTable.scrollRectToVisible(questionTable.getCellRect(newRow, 0, true));

            questionTable.editCellAt(newRow, 1);
            Component editor = questionTable.getEditorComponent();
            if (editor instanceof JTextComponent) {
                JTextComponent textComponent = (JTextComponent) editor;
                textComponent.requestFocusInWindow();
                textComponent.selectAll();
            }
        });

        removeButton.addActionListener(e -> {
            int selectedRow = questionTable.getSelectedRow();
            if (selectedRow != -1 && selectedRow < tableModel.getRowCount()) {
                int id = (int) tableModel.getValueAt(selectedRow, 0);
                controller.removeQuestion(id);
                controller.loadQuestions();
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
            if(question.getAnswers()[0].equals("true") || question.getAnswers()[0].equals("false")){
                answerType = "Boolean";
            }
            if (question.getAnswers().length > 1) {
                answerType += "  (Multiple Answers)";
            }
            String[] answers = Arrays.stream(question.getAnswers())
                    .map(Object::toString)
                    .toArray(String[]::new);
            String answersJoined = String.join(", ", answers); // Join answers with a comma and space
            tableModel.addRow(new Object[]{question.getId(), question.getText(), answerType, answersJoined});
        }
    }
}