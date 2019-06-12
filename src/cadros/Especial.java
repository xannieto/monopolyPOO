package cadros;

import xogadores.Xogador;
import xogo.Taboleiro;
import xogo.Xogo;

public final class Especial extends Cadro {

    private Double valor;

    public Especial(String id, String nome, Double valor){

        super.setId(id);
        super.setNome(nome);
        setValor(valor);

    }

    /* getters */

    public Double getValor() {
        return valor;
    }

    /* setters */

    public void setValor(Double valor) {

        if (valor < 0) this.valor = valor;

    }

    /* metodos */

    @Override
    public void accion(Taboleiro taboleiro, Xogador xogador) {

        switch (this.getId()){

            case "irCarcere":
                xogador.getAvatar().setCarcere(true);
                xogador.getAvatar().setPosicion(taboleiro.obterCadro("carcere"));

                Xogo.getConsola().imprimir("O xogador "+xogador.getNome()+" vai a prisión");
                break;

            case "saida":
                xogador.cobrar(this.valor);
                Xogo.getConsola().imprimir("O xogador "+xogador.getNome()+" cobra "+this.valor);
                break;

            case "aparcamento":
                xogador.cobrar(this.valor);
                Xogo.getConsola().imprimir("O xogador "+xogador.getNome()+" cobra "+this.valor+", o bote da banca");
                break;

            case "carcere":
                Xogo.getConsola().imprimir("Non se toma ningunha acción");
                break;
        }

    }

    @Override
    public String toString() {

        StringBuilder descricion = new StringBuilder();

        switch (this.getId()){

            case "saida":
                descricion.append(String.format("{\n\tCobro por volta: %.2f\n}",this.valor));
                break;

            case "carcere":
                descricion.append(String.format("{\n\tFianza: %.2f\n}",this.valor));
                break;

            case "aparcamento":
                descricion.append(String.format("{\n\tBote: %.2f\n}",this.valor));
                break;

            default:
                descricion.append("Información non dispoñíbel sobre o cadro");
                break;

        }

        return descricion.toString();
    }
}
