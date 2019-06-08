package cadros;

import cartas.Carta;

import java.util.ArrayList;

public final class Sorte extends Accion {

    public Sorte(String id, String nome, ArrayList<Carta> cartas){

        this.setId(id);
        this.setNome(nome);
        this.setCartas(cartas);

    }

}
