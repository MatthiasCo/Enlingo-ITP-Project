public class ExamplesMain {
    public static void main(String[] args) {
        //englisch vokabel trainer
        Frage frage = new Frage("Was ist ein Hund auf englisch?", "Dog");
        Frage frage2 = new Frage("Was ist ein Katze auf englisch?", "Cat");
        Frage frage3 = new Frage("Was ist ein Vogel auf englisch?", "Bird");
        Frage frage7 = new Frage("Was ist ein Kuh auf englisch?", "Cow");
        //nicht nur tiere
        Frage frage9 = new Frage("Was ist ein Apfel auf englisch?", "Apple");
        Frage frage10 = new Frage("Was ist ein Banane auf englisch?", "Banana");
        Frage frage11 = new Frage("Was ist ein Orange auf englisch?", "Orange");
        //noch was anderes
        Frage frage12 = new Frage("Was ist ein Auto auf englisch?", "Car");
        Frage frage13 = new Frage("Was ist ein Haus auf englisch?", "House");

        Frage[] fragen = {frage, frage2, frage3, frage7, frage9, frage10, frage11, frage12, frage13};

        /*
        for(frage : fragen) {
            Datenbank.addFrage(frage);
        }
        */

    }
}
