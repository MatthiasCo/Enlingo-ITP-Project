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
        File inputFile = new File(fileLocation);
        File tempFile = new File("src/database/tempFile.csv");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0 && parts[0].trim().matches("\\d+")) {
                    int id = Integer.parseInt(parts[0].trim());
                    if (id != questionId) {
                        writer.write(line);
                        writer.newLine();
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());
        }

        if (!inputFile.delete()) {
            System.err.println("Could not delete original file");
            return;
        }

        if (!tempFile.renameTo(inputFile)) {
            System.err.println("Could not rename temporary file");
        }
    }

    // Fetches all questions from the CSV file
    public List<Question<T>> getAllQuestions() {
        List<Question<T>> questions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileLocation))) {
            String line;
            for (int id = 1; ((line = reader.readLine()) != null); id++) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    if (parts[0] != null) {
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

        public T convertToType(String value) {
            Object smallestValue;
            if (value.length() == 1 && !Character.isDigit(value.charAt(0))) {
                smallestValue = value.charAt(0);
            } else {
                try {
                    smallestValue = Byte.parseByte(value);
                } catch (NumberFormatException e) {
                    try {
                        smallestValue = Short.parseShort(value);
                    } catch (NumberFormatException ex) {
                        try {
                            smallestValue = Integer.parseInt(value);
                        } catch (NumberFormatException ex2) {
                            try {
                                smallestValue = Long.parseLong(value);
                            } catch (NumberFormatException ex3) {
                                try {
                                    smallestValue = Float.parseFloat(value);
                                } catch (NumberFormatException ex4) {
                                    try {
                                        smallestValue = Double.parseDouble(value);
                                    } catch (NumberFormatException ex5) {
                                        smallestValue = value;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (smallestValue.getClass().equals(type)) {
                return type.cast(smallestValue);
            }
            return null;
        }
}
