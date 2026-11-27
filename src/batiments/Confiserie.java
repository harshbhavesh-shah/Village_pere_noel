package batiments;

import personnages.Personnage;

public class Confiserie extends Batiment{

    public Confiserie(int superficie, int capacite) {
        super(superficie, capacite, Proprete.bon);
    }

    @Override
    public void executerActivites() {
        for (Personnage personnage : getPersonnages()){
            personnage.seRestaurer();
        }
    }
}
