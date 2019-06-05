package cadros;

import xogadores.Xogador;

public class Propiedade extends Cadro {

    private Xogador propietario;
    private Double aluguer;
    private Double valor;
    private Double hipoteca;

    public Propiedade(String id, String nome){

        super.setId(id);
        super.setNome(nome);

    }

    public void aluguer(){

        this.aluguer = Double.valueOf(0);

    }

    public void comprar(Xogador xogador){
        this.propietario = xogador;
    }


    public boolean pertenceAXogador(Xogador xogador){

        if (xogador != null)   return xogador.equals(this.propietario);

        return false;

    }


}
