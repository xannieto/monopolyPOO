package tratos;

import xogadores.Xogador;
import xogo.Taboleiro;

public final class TratoPropiedadePropiedade extends Trato {

    private String propiedade1;
    private String propiedade2;


    public TratoPropiedadePropiedade(String id, Xogador emisor, Xogador receptor, String propiedade1, String propiedade2){
        setId(id);
        setEmisorTrato(emisor);
        setReceptorTrato(receptor);
        setPropiedade1(propiedade1);
        setPropiedade2(propiedade2);

    }

    /* getters */
    public String getPropiedade1() {
        return propiedade1;
    }

    public String getPropiedade2() {
        return propiedade2;
    }

    /* setters */
    public void setPropiedade1(String propiedade1) {
        this.propiedade1 = propiedade1;
    }

    public void setPropiedade2(String propiedade2) {
        this.propiedade2 = propiedade2;
    }

    @Override
    public void viabilidadeTrato(Taboleiro taboleiro) {

    }

    @Override
    public void accion(Taboleiro taboleiro) {

    }
}
