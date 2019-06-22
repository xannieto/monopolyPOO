package cartas;

import excepcions.FortunaInsuficienteExcepcion;
import excepcions.HipotecaExcepcion;
import xogadores.Xogador;
import xogo.Taboleiro;
import xogo.Xogo;

public final class CartaCaixaComunidade extends Carta {

    public CartaCaixaComunidade(String id, String textoCarta, String cadroDestino,Double valor, Boolean pagar, Boolean cobrar){

        this.setId(id);
        this.setTextoCarta(textoCarta);
        this.setCadroDestino(cadroDestino);
        this.setValor(valor);
        this.setPagar(pagar);
        this.setCobrar(cobrar);

    }

    @Override
    public void accion(Taboleiro taboleiro, Xogador xogador){

        if (this.getValor() != null && this.getCadroDestino()==null){
            /* para as que son simplemente multas ou premios por algo */
            if (getPagar()){
                try{
                    xogador.pagar(this.getValor());
                    xogador.incrementarPagoDeImpostos(this.getValor());
                    Xogo.getConsola().imprimir(String.format("Acción: %s",getTextoCarta()));

                } catch (FortunaInsuficienteExcepcion e) {
                    xogador.setDebeda(this.getValor());
                    xogador.setHipotecar(true);
                    Xogo.getConsola().imprimir(e.getMessage());
                }

            } else {
                xogador.cobrar(this.getValor());
                xogador.incrementarPremiosInversionsBote(this.getValor());
                Xogo.getConsola().imprimir(String.format("Acción: %s",getTextoCarta()));
            }

        } else if (this.getCadroDestino() != null){
            /* as que implican desprazarte polo taboleiro, podendo cobrar ou non */
            if (getCadroDestino().equals("carcere")){

                xogador.getAvatar().setCarcere(true);
                xogador.getAvatar().setQuendasPrision(3);
                xogador.getAvatar().resetDatosTirada();
                xogador.incrementarVecesEnPrision();
                xogador.getAvatar().setPosicion(taboleiro.obterCadro(getCadroDestino()));

                Xogo.getConsola().imprimir(String.format("Acción: %s",getTextoCarta()));

            } else if (getCadroDestino().equals("saida")){

                    xogador.getAvatar().setPosicion(taboleiro.obterCadro(getCadroDestino()));

                    try {
                        xogador.getAvatar().getPosicion().accion(taboleiro, xogador);
                        Xogo.getConsola().imprimir(String.format("Acción: %s",getTextoCarta()));
                    } catch (HipotecaExcepcion e){
                        Xogo.getConsola().imprimir(e.getMessage());
                }
            }

        }

    }

}
