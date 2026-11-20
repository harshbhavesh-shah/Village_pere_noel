package personnages;

public class Fee extends Personnage {
    public Fee(String nom, String sexe, int age){
        super(nom,sexe,age, CategoriePersonnage.humanoide, TypePersonnage.Fee);
    }
}
