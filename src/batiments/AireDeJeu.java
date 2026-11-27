package batiments;

import personnages.EtreFantastique;
import personnages.Personnage;
import personnages.SeDivertir;

public class AireDeJeu extends Batiment{
    public AireDeJeu(int superficie, int capacite) {
        super(superficie, capacite, Proprete.bon);
    }

    @Override
    public void executerActivites() {
        for (Personnage personnage : getPersonnages()){
            if (personnage instanceof SeDivertir persoDivertissable){
                persoDivertissable.seDivertir();
            }
            if (personnage instanceof EtreFantastique persoFantastique){
                persoFantastique.etreFantastique();
            }
        }
    }
}
