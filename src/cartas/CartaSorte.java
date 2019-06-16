package cartas;

import excepcions.BancarrotaExcepcion;
import xogadores.Xogador;
import xogo.Taboleiro;

public final class CartaSorte extends Carta {

    public CartaSorte(String textoCarta, String cadroDestino, Boolean pagar, Boolean cobrar){

        this.setTextoCarta(textoCarta);
        this.setCadroDestino(cadroDestino);
        this.setPagar(pagar);
        this.setCobrar(cobrar);

    }

    @Override
    public void accion(Taboleiro taboleiro, Xogador xogador) {

    }
}
