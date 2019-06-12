package cadros;

import xogadores.Xogador;
import xogo.Taboleiro;

public abstract class Propiedade extends Cadro {

    private Xogador propietario;
    private Double aluguer;
    private Double valor;
    private Double hipoteca;
    private Boolean hipotecada;

    /* getters */

    public Xogador getPropietario() {
        return propietario;
    }

    public Double getAluguer() {
        return aluguer;
    }

    public Double getValor() {
        return valor;
    }

    public Double getHipoteca() {
        return hipoteca;
    }

    public Boolean getHipotecada() {
        return hipotecada;
    }

    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public String getNome() {
        return super.getNome();
    }

    /* setters */

    public void setPropietario(Xogador propietario) {
        this.propietario = propietario;
    }

    public void setAluguer(Double aluguer) {
        this.aluguer = aluguer;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setHipoteca(Double hipoteca) {
        this.hipoteca = hipoteca;
    }

    public void setHipotecada(Boolean hipotecada) {
        this.hipotecada = hipotecada;
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }

    @Override
    public void setNome(String nome) {
        super.setNome(nome);
    }

    /* metodos */

    public abstract void aluguer(Taboleiro taboleiro);

    public void comprar(Xogador xogador){
        if(this.getPropietario() == null)
            this.propietario = xogador;
    }

    public abstract String informacionVendaBasica();

    public boolean pertenceAXogador(Xogador xogador){

        if (xogador != null)   return xogador.equals(this.propietario);

        return false;

    }


    public void valor(){

        this.setValor(this.getValor()*1.05);

    }


}
