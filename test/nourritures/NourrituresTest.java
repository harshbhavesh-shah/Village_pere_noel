package nourritures;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests unitaires pour l'énumération Nourritures")
class NourrituresTest {

    @Test
    @DisplayName("L'énumération contient exactement cinq nourritures")
    void testNombreDeValeurs() {
        assertEquals(5, Nourritures.values().length);
    }

    @ParameterizedTest(name = "{0} nourrit de {1}")
    @CsvSource({
            "BISCUITS, 3",
            "CHOCOLATSCHAUD, 5",
            "GUIMAUVES, 2",
            "GRUAU, 2",
            "FRIANDISE, 2"
    })
    @DisplayName("Chaque nourriture a la bonne valeur nutritive")
    void testNutrition(Nourritures nourriture, int nutritionAttendue) {
        assertEquals(nutritionAttendue, nourriture.getNutrition());
    }

    @Test
    @DisplayName("valueOf avec un nom inconnu lève une IllegalArgumentException")
    void testValueOfInconnu() {
        assertThrows(IllegalArgumentException.class, () -> Nourritures.valueOf("PAIN"));
    }
}
