package interfaces;

import xogo.Taboleiro;

public interface Comando {

    void comprarCadro(String id);
    void describirAvatar(String id);
    void describirCadro(String id);
    void describirXogador(String nome);
    void edificar(String edificacion);
    void lanzarDados();
    void listarAvatares();
    void listarAVenda();
    void listarEdificios();
    void listarEdificiosGrupo(String idGrupo);
    void listarXogadores();
    void sairCarcere();
    void verTaboleiro(Taboleiro taboleiro);

}
