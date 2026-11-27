package batiments;

import personnages.Humanoide;
import personnages.Personnage;

import java.util.Arrays;

public class Chalet extends Batiment{
    String sexe;

    public Chalet(int superficie, int capacite, String sexe) {
        super(superficie, capacite, Proprete.bon);
        this.sexe = sexe;
    }

    @Override
    public void rejoindre(Personnage p){
        if (categorieAutorise != null && p.getClass() == categorieAutorise){
            if (typesAutorise.length != 0 && Arrays.asList(typesAutorise).contains(p.getClass())){
                if (this.sexe.equals(p.getSexe())) {
                    personnages.add(p);
                }
            }
        }
    }

    @Override
    public void executerActivites() {
        for (Personnage personnage : getPersonnages()) {
            if (personnage instanceof Humanoide) {
                personnage.seReposer();
            }
        }
    }
}
