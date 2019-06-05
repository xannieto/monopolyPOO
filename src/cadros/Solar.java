package cadros;

import edificacions.Edificacion;
import java.util.HashMap;

public final class Solar extends Propiedade {

    private HashMap<String,Edificacion> edificacions;


    public Solar(String id, String nome){

        super(id,nome);
        this.edificacions = new HashMap<>();

    }

    /* getters */
    public HashMap getEdficiacions(){
        return edificacions;
    }

    /* setters */
    public void setEdificacions(HashMap<String,Edificacion> edificacions){
        this.edificacions = edificacions;
    }

    /* métodos */
    public void engadirEdificacion(Edificacion edificacion){

        if(edificacion != null){
            edificacions.put(edificacion.getId(),edificacion);
        }

    }

    public void quitarEdificacion(String edificacion){

        if(edificacion.isEmpty()){
            edificacions.remove(edificacion);
        }

    }

    @Override
    public void aluguer() {

    }
}
