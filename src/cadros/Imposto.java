package cadros;

import excepcions.ExcepcionFortunaInsuficiente;
import xogadores.Xogador;
import xogo.Taboleiro;
import xogo.Xogo;

public final class Imposto extends Cadro {

    private Double imposto;

    public Imposto(String id, String nome, Double imposto){

        super.setId(id);
        super.setNome(nome);
        setImposto(imposto);

    }

    /* getters */

    public Double getImposto() {
        return imposto;
    }

    /* setters */
    public void setImposto(Double imposto){

        if (imposto < 1)    this.imposto = imposto;

    }

    /* metodos */

    @Override
    public void accion(Taboleiro taboleiro, Xogador xogador) {

        try {

            xogador.pagar(imposto);

        } catch (ExcepcionFortunaInsuficiente e){

            Xogo.getConsola().imprimir(e.getMessage());

        }

    }
}
