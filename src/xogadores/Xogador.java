package xogadores;

import avatares.*;
import cadros.Propiedade;
import edificacions.Edificacion;
import xogo.Xogo;

import java.util.Collection;
import java.util.HashMap;

public class Xogador {

    private String nome;
    private Double fortuna;
    private Avatar avatar;
    private HashMap<String, Propiedade> propiedades;
    private HashMap<String, Edificacion> edificacions;
    private Boolean hipotecar;

    public Xogador(String nome, Double fortunaInicial, String avatar){

        this.nome = nome;
        this.fortuna = fortunaInicial;
        this.propiedades = new HashMap<>();
        this.edificacions = new HashMap<>();

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

    public HashMap<String, Edificacion> getEdificacions() {
        return edificacions;
    }

    /* setters */

    public void setFortuna(Double fortuna) {
        this.fortuna = fortuna;
    }

    /* metodos */

    public void engadirPropiedade(Propiedade propiedade){

        if (propiedade != null) propiedades.put(propiedade.getId(), propiedade);

    }

    public void cobrar(Double cantidade){

        if (cantidade > 0)  this.setFortuna(this.fortuna + cantidade);

    }

    public void pagar(Double cantidade){

        if (cantidade > this.fortuna ) {

            this.hipotecar = true;
            Xogo.getConsola().imprimir("O xogador "+this.getNome()+" non pode realizar o pagamento. Debe hipotecar propiedades ou vender edificacións");

        }

        setFortuna(this.fortuna - cantidade);
        Xogo.getConsola().imprimir("O xogador "+this.getNome()+" realiza un pagamento de "+cantidade+"€");

    }

    @Override
    public String toString() {

        StringBuilder descricion = new StringBuilder();

        descricion.append("{\n\tNome: ");descricion.append(this.getNome());descricion.append("\n\tAvatar: ");descricion.append(this.getAvatar().getId());
        descricion.append("\n\tFortuna: ");descricion.append(this.getFortuna());descricion.append("\n\tPropiedades: ");

        if (!this.getPropiedades().isEmpty()){

            Collection<Propiedade> prop = this.propiedades.values();
            descricion.append("[");

            for (Propiedade propiedade : prop){
                if (!propiedade.getHipotecada()) descricion.append(propiedade.getId());
                descricion.append(", ");
            }



        } else descricion.append("ningunha\n\tHipotecas: ningunha\n\tEdificios: ningún");

        return new String(descricion);
    }

    @Override
    public boolean equals(Object obj) {

        if (obj != null) return ((Xogador)obj).equals(this.nome);

        return false;

    }
}
