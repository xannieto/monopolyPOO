package avatares;

import xogadores.Xogador;
import xogo.Taboleiro;

public final class Esfinxe extends Avatar {

    public Esfinxe(Xogador xogador){

        this.xenerarId();
        this.setNome("Esfinxe");
        this.setXogador(xogador);
        this.setPosicion(null);
        this.setVoltasDadas(0);
        this.setMovementoAvanzado(Boolean.FALSE);
        this.setSacarDobres(false);
        this.setVecesDobres(0);
        this.setCarcere(false);
        this.setQuendasPrision(0);

    }


    @Override
    public void moverEnAvanzado(Taboleiro taboleiro, Integer[] integer) {

    }

}
