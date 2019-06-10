package cadros;

import xogadores.Xogador;
import xogo.Taboleiro;

public final class Transporte extends Propiedade {



    public Transporte(String id, String nome, Double valor){

        this.setId(id);
        this.setNome(nome);
        this.setValor(valor);
        this.setAluguer(valor);
        this.setHipotecada(false);
        this.setHipoteca(valor*0.5);

    }

    @Override
    public void aluguer() {

    }

    @Override
    public void accion(Taboleiro taboleiro, Xogador xogador) {

    }
}