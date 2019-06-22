package tratos;

import xogadores.Xogador;
import xogo.Taboleiro;

public final class TratoPropiedadePropiedadeCartos extends Trato {

    public String propiedade1;
    public String propiedade2;
    public Double cartos;
    public Boolean invertir;

    public TratoPropiedadePropiedadeCartos(String id, Xogador emisor, Xogador receptor, String propiedade1, Double cartos, Boolean invertir){
        setId(id);
        setEmisorTrato(emisor);
        setReceptorTrato(receptor);
        setCartos(cartos);
        setPropiedade1(propiedade1);
        setPropiedade2(propiedade2);
        setInvertir(invertir);
    }

    /* setters */
    public void setCartos(Double cartos) {
        this.cartos = cartos;
    }

    public void setPropiedade1(String propiedade1) {
        this.propiedade1 = propiedade1;
    }

    public void setPropiedade2(String propiedade2) {
        this.propiedade2 = propiedade2;
    }

    public void setInvertir(Boolean invertir) {
        this.invertir = invertir;
    }

    @Override
    public void viabilidadeTrato(Taboleiro taboleiro) {

    }

    @Override
    public void accion(Taboleiro taboleiro) {

    }
}
