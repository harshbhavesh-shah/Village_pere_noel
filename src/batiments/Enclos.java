package batiments;

import personnages.Personnage;

public class Enclos extends Batiment{

    private Class<? extends Personnage> type;

    public Enclos(int superficie, int capacite, Class<? extends Personnage> type) {
        super(superficie, capacite, Proprete.bon);
        this.type = type;
    }
}
