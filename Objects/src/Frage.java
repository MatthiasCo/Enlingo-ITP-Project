public class Frage {
    private int id;
    private String frage;
    private String[] antworten;


    public Frage(int id, String frage, String[] antworten) {
        if(antworten == null || frage == null)
            throw new IllegalArgumentException("Antworten und Frage dürfen nicht null sein");

        this.id = id;
        this.frage = frage;
        this.antworten = antworten;
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
        this.frage = frage;
    }

    public String[] getAntworten() {
        return antworten;
    }

    public void setAntworten(String[] antworten) {
        this.antworten = antworten;
    }
}
