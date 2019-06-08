package cadros;

public final class Especial extends Cadro {

    private Double valor;

    public Especial(String id, String nome, Double valor){

        super.setId(id);
        super.setNome(nome);

    }

    /* getters */

    public Double getValor() {
        return valor;
    }

    /* setters */

    public void setValor(Double valor) {

        if (valor < 0) this.valor = valor;

    }

    /* metodos */

    @Override
    public String toString() {

        String descricion = null;

        switch (this.getId()){

            case "saida":
                descricion = "{\n"+"Cobro por volta:"+this.valor+"\n}";
                break;

            case "carcere":
                descricion = "{\n"+"Fianza:"+this.valor+"\n}";
                break;

            case "irCarcere":
                descricion = "{\n"+"Atallo ao cárcere"+"\n}";
                break;

            case "aparcamento":
                descricion = "{\n"+"Bote: "+this.valor+"\n}";
                break;

        }

        return descricion;
    }
}
