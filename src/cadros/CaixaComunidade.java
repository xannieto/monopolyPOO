package cadros;

import cartas.Carta;
import xogadores.Xogador;
import xogo.Taboleiro;

import java.util.ArrayList;
import java.util.HashMap;

public final class CaixaComunidade extends Accion {

    //private static HashMap<String,Carta> montoDeCartas;

    public CaixaComunidade(String id, String nome, ArrayList<Carta> cartas){

        this.setId(id);
        this.setCartas(cartas);
        this.setNome(nome);

    }

    @Override
    public void accion(Taboleiro taboleiro, Xogador xogador) {

    }


}
