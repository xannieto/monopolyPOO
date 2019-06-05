package avatares;

import xogadores.Xogador;

public final class Esfinxe extends Avatar {

    public Esfinxe(String id, Xogador xogador){

        this.setId(id);
        this.setNome("Esfinxe");
        this.setXogador(xogador);
        this.setPosicion(null);
        this.setVoltasDadas(0);
        this.setMovementoAvanzado(Boolean.FALSE);

    }

    @Override
    public void moverEnBasico() {

    }

    @Override
    public void moverEnAvanzado() {

    }

}
