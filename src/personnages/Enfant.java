package personnages;

public class Enfant extends Humanoide implements SeDivertir{
    public Enfant(String nom, String sexe, int age) {
        super(nom, sexe, age, 0, 0, 10);
    }

    @Override
    public void augmenterMagie(int delta) {
        super.augmenterMagie(delta * 2);
    }

    @Override
    public void seDivertir(){
        augmenterMagie(2);
    }

    @Override
    public void seDeplacer(){
        seDivertir();
    }
}