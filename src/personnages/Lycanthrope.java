package personnages;

public class Lycanthrope extends Personnage {
    public Lycanthrope(String nom, String sexe, int age){
        super(nom,sexe,age, CategoriePersonnage.creature, TypePersonnage.Lycanthrope);
    }
}
