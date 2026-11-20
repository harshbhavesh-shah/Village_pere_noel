package personnages;

public class Fee extends Humanoide implements EtreFantastique, Travailler{

    public Fee(String nom, String sexe, int age) {
        super(nom, sexe, age, 0, 0, 10);
    }

    @Override
    public void travailler() {
        setTravail(true);
        augmenterMagie(2);
    }

    @Override
    public void etreFantastique(){
        augmenterMagie(2);
    }
}
