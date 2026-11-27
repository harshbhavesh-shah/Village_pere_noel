package batiments;

import personnages.*;

public class Atelier extends Batiment{

    public Atelier(int superficie, int capacite) {
        super(superficie, capacite, Proprete.bon);
        setCategorieAutorise(Humanoide.class);
    }

    @Override
    public void executerActivites() {
        for (Personnage personnage : getPersonnages()) {
            if (personnage instanceof Travailler persoTravailleur) {
                if (persoTravailleur instanceof Lutin || persoTravailleur instanceof Fee) {
                    persoTravailleur.travailler();
                }
            }
        }
    }
}
