package tratos;

import xogadores.Xogador;
import xogo.Taboleiro;

public class TratoPropiedadePropiedadeAluguer extends Trato{

    public String propiedade1;
    public String propiedade2;
    public String propiedade3;
    public Integer quendas;

    public TratoPropiedadePropiedadeAluguer(String id, Xogador emisor, Xogador receptor, String propiedade1, String propiedade2, String propiedade3, Integer quendas){

        setId(id);
        setEmisorTrato(emisor);
        setReceptorTrato(receptor);
        setPropiedade1(propiedade1);
        setPropiedade2(propiedade2);
        setPropiedade3(propiedade3);
        setQuendas(quendas);

    }
    /* getters */
    public String getPropiedade1() {
        return propiedade1;
    }

    public String getPropiedade2() {
        return propiedade2;
    }

    public String getPropiedade3() {
        return propiedade3;
    }

    public Integer getQuendas() {
        return quendas;
    }

    /* setters */

    public void setPropiedade1(String propiedade1) {
        this.propiedade1 = propiedade1;
    }

    public void setPropiedade2(String propiedade2) {
        this.propiedade2 = propiedade2;
    }

    public void setPropiedade3(String propiedade3) {
        this.propiedade3 = propiedade3;
    }

    public void setQuendas(Integer quendas) {
        this.quendas = quendas;
    }

    @Override
    public void viabilidadeTrato(Taboleiro taboleiro) {

    }

    @Override
    public void accion(Taboleiro taboleiro) {

    }
}
