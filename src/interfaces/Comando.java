package interfaces;

import xogadores.Xogador;
import xogo.Taboleiro;

public interface Comando {

    Integer[] lanzarDados();

    void verTaboleiro(Taboleiro taboleiro);

}
