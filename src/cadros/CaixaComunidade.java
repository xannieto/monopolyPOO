package cadros;

import cartas.Carta;
import excepcions.HipotecaExcepcion;
import xogadores.Xogador;
import xogo.Taboleiro;

import java.util.ArrayList;
import java.util.HashMap;

public final class CaixaComunidade extends Accion {

    private static HashMap<String,Carta> monteDeCartas;
    private Carta ultimaEscollida;

    public CaixaComunidade(String id, String nome, ArrayList<Carta> cartas){

        this.setId(id);
        this.setCartas(cartas);
        this.setNome(nome);

    }
    /* getters */
    public Carta getUltimaEscollida() {
        return ultimaEscollida;
    }

    /* setters */
    private static void setMonteDeCartas(HashMap<String, Carta> monteDeCartas) {
        CaixaComunidade.monteDeCartas = monteDeCartas;
    }

    public void setUltimaEscollida(Carta ultimaEscollida) {
        this.ultimaEscollida = ultimaEscollida;
    }

    @Override
    public void accion(Taboleiro taboleiro, Xogador xogador) throws HipotecaExcepcion {

    }


}
