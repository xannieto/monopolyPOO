package tratos;

import xogadores.Xogador;
import xogo.Taboleiro;

public final class TratoPropiedadeCartos extends Trato {

    public String propiedade;
    public Double cartos;
    public Boolean invertir;

    public TratoPropiedadeCartos(String id, Xogador emisor, Xogador receptor,String propiedade, Double cartos, Boolean invertir){
        setId(id);
        setEmisorTrato(emisor);
        setReceptorTrato(receptor);
        setCartos(cartos);
        setPropiedade(propiedade);
        setInvertir(invertir);
    }

    /* getters */
    public String getPropiedade() {
        return propiedade;
    }

    public Double getCartos() {
        return cartos;
    }

    public Boolean getInvertir() {
        return invertir;
    }

    /* setters */
    public void setPropiedade(String propiedade) {
        this.propiedade = propiedade;
    }

    public void setCartos(Double cartos) {
        this.cartos = cartos;
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
