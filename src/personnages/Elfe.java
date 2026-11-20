package personnages;

public class Elfe extends Humanoide implements Travailler{
    public Elfe(String nom, String sexe, int age) {
        super(nom, sexe, age, 0, 0, 10);
    }

    @Override
    public void travailler() {
        setTravail(true);
        augmenterMagie(2);
    }
}