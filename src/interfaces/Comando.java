package interfaces;

import xogo.Taboleiro;

public interface Comando {

    void acabarQuenda();
    void comprarCadro(String id);
    void describirAvatar(String id);
    void describirCadro(String id);
    void describirXogador(String nome);
    void deshipotecar(String id);
    void edificar(String edificacion);
    void estatisticas();
    void estatisticasXogador(String id);
    void hipotecar(String id);
    void lanzarDados();
    void listarAvatares();
    void listarAVenda();
    void listarEdificios();
    void listarEdificiosGrupo(String idGrupo);
    void listarXogadores();
    void sairCarcere();
    void venderEdificios(String edificacion, String solar, Integer cantidade);
    void verTaboleiro(Taboleiro taboleiro);

}
