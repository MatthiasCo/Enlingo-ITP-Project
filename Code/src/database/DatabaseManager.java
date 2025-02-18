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
                int id = 0;
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    if (parts.length > 0 && parts[0] != null) {
                        String trimmed = parts[0].trim();
                        if (trimmed.matches("\\d+")) {  // Check if it's only digits
                            id = Integer.parseInt(trimmed);
                        } else {
                            System.out.println("Invalid input: '" + trimmed + "'");
                        }
                    } else {
                        System.out.println("Invalid parts array.");
                    }
                    String text = parts[1];
                    String[] answers = (parts[2].trim()).replace("[", "").replace("]", "").split(";");

                    T convertedAnswer = convertToType(answers[0]);
                    if (type.isInstance(convertedAnswer)) {
                        questions.add(new Question<>(id, text, convertedAnswer, type));
                    }
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

    private T convertToType(String value) {
        try {
            if (type == Byte.class && Byte.parseByte(value) == Double.parseDouble(value)) {
                return type.cast(Byte.parseByte(value));
            } else if (type == Short.class && Short.parseShort(value) == Double.parseDouble(value)) {
                return type.cast(Short.parseShort(value));
            } else if (type == Integer.class && Integer.parseInt(value) == Double.parseDouble(value)) {
                return type.cast(Integer.parseInt(value));
            } else if (type == Long.class && Long.parseLong(value) == Double.parseDouble(value)) {
                return type.cast(Long.parseLong(value));
            } else if (type == Float.class && Float.parseFloat(value) == Double.parseDouble(value)) {
                return type.cast(Float.parseFloat(value));
            } else if (type == Double.class) {
                return type.cast(Double.parseDouble(value));
            } else if (type == Boolean.class) {
                if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
                    return type.cast(Boolean.parseBoolean(value));
                }
            } else if (type == Character.class) {
                if (value.length() == 1) {
                    return type.cast(value.charAt(0));
                }
            } else if (type == String.class) {
                return type.cast(value);
            }
        } catch (NumberFormatException e) {
            System.err.println("Error converting value: " + value + " to type: " + type.getSimpleName());
            return null;
        }
        return null;
    }
}
