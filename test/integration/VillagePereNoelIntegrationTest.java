package integration;

import batiments.AireDeJeu;
import batiments.Atelier;
import batiments.Batiment;
import batiments.BureauDePoste;
import batiments.Ecole;
import batiments.Reserve;
import nourritures.Nourritures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import personnages.Elfe;
import personnages.Enfant;
import personnages.Fee;
import personnages.Humanoide;
import personnages.Lutin;
import personnages.Personnage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static support.Reflexion.magie;

@DisplayName("Tests d'intégration du Village du Père Noël")
class VillagePereNoelIntegrationTest {

    private static void accueillir(Batiment batiment, Personnage... personnages) {
        batiment.getPersonnages().addAll(List.of(personnages));
    }

    @Test
    @DisplayName("Une journée de travail : les travailleurs ne sont plus sensibles aux farces")
    void scenarioJourneeDeTravail() {
        Lutin fizzy = new Lutin("Fizzy", "M", 80);
        Fee clochette = new Fee("Clochette", "F", 120);
        Elfe bernard = new Elfe("Bernard", "M", 350);

        Atelier atelier = new Atelier(400, 30);
        BureauDePoste poste = new BureauDePoste(80, 5);
        accueillir(atelier, fizzy, clochette);
        accueillir(poste, bernard);

        atelier.executerActivites();
        poste.executerActivites();

        for (Humanoide travailleur : List.<Humanoide>of(fizzy, clochette, bernard)) {
            assertTrue(travailleur.estAuTravail());
            travailleur.percevoirFarce();
            assertEquals(12, magie(travailleur));
        }
    }

    @Test
    @DisplayName("Une journée de loisirs : les enfants gagnent deux fois plus de magie")
    void scenarioJourneeDeLoisirs() {
        Enfant lea = new Enfant("Léa", "F", 8);
        Lutin fizzy = new Lutin("Fizzy", "M", 80);
        Fee clochette = new Fee("Clochette", "F", 120);

        AireDeJeu aire = new AireDeJeu(200, 25);
        Ecole ecole = new Ecole(300, 40);
        accueillir(aire, lea, fizzy);
        accueillir(ecole, clochette, fizzy);

        aire.executerActivites();
        ecole.executerActivites();
        lea.percevoirFarce();

        assertEquals(18, magie(lea));
        assertEquals(16, magie(fizzy));
        assertEquals(12, magie(clochette));
    }

    @Test
    @DisplayName("Approvisionnement : la réserve se remplit puis se vide au fil des repas")
    void scenarioApprovisionnement() {
        Reserve reserve = new Reserve(60, 4);
        for (Nourritures nourriture : Nourritures.values()) {
            reserve.addNourriture(nourriture);
        }
        assertEquals(4, reserve.getStockDeNourriture().size());

        int nutritionTotale = reserve.getStockDeNourriture().stream().mapToInt(Nourritures::getNutrition).sum();
        assertEquals(12, nutritionTotale);

        for (Nourritures nourriture : Nourritures.values()) {
            reserve.removeNourriture(nourriture);
        }
        assertTrue(reserve.getStockDeNourriture().isEmpty());
    }
}
