package batiments;

import nourritures.Nourritures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests unitaires pour la classe Reserve")
class ReserveTest {

    private Reserve reserve;

    @BeforeEach
    void setUp() {
        reserve = new Reserve(60, 3);
    }

    @Test
    @DisplayName("Une nouvelle réserve est vide")
    void testReserveVide() {
        assertTrue(reserve.getStockDeNourriture().isEmpty());
    }

    @Test
    @DisplayName("addNourriture ajoute la nourriture au stock")
    void testAjouter() {
        reserve.addNourriture(Nourritures.BISCUITS);
        reserve.addNourriture(Nourritures.GRUAU);
        assertEquals(List.of(Nourritures.BISCUITS, Nourritures.GRUAU), reserve.getStockDeNourriture());
    }

    @Test
    @DisplayName("Une même nourriture peut être stockée plusieurs fois")
    void testAjouterDoublons() {
        reserve.addNourriture(Nourritures.GUIMAUVES);
        reserve.addNourriture(Nourritures.GUIMAUVES);
        assertEquals(2, reserve.getStockDeNourriture().size());
    }

    @Test
    @DisplayName("addNourriture ignore les ajouts au-delà de la capacité de stockage")
    void testCapaciteMaximale() {
        reserve.addNourriture(Nourritures.BISCUITS);
        reserve.addNourriture(Nourritures.GRUAU);
        reserve.addNourriture(Nourritures.FRIANDISE);
        reserve.addNourriture(Nourritures.CHOCOLATSCHAUD);
        assertEquals(3, reserve.getStockDeNourriture().size());
        assertFalse(reserve.getStockDeNourriture().contains(Nourritures.CHOCOLATSCHAUD));
    }

    @Test
    @DisplayName("Une réserve de capacité 0 n'accepte aucune nourriture")
    void testCapaciteNulle() {
        Reserve vide = new Reserve(10, 0);
        vide.addNourriture(Nourritures.BISCUITS);
        assertTrue(vide.getStockDeNourriture().isEmpty());
    }

    @Test
    @DisplayName("removeNourriture retire une seule occurrence de la nourriture")
    void testRetirer() {
        reserve.addNourriture(Nourritures.GRUAU);
        reserve.addNourriture(Nourritures.GRUAU);
        reserve.removeNourriture(Nourritures.GRUAU);
        assertEquals(List.of(Nourritures.GRUAU), reserve.getStockDeNourriture());
    }

    @Test
    @DisplayName("removeNourriture d'une nourriture absente ne change rien")
    void testRetirerAbsente() {
        reserve.addNourriture(Nourritures.BISCUITS);
        reserve.removeNourriture(Nourritures.FRIANDISE);
        assertEquals(List.of(Nourritures.BISCUITS), reserve.getStockDeNourriture());
    }

    @Test
    @DisplayName("Retirer une nourriture libère de la place dans la réserve")
    void testRetirerLibereDeLaPlace() {
        reserve.addNourriture(Nourritures.BISCUITS);
        reserve.addNourriture(Nourritures.GRUAU);
        reserve.addNourriture(Nourritures.FRIANDISE);
        reserve.removeNourriture(Nourritures.GRUAU);
        reserve.addNourriture(Nourritures.CHOCOLATSCHAUD);
        assertTrue(reserve.getStockDeNourriture().contains(Nourritures.CHOCOLATSCHAUD));
    }
}
