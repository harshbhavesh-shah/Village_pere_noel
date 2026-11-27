package support;

import personnages.Personnage;

import java.lang.reflect.Field;

public final class Reflexion {

    private Reflexion() {
    }

    public static Object lire(Object cible, Class<?> declarant, String nomChamp) {
        try {
            Field champ = declarant.getDeclaredField(nomChamp);
            champ.setAccessible(true);
            return champ.get(cible);
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException("Champ inaccessible : " + nomChamp, e);
        }
    }

    public static int magie(Personnage personnage) {
        return (int) lire(personnage, Personnage.class, "magie");
    }
}
