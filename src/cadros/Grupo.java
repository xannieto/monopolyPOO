package cadros;

import edificacions.Edificacion;
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

    public Boolean pertenceAXogador(Xogador xogador) {

        if (xogador != null) return xogador.equals(this.propietario);

        return false;

    }

    public Boolean estaCompradoPorUnPropietario(){

        if (this.getPropietario()!=null)    return true;
        else {
            Xogador xogador = this.solares.get(0).getPropietario();

            if (xogador != null) {

                for (int i = 1; i < this.solares.size(); i++)
                    if (!this.solares.get(i).pertenceAXogador(xogador)) return false;

                this.setPropietario(xogador);
                return true;

            }
        }
        return false;

    }

    public String queSePodeConstruir(){

        StringBuilder informacion = new StringBuilder();

        Integer casas = 0, hoteis = 0, piscinas = 0, pistas = 0;

        for (Solar solar: this.getSolares()){
            if(!solar.getEdificacions().isEmpty()){
                casas += solar.contarCasas();
                hoteis += solar.contarHoteis();
                piscinas += solar.contarPiscinas();
                pistas += solar.contarPistas();
            }
        }

        informacion.append("Pódense construír:");



        return informacion.toString();
    }

    @Override
    public void accion(Taboleiro taboleiro, Xogador xogador) {

    }
}
