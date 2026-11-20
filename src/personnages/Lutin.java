package personnages;

public class Lutin extends Personnage {
    public Lutin(String nom, String sexe, int age){
        super(nom,sexe,age, CategoriePersonnage.humanoide, TypePersonnage.Lutin);
    }
}
