package batiments;

import personnages.CategoriePersonnage;

public class Atelier extends Batiment{

    public Atelier(int superficie, int capacite) {
        super(superficie, capacite, Proprete.bon);
    }
}
