package interfaces;

import xogadores.Xogador;
import xogo.Taboleiro;

public interface Comando {

    Integer[] lanzarDados();
    void listarXogadores();
    void listarAvatares();
    void sairCarcere();
    void describirCadro(String id);
    void describirXogador(String nome);
    void describirAvatar(String id);

    void verTaboleiro(Taboleiro taboleiro);

}
