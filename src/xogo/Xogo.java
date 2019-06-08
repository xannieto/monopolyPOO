package xogo;

import interfaces.Comando;

public class Xogo implements Comando {

    private static ConsolaNormal consola;
    private Taboleiro taboleiro;


    public Xogo(){

        this.taboleiro = new Taboleiro();

    }


    /* metodos */

    @Override
    public void verTaboleiro(Taboleiro taboleiro) {

        consola.imprimir(taboleiro.toString());

    }


}
