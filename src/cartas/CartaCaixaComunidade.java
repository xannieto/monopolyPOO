package cartas;

import excepcions.FortunaInsuficienteExcepcion;
import xogadores.Xogador;
import xogo.Taboleiro;
import xogo.Xogo;

public final class CartaCaixaComunidade extends Carta {

    public CartaCaixaComunidade(String textoCarta, String cadroDestino,Double valor, Boolean pagar, Boolean cobrar){

        this.setTextoCarta(textoCarta);
        this.setCadroDestino(cadroDestino);
        this.setValor(valor);
        this.setPagar(pagar);
        this.setCobrar(cobrar);

    }

    @Override
    public void accion(Taboleiro taboleiro, Xogador xogador){

        if (this.getValor() != null){

            if (getPagar()){
                try{
                    xogador.pagar(this.getValor());
                    Xogo.getConsola().imprimir(String.format("Acción: %s",getTextoCarta()));

                } catch (FortunaInsuficienteExcepcion e) {
                    Xogo.getConsola().imprimir(e.getMessage());
                    xogador.setDebeda(this.getValor());
                    xogador.setHipotecar(true);
                }

            } else {
                xogador.cobrar(this.getValor());
                Xogo.getConsola().imprimir(String.format("Acción: %s",getTextoCarta()));
            }

        } else if (!this.getCadroDestino().isEmpty()){
            if (getCadroDestino().equals("carcere")){

                xogador.getAvatar().setCarcere(true);
                xogador.getAvatar().setQuendasPrision(3);
                xogador.getAvatar().resetDatosTirada();
                xogador.getAvatar().setPosicion(taboleiro.obterCadro(getCadroDestino()));

                Xogo.getConsola().imprimir(String.format("Acción: %s",getTextoCarta()));

            } else {

                if (this.getCobrar()){

                    Integer posicionAntiga = taboleiro.posicionActual(xogador.getAvatar());
                    xogador.getAvatar().setPosicion(taboleiro.obterCadro(getCadroDestino()));
                    Integer posicionNova = taboleiro.posicionActual(xogador.getAvatar());

                    if (posicionAntiga > posicionNova)  taboleiro.obterCadro("saida").accion(taboleiro,xogador);


                } else {
                    xogador.getAvatar().setPosicion(taboleiro.obterCadro(getCadroDestino()));
                }



            }

        }

    }
}
