package avatares;

import xogadores.Xogador;

public final class Sombreiro extends Avatar {

    public Sombreiro(String id, Xogador xogador){

        this.setId(id);
        this.setNome("Sombreiro");
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
