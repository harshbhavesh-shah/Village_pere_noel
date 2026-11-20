package personnages;

public abstract class Humanoide extends Personnage{
    private boolean auTravail;
    public Humanoide(String nom, String sexe, int age, int faim, int fatigue, int magie) {
        super(nom, sexe, age, faim, fatigue, magie);
        this.auTravail = false;
    }
    public boolean getTravail() {
        return auTravail;
    }
    public void setTravail(boolean auTravail) {
        this.auTravail = auTravail;
    }
    public void percevoirFarce(){
        if(!getTravail){
            augmenterMagieMagie(2);  
        }
    }
}