package personnages;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static support.Reflexion.lire;
import static support.Reflexion.magie;

@DisplayName("Tests unitaires pour la classe Personnage")
class PersonnageTest {

    private Personnage personnage;

    @BeforeEach
    void setUp() {
        personnage = new Renne("Tornade", "F", 7);
    }

    static Stream<Arguments> tousLesPersonnages() {
        return Stream.of(
                Arguments.of("Elfe", new Elfe("Bernard", "M", 350)),
                Arguments.of("Lutin", new Lutin("Fizzy", "M", 80)),
                Arguments.of("Fee", new Fee("Clochette", "F", 120)),
                Arguments.of("Enfant", new Enfant("Léa", "F", 8)),
                Arguments.of("Renne", new Renne("Rodolphe", "M", 5)),
                Arguments.of("Esprit", new Esprit("Noël", "M", 500)),
                Arguments.of("Lycanthrope", new Lycanthrope("Garou", "M", 40)),
                Arguments.of("BonshommeDeNeige", new BonshommeDeNeige("Olaf", "M", 3))
        );
    }

    @Test
    @DisplayName("Le constructeur enregistre le nom, le sexe et l'âge")
    void testConstructeur() {
        assertEquals("Tornade", lire(personnage, Personnage.class, "nom"));
        assertEquals("F", lire(personnage, Personnage.class, "sexe"));
        assertEquals(7, lire(personnage, Personnage.class, "age"));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("tousLesPersonnages")
    @DisplayName("Chaque personnage commence avec faim 0, fatigue 0 et magie 10")
    void testValeursInitiales(String type, Personnage p) {
        assertEquals(0, lire(p, Personnage.class, "faim"));
        assertEquals(0, lire(p, Personnage.class, "fatigue"));
        assertEquals(10, magie(p));
    }

    @Test
    @DisplayName("getSexe renvoie le sexe du personnage")
    void testGetSexe() {
        assertEquals("F", personnage.getSexe());
        assertEquals("M", new Elfe("Bernard", "M", 350).getSexe());
    }

    @Test
    @DisplayName("augmenterMagie ajoute la valeur à la magie")
    void testAugmenterMagie() {
        personnage.augmenterMagie(5);
        assertEquals(15, magie(personnage));
    }

    @Test
    @DisplayName("baisserMagie retire la valeur de la magie")
    void testBaisserMagie() {
        personnage.baisserMagie(4);
        assertEquals(6, magie(personnage));
    }

    @Test
    @DisplayName("La magie peut devenir négative")
    void testMagieNegative() {
        personnage.baisserMagie(15);
        assertEquals(-5, magie(personnage));
    }

    @Test
    @DisplayName("Les actions de base ne modifient pas la magie")
    void testActionsDeBase() {
        assertDoesNotThrow(() -> {
            personnage.emettreSon();
            personnage.seDeplacer();
            personnage.seRestaurer();
            personnage.seReposer();
            personnage.seMettreEnActivite();
        });
        assertEquals(10, magie(personnage));
    }
}
