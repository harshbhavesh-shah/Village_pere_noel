package personnages;

public class Esprit extends Creature implements EtreFantastique{
    public Esprit(String nom, String sexe, int age) {
        super(nom, sexe, age, 0, 0, 10);
    }

    @Override
    public void etreFantastique(){
        augmenterMagie(2);
    }
}