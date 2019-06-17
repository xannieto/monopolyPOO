package cadros;

import cartas.Carta;
import excepcions.HipotecaExcepcion;
import xogadores.Xogador;
import xogo.Taboleiro;

import java.util.ArrayList;
import java.util.HashMap;

public final class Sorte extends Accion {

    private static ArrayList<Carta> monteDeCartas;
    private Carta ultimaEscollida;

    public Sorte(String id, String nome, ArrayList<Carta> cartas){

        this.setId(id);
        this.setNome(nome);
        this.setCartas(cartas);

    }

    /* getters */
    public Carta getUltimaEscollida() {
        return ultimaEscollida;
    }

    /* setters */
    private static void setMonteDeCartas(HashMap<String, Carta> montoDeCartas) {
        monteDeCartas = monteDeCartas;
    }

    public void setUltimaEscollida(Carta ultimaEscollida) {
        this.ultimaEscollida = ultimaEscollida;
    }

    /* metodos */

    private ArrayList<Carta> barallar(){




    }

    @Override
    public void accion(Taboleiro taboleiro, Xogador xogador) throws HipotecaExcepcion {

        if (ultimaEscollida != null){





        } else {
            ultimaEscollida.accion(taboleiro,xogador);
            ultimaEscollida = null;
        }

    }
}
