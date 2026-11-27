package batiments;

import personnages.*;

public class BureauDePoste extends Batiment{
    public BureauDePoste(int superficie, int capacite) {
        super(superficie, capacite, Proprete.bon);
        setCategorieAutorise(Humanoide.class);
    }

    @Override
    public void executerActivites() {
        for (Personnage personnage : getPersonnages()) {
            if (personnage instanceof Travailler persoTravailleur) {
                if (persoTravailleur instanceof Elfe) {
                    persoTravailleur.travailler();
                }
            }
        }
    }
}
