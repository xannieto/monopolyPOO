package tratos;

import cadros.*;
import edificacions.Edificacion;
import xogadores.Xogador;
import xogo.Taboleiro;
import xogo.Xogo;

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
    public Boolean viabilidadeTrato(Taboleiro taboleiro) {

        Cadro c1 = taboleiro.obterCadro(getPropiedade1());
        Cadro c2 = taboleiro.obterCadro(getPropiedade2());

        if (c1 instanceof Solar || c1 instanceof Servizo || c1 instanceof Transporte){
            if (!((Propiedade) c1).pertenceAXogador(getEmisorTrato()))   return false;
        } else return false;

        if (c2 instanceof Solar || c2 instanceof Servizo || c2 instanceof Transporte){
            if (!((Propiedade) c2).pertenceAXogador(getReceptorTrato()))   return false;
        } else return false;

        return true;
    }

    @Override
    public void accion(Taboleiro taboleiro) {

        if (viabilidadeTrato(taboleiro)){

            Propiedade propiedade1 = (Propiedade) taboleiro.obterCadro(getPropiedade1());
            Propiedade propiedade2 = (Propiedade) taboleiro.obterCadro(getPropiedade2());

            /* intercambio de propiedades */
            getEmisorTrato().quitarPropiedade(propiedade1);
            propiedade1.setPropietario(getReceptorTrato());
            getReceptorTrato().engadirPropiedade(propiedade1);

            getReceptorTrato().quitarPropiedade(propiedade2);
            propiedade1.setPropietario(getEmisorTrato());
            getEmisorTrato().engadirPropiedade(propiedade2);

            propiedade1.aluguer(taboleiro);
            propiedade2.aluguer(taboleiro);

            /* se son solares hai que cambiar os propietarios dos edificios */
            if (propiedade1 instanceof Solar){
                if (!((Solar) propiedade1).getEdificacions().isEmpty()){
                    for (Edificacion edificacion: ((Solar) propiedade1).getEdificacions().values())
                        edificacion.setPropietario(getReceptorTrato());
                }
            }

            if (propiedade2 instanceof Solar){
                if (!((Solar) propiedade2).getEdificacions().isEmpty()){
                    for (Edificacion edificacion: ((Solar) propiedade2).getEdificacions().values())
                        edificacion.setPropietario(getEmisorTrato());
                }
            }

            Xogo.getConsola().imprimir(String.format("Aceptou o seguinte trato con %s: doulle %s e %s dame %s.",
                    getEmisorTrato().getNome(),getPropiedade2(),getEmisorTrato().getNome(),getPropiedade1()));

        } else Xogo.getConsola().imprimir("O trato non se pode realizar no estado actual.");
    }

    @Override
    public String toString() {
        StringBuilder informacion = new StringBuilder();

        informacion.append(String.format("{\n\tid: %s,\n\txogador emisor: %s,\n\ttrato: (%s, %s)\n}",getId(),getEmisorTrato().getNome(),getPropiedade1(),getPropiedade2()));

        return informacion.toString();
    }
}
