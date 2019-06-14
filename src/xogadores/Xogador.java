package xogadores;

import avatares.*;
import cadros.Propiedade;
import cadros.Solar;
import edificacions.Edificacion;
import excepcions.FortunaInsuficienteExcepcion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Xogador {

    private String nome;
    private Double fortuna;
    private Avatar avatar;
    private HashMap<String, Propiedade> propiedades;
    private Boolean hipotecar;

    public Xogador(String nome, Double fortunaInicial, String avatar){

        this.nome = nome;
        this.fortuna = fortunaInicial;
        this.propiedades = new HashMap<>();

        switch (avatar){

            case "coche":
                this.avatar = new Coche(this);
                break;

            case "pelota":
                this.avatar = new Pelota(this);
                break;

            case "sombreiro":
                this.avatar = new Sombreiro(this);
                break;

            case "esfinxe":
                this.avatar = new Esfinxe(this);
                break;

        }

    }


    /* getters */

    public String getNome() {
        return nome;
    }

    public Double getFortuna() {
        return fortuna;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public HashMap<String, Propiedade> getPropiedades() {
        return propiedades;
    }


    /* setters */

    public void setFortuna(Double fortuna) {
        this.fortuna = fortuna;
    }

    /* metodos */

    public ArrayList<Edificacion> obterEdificacions(){

        if (!propiedades.isEmpty()){

            ArrayList<Edificacion> edificacions = new ArrayList<>();

            for (Propiedade propiedade: propiedades.values()){
                if (propiedade instanceof Solar){
                    Collection<Edificacion> edif = ((Solar) propiedade).getEdificacions().values();
                    edificacions.addAll(edif);
                }

            }

            return edificacions;
        }

        return null;
    }

    public void engadirPropiedade(Propiedade propiedade){

        if (propiedade != null) propiedades.put(propiedade.getId(), propiedade);

    }

    public void cobrar(Double cantidade){

        if (cantidade > 0)  this.setFortuna(this.fortuna + cantidade);

    }

    public void pagar(Double cantidade) throws FortunaInsuficienteExcepcion {

        if (cantidade > this.fortuna ) {

            this.hipotecar = true;
            throw new FortunaInsuficienteExcepcion(String.format("O xogador %s non pode realizar o pago. Deberá buscar financiamento (hipotecas, vendas, tratos).",this.getNome()));

        } else {
            setFortuna(this.fortuna - cantidade);
            //Xogo.getConsola().imprimir(String.format("O xogador %s realiza un pagamento de %.2f€",this.getNome(),cantidade));
        }

    }

    @Override
    public String toString() {

        StringBuilder descricion = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        descricion.append(String.format("{\n\tNome: %s",this.nome));
        descricion.append(String.format("\n\tAvatar: %s",this.getAvatar().getId()));
        descricion.append(String.format("\n\tFortuna: %.2f€",this.getFortuna()));
        descricion.append("\n\tPropiedades: ");

        if (!this.getPropiedades().isEmpty()){

            Collection<Propiedade> prop = this.propiedades.values();

            descricion.append("[");
            temp.append("[");

            for (Propiedade propiedade : prop){
                if (!propiedade.getHipotecada()){
                    descricion.append(propiedade.getId());
                    descricion.append(", ");
                } else {
                    temp.append(String.format("%s, ",propiedade.getId()));
                }
            }
            descricion.replace(descricion.lastIndexOf(","),descricion.lastIndexOf(" "),"]");

            if (temp.length()>1)    temp.replace(temp.lastIndexOf(","),temp.lastIndexOf(" "),"]");
            else {
                temp.setLength(0);  temp.append("ningunha");
            }

            descricion.append(String.format("\n\tHipotecas: %s",temp));
            temp.setLength(0);

            descricion.append("\n\tEdificacións: ");
            temp.append("[");
            for (Edificacion edificacion: obterEdificacions())
                temp.append(String.format("%s, ",edificacion.getId()));

            if (temp.length()>1)    temp.replace(temp.lastIndexOf(","),temp.lastIndexOf(" "),"]");
            else {
                descricion.append("ningunha\n}"); temp.setLength(0);
            }

        } else descricion.append("ningunha\n\tHipotecas: ningunha\n\tEdificios: ningún\n}");

        return new String(descricion);
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Xogador) return obj.equals(this.nome);

        return false;

    }

}
