package personnages;

import java.util.Random;

public class Lutin extends Humanoide implements EtreFantastique, SeDivertir, Travailler{
    public Lutin(String nom, String sexe, int age) {
        super(nom, sexe, age, 0, 0, 10);
    }

    @Override
    public void seDivertir(){
        augmenterMagie(2);
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
    // random farce a faire
}