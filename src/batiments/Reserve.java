package batiments;

import nourritures.Nourritures;

import java.util.ArrayList;

public class Reserve extends Batiment{
    ArrayList<Nourritures> stockDeNourriture;
    int capaciteStockage;

    public Reserve(int superficie, int capaciteStockage) {
        super(superficie, 0, Proprete.bon);
        stockDeNourriture = new ArrayList<>();
        this.capaciteStockage = capaciteStockage;
    }

    public void addNourriture(Nourritures nourriture){
        if (stockDeNourriture.size() < capaciteStockage) stockDeNourriture.add(nourriture);
    }

    public void removeNourriture(Nourritures nourriture){
        stockDeNourriture.remove(nourriture);
    }

    public ArrayList<Nourritures> getStockDeNourriture(){
        return stockDeNourriture;
    }


}
