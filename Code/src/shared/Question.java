package shared;

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

    public Question(int id, String text, T answer) {
        this.id = id;
        this.text = text;
        this.answers = (T[]) new Object[]{answer};
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
}