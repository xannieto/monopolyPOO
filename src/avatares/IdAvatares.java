package avatares;

import java.util.HashMap;

public abstract class IdAvatares {

    private final static Character[] id = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','Ñ','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    private static HashMap<Character,Boolean> mapaId = new HashMap<>();

    /* getters */
    public Character getId(Integer indice){

        if (indice > -1 && indice < id.length)
            return id[indice];
        return null;
    }

    public Boolean getUsado(Character idProvisional){

        if (!mapaId.isEmpty())  return mapaId.get(idProvisional);

        return Boolean.FALSE;
    }

    /* métodos */
    public void introducirId(Character id){

        if (!getUsado(id).booleanValue())   mapaId.put(id,Boolean.TRUE);

    }

}
