package avatares;

import cadros.Cadro;
import xogadores.Xogador;

public abstract class Avatar {

    private String id;
    private String nome;
    private Xogador xogador;
    private Cadro posicion;
    private Boolean movementoAvanzado = Boolean.FALSE;

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

    public void mover(){

        if (!movementoAvanzado) moverEnBasico();
        else moverEnAvanzado();

    }

    public abstract void moverEnBasico();

    public abstract void moverEnAvanzado();

    @Override
    public boolean equals(Object obj) {

        if (obj != null)    return ((Avatar)obj).getId().equals(this.id);

        return false;

    }
}
