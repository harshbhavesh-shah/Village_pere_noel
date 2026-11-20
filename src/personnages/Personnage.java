package personnages;

public abstract class Personnage {

    private String nom, sexe;
    private int age, faim = 0, fatigue = 0, magie = 0;

    public Personnage(String nom, String sexe, int age, int  faim,int fatigue,int magie) {
        this.nom = nom;
        this.sexe = sexe;
        this.age = age;
        this.faim = faim;
        this.fatigue = fatigue;
        this.magie = magie;
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