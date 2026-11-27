package batiments;

import personnages.Personnage;

public class Enclos extends Batiment{

     public Enclos(int superficie, int capacite, Class<? extends Personnage> type) {
        super(superficie, capacite, Proprete.bon);
        this.setTypesAutorise(new Class[]{type});
    }
}
