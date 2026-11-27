package batiments;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import personnages.Elfe;
import personnages.Humanoide;
import personnages.Lutin;
import personnages.Personnage;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests unitaires pour la classe Chalet")
class ChaletTest {

    private Chalet chaletMasculin;
    private Elfe elfeMasculin;
    private Elfe elfeFeminin;

    @SafeVarargs
    private static Class<? extends Personnage>[] types(Class<? extends Personnage>... types) {
        return types;
    }

    @BeforeEach
    void setUp() {
        chaletMasculin = new Chalet(120, 6, "M");
        elfeMasculin = new Elfe("Bernard", "M", 350);
        elfeFeminin = new Elfe("Alabaster", "F", 300);
    }

    @Test
    @DisplayName("Le constructeur enregistre le sexe des occupants du chalet")
    void testConstructeur() {
        assertEquals("M", chaletMasculin.sexe);
        assertEquals("F", new Chalet(120, 6, "F").sexe);
        assertEquals(120, chaletMasculin.getSuperficie());
        assertEquals(6, chaletMasculin.getCapacite());
    }

    @Test
    @DisplayName("Un personnage autorisé et du même sexe rejoint le chalet")
    void testMemeSexe() {
        chaletMasculin.setCategorieAutorise(Elfe.class);
        chaletMasculin.setTypesAutorise(types(Elfe.class));
        chaletMasculin.rejoindre(elfeMasculin);
        assertTrue(chaletMasculin.getPersonnages().contains(elfeMasculin));
    }

    @Test
    @DisplayName("Un personnage autorisé mais de l'autre sexe est refusé")
    void testAutreSexe() {
        chaletMasculin.setCategorieAutorise(Elfe.class);
        chaletMasculin.setTypesAutorise(types(Elfe.class));
        chaletMasculin.rejoindre(elfeFeminin);
        assertTrue(chaletMasculin.getPersonnages().isEmpty());
    }

    @Test
    @DisplayName("Sans catégorie autorisée, personne ne peut rejoindre le chalet")
    void testSansCategorie() {
        chaletMasculin.rejoindre(elfeMasculin);
        assertTrue(chaletMasculin.getPersonnages().isEmpty());
    }

    @Test
    @DisplayName("Un personnage de la bonne catégorie mais d'un type non autorisé est refusé")
    void testTypeNonAutorise() {
        Lutin lutin = new Lutin("Fizzy", "M", 80);
        chaletMasculin.setCategorieAutorise(Lutin.class);
        chaletMasculin.setTypesAutorise(types(Elfe.class));
        chaletMasculin.rejoindre(lutin);
        assertTrue(chaletMasculin.getPersonnages().isEmpty());
    }

    @Test
    @DisplayName("Avec une liste de types vide, personne ne peut rejoindre le chalet")
    void testTypesVides() {
        chaletMasculin.setCategorieAutorise(Elfe.class);
        chaletMasculin.setTypesAutorise(types());
        chaletMasculin.rejoindre(elfeMasculin);
        assertTrue(chaletMasculin.getPersonnages().isEmpty());
    }

    @Test
    @DisplayName("Un personnage d'une autre catégorie est refusé")
    void testCategorieDifferente() {
        Lutin lutin = new Lutin("Fizzy", "M", 80);
        chaletMasculin.setCategorieAutorise(Elfe.class);
        chaletMasculin.setTypesAutorise(types(Elfe.class, Lutin.class));
        chaletMasculin.rejoindre(lutin);
        assertTrue(chaletMasculin.getPersonnages().isEmpty());
    }

    @Test
    @DisplayName("Un elfe du même sexe rejoint un chalet réservé aux humanoïdes")
    void testCategorieParente() {
        chaletMasculin.setCategorieAutorise(Humanoide.class);
        chaletMasculin.setTypesAutorise(types(Elfe.class));
        chaletMasculin.rejoindre(elfeMasculin);
        assertTrue(chaletMasculin.getPersonnages().contains(elfeMasculin));
    }
}
