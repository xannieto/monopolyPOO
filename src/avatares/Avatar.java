package avatares;

import xogadores.Xogador;

public abstract class Avatar {

    private String id;
    private String nome;
    private Xogador xogador;

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

    /* setters */
    public void setNome(String nome){
        this.nome = nome;
    }

    public void setXogador(Xogador xogador){
        this.xogador = xogador;
    }

    public void setId(){

    }

    public abstract void moverEnBasico();

    public abstract void moverEnAvanzado();

}
