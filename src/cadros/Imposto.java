package cadros;

import excepcions.FortunaInsuficienteExcepcion;
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

        if (imposto > 0)    this.imposto = imposto;

    }

    /* metodos */

    @Override
    public void accion(Taboleiro taboleiro, Xogador xogador) {

        try {

            xogador.pagar(imposto);
            ((Especial)taboleiro.obterCadro("aparcamento")).setValor(((Especial)taboleiro.obterCadro("aparcamento")).getValor()+imposto);
            Xogo.getConsola().imprimir(String.format("O xogador %s paga o %s de %.2f€",xogador.getNome(),this.getNome(),this.imposto));
        } catch (FortunaInsuficienteExcepcion e){
            Xogo.getConsola().imprimir(e.getMessage());

        }

    }

    @Override
    public String toString() {

        StringBuilder informacion = new StringBuilder();

        informacion.append(String.format("{\n\ttipo: imposto,\n\ta pagar: %.2f€\n}",this.imposto));

        return informacion.toString();
    }
}
