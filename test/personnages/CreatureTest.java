package personnages;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static support.Reflexion.magie;

@DisplayName("Tests unitaires pour les créatures")
class CreatureTest {

    static Stream<Arguments> creaturesFantastiques() {
        return Stream.of(
                Arguments.of("Esprit", new Esprit("Noël", "M", 500)),
                Arguments.of("Lycanthrope", new Lycanthrope("Garou", "M", 40)),
                Arguments.of("BonshommeDeNeige", new BonshommeDeNeige("Olaf", "M", 3))
        );
    }

    @Test
    @DisplayName("Renne, Esprit, Lycanthrope et Bonhomme de neige sont des créatures")
    void testHierarchie() {
        assertInstanceOf(Creature.class, new Renne("Rodolphe", "M", 5));
        assertInstanceOf(Creature.class, new Esprit("Noël", "M", 500));
        assertInstanceOf(Creature.class, new Lycanthrope("Garou", "M", 40));
        assertInstanceOf(Creature.class, new BonshommeDeNeige("Olaf", "M", 3));
    }

    @Test
    @DisplayName("Une créature n'est pas un humanoïde")
    void testPasHumanoide() {
        assertFalse(Humanoide.class.isInstance(new Renne("Rodolphe", "M", 5)));
    }

    @Test
    @DisplayName("Une farce baisse la magie d'une créature de 1")
    void testPercevoirFarce() {
        Renne renne = new Renne("Rodolphe", "M", 5);
        renne.percevoirFarce();
        assertEquals(9, magie(renne));
    }

    @Test
    @DisplayName("Un renne n'est ni fantastique, ni travailleur, ni divertissable")
    void testRenneSansInterface() {
        Renne renne = new Renne("Rodolphe", "M", 5);
        assertFalse(renne instanceof EtreFantastique);
        assertFalse(renne instanceof Travailler);
        assertFalse(renne instanceof SeDivertir);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("creaturesFantastiques")
    @DisplayName("etreFantastique augmente la magie de 2")
    void testEtreFantastique(String type, Creature creature) {
        assertInstanceOf(EtreFantastique.class, creature);
        ((EtreFantastique) creature).etreFantastique();
        assertEquals(12, magie(creature));
    }
}
