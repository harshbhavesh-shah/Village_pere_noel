package personnages;

public class Lycanthrope extends Creature implements EtreFantastique{
    public Lycanthrope(String nom, String sexe, int age) {
        super(nom, sexe, age, 0, 0, 10);
    }
    @Override
    public void etreFantastique(){
        augmenterMagie(2);
    }
}