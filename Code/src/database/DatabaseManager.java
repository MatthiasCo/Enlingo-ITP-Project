package database;
import shared.Question;

import java.io.*;
import java.util.*;

public class DatabaseManager {

    private static String fileLocation = "src/database/Datenbank_Enlingo.csv";

    public DatabaseManager() {
        fileLocation = fileLocation;
    }


    // Adds a question to the CSV file
    public void addQuestion(Question<String> question) {
        try (FileWriter writer = new FileWriter(fileLocation, true)) {
            writer.append(question.csvConvert()).append("\n");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // Removes a question by ID
    public void removeQuestion(int questionId) {
        List<Question<String>> allQuestions = getAllQuestions();
        try (FileWriter writer = new FileWriter(fileLocation)) {
            for (Question<String> question : allQuestions) {
                if (question.getId() != questionId) {
                    writer.append(question.toString()).append("\n");
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // Fetches all questions from the CSV file
    public List<Question<String>> getAllQuestions() {
        List<Question<String>> questions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileLocation))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0]);
                    String text = parts[1];
                    String[] answers = parts[2].replace("[", "").replace("]", "").split(";");
                    if(answers.length == 1){
                        questions.add(new Question<>(id, text, answers[0], String.class));
                        } else {
                            questions.add(new Question<>(id, text, answers));
                        }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return questions;
    }

    // Fetches a random question from the CSV file
    public Question<String> getRandomQuestion() {
        List<Question<String>> questions = getAllQuestions();
        if (questions.isEmpty()) {
            return null;
        }
        Random random = new Random();
        return questions.get(random.nextInt(questions.size()));
    }

    public Question<String> getRandomWordleQuestion(){
        List<Question<String>> questions = getAllQuestions();
        if (questions.isEmpty()) {
            return null;
        }
        Random random = new Random();
        Question<String> question = questions.get(random.nextInt(questions.size()));
        while (!question.isForWordle()) {
            question = questions.get(random.nextInt(questions.size()));
        }
        return question;
    }
}