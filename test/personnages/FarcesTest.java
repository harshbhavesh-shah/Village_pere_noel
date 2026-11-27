package personnages;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests unitaires pour l'énumération Farces")
class FarcesTest {

    @Test
    @DisplayName("L'énumération contient exactement les quatre farces prévues")
    void testValeurs() {
        assertArrayEquals(
                new Farces[]{Farces.laitMagique, Farces.soupeBas, Farces.papierRouler, Farces.yeuxRemuant},
                Farces.values());
    }

    @Test
    @DisplayName("valueOf retrouve une farce par son nom")
    void testValueOf() {
        assertEquals(Farces.soupeBas, Farces.valueOf("soupeBas"));
    }

    @Test
    @DisplayName("valueOf avec un nom inconnu lève une IllegalArgumentException")
    void testValueOfInconnu() {
        assertThrows(IllegalArgumentException.class, () -> Farces.valueOf("inconnue"));
    }
}
