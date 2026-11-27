package personnages;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static support.Reflexion.magie;

@DisplayName("Tests unitaires pour les humanoïdes")
class HumanoideTest {

    @Nested
    @DisplayName("Comportement commun des humanoïdes")
    class Commun {

        @Test
        @DisplayName("Elfe, Lutin, Fée et Enfant sont des humanoïdes")
        void testHierarchie() {
            assertInstanceOf(Humanoide.class, new Elfe("Bernard", "M", 350));
            assertInstanceOf(Humanoide.class, new Lutin("Fizzy", "M", 80));
            assertInstanceOf(Humanoide.class, new Fee("Clochette", "F", 120));
            assertInstanceOf(Humanoide.class, new Enfant("Léa", "F", 8));
        }

        @Test
        @DisplayName("Un humanoïde n'est pas au travail par défaut")
        void testPasAuTravailParDefaut() {
            assertFalse(new Elfe("Bernard", "M", 350).estAuTravail());
        }

        @Test
        @DisplayName("setTravail modifie l'état de travail")
        void testSetTravail() {
            Elfe elfe = new Elfe("Bernard", "M", 350);
            elfe.setTravail(true);
            assertTrue(elfe.estAuTravail());
            elfe.setTravail(false);
            assertFalse(elfe.estAuTravail());
        }

        @Test
        @DisplayName("Une farce augmente la magie de 2 quand l'humanoïde ne travaille pas")
        void testFarceHorsTravail() {
            Elfe elfe = new Elfe("Bernard", "M", 350);
            elfe.percevoirFarce();
            assertEquals(12, magie(elfe));
        }

        @Test
        @DisplayName("Une farce ne change rien quand l'humanoïde travaille")
        void testFarceAuTravail() {
            Elfe elfe = new Elfe("Bernard", "M", 350);
            elfe.setTravail(true);
            elfe.percevoirFarce();
            assertEquals(10, magie(elfe));
        }
    }

    @Nested
    @DisplayName("Elfe")
    class ElfeTest {

        @Test
        @DisplayName("Un elfe sait travailler mais ne se divertit pas et n'est pas fantastique")
        void testInterfaces() {
            Elfe elfe = new Elfe("Bernard", "M", 350);
            assertInstanceOf(Travailler.class, elfe);
            assertFalse(elfe instanceof SeDivertir);
            assertFalse(elfe instanceof EtreFantastique);
        }

        @Test
        @DisplayName("travailler met l'elfe au travail et augmente sa magie de 2")
        void testTravailler() {
            Elfe elfe = new Elfe("Bernard", "M", 350);
            elfe.travailler();
            assertTrue(elfe.estAuTravail());
            assertEquals(12, magie(elfe));
        }
    }

    @Nested
    @DisplayName("Lutin")
    class LutinTest {

        @Test
        @DisplayName("Un lutin travaille, se divertit et est fantastique")
        void testInterfaces() {
            Lutin lutin = new Lutin("Fizzy", "M", 80);
            assertInstanceOf(Travailler.class, lutin);
            assertInstanceOf(SeDivertir.class, lutin);
            assertInstanceOf(EtreFantastique.class, lutin);
        }

        @Test
        @DisplayName("travailler met le lutin au travail et augmente sa magie de 2")
        void testTravailler() {
            Lutin lutin = new Lutin("Fizzy", "M", 80);
            lutin.travailler();
            assertTrue(lutin.estAuTravail());
            assertEquals(12, magie(lutin));
        }

        @Test
        @DisplayName("seDivertir augmente la magie de 2")
        void testSeDivertir() {
            Lutin lutin = new Lutin("Fizzy", "M", 80);
            lutin.seDivertir();
            assertEquals(12, magie(lutin));
        }

        @Test
        @DisplayName("etreFantastique augmente la magie de 2")
        void testEtreFantastique() {
            Lutin lutin = new Lutin("Fizzy", "M", 80);
            lutin.etreFantastique();
            assertEquals(12, magie(lutin));
        }
    }

    @Nested
    @DisplayName("Fée")
    class FeeTest {

        @Test
        @DisplayName("Une fée travaille et est fantastique mais ne se divertit pas")
        void testInterfaces() {
            Fee fee = new Fee("Clochette", "F", 120);
            assertInstanceOf(Travailler.class, fee);
            assertInstanceOf(EtreFantastique.class, fee);
            assertFalse(fee instanceof SeDivertir);
        }

        @Test
        @DisplayName("travailler met la fée au travail et augmente sa magie de 2")
        void testTravailler() {
            Fee fee = new Fee("Clochette", "F", 120);
            fee.travailler();
            assertTrue(fee.estAuTravail());
            assertEquals(12, magie(fee));
        }

        @Test
        @DisplayName("etreFantastique augmente la magie de 2")
        void testEtreFantastique() {
            Fee fee = new Fee("Clochette", "F", 120);
            fee.etreFantastique();
            assertEquals(12, magie(fee));
        }
    }

    @Nested
    @DisplayName("Enfant")
    class EnfantTest {

        @Test
        @DisplayName("Un enfant se divertit mais ne travaille pas")
        void testInterfaces() {
            Enfant enfant = new Enfant("Léa", "F", 8);
            assertInstanceOf(SeDivertir.class, enfant);
            assertFalse(enfant instanceof Travailler);
            assertFalse(enfant instanceof EtreFantastique);
        }

        @Test
        @DisplayName("augmenterMagie double la valeur reçue")
        void testAugmenterMagieDouble() {
            Enfant enfant = new Enfant("Léa", "F", 8);
            enfant.augmenterMagie(3);
            assertEquals(16, magie(enfant));
        }

        @Test
        @DisplayName("baisserMagie n'est pas doublée")
        void testBaisserMagieNonDoublee() {
            Enfant enfant = new Enfant("Léa", "F", 8);
            enfant.baisserMagie(3);
            assertEquals(7, magie(enfant));
        }

        @Test
        @DisplayName("seDivertir augmente la magie de 4")
        void testSeDivertir() {
            Enfant enfant = new Enfant("Léa", "F", 8);
            enfant.seDivertir();
            assertEquals(14, magie(enfant));
        }

        @Test
        @DisplayName("seDeplacer fait se divertir l'enfant")
        void testSeDeplacer() {
            Enfant enfant = new Enfant("Léa", "F", 8);
            enfant.seDeplacer();
            assertEquals(14, magie(enfant));
        }

        @Test
        @DisplayName("Une farce augmente la magie de 4 car l'enfant double les gains")
        void testFarce() {
            Enfant enfant = new Enfant("Léa", "F", 8);
            enfant.percevoirFarce();
            assertEquals(14, magie(enfant));
        }
    }
}
