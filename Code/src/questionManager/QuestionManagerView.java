package questionManager;

import javax.swing.*;
import java.awt.*;

public class QuestionManagerView extends JFrame {
    private QuestionManagerController controller;

    public QuestionManagerView(QuestionManagerController controller) {
        this.controller = controller;
        setTitle("Question Manager");
        setSize(960, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 1)); // 1 row, 1 column

        JButton backButton = new JButton("Back");
        backButton.setHorizontalAlignment(SwingConstants.CENTER);
        backButton.addActionListener(e -> controller.navigateBack());

        add(backButton);
        setResizable(false);
    }

    public void init() {
        setVisible(true);
    }
}