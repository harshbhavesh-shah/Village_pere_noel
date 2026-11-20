package personnages;

public class BonshommeDeNeige extends Creature implements EtreFantastique{
    public BonshommeDeNeige(String nom, String sexe, int age) {
        super(nom, sexe, age, 0, 0, 10);
    }
    private Integer vie;

    // faire le système de perte d'HP
    @Override
    public void etreFantastique(){
        augmenterMagie(2);
    }
}