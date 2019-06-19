package xogadores;

import avatares.*;
import cadros.Propiedade;
import cadros.Solar;
import edificacions.Edificacion;
import excepcions.FortunaInsuficienteExcepcion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Xogador {

    private String nome;
    private Double fortuna;
    private Avatar avatar;
    private HashMap<String, Propiedade> propiedades;
    private Boolean hipotecar;
    private Double debeda;

    private Double dinheiroInvestido;
    private Double pagoDeAlugueres;
    private Double cobroDeAlugueres;
    private Double pagoDeImpostos;
    private Double cobroDeSaida;
    private Double premiosInversionsBote;
    private Integer vecesEnPrision;


    public Xogador(String nome, Double fortunaInicial, String avatar){

        this.nome = nome;
        this.fortuna = fortunaInicial;
        this.propiedades = new HashMap<>();
        this.debeda = 0.0;

        this.dinheiroInvestido = 0.0;
        this.pagoDeAlugueres = 0.0;
        this.cobroDeAlugueres = 0.0;
        this.pagoDeImpostos = 0.0;
        this.cobroDeSaida = 0.0;
        this.premiosInversionsBote = 0.0;
        this.vecesEnPrision = 0;
        this.hipotecar = false;

        switch (avatar){

            case "coche":
                this.avatar = new Coche(this);
                break;

            case "pelota":
                this.avatar = new Pelota(this);
                break;

            case "sombreiro":
                this.avatar = new Sombreiro(this);
                break;

            case "esfinxe":
                this.avatar = new Esfinxe(this);
                break;

        }

    }


    /* getters */

    public String getNome() {
        return nome;
    }

    public Double getFortuna() {
        return fortuna;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public HashMap<String, Propiedade> getPropiedades() {
        return propiedades;
    }

    public Double getDebeda() {
        return debeda;
    }

    public Boolean getHipotecar() {
        return hipotecar;
    }

    public Double getDinheiroInvestido() {
        return dinheiroInvestido;
    }

    public Double getPagoDeAlugueres() {
        return pagoDeAlugueres;
    }

    public Double getCobroDeAlugueres() {
        return cobroDeAlugueres;
    }

    public Double getPagoDeImpostos() {
        return pagoDeImpostos;
    }

    public Double getCobroDeSaida() {
        return cobroDeSaida;
    }

    public Double getPremiosInversionsBote() {
        return premiosInversionsBote;
    }

    public Integer getVecesEnPrision() {
        return vecesEnPrision;
    }

    /* setters */

    public void setFortuna(Double fortuna) {
        this.fortuna = fortuna;
    }

    public void setDebeda(Double debeda) {
        this.debeda = debeda;
    }

    public void setHipotecar(Boolean hipotecar) {
        this.hipotecar = hipotecar;
    }

    /* metodos */

    public void incrementarDinheiroInvestido(Double cantidade){

        if (cantidade > 0)  this.dinheiroInvestido += cantidade;

    }

    public void incrementarCobroDeAluguere(Double cantidade){

        if (cantidade > 0)  this.cobroDeAlugueres += cantidade;

    }

    public void incrementarPagoDeAlugueres(Double cantidade){

        if (cantidade > 0)  this.pagoDeAlugueres += cantidade;

    }

    public void incrementarCobroDeSaida(Double cantidade){

        if (cantidade > 0)  this.cobroDeSaida += cantidade;
    }


    public void incrementarPagoDeImpostos(Double cantidade){

        if (cantidade > 0)  this.pagoDeImpostos += cantidade;
    }


    public void incrementarPremiosInversionsBote(Double cantidade){

        if (cantidade > 0)  this.premiosInversionsBote += cantidade;
    }

    public void incrementarVecesEnPrision(){
        this.vecesEnPrision++;
    }

    public void incrementar(Double cantidade){

        if (cantidade > 0)  this.pagoDeAlugueres += cantidade;

    }

    public ArrayList<Edificacion> obterEdificacions(){

        if (!propiedades.isEmpty()){

            ArrayList<Edificacion> edificacions = new ArrayList<>();

            for (Propiedade propiedade: propiedades.values()){
                if (propiedade instanceof Solar){
                    Collection<Edificacion> edif = ((Solar) propiedade).getEdificacions().values();
                    edificacions.addAll(edif);
                }

            }

            return edificacions;
        }

        return null;
    }

    public void engadirPropiedade(Propiedade propiedade){

        if (propiedade != null) propiedades.put(propiedade.getId(), propiedade);

    }

    public void cobrar(Double cantidade){

        if (cantidade > 0)  this.setFortuna(this.fortuna + cantidade);

    }

    public void pagar(Double cantidade) throws FortunaInsuficienteExcepcion {

        if (cantidade > this.fortuna ) {

            this.hipotecar = true;
            throw new FortunaInsuficienteExcepcion(String.format("O xogador %s non pode realizar o pago. Deberá buscar financiamento (hipotecas, vendas, tratos).",this.getNome()));

        } else {
            setFortuna(this.fortuna - cantidade);
            //Xogo.getConsola().imprimir(String.format("O xogador %s realiza un pagamento de %.2f€",this.getNome(),cantidade));
        }

    }

    public String estatiscas(){

        StringBuilder descricion = new StringBuilder();

        descricion.append(String.format("{\n\tdiñeiro investido: %.2f€,\n\tpago de alugueres: %.2f€,\n\tcobro de alugueres: %.2f,\n\tpago de taxas e impostos: %.2f€,\n\tcobros por pasar pola saída: %.2f€,\n\tpremios por inversion ou bote: %.2f€,\n\tveces en prisión: %d\n}",
                this.dinheiroInvestido, this.pagoDeAlugueres,this.cobroDeAlugueres, this.pagoDeImpostos, this.cobroDeSaida,this.premiosInversionsBote, this.vecesEnPrision));

        return descricion.toString();
    }

    public Double riqueza(){

        Double riqueza = 0.0;

        for (Propiedade propiedade: propiedades.values()){
            if (!propiedade.getHipotecada()){
                riqueza += propiedade.getValor();

                if (propiedade instanceof Solar){
                    if (!((Solar) propiedade).getEdificacions().isEmpty()){



                    }
                }
            }

        }

        return riqueza;
    }

    @Override
    public String toString() {

        StringBuilder descricion = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        StringBuilder temp2 = new StringBuilder();

        descricion.append(String.format("{\n\tNome: %s",this.nome));
        descricion.append(String.format("\n\tAvatar: %s",this.getAvatar().getId()));
        descricion.append(String.format("\n\tFortuna: %.2f€",this.getFortuna()));
        descricion.append("\n\tPropiedades: ");

        if (!this.getPropiedades().isEmpty()){

            Collection<Propiedade> prop = this.propiedades.values();

            temp2.append("[");
            temp.append("[");

            for (Propiedade propiedade : prop){
                if (!propiedade.getHipotecada()){
                    temp2.append(propiedade.getId());
                    temp2.append(", ");
                } else {
                    temp.append(String.format("%s, ",propiedade.getId()));
                }
            }

            if (temp2.length()>1)   temp.replace(temp.lastIndexOf(","),temp.lastIndexOf(" "),"]");
            else {
                temp2.setLength(0); temp2.append("ningunha");
            }

            descricion.append(temp2);

            if (temp.length()>1)    temp.replace(temp.lastIndexOf(","),temp.lastIndexOf(" "),"]");
            else {
                temp.setLength(0);  temp.append("ningunha");
            }

            descricion.append(String.format("\n\tHipotecas: %s",temp));
            temp.setLength(0);

            descricion.append("\n\tEdificacións: ");
            temp.append("[");
            for (Edificacion edificacion: obterEdificacions())
                temp.append(String.format("%s, ",edificacion.getId()));

            if (temp.length()>1)    temp.replace(temp.lastIndexOf(","),temp.lastIndexOf(" "),"]");
            else {
                descricion.append("ningunha\n}"); temp.setLength(0);
            }

        } else descricion.append("ningunha\n\tHipotecas: ningunha\n\tEdificios: ningún\n}");

        return new String(descricion);
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Xogador) return ((Xogador) obj).getNome().equals(this.nome);

        return false;

    }

}
