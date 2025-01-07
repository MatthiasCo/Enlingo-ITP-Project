package shared;

public class Question {
    private int id;
    private String text;
    private int answer; //make generic

    public Question(int id, String text, int answer) {
        this.id = id;
        this.text = text;
        this.answer = answer;
    }


}
