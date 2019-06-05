package avatares;

import xogadores.Xogador;

public final class Coche extends Avatar {



    public Coche(String id, Xogador xogador){

        this.setId(id);
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
