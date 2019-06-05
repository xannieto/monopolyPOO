package avatares;

import xogadores.Xogador;

public final class Coche extends Avatar {



    public Coche(Xogador xogador){

        this.xenerarId();
        this.setNome("Coche");
        this.setXogador(xogador);
        this.setPosicion(null);
        this.setVoltasDadas(0);
        this.setMovementoAvanzado(Boolean.FALSE);

    }


    /* métodos */

    @Override
    public void moverEnBasico() {

    }

    @Override
    public void moverEnAvanzado() {

    }

}
