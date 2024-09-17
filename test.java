public class test {
    public static void main(String[] args) {
        Antwort antwort = new Antwort(1, "Gut");
        Frage frage = new Frage(1, "Wie geht es dir?", new Antwort[]{antwort});
        
    }
}
