package batiments;

import personnages.Humanoide;

public class Boutique extends Batiment{
    public Boutique(int superficie, int capacite) {
        super(superficie, capacite, Proprete.bon);
        setCategorieAutorise(Humanoide.class);
    }
}
