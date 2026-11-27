package batiments;

import personnages.Humanoide;

public class Atelier extends Batiment{

    public Atelier(int superficie, int capacite) {
        super(superficie, capacite, Proprete.bon);
        setCategorieAutorise(Humanoide.class);
    }
}
