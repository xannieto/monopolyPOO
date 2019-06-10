package avatares;

import xogadores.Xogador;
import xogo.Taboleiro;

public final class Coche extends Avatar {



    public Coche(Xogador xogador){

        this.xenerarId();
        this.setNome("Coche");
        this.setXogador(xogador);
        this.setPosicion(null);
        this.setVoltasDadas(0);
        this.setMovementoAvanzado(Boolean.FALSE);
        this.setSacarDobres(false);
        this.setVecesDobres(0);
        this.setCarcere(false);
        this.setQuendasPrision(0);
        this.setCobrarSaida(false);

    }


    /* métodos */

    @Override
    public void moverEnAvanzado(Taboleiro taboleiro, Integer[] integer) {

    }

}
