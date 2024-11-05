import java.util.Random;

/**
 *
 */
public class Frage {
    private int id;
    private String frage;
    private String antwort;


    public Frage(String frage, String antwort) {
        if(antwort == null || frage == null)
            throw new IllegalArgumentException("Antworten und Frage dürfen nicht null sein");

        this.frage = frage;
        this.antwort = antwort;
        Random rand = new Random();
        this.id = rand.nextInt(1000000, 9999999);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrage() {
        return frage;
    }

    public void setFrage(String frage) {
        if(frage == null)
            throw new IllegalArgumentException("Frage darf nicht null sein");
        this.frage = frage;
    }

    public String getAntwort() {
        return antwort;
    }

    public void setAntwort(String antwort) {
        this.antwort = antwort;
    }
}
