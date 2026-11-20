public abstract class Creature extends Personnage {
    public Creature(String nom, String sexe, int age, int faim, int fatigue, int magie) {
        super(nom, sexe, age, faim, fatigue, magie);
    }
    public void percevoirFarce(){
        baisserMagieMagie(1);
    }
}