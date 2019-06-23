package cadros;

import edificacions.*;
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

        Xogador xogador = this.solares.get(0).getPropietario();

            if (xogador != null) {

                for (int i = 1; i < this.solares.size(); i++)
                    if (!this.solares.get(i).pertenceAXogador(xogador)) return false;

                this.setPropietario(xogador);
                return true;

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

        informacion.append(String.format("Pódense construír:\n\tCasas: %d\n\tHoteis: %d\n\tPiscinas: %d\n\tPistas de deporte: %d",
                tamanhoGrupo-casas,tamanhoGrupo-hoteis,tamanhoGrupo-piscinas,tamanhoGrupo-pistas));


        return informacion.toString();
    }

    @Override
    public void accion(Taboleiro taboleiro, Xogador xogador) {

    }



    @Override
    public String toString() {
        StringBuilder informacion = new StringBuilder();
        StringBuilder casas = new StringBuilder();
        StringBuilder hoteis = new StringBuilder();
        StringBuilder piscinas = new StringBuilder();
        StringBuilder pistas = new StringBuilder();

        for (Solar solar: this.getSolares()){
            informacion.append(String.format("{\n\tpropiedade: %s (%s),",solar.getNome(),solar.getId()));

            casas.append("["); hoteis.append("["); piscinas.append("["); pistas.append("[");
            for (Edificacion edificacion: solar.getEdificacions().values()){
                if (edificacion instanceof Casa)    casas.append(String.format("%s, ",edificacion.getId()));
                else if (edificacion instanceof Hotel)  hoteis.append(String.format("%s, ",edificacion.getId()));
                else if (edificacion instanceof Piscina)  piscinas.append(String.format("%s, ",edificacion.getId()));
                else if (edificacion instanceof PistaDeporte)  pistas.append(String.format("%s, ",edificacion.getId()));
            }

            if (casas.length()>1)   casas.replace(casas.lastIndexOf(","),casas.lastIndexOf(" "),"]");
            else{
                casas.setLength(0); casas.append("ningunha");
            }

            if (hoteis.length()>1)   hoteis.replace(hoteis.lastIndexOf(","),hoteis.lastIndexOf(" "),"]");
            else{
                hoteis.setLength(0); hoteis.append("ningún");
            }

            if (piscinas.length()>1)   piscinas.replace(piscinas.lastIndexOf(","),piscinas.lastIndexOf(" "),"]");
            else{
                piscinas.setLength(0); piscinas.append("ningunha");
            }

            if (pistas.length()>1)   pistas.replace(pistas.lastIndexOf(","),pistas.lastIndexOf(" "),"]");
            else{
                pistas.setLength(0); pistas.append("ningunha");
            }

            informacion.append(String.format("\n\thoteis: %s,\n\tcasas: %s,\n\tpiscinas: %s,\n\tpistas de deporte: %s,\n\taluguer: %.2f€\n}\n",
                    hoteis,casas,piscinas,pistas,solar.getAluguer()));

            casas.setLength(0);hoteis.setLength(0);piscinas.setLength(0);pistas.setLength(0);
        }

        casas.setLength(0);hoteis.setLength(0);piscinas.setLength(0);pistas.setLength(0);

        return informacion.toString();
    }
}
