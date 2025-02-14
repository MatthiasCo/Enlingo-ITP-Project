package shared;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Question<T> {
    private int id;
    private String text;
    private T[] answers;
    private boolean multiAnswer;
    private boolean forWordle;

    public Question(int id, String text, T[] answers) {
        this.id = id;
        this.text = text;
        this.answers = answers;
        this.multiAnswer = true;
        forWordle = false;
    }

    public Question(int id, String text, T answer, Class<T> type) {
        this.id = id;
        this.text = text;
        this.answers = (T[]) Array.newInstance(type, 1);
        this.answers[0] = answer;
        this.multiAnswer = false;
        //is type is string and its 5 characters long
        this.forWordle = type == Classes.STRING || answer.toString().length() == 5;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public T[] getAnswers() {
        return answers;
    }

    public boolean isMultiAnswer() {
        return multiAnswer;
    }

    public boolean isForWordle() {
        return forWordle;
    }

    @Override
    public String toString() {
        return "Question ID: " + id + "\n" +
                "Text: " + text + "\n" +
                "Answers: " + Arrays.toString(answers) + "\n" +
                "MultiAnswer: " + multiAnswer + "\n" +
                "For use in Wordle: " + forWordle;
    }

}