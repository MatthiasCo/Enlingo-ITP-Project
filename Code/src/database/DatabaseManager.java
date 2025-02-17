package database;

import shared.Question;
import java.io.*;
import java.util.*;

public class DatabaseManager<T> {
    private static String fileLocation = "src/database/Datenbank_Enlingo.csv";
    private final Class<T> type;

    public DatabaseManager(Class<T> type) {
        this.type = type;
    }

    // Adds a question to the CSV file
    public void addQuestion(Question<T> question) {
        try (FileWriter writer = new FileWriter(fileLocation, true)) {
            writer.append(question.csvConvert()).append("\n");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // Removes a question by ID
    public void removeQuestion(int questionId) {
        List<Question<T>> allQuestions = getAllQuestions();
        try (FileWriter writer = new FileWriter(fileLocation)) {
            for (Question<T> question : allQuestions) {
                if (question.getId() != questionId) {
                    writer.append(question.csvConvert()).append("\n");
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // Fetches all questions from the CSV file
    public List<Question<T>> getAllQuestions() {
        List<Question<T>> questions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileLocation))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0]);
                    String text = parts[1];
                    String[] answers = parts[2].replace("[", "").replace("]", "").split(";");

                    T convertedAnswer = convertToType(answers[0]);
                    questions.add(new Question<>(id, text, convertedAnswer, type));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return questions;
    }

    // Fetches a random question from the CSV file
    public Question<T> getRandomQuestion() {
        List<Question<T>> questions = getAllQuestions();
        if (questions.isEmpty()) {
            return null;
        }
        Random random = new Random();
        return questions.get(random.nextInt(questions.size()));
    }

    public Question<T> getRandomWordleQuestion() {
        List<Question<T>> questions = getAllQuestions();
        if (questions.isEmpty()) {
            return null;
        }
        Random random = new Random();
        Question<T> question = questions.get(random.nextInt(questions.size()));
        while (!question.isForWordle()) {
            question = questions.get(random.nextInt(questions.size()));
        }
        return question;
    }

    // Converts the string answer to the specified type dynamically
    private T convertToType(String value) {
        if (type == Integer.class) {
            return type.cast(Integer.parseInt(value));
        } else if (type == Double.class) {
            return type.cast(Double.parseDouble(value));
        } else if (type == Boolean.class) {
            return type.cast(Boolean.parseBoolean(value));
        } else {
            return type.cast(value); // Default to String
        }
    }
}
