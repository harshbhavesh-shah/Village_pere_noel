package personnages;

public class Personnage {

    private String nom, sexe;
    private int age, faim = 0, fatigue = 0, magie = 0;
    private CategoriePersonnage categorie;
    private TypePersonnage type;

    public Personnage(String nom, String sexe, int age, CategoriePersonnage categorie, TypePersonnage type) {
        this.nom = nom;
        this.sexe = sexe;
        this.age = age;
        this.categorie = categorie;
        this.type = type;
    }

    public void emettreSon() {
    }


    public void seDeplacer() {
    }


    public void seRestaurer() {
    }


    public void seReposer() {
    }


    public void seMettreEnActivite() {
    }

    public void baisserMagie(int valeur){
        magie -= valeur;
    }

    public void augmenterMagie(int valeur){
        magie += valeur;
    }

    public CategoriePersonnage getCategorie() {
        return categorie;
    }

    public TypePersonnage getType() {
        return type;
    }
}