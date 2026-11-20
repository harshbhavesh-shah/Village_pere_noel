package personnages;

public class Enfant extends Personnage {
    public Enfant(String nom, String sexe, int age){
        super(nom,sexe,age, CategoriePersonnage.humanoide, TypePersonnage.Enfant);
    }
}
