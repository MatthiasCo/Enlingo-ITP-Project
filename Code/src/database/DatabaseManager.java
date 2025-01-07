package database;
import java.io.*;
import java.util.*;

public class DatabaseManager {

    private final static String fileLocation = ".Datenbank_Enlingo.csv";

    public DatabaseManager() {
        this.fileLocation = fileLocation;
    }

    // Adds a question to the CSV file
    public void addQuestion(Question question) {
        try (FileWriter writer = new FileWriter(this.fileLocation)) {
            writer.append(question.getId() + "," + question.getText() + "," + question.getCorrectAnswer() + "\n");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // Removes a question by ID
    public void removeQuestion(int questionId) {
        List<Question> allQuestions = getAllQuestions();
        try (FileWriter writer = new FileWriter(fileLocation)) {
            for (Question question : allQuestions) {
                if (question.getId() != questionId) {
                    writer.append(question.getId() + "," + question.getText() + "," + question.getCorrectAnswer() + "\n");
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // Fetches all questions from the CSV file
    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileLocation))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0]);
                    String text = parts[1];
                    String correctAnswer = parts[2];
                    questions.add(new Question(id, text, correctAnswer));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return questions;
    }

    // Fetches a random question from the CSV file
    public Question getRandomQuestion() {
        List<Question> questions = getAllQuestions();
        if (questions.isEmpty()) {
            return null;
        }
        Random random = new Random();
        return questions.get(random.nextInt(questions.size()));
    }
}

class Question {
    private int id;
    private String text;
    private String correctAnswer;

    public Question(int id, String text, String correctAnswer) {
        this.id = id;
        this.text = text;
        this.correctAnswer = correctAnswer;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                '}';
    }
}