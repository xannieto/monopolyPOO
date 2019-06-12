package cadros;

import avatares.Avatar;
import xogadores.Xogador;
import xogo.Taboleiro;

public abstract class Cadro {

    private String id;
    private String nome;

    /* getters */

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    /* setters */
    public void setId(String id){
        this.id = id;
    }

    public void setNome(String nome){
        this.nome = nome;
    }


    /* metodos */
    public boolean estaAvatar(Avatar avatar){

        if (avatar != null) return avatar.getPosicion().equals(this);
        return false;

    }

    public abstract void accion(Taboleiro taboleiro, Xogador xogador);

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Cadro)  return ((Cadro)obj).getId().equals(this.id);

        return false;

    }
}
