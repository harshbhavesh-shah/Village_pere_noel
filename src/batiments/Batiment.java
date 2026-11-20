package batiments;

import personnages.CategoriePersonnage;
import personnages.Personnage;
import personnages.TypePersonnage;

import java.util.Arrays;
import java.util.List;

public class Batiment {
    private int superficie, capacite;
    private Proprete degreProprete;
    private List<Personnage> personnages;
    private CategoriePersonnage categorieAutorise = null;
    private TypePersonnage[] typesAutorise;

    public Batiment(int superficie, int capacite, Proprete degreProprete) {
        this.superficie = superficie;
        this.capacite = capacite;
        this.degreProprete = degreProprete;
    }

    public Proprete getDegreProprete() {
        return degreProprete;
    }

    public void setDegreProprete(Proprete degreProprete) {
        this.degreProprete = degreProprete;
    }

    public int getSuperficie() {
        return superficie;
    }

    public int getCapacite() {
        return capacite;
    }

    public List<Personnage> getPersonnages() {
        return personnages;
    }


    public TypePersonnage[] getTypesAutorise() {
        return typesAutorise;
    }

    public void setTypesAutorise(TypePersonnage[] typesAutorise) {
        this.typesAutorise = typesAutorise;
    }

    public CategoriePersonnage getCategorieAutorise() {
        return categorieAutorise;
    }

    public void setCategorieAutorise(CategoriePersonnage categorieAutorise) {
        this.categorieAutorise = categorieAutorise;
    }


    public void rejoindre(Personnage p){
        if (categorieAutorise != null && categorieAutorise == p.getCategorie()){
            if (typesAutorise.length != 0 && Arrays.asList(typesAutorise).contains(p.getType())){
                personnages.add(p);
            }
        }
    }
}
