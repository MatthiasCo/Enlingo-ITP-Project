public class Antwort {
    private int id;
    private String antwort;

    public Antwort(int id, String antwort) {
        if(antwort == null)
            throw new IllegalArgumentException("Antwort darf nicht null sein");
        this.id = id;
        this.antwort = antwort;
    }
}
