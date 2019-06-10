package cadros;

import cartas.Carta;
import xogadores.Xogador;
import xogo.Taboleiro;

import java.util.ArrayList;

public final class Sorte extends Accion {

    public Sorte(String id, String nome, ArrayList<Carta> cartas){

        this.setId(id);
        this.setNome(nome);
        this.setCartas(cartas);

    }

    @Override
    public void accion(Taboleiro taboleiro, Xogador xogador) {

    }
}
