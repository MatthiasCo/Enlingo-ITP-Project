public class Frage {
    private int id;
    private String frage;
    private Antwort[] antworten;


    public Frage(int id, String frage, Antwort[] antworten) {
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
        if(frage == null)
            throw new IllegalArgumentException("Frage darf nicht null sein");
        this.frage = frage;
    }

    public Antwort[] getAntworten() {
        return antworten;
    }

    public void setAntworten(Antwort[] antworten) {
        this.antworten = antworten;
    }

    public void addAntwort(Antwort antwort) {
        if(antwort == null)
            throw new IllegalArgumentException("Antwort darf nicht null sein");
        Antwort[] newAntworten = new Antwort[antworten.length + 1];
        System.arraycopy(antworten, 0, newAntworten, 0, antworten.length);
        newAntworten[antworten.length] = antwort;
        antworten = newAntworten;
    }

    public void removeAntwort(Antwort antwort) {
        if(antwort == null)
            throw new IllegalArgumentException("Antwort darf nicht null sein");
        Antwort[] newAntworten = new Antwort[antworten.length - 1];
        int index = 0;
        for (Antwort value : antworten) {
            if (value.equals(antwort)) {
                continue;
            }
            newAntworten[index] = value;
            index++;
        }
        antworten = newAntworten;
    }
}
