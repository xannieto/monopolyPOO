package cadros;

import cartas.Carta;
import xogadores.Xogador;
import xogo.Taboleiro;

import java.util.ArrayList;

public final class CaixaComunidade extends Accion {


    public CaixaComunidade(String id, String nome, ArrayList<Carta> cartas){

        this.setId(id);
        this.setCartas(cartas);
        this.setNome(nome);

    }

    @Override
    public void accion(Taboleiro taboleiro, Xogador xogador) {

    }
}
