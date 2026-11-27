package batiments;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import personnages.Elfe;
import personnages.Enfant;
import personnages.Esprit;
import personnages.Fee;
import personnages.Lutin;
import personnages.Personnage;
import personnages.Renne;

import static org.junit.jupiter.api.Assertions.*;
import static support.Reflexion.magie;

@DisplayName("Tests de la méthode executerActivites de chaque bâtiment")
class ActivitesBatimentsTest {

    private static <B extends Batiment> B peupler(B batiment, Personnage... personnages) {
        for (Personnage personnage : personnages) {
            batiment.getPersonnages().add(personnage);
        }
        return batiment;
    }

    @Test
    @DisplayName("Aire de jeu : les personnages se divertissent et les êtres fantastiques le sont")
    void testAireDeJeu() {
        Enfant enfant = new Enfant("Léa", "F", 8);
        Lutin lutin = new Lutin("Fizzy", "M", 80);
        Esprit esprit = new Esprit("Noël", "M", 500);
        Elfe elfe = new Elfe("Bernard", "M", 350);
        Renne renne = new Renne("Rodolphe", "M", 5);

        peupler(new AireDeJeu(200, 25), enfant, lutin, esprit, elfe, renne).executerActivites();

        assertEquals(14, magie(enfant));
        assertEquals(14, magie(lutin));
        assertEquals(12, magie(esprit));
        assertEquals(10, magie(elfe));
        assertEquals(10, magie(renne));
    }

    @Test
    @DisplayName("Atelier : seuls les lutins et les fées travaillent")
    void testAtelier() {
        Lutin lutin = new Lutin("Fizzy", "M", 80);
        Fee fee = new Fee("Clochette", "F", 120);
        Elfe elfe = new Elfe("Bernard", "M", 350);
        Enfant enfant = new Enfant("Léa", "F", 8);

        peupler(new Atelier(400, 30), lutin, fee, elfe, enfant).executerActivites();

        assertTrue(lutin.estAuTravail());
        assertTrue(fee.estAuTravail());
        assertFalse(elfe.estAuTravail());
        assertFalse(enfant.estAuTravail());
        assertEquals(12, magie(lutin));
        assertEquals(12, magie(fee));
        assertEquals(10, magie(elfe));
        assertEquals(10, magie(enfant));
    }

    @Test
    @DisplayName("Boutique : les personnages qui savent se divertir se divertissent")
    void testBoutique() {
        Lutin lutin = new Lutin("Fizzy", "M", 80);
        Enfant enfant = new Enfant("Léa", "F", 8);
        Elfe elfe = new Elfe("Bernard", "M", 350);

        peupler(new Boutique(100, 10), lutin, enfant, elfe).executerActivites();

        assertEquals(12, magie(lutin));
        assertEquals(14, magie(enfant));
        assertEquals(10, magie(elfe));
    }

    @Test
    @DisplayName("Bureau de poste : seuls les elfes travaillent")
    void testBureauDePoste() {
        Elfe elfe = new Elfe("Bernard", "M", 350);
        Lutin lutin = new Lutin("Fizzy", "M", 80);
        Enfant enfant = new Enfant("Léa", "F", 8);

        peupler(new BureauDePoste(80, 5), elfe, lutin, enfant).executerActivites();

        assertTrue(elfe.estAuTravail());
        assertFalse(lutin.estAuTravail());
        assertEquals(12, magie(elfe));
        assertEquals(10, magie(lutin));
        assertEquals(10, magie(enfant));
    }

    @Test
    @DisplayName("École : les êtres fantastiques exercent leur magie")
    void testEcole() {
        Fee fee = new Fee("Clochette", "F", 120);
        Esprit esprit = new Esprit("Noël", "M", 500);
        Enfant enfant = new Enfant("Léa", "F", 8);

        peupler(new Ecole(300, 40), fee, esprit, enfant).executerActivites();

        assertEquals(12, magie(fee));
        assertEquals(12, magie(esprit));
        assertEquals(10, magie(enfant));
    }

    @Test
    @DisplayName("Chalet, Confiserie et Enclos : les activités s'exécutent sans changer la magie")
    void testActivitesSansEffetSurLaMagie() {
        Elfe elfe = new Elfe("Bernard", "M", 350);
        Renne renne = new Renne("Rodolphe", "M", 5);
        Enfant enfant = new Enfant("Léa", "F", 8);

        assertDoesNotThrow(() -> {
            peupler(new Chalet(120, 6, "M"), elfe, renne).executerActivites();
            peupler(new Confiserie(90, 12), elfe, enfant).executerActivites();
            peupler(new Enclos(250, 8, Renne.class), renne).executerActivites();
        });

        assertEquals(10, magie(elfe));
        assertEquals(10, magie(renne));
        assertEquals(10, magie(enfant));
    }

    @Test
    @DisplayName("Maison du Père Noël, Tour de l'horloge et Réserve n'ont pas d'activité")
    void testBatimentsSansActivite() {
        assertDoesNotThrow(() -> {
            new MaisonPN(500).executerActivites();
            new TourHorloge(50).executerActivites();
            new Reserve(60, 10).executerActivites();
        });
    }
}
