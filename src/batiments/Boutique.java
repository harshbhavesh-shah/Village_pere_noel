package batiments;

import personnages.Humanoide;
import personnages.Personnage;
import personnages.SeDivertir;

public class Boutique extends Batiment{
    public Boutique(int superficie, int capacite) {
        super(superficie, capacite, Proprete.bon);
        setCategorieAutorise(Humanoide.class);
    }

    @Override
    public void executerActivites() {
        for (Personnage personnage : getPersonnages()) {
            if (personnage instanceof SeDivertir persoDivertissable) {
                persoDivertissable.seDivertir();
            }
        }
    }
}
