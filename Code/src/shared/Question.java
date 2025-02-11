package shared;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Question<T> {
    private int id;
    private String text;
    private T[] answers;
    private boolean multiAnswer;

    public Question(int id, String text, T[] answers) {
        this.id = id;
        this.text = text;
        this.answers = answers;
        this.multiAnswer = true;
    }

    public Question(int id, String text, T answer, Class<T> type) {
        this.id = id;
        this.text = text;
        // Create a new array of type T
        this.answers = (T[]) Array.newInstance(type, 1);
        this.answers[0] = answer;
        this.multiAnswer = false;
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

    @Override
    public String toString() {
        return id + "," + text + "," + Arrays.toString(answers);
    }

}