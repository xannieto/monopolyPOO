package cadros;

import cartas.Carta;

import java.util.ArrayList;

public abstract class Accion extends Cadro {

    public ArrayList<Carta> cartas;

    /* getters */
    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    /* setters */
    public void setCartas(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }


}
