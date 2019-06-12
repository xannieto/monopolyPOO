package cadros;

import xogadores.Xogador;
import xogo.Taboleiro;

import java.util.ArrayList;

public final class Grupo extends Cadro {

    private ArrayList<Solar> solares;
    private Integer tamanhoGrupo;
    private Xogador propietario;
    private String cor;

    public Grupo(String idGrupo, String nomeGrupo, ArrayList<Solar> solares, String cor){

        setIdGrupo(idGrupo);
        setNomeGrupo(nomeGrupo);
        setSolares(solares);
        setTamanhoGrupo(solares.size());
        setPropietario(null);
        setCor(cor);


    }

    /* getters */
    public ArrayList<Solar> getSolares() {
        return solares;
    }

    public String getIdGrupo(){
        return super.getId();
    }

    public String getNomeGrupo() {
        return super.getNome();
    }

    public Integer getTamanhoGrupo(){return tamanhoGrupo;}

    public Xogador getPropietario() {
        return propietario;
    }

    public String getCor(){return cor;}

    /* setters */
    public void setIdGrupo(String idGrupo) {
        super.setId(idGrupo);
    }

    public void setNomeGrupo(String nomeGrupo) {
        this.setNome(nomeGrupo);
    }

    public void setSolares(ArrayList<Solar> solares) {
        this.solares = solares;
    }

    public void setTamanhoGrupo(Integer tamanhoGrupo) {
        this.tamanhoGrupo = tamanhoGrupo;
    }

    public void setPropietario(Xogador xogador){
        this.propietario = xogador;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    /* métodos */

    public void estaCompradoPorUnPropietario(){


        for (Solar solar: solares){

        }


    }

    @Override
    public void accion(Taboleiro taboleiro, Xogador xogador) {

    }
}
