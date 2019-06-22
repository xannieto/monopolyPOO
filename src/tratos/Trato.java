package tratos;

import xogadores.Xogador;
import xogo.Taboleiro;

public abstract class Trato {

    private String id;
    private Xogador emisorTrato;
    private Xogador receptorTrato;

    /* getter */

    public String getId() {
        return id;
    }

    public Xogador getEmisorTrato() {
        return emisorTrato;
    }

    public Xogador getReceptorTrato() {
        return receptorTrato;
    }

    /* setter */

    public void setId(String id) {
        this.id = id;
    }

    public void setEmisorTrato(Xogador emisorTrato) {
        this.emisorTrato = emisorTrato;
    }

    public void setReceptorTrato(Xogador receptorTrato) {
        this.receptorTrato = receptorTrato;
    }

    public abstract void viabilidadeTrato(Taboleiro taboleiro);

    public abstract void accion(Taboleiro taboleiro);

}
