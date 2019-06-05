package cadros;

import java.util.ArrayList;

public final class Grupo {

    private ArrayList<Solar> solares;
    private String nomeGrupo;
    private Double prezoCadro;
    private Double hipotecaCadro;
    private Double prezoCasa;
    private Double prezoHotel;
    private Double prezoPiscina;
    private Double prezoPista;
    private Double aluguerSolar;

    public Grupo(String nomeGrupo){

        this.nomeGrupo = nomeGrupo;
        this.solares = new ArrayList<>();

    }

    /* getters */
    public ArrayList<Solar> getSolares() {
        return solares;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    /* setters */
    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    public void setSolares(ArrayList<Solar> solares) {
        this.solares = solares;
    }


}
