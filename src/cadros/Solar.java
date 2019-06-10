package cadros;

import edificacions.Edificacion;
import xogadores.Xogador;
import xogo.Taboleiro;

import java.util.HashMap;

public final class Solar extends Propiedade {

    private HashMap<String,Edificacion> edificacions;
    private Grupo grupo;


    public Solar(String id, String nome, Double valor){

        this.setId(id);
        this.setNome(nome);
        this.setValor(valor);
        this.setHipotecada(false);
        this.setHipoteca(valor*0.5);
        this.edificacions = new HashMap<>();
        this.grupo = null;

    }

    /* getters */
    public HashMap getEdficiacions(){
        return edificacions;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    /* setters */
    public void setEdificacions(HashMap<String,Edificacion> edificacions){
        this.edificacions = edificacions;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    /* métodos */
    public void engadirEdificacion(Edificacion edificacion){

        if(edificacion != null){
            edificacions.put(edificacion.getId(),edificacion);
        }

    }

    public void quitarEdificacion(String edificacion){

        if(!edificacion.isEmpty()){
            edificacions.remove(edificacion);
        }

    }

    public void incrementarValor(){

        this.setValor(this.getValor()*1.05);

    }

    @Override
    public void aluguer() {

    }

    @Override
    public void accion(Taboleiro taboleiro, Xogador xogador) {

    }
}
