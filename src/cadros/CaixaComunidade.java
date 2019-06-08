package cadros;

import cartas.Carta;

import java.util.ArrayList;

public final class CaixaComunidade extends Accion {


    public CaixaComunidade(String id, String nome, ArrayList<Carta> cartas){

        this.setId(id);
        this.setCartas(cartas);
        this.setNome(nome);

    }


}
