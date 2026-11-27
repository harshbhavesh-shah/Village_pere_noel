package batiments;

import personnages.Humanoide;

public class BureauDePoste extends Batiment{
    public BureauDePoste(int superficie, int capacite) {
        super(superficie, capacite, Proprete.bon);
        setCategorieAutorise(Humanoide.class);
    }
}
