package cadros;

import avatares.Avatar;
import xogadores.Xogador;
import xogo.Taboleiro;
import xogo.Xogo;

import java.util.ArrayList;

public final class Especial extends Cadro {

    private Double valor;

    public Especial(String id, String nome, Double valor){

        super.setId(id);
        super.setNome(nome);
        this.setValor(valor);

    }

    /* getters */

    public Double getValor() {
        return valor;
    }

    /* setters */

    public void setValor(Double valor) {

        if (valor > -1) this.valor = valor;

    }

    /* metodos */

    @Override
    public void accion(Taboleiro taboleiro, Xogador xogador) {

        switch (this.getId()){

            case "irCarcere":
                xogador.getAvatar().setCarcere(true);
                xogador.getAvatar().setPosicion(taboleiro.obterCadro("carcere"));

                Xogo.getConsola().imprimir(String.format("O xogador %s vai a prisión.",xogador.getNome()));
                break;

            case "saida":
                xogador.cobrar(this.valor);
                Xogo.getConsola().imprimir(String.format("O xogador %s cobra %.2f€ por pasar polo cadro de saída.",xogador.getNome(),this.valor));
                break;

            case "aparcamento":
                xogador.cobrar(this.valor);
                if (this.valor > 0.0)
                    Xogo.getConsola().imprimir(String.format("O xogador %s cobra %.2f€, o bote da banca.",xogador.getNome(),this.valor));
                this.setValor(0.0);
                break;

            case "carcere":
                Xogo.getConsola().imprimir("Non se toma ningunha acción.");
                break;
        }

    }

    @Override
    public String toString() {

        StringBuilder descricion = new StringBuilder();

        if (this.getId().equals("saida"))
            descricion.append(String.format("{\n\tCobro por volta: %.2f\n}",this.valor));

        else
            descricion.append("Información non dispoñíbel sobre o cadro.");

        return descricion.toString();
    }

    public String toString(Taboleiro taboleiro){

        StringBuilder descricion = new StringBuilder();

        if (this.getId().equals("aparcamento")){

            descricion.append(String.format("{\n\tbote: %.2f\n\txogadores: ",this.valor));

            ArrayList<Avatar> avatares = taboleiro.obterAvataresCadro(this);

            if (!avatares.isEmpty()){
                descricion.append("[");

                for (int i = 0; i < avatares.size(); i++)
                    if ((i+1)!= avatares.size()) descricion.append(String.format("%s, ",avatares.get(i).getId()));
                    else descricion.append(String.format("%s]",avatares.get(i).getId()));

            } else descricion.append("ningún\n}");

        } else if (this.getId().equals("carcere")){

            descricion.append(String.format("{\n\tfianza: %.2f\n\txogadores: ",this.valor));

            ArrayList<Avatar> avatares = taboleiro.obterAvataresCadro(this);

            if (!avatares.isEmpty()) {
                for (Avatar avatar : avatares)
                    descricion.append(String.format("[%s,%d]", avatar.getId(), avatar.getQuendasPrision()));

            } else descricion.append("ningún\n}");

        } else descricion.append("Información non dispoñíbel sobre o cadro.");

        return descricion.toString();
    }
}
