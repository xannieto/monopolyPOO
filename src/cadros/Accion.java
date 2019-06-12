package cadros;

import cartas.Carta;

import java.util.ArrayList;

public abstract class Accion extends Cadro {

    public ArrayList<Carta> cartasRevoltas;

    /* getters */
    public ArrayList<Carta> getCartas() {
        return cartasRevoltas;
    }

    /* setters */
    public void setCartas(ArrayList<Carta> cartasRevoltas) {
        this.cartasRevoltas = cartasRevoltas;
    }


}
