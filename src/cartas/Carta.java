package cartas;

import xogadores.Xogador;
import xogo.Taboleiro;

public abstract class Carta {

    private String textoCarta;
    private String cadroDestino;
    private Double valor;
    private Boolean pagar;
    private Boolean cobrar;

    public abstract void accion(Taboleiro taboleiro, Xogador xogador);

    /* setter */
    public void setTextoCarta(String textoCarta) {
        this.textoCarta = textoCarta;
    }

    public void setCadroDestino(String cadroDestino) {
        this.cadroDestino = cadroDestino;
    }

    public void setPagar(Boolean pagar) {
        this.pagar = pagar;
    }

    public void setCobrar(Boolean cobrar) {
        this.cobrar = cobrar;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    /* getters */

    public String getTextoCarta() {
        return textoCarta;
    }
    public String getCadroDestino() {
        return cadroDestino;
    }

    public Boolean getPagar() {
        return pagar;
    }

    public Boolean getCobrar() {
        return cobrar;
    }

    public Double getValor() {
        return valor;
    }
}
