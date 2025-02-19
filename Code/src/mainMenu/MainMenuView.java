package mainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenuView extends JFrame {
    private JLabel quizGameLabel;
    private JLabel wordleGameLabel;
    private JLabel questionManagerLabel;
    private JPanel topPanel;
    private JPanel centerPanel;
    private JLabel quizGameText;
    private JLabel wordleGameText;
    private JLabel questionManagerText;

    public MainMenuView(MainMenuControl control) {
        setTitle("Enlingo Menu");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Top panel
        topPanel = control.topPanel();
        add(topPanel, BorderLayout.NORTH);

        // Center panel with GridBagLayout for images and text
        centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Load images
        ImageIcon quizGameIcon = new ImageIcon("src/images/Question.svg.png");
        ImageIcon wordleGameIcon = new ImageIcon("src/images/Wordle.svg.png");
        ImageIcon questionManagerIcon = new ImageIcon("src/images/dataB.png");

        Image scaledImageQuiz = quizGameIcon.getImage().getScaledInstance(200, 175, Image.SCALE_DEFAULT);
        ImageIcon resizedIconQuiz = new ImageIcon(scaledImageQuiz);
        Image scaledImageWordle = wordleGameIcon.getImage().getScaledInstance(175, 175, Image.SCALE_DEFAULT);
        ImageIcon resizedIconWordle = new ImageIcon(scaledImageWordle);
        Image scaledImageManager = questionManagerIcon.getImage().getScaledInstance(175, 175, Image.SCALE_DEFAULT);
        ImageIcon resizedIconManager = new ImageIcon(scaledImageManager);

        // Create labels with images
        quizGameLabel = new JLabel(resizedIconQuiz);
        wordleGameLabel = new JLabel(resizedIconWordle);
        questionManagerLabel = new JLabel(resizedIconManager);

        // Create text placeholders
        quizGameText = new JLabel("Quiz Game", SwingConstants.CENTER);
        wordleGameText = new JLabel("Wordle Game", SwingConstants.CENTER);
        questionManagerText = new JLabel("Question Manager", SwingConstants.CENTER);

        // Add text and image labels to the center panel
        gbc.gridy = 0;
        gbc.gridx = 0;
        centerPanel.add(quizGameText, gbc);
        gbc.gridx = 1;
        centerPanel.add(wordleGameText, gbc);
        gbc.gridx = 2;
        centerPanel.add(questionManagerText, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        centerPanel.add(quizGameLabel, gbc);
        gbc.gridx = 1;
        centerPanel.add(wordleGameLabel, gbc);
        gbc.gridx = 2;
        centerPanel.add(questionManagerLabel, gbc);

        // Add mouse listeners for navigation
        quizGameLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                control.navigateToQuizGame();
            }
        });

        wordleGameLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                control.navigateToWordleGame();
            }
        });

        questionManagerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                control.navigateToQuestionManager();
            }
        });

        // Add center panel to the frame
        add(centerPanel, BorderLayout.CENTER);
    }

    public void display(boolean b) {
        setVisible(b);
    }
}