package avatares;

import cadros.Cadro;
import xogadores.Xogador;
import xogo.Taboleiro;
import xogo.Xogo;

public abstract class Avatar {

    private String id;
    private String nome;
    private Xogador xogador;
    private Cadro posicion;
    private Boolean movementoAvanzado;
    private Integer voltasDadas;
    private Boolean sacarDobres;
    private Integer vecesDobres;
    private Boolean carcere;
    private Integer quendasPrision;

    /* getters */
    public String getNome(){
        return nome;
    }

    public Xogador getXogador(){
        return xogador;
    }

    public String getId(){
        return id;
    }

    public Cadro getPosicion(){
        return posicion;
    }

    public Boolean getAvanzado(){
        return movementoAvanzado;
    }

    public Integer getVoltasDadas(){
        return voltasDadas;
    }

    public Boolean getMovementoAvanzado() {
        return movementoAvanzado;
    }

    public Boolean getSacarDobres() {
        return sacarDobres;
    }

    public Integer getVecesDobres() {
        return vecesDobres;
    }

    public Boolean getCarcere(){
        return carcere;
    }

    /* setters */
    public void setNome(String nome){
        this.nome = nome;
    }

    public void setXogador(Xogador xogador){
        this.xogador = xogador;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setPosicion(Cadro posicion){
        this.posicion = posicion;
    }

    public void setMovementoAvanzado(Boolean movementoAvanzado){
        this.movementoAvanzado = movementoAvanzado;
    }

    public void setVoltasDadas(){

        if (voltasDadas != null)    voltasDadas++;

    }

    public void setVoltasDadas(Integer volta){

        if (volta != null)  voltasDadas = volta;

    }

    public void setSacarDobres(Boolean sacarDobres) {
        this.sacarDobres = sacarDobres;
    }

    public void setVecesDobres(Integer vecesDobres) {
        this.vecesDobres = vecesDobres;
    }

    public void setCarcere(Boolean carcere){
        this.carcere = carcere;
    }

    public void setQuendasPrision(Integer quendasPrision) {
        this.quendasPrision = quendasPrision;
    }

    /* métodos */

    public void mover(Taboleiro taboleiro, Integer[] tirada){

        if (!movementoAvanzado) moverEnBasico(taboleiro,tirada);
        else moverEnAvanzado(taboleiro,tirada);

    }

    public void xenerarId(){

        Boolean finalizado = Boolean.FALSE;

        while (!finalizado){

            Integer indice = null;
            Character id = null;

            indice = (int) (Math.random()*(90-65+1) + 65);

            if (IdAvatares.getLibre(indice)){

                id = (char) indice.intValue();

                setId(String.valueOf(id));

                IdAvatares.setNovoId(indice,id);

                finalizado = Boolean.TRUE;

            }

        }

    }

    public void moverEnBasico(Taboleiro taboleiro, Integer[] integer){

        Integer posicionAntiga, posicionNova, avance = integer[0] + integer[1];

        if (integer[0].equals(integer[1])){

            if (!this.sacarDobres) this.sacarDobres = true;
            if ((this.vecesDobres++)==3){
                this.setPosicion(taboleiro.obterCadro("carcere"));

                setCarcere(true);
                setQuendasPrision(3);
            }

            posicionAntiga = taboleiro.posicionActual(this);
            posicionNova = (posicionAntiga + avance) % 41 + 1;

            if (posicionAntiga > posicionNova)  this.setVoltasDadas();

            this.setPosicion(taboleiro.obterCadro(posicionNova));


        } else {

            posicionAntiga = taboleiro.posicionActual(this);
            posicionNova = (posicionAntiga + avance) % 41 + 1;

            if (posicionAntiga > posicionNova)  this.setVoltasDadas();

            this.setPosicion(taboleiro.obterCadro(posicionNova));
        }

        Xogo.getConsola().imprimir("O avatar "+this.getId()+" avanza "+avance+" posicións, desde "+taboleiro.obterCadro(posicionAntiga).getNome()+" até "+taboleiro.obterCadro(posicionNova).getNome()+".");

    }

    public abstract void moverEnAvanzado(Taboleiro taboleiro, Integer[] integer);

    public void resetDatosTirada(){

        this.sacarDobres = false;
        this.vecesDobres = 0;

    }

    public Boolean podeRematarQuenda(){

        return !this.sacarDobres;

    }

    @Override
    public boolean equals(Object obj) {

        if (obj != null)    return ((Avatar)obj).getId().equals(this.id);

        return false;

    }
}
