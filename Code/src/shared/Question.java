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
        this.forWordle = type == Classes.STRING && answer.toString().length() == 5;
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

    public void setText(String text) {
        this.text = text;
    }

    public void setAnswers(T[] answers) {
        if(answers == null) {
            return;
        }
        this.multiAnswer = answers.length != 1;
        if(answers.length == 1) {
            this.forWordle = answers[0].toString().length() == 5;
        }
        this.answers = answers;
    }

    public boolean isMultiAnswer() {
        return multiAnswer;
    }

    public boolean isForWordle() {
        return forWordle;
    }

    public String csvConvert() {
        return id + "," + text + "," + String.join(";", Arrays.stream(answers).map(Object::toString).toArray(String[]::new));
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