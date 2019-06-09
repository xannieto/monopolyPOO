package interfaces;

import xogadores.Xogador;
import xogo.Taboleiro;

public interface Comando {

    void lanzarDados(Xogador xogador);

    void verTaboleiro(Taboleiro taboleiro);

}
