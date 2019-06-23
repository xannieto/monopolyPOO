package cadros;

import avatares.Avatar;
import avatares.Esfinxe;
import excepcions.HipotecaExcepcion;
import xogadores.Xogador;
import xogo.Taboleiro;

public abstract class Cadro {

    private String id;
    private String nome;
    private Integer visitas;

    /* getters */

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer frecuenciaVisitas(){return visitas;}

    /* setters */
    public void setId(String id){
        this.id = id;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setVisitas(){
        if (this.visitas != null) this.visitas++;
        else this.visitas = 0;
    }

    /* metodos */
    public boolean estaAvatar(Avatar avatar){

        if (avatar != null) return avatar.getPosicion().equals(this);
        return false;

    }

    public abstract void accion(Taboleiro taboleiro, Xogador xogador) throws HipotecaExcepcion;

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Especial || obj instanceof Solar || obj instanceof Servizo || obj instanceof Sorte || obj instanceof Transporte || obj instanceof Imposto || obj instanceof CaixaComunidade)
            return ((Cadro)obj).getId().equals(this.id);

        return false;

    }
}
