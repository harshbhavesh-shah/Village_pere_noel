package batiments;

import personnages.Personnage;
import personnages.SeDivertir;

public class Enclos extends Batiment{

     public Enclos(int superficie, int capacite, Class<? extends Personnage> type) {
        super(superficie, capacite, Proprete.bon);
        this.setTypesAutorise(new Class[]{type});
    }

    @Override
    public void executerActivites() {
        for (Personnage personnage : getPersonnages()) {
            personnage.seReposer();
        }
    }
}
