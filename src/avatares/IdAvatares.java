package avatares;

import java.util.HashMap;

public final class IdAvatares {

    //private final static Character[] id = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','Ñ','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    private HashMap<Integer,Character> mapaId;

    public IdAvatares(){

        mapaId = new HashMap<>();

    }

    /* getters */
    public Boolean getUsado(Integer indice) {

        return mapaId.containsKey(indice);

    }

    /* setters */
    public void setNovoId(Integer indice, Character id){

        if (getUsado(indice))   mapaId.put(indice,id);

    }


}
