package avatares;

import java.util.HashMap;

public final class IdAvatares {

    private static HashMap<Integer,Character> mapaId = new HashMap<>();

    /* getters */
    public static Boolean getLibre(Integer indice) {

        return mapaId.get(indice) == null;

    }

    /* setters */
    public static void setNovoId(Integer indice, Character id){

        if (getLibre(indice))   mapaId.put(indice,id);

    }


}
