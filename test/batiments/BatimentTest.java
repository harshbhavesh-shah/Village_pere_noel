package batiments;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import personnages.Elfe;
import personnages.Humanoide;
import personnages.Lutin;
import personnages.Personnage;
import personnages.Renne;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests unitaires pour la classe Batiment")
class BatimentTest {

    private Batiment batiment;
    private Elfe elfe;
    private Lutin lutin;
    private Renne renne;

    @SafeVarargs
    private static Class<? extends Personnage>[] types(Class<? extends Personnage>... types) {
        return types;
    }

    private static Batiment nouveauBatiment() {
        return new Batiment(150, 20, Proprete.bon) {
            @Override
            public void executerActivites() {
            }
        };
    }

    @BeforeEach
    void setUp() {
        batiment = nouveauBatiment();
        elfe = new Elfe("Buddy", "M", 30);
        lutin = new Lutin("Barnabé", "M", 25);
        renne = new Renne("Comète", "F", 4);
    }

    @Nested
    @DisplayName("Constructeur et accesseurs")
    class ConstructeurEtAccesseurs {

        @Test
        @DisplayName("Le constructeur initialise la superficie, la capacité et la propreté")
        void testConstructeur() {
            assertEquals(150, batiment.getSuperficie());
            assertEquals(20, batiment.getCapacite());
            assertEquals(Proprete.bon, batiment.getDegreProprete());
        }

        @Test
        @DisplayName("Par défaut, aucune catégorie ni aucun type n'est autorisé")
        void testRestrictionsParDefaut() {
            assertNull(batiment.getCategorieAutorise());
            assertNull(batiment.getTypesAutorise());
        }

        @Test
        @DisplayName("setDegreProprete modifie le degré de propreté")
        void testSetDegreProprete() {
            batiment.setDegreProprete(Proprete.mauvais);
            assertEquals(Proprete.mauvais, batiment.getDegreProprete());
        }

        @Test
        @DisplayName("setCategorieAutorise modifie la catégorie autorisée")
        void testSetCategorieAutorise() {
            batiment.setCategorieAutorise(Humanoide.class);
            assertEquals(Humanoide.class, batiment.getCategorieAutorise());
        }

        @Test
        @DisplayName("setTypesAutorise modifie les types autorisés")
        void testSetTypesAutorise() {
            Class<? extends Personnage>[] autorises = types(Elfe.class, Lutin.class);
            batiment.setTypesAutorise(autorises);
            assertArrayEquals(autorises, batiment.getTypesAutorise());
        }

        @Test
        @DisplayName("Un nouveau bâtiment a une liste de personnages vide")
        void testListeVideParDefaut() {
            assertNotNull(batiment.getPersonnages());
            assertTrue(batiment.getPersonnages().isEmpty());
        }
    }

    @Nested
    @DisplayName("Méthode rejoindre(Personnage)")
    class MethodeRejoindre {

        @Test
        @DisplayName("Sans catégorie autorisée, personne ne peut rejoindre")
        void testSansCategorie() {
            batiment.setTypesAutorise(types(Elfe.class));
            batiment.rejoindre(elfe);
            assertTrue(batiment.getPersonnages().isEmpty());
        }

        @Test
        @DisplayName("Un personnage dont la classe et le type sont autorisés rejoint le bâtiment")
        void testRejoindreAutorise() {
            batiment.setCategorieAutorise(Elfe.class);
            batiment.setTypesAutorise(types(Elfe.class));
            batiment.rejoindre(elfe);
            assertEquals(1, batiment.getPersonnages().size());
            assertTrue(batiment.getPersonnages().contains(elfe));
        }

        @Test
        @DisplayName("Un personnage d'une autre catégorie est refusé")
        void testCategorieDifferente() {
            batiment.setCategorieAutorise(Elfe.class);
            batiment.setTypesAutorise(types(Elfe.class, Renne.class));
            batiment.rejoindre(renne);
            assertTrue(batiment.getPersonnages().isEmpty());
        }

        @Test
        @DisplayName("Un personnage de la bonne catégorie mais d'un type non autorisé est refusé")
        void testTypeNonAutorise() {
            batiment.setCategorieAutorise(Lutin.class);
            batiment.setTypesAutorise(types(Elfe.class));
            batiment.rejoindre(lutin);
            assertTrue(batiment.getPersonnages().isEmpty());
        }

        @Test
        @DisplayName("Avec une liste de types vide, personne ne peut rejoindre")
        void testTypesVides() {
            batiment.setCategorieAutorise(Elfe.class);
            batiment.setTypesAutorise(types());
            batiment.rejoindre(elfe);
            assertTrue(batiment.getPersonnages().isEmpty());
        }

        @Test
        @DisplayName("Un elfe rejoint un bâtiment réservé aux humanoïdes")
        void testCategorieParente() {
            batiment.setCategorieAutorise(Humanoide.class);
            batiment.setTypesAutorise(types(Elfe.class));
            batiment.rejoindre(elfe);
            assertTrue(batiment.getPersonnages().contains(elfe));
        }
    }
}
