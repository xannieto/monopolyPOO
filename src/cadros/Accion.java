package cadros;

import cartas.Carta;

import java.security.SecureRandom;
import java.util.ArrayList;

public abstract class Accion extends Cadro {

    private Carta ultimaEscollida;

    /* metodos */

    protected void setUltimaEscollida(Carta ultimaEscollida) {
        this.ultimaEscollida = ultimaEscollida;
    }

    protected Carta getUltimaEscollida() {
        return ultimaEscollida;
    }
}
