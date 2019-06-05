package cadros;

import java.util.ArrayList;

public final class Grupo {

    private ArrayList<Solar> solares;
    private String nomeGrupo;
    private Integer limiteEdificacions;
    private Double prezoCadro;
    private Double hipotecaCadro;
    private Double prezoCasa;
    private Double prezoHotel;
    private Double prezoPiscina;
    private Double prezoPista;
    private Double aluguerSolar;
    private Double aluguerHotel;

    public Grupo(String nomeGrupo, ArrayList<Solar> solares){

        setNomeGrupo(nomeGrupo);
        setSolares(solares);
        setLimiteEdificacions(solares.size());

    }

    /* getters */
    public ArrayList<Solar> getSolares() {
        return solares;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public Integer getLimiteEdificacions(){return limiteEdificacions;}

    public Double getPrezoCadro() {
        return prezoCadro;
    }

    public Double getHipotecaCadro() {
        return hipotecaCadro;
    }

    public Double getPrezoCasa() {
        return prezoCasa;
    }

    public Double getPrezoHotel() {
        return prezoHotel;
    }

    public Double getPrezoPiscina() {
        return prezoPiscina;
    }

    public Double getPrezoPista() {
        return prezoPista;
    }

    public Double getAluguerSolar() {
        return aluguerSolar;
    }

    public Double getAluguerHotel() {
        return aluguerHotel;
    }

    /* setters */
    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    public void setSolares(ArrayList<Solar> solares) {
        this.solares = solares;
    }

    public void setLimiteEdificacions(Integer limiteEdificacions) {
        this.limiteEdificacions = limiteEdificacions;
    }

    public void setPrezoCadro(Double prezoCadro) {
        this.prezoCadro = prezoCadro;
    }

    public void setHipotecaCadro(Double hipotecaCadro) {
        this.hipotecaCadro = hipotecaCadro;
    }

    public void setPrezoCasa(Double prezoCasa) {
        this.prezoCasa = prezoCasa;
    }

    public void setPrezoHotel(Double prezoHotel) {
        this.prezoHotel = prezoHotel;
    }

    public void setPrezoPiscina(Double prezoPiscina) {
        this.prezoPiscina = prezoPiscina;
    }

    public void setPrezoPista(Double prezoPista) {
        this.prezoPista = prezoPista;
    }

    public void setAluguerSolar(Double aluguerSolar) {
        this.aluguerSolar = aluguerSolar;
    }

    public void setAluguerHotel(Double aluguerHotel) {
        this.aluguerHotel = aluguerHotel;
    }
}
