package avatares;

import xogadores.Xogador;
import xogo.Taboleiro;

public final class Pelota extends Avatar {

    public Pelota(Xogador xogador){

        this.xenerarId();
        this.setNome("Pelota");
        this.setXogador(xogador);
        this.setPosicion(null);
        this.setVoltasDadas(0);
        this.setMovementoAvanzado(Boolean.FALSE);
        this.setSacarDobres(false);
        this.setVecesDobres(0);
        this.setCarcere(false);
        this.setQuendasPrision(0);
        this.setCobrarSaida(false);
        this.setUltimoAvance(0);
        this.setVecesDadosLanzados();

    }


    @Override
    public void moverEnAvanzado(Taboleiro taboleiro, Integer[] integer) {

    }


}
