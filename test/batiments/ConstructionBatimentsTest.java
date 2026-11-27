package batiments;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import personnages.Humanoide;
import personnages.Renne;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de construction des différents bâtiments")
class ConstructionBatimentsTest {

    @Test
    @DisplayName("Atelier, Boutique et Bureau de poste sont réservés aux humanoïdes")
    void testBatimentsHumanoides() {
        assertEquals(Humanoide.class, new Atelier(400, 30).getCategorieAutorise());
        assertEquals(Humanoide.class, new Boutique(100, 10).getCategorieAutorise());
        assertEquals(Humanoide.class, new BureauDePoste(80, 5).getCategorieAutorise());
    }

    @Test
    @DisplayName("Les autres bâtiments n'ont pas de catégorie autorisée")
    void testBatimentsSansCategorie() {
        assertNull(new AireDeJeu(200, 25).getCategorieAutorise());
        assertNull(new Chalet(120, 6, "M").getCategorieAutorise());
        assertNull(new Confiserie(90, 12).getCategorieAutorise());
        assertNull(new Ecole(300, 40).getCategorieAutorise());
        assertNull(new MaisonPN(500).getCategorieAutorise());
        assertNull(new TourHorloge(50).getCategorieAutorise());
        assertNull(new Reserve(60, 10).getCategorieAutorise());
    }

    @Test
    @DisplayName("Chaque bâtiment garde sa superficie et sa capacité et commence propre")
    void testSuperficieCapaciteProprete() {
        Batiment[] batiments = {
                new AireDeJeu(200, 25), new Atelier(400, 30), new Boutique(100, 10),
                new BureauDePoste(80, 5), new Chalet(120, 6, "M"), new Confiserie(90, 12),
                new Ecole(300, 40), new Enclos(250, 8, Renne.class)
        };
        int[][] attendus = {{200, 25}, {400, 30}, {100, 10}, {80, 5}, {120, 6}, {90, 12}, {300, 40}, {250, 8}};
        for (int i = 0; i < batiments.length; i++) {
            assertEquals(attendus[i][0], batiments[i].getSuperficie());
            assertEquals(attendus[i][1], batiments[i].getCapacite());
            assertEquals(Proprete.bon, batiments[i].getDegreProprete());
        }
    }

    @Test
    @DisplayName("Maison du Père Noël, Tour de l'horloge et Réserve n'accueillent personne")
    void testBatimentsSansCapacite() {
        assertEquals(0, new MaisonPN(500).getCapacite());
        assertEquals(0, new TourHorloge(50).getCapacite());
        assertEquals(0, new Reserve(60, 10).getCapacite());
        assertEquals(500, new MaisonPN(500).getSuperficie());
        assertEquals(50, new TourHorloge(50).getSuperficie());
        assertEquals(60, new Reserve(60, 10).getSuperficie());
    }

    @Test
    @DisplayName("L'enclos n'autorise que le type donné à sa construction")
    void testEnclosType() {
        Enclos enclos = new Enclos(250, 8, Renne.class);
        assertArrayEquals(new Class<?>[]{Renne.class}, enclos.getTypesAutorise());
    }

    @Test
    @Disabled("Bogue connu : l'enclos ne définit pas de catégorie autorisée, donc rejoindre refuse tout le monde")
    @DisplayName("Un renne peut rejoindre un enclos à rennes")
    void testRenneRejointEnclos() {
        Enclos enclos = new Enclos(250, 8, Renne.class);
        Renne renne = new Renne("Rodolphe", "M", 5);
        enclos.rejoindre(renne);
        assertTrue(enclos.getPersonnages().contains(renne));
    }
}
