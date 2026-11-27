package batiments;

import personnages.Humanoide;
import personnages.Personnage;
import personnages.Travailler;

public class Chalet extends Batiment{

    public Chalet(int superficie, int capacite) {
        super(superficie, capacite, Proprete.bon);
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
