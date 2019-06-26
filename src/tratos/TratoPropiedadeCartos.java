package tratos;

import cadros.*;
import edificacions.Edificacion;
import excepcions.FortunaInsuficienteExcepcion;
import xogadores.Xogador;
import xogo.Taboleiro;
import xogo.Xogo;

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
    public Boolean viabilidadeTrato(Taboleiro taboleiro) {

        Cadro cadro = taboleiro.obterCadro(propiedade);

        if (!invertir){

            if (cadro instanceof Solar || cadro instanceof Servizo || cadro instanceof Transporte){
                if (!((Propiedade) cadro).pertenceAXogador(this.getEmisorTrato()))
                    return false;
            }

            if (getReceptorTrato().getFortuna() < this.cartos)    return false;

            return true;

        } else {


            if (cadro instanceof Solar || cadro instanceof Servizo || cadro instanceof Transporte){
                if (!((Propiedade) cadro).pertenceAXogador(this.getReceptorTrato()))
                    return false;
            }

            if (getEmisorTrato().getFortuna() < this.cartos)    return false;

            return true;

        }

    }

    @Override
    public void accion(Taboleiro taboleiro) {

        if (viabilidadeTrato(taboleiro)){

            if (invertir)   realizarAccion(getReceptorTrato(),getEmisorTrato(),taboleiro);
            else realizarAccion(getEmisorTrato(),getReceptorTrato(),taboleiro);

        } else Xogo.getConsola().imprimir("O trato non se pode realizar no estado actual.");

    }

    private void realizarAccion(Xogador emisor, Xogador receptor, Taboleiro taboleiro){

        try{
            /* cambio de cartos */
            receptor.pagar(cartos);
            emisor.cobrar(cartos);

            /* cambio de propiedade */
            Propiedade propiedade = (Propiedade) taboleiro.obterCadro(getPropiedade());

            propiedade.setPropietario(receptor);
            emisor.quitarPropiedade(propiedade);
            propiedade.aluguer(taboleiro);

            if (propiedade instanceof Solar)
                if (!((Solar) propiedade).getEdificacions().isEmpty())
                    for (Edificacion edificacion: ((Solar) propiedade).getEdificacions().values())
                        edificacion.setPropietario(receptor);

            /* adaptando a saída por pantalla */
            if (!invertir) {
                Xogo.getConsola().imprimir(String.format("Aceptouse o seguinte trato con %s: doulle %.2f€ e %s dame %s.",
                        getEmisorTrato().getNome(),getCartos(),getEmisorTrato().getNome(),getPropiedade()));
            } else
                Xogo.getConsola().imprimir(String.format("Aceptouse o seguinte trato con %s: doulle %s e %s dame %.2f€.",
                        getEmisorTrato().getNome(),getPropiedade(),getEmisorTrato().getNome(),getCartos()));

        } catch (FortunaInsuficienteExcepcion e){
            Xogo.getConsola().imprimir(e.getMessage());
        }

    }

    @Override
    public String toString() {
        StringBuilder informacion = new StringBuilder();

        informacion.append(String.format("{\n\tid: %s,\n\txogador emisor: %s,\n\ttrato: (",getId(),getEmisorTrato().getNome()));

        if (!invertir) informacion.append(String.format("%s, %.2f€)\n}",getPropiedade(),getCartos()));

        else informacion.append(String.format("%.2f€, %s)\n}",getCartos(),getPropiedade()));


        return informacion.toString();
    }
}
