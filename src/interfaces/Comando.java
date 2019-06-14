package interfaces;

import xogo.Taboleiro;

public interface Comando {

    void comprarCadro(String id);
    void describirCadro(String id);
    void describirXogador(String nome);
    void describirAvatar(String id);
    void edificar(String edificacion);
    void lanzarDados();
    void listarXogadores();
    void listarAvatares();
    void listarAVenda();
    void sairCarcere();
    void verTaboleiro(Taboleiro taboleiro);

}
