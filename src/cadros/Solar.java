package cadros;

import edificacions.*;
import excepcions.ExcepcionFortunaInsuficiente;
import interfaces.Constantes;
import xogadores.Xogador;
import xogo.Taboleiro;
import xogo.Xogo;

import java.util.HashMap;

public final class Solar extends Propiedade {

    private HashMap<String,Edificacion> edificacions;
    private Grupo grupo;
    private Double valorCasa;
    private Double valorHotel;
    private Double valorPiscina;
    private Double valorPista;
    private Double aluguer1Casa;
    private Double aluguer2Casa;
    private Double aluguer3Casa;
    private Double aluguer4Casa;
    private Double aluguerHotel;
    private Double aluguerPiscina;
    private Double aluguerPista;

    public Solar(String id, String nome, Double valor){

        this.setId(id);
        this.setNome(nome);
        this.setValor(valor);
        this.setAluguer(valor*0.1);
        this.setHipotecada(false);
        this.setHipoteca(valor*0.5);
        this.edificacions = new HashMap<>();
        this.grupo = null;
        calcularValoresDerivados();

    }

    /* getters */

    public Grupo getGrupo() {
        return grupo;
    }

    public HashMap<String, Edificacion> getEdificacions() {
        return edificacions;
    }

    public Double getValorCasa() {
        return valorCasa;
    }

    public Double getValorHotel() {
        return valorHotel;
    }

    public Double getValorPiscina() {
        return valorPiscina;
    }

    public Double getValorPista() {
        return valorPista;
    }

    public Double getAluguer1Casa() {
        return aluguer1Casa;
    }

    public Double getAluguer2Casa() {
        return aluguer2Casa;
    }

    public Double getAluguer3Casa() {
        return aluguer3Casa;
    }

    public Double getAluguer4Casa() {
        return aluguer4Casa;
    }

    public Double getAluguerHotel() {
        return aluguerHotel;
    }

    public Double getAluguerPiscina() {
        return aluguerPiscina;
    }

    public Double getAluguerPista() {
        return aluguerPista;
    }

    /* setters */
    public void setEdificacions(HashMap<String,Edificacion> edificacions){
        this.edificacions = edificacions;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public void setValorCasa(Double valorCasa) {
        this.valorCasa = valorCasa;
    }

    public void setValorHotel(Double valorHotel) {
        this.valorHotel = valorHotel;
    }

    public void setValorPiscina(Double valorPiscina) {
        this.valorPiscina = valorPiscina;
    }

    public void setValorPista(Double valorPista) {
        this.valorPista = valorPista;
    }

    public void setAluguer1Casa(Double aluguer1Casa) {
        this.aluguer1Casa = aluguer1Casa;
    }

    public void setAluguer2Casa(Double aluguer2Casa) {
        this.aluguer2Casa = aluguer2Casa;
    }

    public void setAluguer3Casa(Double aluguer3Casa) {
        this.aluguer3Casa = aluguer3Casa;
    }

    public void setAluguer4Casa(Double aluguer4Casa) {
        this.aluguer4Casa = aluguer4Casa;
    }

    public void setAluguerHotel(Double aluguerHotel) {
        this.aluguerHotel = aluguerHotel;
    }

    public void setAluguerPiscina(Double aluguerPiscina) {
        this.aluguerPiscina = aluguerPiscina;
    }

    public void setAluguerPista(Double aluguerPista) {
        this.aluguerPista = aluguerPista;
    }

    /* métodos */
    private void calcularValoresDerivados(){

        valorCasa = this.getValor()*0.6;
        valorHotel = valorCasa;
        valorPiscina = this.getValor()*0.4;
        valorPista = this.getValor()*1.25;
        aluguer1Casa = this.getAluguer()*5;
        aluguer2Casa = this.getAluguer()*15;
        aluguer3Casa = this.getAluguer()*35;
        aluguer4Casa = this.getAluguer()*50;
        aluguerHotel = this.getAluguer()*70;
        aluguerPiscina = this.getAluguer()*25;
        aluguerPista = aluguerPiscina;

    }

    public void engadirEdificacion(Edificacion edificacion){

        if(edificacion != null){
            edificacions.put(edificacion.getId(),edificacion);
        }

    }

    public void quitarEdificacion(String edificacion){

        if(!edificacion.isEmpty()){
            edificacions.remove(edificacion);
        }

    }

    @Override
    public String toString() {
        StringBuilder descricion = new StringBuilder();

        descricion.append(String.format("{\n\ttipo: solar,\n\tgrupo: %s,\n\tvalor: %.2f,",this.grupo.getNomeGrupo(),this.getValor()));
        descricion.append(String.format("\n\taluguer: %.2f,\n\tvalor casa: %.2f,\n\tvalor hotel: %.2f,\n\tvalor piscina: %.2f,\n\tvalor pista de deporte: %.2f,",
                this.getAluguer(),this.getValorCasa(),this.getValorHotel(),this.getValorPiscina(),this.getValorPista()));
        descricion.append(String.format("\n\taluguer dunha casa: %.2f,\n\taluguer de dúas casas: %.2f,\n\taluguer de tres casas: %.2f,\n\taluguer de catro casas: %.2f,",
                this.getAluguer1Casa(),this.getAluguer2Casa(),this.getAluguer3Casa(),this.getAluguer3Casa(),this.getAluguer4Casa()));
        descricion.append(String.format("\n\taluguer hotel: %.2f,\n\taluguer piscina: %.2f,\n\taluguer pista de deporte: %.2f\n}",
                this.getAluguerHotel(),this.getAluguerPiscina(),this.getAluguerPista()));

        return descricion.toString();
    }

    @Override
    public void aluguer(Taboleiro taboleiro) {

        if (this.getPropietario() != null){

            Double aluguer = 0.0;

            aluguer += Constantes.valoresSolares.get(this.getId())*0.1;

            if (edificacions != null){

                Integer casas = 0, hoteis = 0, piscinas = 0, pistas = 0 ;

                for (Edificacion edificacion: this.edificacions.values()){
                    if (edificacion instanceof Casa)    casas++;
                    else if (edificacion instanceof Hotel)   hoteis++;
                    else if (edificacion instanceof Piscina) piscinas++;
                    else if (edificacion instanceof PistaDeporte) pistas++;
                }

                if (casas == 1) aluguer += aluguer1Casa;
                else if (casas == 2) aluguer += aluguer1Casa + aluguer2Casa;
                else if (casas == 3) aluguer += aluguer1Casa + aluguer2Casa + aluguer3Casa;
                else if (casas == 4) aluguer += aluguer1Casa + aluguer2Casa + aluguer4Casa;

                if (hoteis == 1)    aluguer += aluguerHotel;
                else if (hoteis == 2)    aluguer += 2*aluguerHotel;

                if (piscinas == 1)  aluguer += aluguerPiscina;
                else if (piscinas == 2)  aluguer += 2*aluguerPiscina;

                if (pistas == 1)    aluguer += aluguerPista;
                else if (pistas == 2)    aluguer += 2*aluguerPista;

            }

            if (this.getGrupo().getPropietario() != null)   aluguer *= 2;

            this.setAluguer(aluguer);

        } else{
            this.setAluguer(Constantes.valoresSolares.get(this.getId())*0.1);
        }

    }

    @Override
    public void accion(Taboleiro taboleiro, Xogador xogador) {

        if (this.getPropietario()!=null){

            if (!this.getPropietario().equals(xogador) && this.getPropietario() != null && !this.getHipotecada()){

                try {
                    xogador.pagar(this.getAluguer());
                    xogador.cobrar(this.getAluguer());

                } catch (ExcepcionFortunaInsuficiente e){
                    Xogo.getConsola().imprimir(e.getMessage());
                }

            }

        }

    }

    public void edificar(Taboleiro taboleiro, String edificio){



    }

    @Override
    public String informacionVendaBasica() {

        StringBuilder informacion = new StringBuilder();

        informacion.append(String.format("{\n\ttipo: solar,\n\tgrupo: %s,\n\tvalor: %.2f\n}",this.grupo.getNomeGrupo(),this.getValor()));

        return informacion.toString();
    }
}
