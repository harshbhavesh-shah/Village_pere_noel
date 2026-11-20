package personnages;

public class Enfant extends Humanoide implements SeDivertir{
    public Enfant(String nom, String sexe, int age) {
        super(nom, sexe, age, 0, 0, 10);
    }
    @Override
    protected void modifierMagie(int delta) {
    super.augmenterMagie(delta * 2);
    }
    @Override
    public void seDivertir(){
        augmenterMagieMagie(2);
    }
    @Override
    public void seDeplacer(){
        seDivertir();
    }
}