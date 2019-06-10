package cadros;

import xogadores.Xogador;
import xogo.Taboleiro;

public final class Servizo extends Propiedade {

    public Servizo(String id, String nome, Double valor){

        this.setId(id);
        this.setNome(nome);
        this.setValor(valor);
        this.setHipoteca(valor*0.5);
        this.setHipotecada(false);

    }

    @Override
    public void aluguer() {



    }

    @Override
    public void accion(Taboleiro taboleiro, Xogador xogador) {

    }
}