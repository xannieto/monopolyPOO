package cadros;

public final class Imposto extends Cadro {

    private Double imposto;

    public Imposto(String id, String nome, Double imposto){

        super.setId(id);
        super.setNome(nome);

    }

    /* getters */

    public Double getImposto() {
        return imposto;
    }

    /* setters */
    public void setImposto(Double imposto){

        if (imposto < 1)    this.imposto = imposto;

    }

    /* metodos */


}
