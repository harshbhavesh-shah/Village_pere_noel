package batiments;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests unitaires pour l'énumération Proprete")
class PropreteTest {

    @Test
    @DisplayName("Les niveaux de propreté sont dans l'ordre mauvais, correct, bon")
    void testValeurs() {
        assertArrayEquals(new Proprete[]{Proprete.mauvais, Proprete.correct, Proprete.bon}, Proprete.values());
    }

    @Test
    @DisplayName("valueOf retrouve un niveau par son nom")
    void testValueOf() {
        assertEquals(Proprete.correct, Proprete.valueOf("correct"));
    }
}
