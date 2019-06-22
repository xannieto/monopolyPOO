package cadros;

import excepcions.FortunaInsuficienteExcepcion;
import excepcions.HipotecaExcepcion;
import xogadores.Xogador;
import xogo.Taboleiro;
import xogo.Xogo;

public abstract class Propiedade extends Cadro {

    private Xogador propietario;
    private Double aluguer;
    private Double valor;
    private Double hipoteca;
    private Boolean hipotecada;
    private Double alugueresCobrados;

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

    public Double getAlugueresCobrados() {
        return alugueresCobrados;
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

    public void setAlugueresCobrados(Double alugueresCobrados) {

        if (this.alugueresCobrados != null)  this.alugueresCobrados += alugueresCobrados;
        else this.alugueresCobrados = alugueresCobrados;

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
        if(this.getPropietario() == null) {
            setPropietario(xogador);
            xogador.incrementarDinheiroInvestido(getValor());
        }

    }

    public abstract String informacionVendaBasica();

    public Boolean pertenceAXogador(Xogador xogador){

        if (xogador != null)   return xogador.equals(this.propietario);

        return false;

    }

    public void hipotecarPropiedade() throws HipotecaExcepcion {

        if (this.hipotecada)
            throw new HipotecaExcepcion(String.format("%s, a propiedade %s (%s) xa está hipotecada.",this.propietario,this.getNome(),this.getId()));

        else {
            this.propietario.cobrar(this.hipoteca);
            setHipotecada(true);
            Xogo.getConsola().imprimir(String.format("%s recibe %.2f€ pola hipoteca de %s. Non pode cobrar ningún aluguer.",
                    this.propietario.getNome(),this.hipoteca,this.getNome()));
        }

    }

    public void deshipotecarPropiedade() throws HipotecaExcepcion {

        if (!this.hipotecada)
            throw new HipotecaExcepcion(String.format("%s, a propiedade %s (%s) xa está deshipotecada.",this.propietario,this.getNome(),this.getId()));

        else{
            try {
                this.propietario.pagar(this.hipoteca*1.1);
                this.setHipotecada(false);
                Xogo.getConsola().imprimir(String.format("%s paga %.2f€ pola hipoteca de %s. Podo volver a cobrar o aluguer.",
                        this.propietario.getNome(),this.hipoteca*1.1,this.getNome()));
            } catch (FortunaInsuficienteExcepcion e){
                Xogo.getConsola().imprimir(String.format("Non se pode levar a cabo a deshipoteca de %s (%s).", this.getNome(),this.getId()));
            }

        }

    }

    public void valor(){

        this.setValor(this.getValor()*1.05);

    }


}
