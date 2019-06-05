package avatares;

import xogadores.Xogador;

public final class Pelota extends Avatar {

    public Pelota(Xogador xogador){

        this.xenerarId();
        this.setNome("Pelota");
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
