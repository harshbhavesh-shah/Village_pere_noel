package batiments;

import personnages.CategoriePersonnage;
import personnages.TypePersonnage;

public class Enclos extends Batiment{

    private TypePersonnage type;

    public Enclos(int superficie, int capacite, TypePersonnage type) {
        super(superficie, capacite, Proprete.bon);
        this.type = type;
    }
}
