package personnages;

public class Esprit extends Personnage {
    public Esprit(String nom, String sexe, int age){
        super(nom,sexe,age, CategoriePersonnage.creature, TypePersonnage.Esprit);
    }
}
