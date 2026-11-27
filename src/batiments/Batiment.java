package batiments;

import personnages.Personnage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Batiment {
    protected int superficie, capacite;
    protected Proprete degreProprete;
    protected List<Personnage> personnages = new ArrayList<>();
    protected Class<? extends Personnage> categorieAutorise = null;
    protected Class<? extends Personnage>[] typesAutorise;

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


    public Class<? extends Personnage>[] getTypesAutorise() {
        return typesAutorise;
    }

    public void setTypesAutorise(Class<? extends Personnage>[] typesAutorise) {
        this.typesAutorise = typesAutorise;
    }

    public Class<? extends Personnage> getCategorieAutorise() {
        return categorieAutorise;
    }

    public void setCategorieAutorise(Class<? extends Personnage> categorieAutorise) {
        this.categorieAutorise = categorieAutorise;
    }


    public void rejoindre(Personnage p){
        if (categorieAutorise != null && p.getClass() == categorieAutorise){
            if (typesAutorise.length != 0 && Arrays.asList(typesAutorise).contains(p.getClass())){
                personnages.add(p);
            }
        }
    }

    public abstract void executerActivites() ;
}
