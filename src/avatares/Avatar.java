package avatares;

import cadros.Cadro;
import xogadores.Xogador;

import java.security.SecureRandom;
import java.util.Random;

public abstract class Avatar {

    private String id;
    private String nome;
    private Xogador xogador;
    private Cadro posicion;
    private Boolean movementoAvanzado;
    private Integer voltasDadas;

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
    /* métodos */

    public void mover(){

        if (!movementoAvanzado) moverEnBasico();
        else moverEnAvanzado();

    }

    public void xenerarId(){

        Boolean finalizado = Boolean.FALSE;

        while (!finalizado){

            Integer indice = null;
            Character id = null;
            SecureRandom numAleatorio = new SecureRandom();




        }

    }

    public abstract void moverEnBasico();

    public abstract void moverEnAvanzado();

    @Override
    public boolean equals(Object obj) {

        if (obj != null)    return ((Avatar)obj).getId().equals(this.id);

        return false;

    }
}
