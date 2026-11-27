package batiments;

import personnages.EtreFantastique;
import personnages.Personnage;
import personnages.Travailler;

public class Ecole extends Batiment{
    public Ecole(int superficie, int capacite) {
        super(superficie, capacite, Proprete.bon);
    }

    @Override
    public void executerActivites() {
        for (Personnage personnage : getPersonnages()) {
            if (personnage instanceof EtreFantastique persoFantastique) {
                persoFantastique.etreFantastique();
            }
        }
    }
}
