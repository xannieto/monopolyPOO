package cadros;

import edificacions.*;
import excepcions.*;
import interfaces.Constantes;
import xogadores.Xogador;
import xogo.Taboleiro;
import xogo.Xogo;

import java.awt.*;
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
    private Integer vecesCaidas;

    public Solar(String id, String nome, Double valor){

        this.setId(id);
        this.setNome(nome);
        this.setValor(valor);
        this.setAluguer(valor*Constantes.factorAluguerSolar);
        this.setHipotecada(false);
        this.setHipoteca(valor*Constantes.factorHipoteca);
        this.vecesCaidas = 0;
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

    public Integer getVecesCaidas(){return vecesCaidas;}

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
    public void incrementarVecesCaidas(){
        if (vecesCaidas != null)
            this.vecesCaidas++;
    }

    private void calcularValoresDerivados(){

        setValorCasa(this.getValor()*Constantes.factorCasaHotel);
        setValorHotel(this.valorCasa);
        setValorPiscina(this.getValor()*Constantes.factorPiscina);
        setValorPista(this.getValor()*Constantes.factorPistaDeporte);
        setAluguer1Casa(this.getAluguer()*Constantes.factorAluguerCasa1);
        setAluguer2Casa(this.getAluguer()*Constantes.factorAluguerCasa2);
        setAluguer3Casa(this.getAluguer()*Constantes.factorAluguerCasa3);
        setAluguer4Casa(this.getAluguer()*Constantes.factorAluguerCasa4);
        setAluguerHotel(this.getAluguer()*Constantes.factorAluguerHotel);
        setAluguerPiscina(this.getAluguer()*Constantes.factorAluguerPiscinaPista);
        setAluguerPista(aluguerPiscina);

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
    public void hipotecarPropiedade() throws HipotecaExcepcion {

        if (!this.getHipotecada()){
            if (this.edificacions.isEmpty()) {
                this.setHipotecada(true);
                this.getPropietario().cobrar(this.getHipoteca());
                Xogo.getConsola().imprimir(String.format("%s recibe %.2f€ pola hipoteca de %s. Non pode cobrar ningún aluguer.",
                        this.getPropietario().getNome(),this.getHipoteca(),this.getNome()));

            } else Xogo.getConsola().imprimir("Este solar ten edificacións, deberá vendelas primeiro antes de hipotecar este solar.");
        } else throw new HipotecaExcepcion(String.format("%s, a propiedade %s (%s) xa está hipotecada.",this.getPropietario(),this.getNome(),this.getId()));
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

                switch (casas){

                    case 4:
                        aluguer += aluguer4Casa;
                    case 3:
                        aluguer += aluguer3Casa;
                    case 2:
                        aluguer += aluguer3Casa;
                    case 1:
                        aluguer += aluguer1Casa;

                }

                /*if (casas == 1) aluguer += aluguer1Casa;
                else if (casas == 2) aluguer += aluguer1Casa + aluguer2Casa;
                else if (casas == 3) aluguer += aluguer1Casa + aluguer2Casa + aluguer3Casa;
                else if (casas == 4) aluguer += aluguer1Casa + aluguer2Casa + aluguer4Casa;*/

                if (hoteis == 1)    aluguer += aluguerHotel;
                else if (hoteis == 2)    aluguer += 2*aluguerHotel;

                if (piscinas == 1)  aluguer += aluguerPiscina;
                else if (piscinas == 2)  aluguer += 2*aluguerPiscina;

                if (pistas == 1)    aluguer += aluguerPista;
                else if (pistas == 2)    aluguer += 2*aluguerPista;

            }

            if (this.getGrupo().estaCompradoPorUnPropietario())   aluguer *= 2;

            this.setAluguer(aluguer);

        } else{
            this.setAluguer(Constantes.valoresSolares.get(this.getId())*0.1);
        }

    }

    @Override
    public void accion(Taboleiro taboleiro, Xogador xogador) throws HipotecaExcepcion {

        if (this.getPropietario() != null){

            if (!this.pertenceAXogador(xogador) && !this.getHipotecada()){

                try {
                    xogador.pagar(this.getAluguer());
                    this.getPropietario().cobrar(this.getAluguer());

                    xogador.incrementarPagoDeAlugueres(this.getAluguer());
                    this.getPropietario().incrementarCobroDeAluguere(this.getAluguer());

                    Xogo.getConsola().imprimir(String.format("O xogador %s paga un aluguer de %.2f€",xogador.getNome(),this.getAluguer()));

                } catch (FortunaInsuficienteExcepcion e){
                    xogador.setHipotecar(true);
                    xogador.setDebeda(this.getValor());
                    Xogo.getConsola().imprimir(e.getMessage());
                    throw new HipotecaExcepcion("Debe hipotecar ou vender propiedades.");
                }

            }

        } else incrementarVecesCaidas();

    }

    public void edificar(Taboleiro taboleiro, String edificio) throws EdificacionExcepcion {

        Xogador xogador = this.getGrupo().getPropietario();

        if (this.getGrupo().pertenceAXogador(xogador) || vecesCaidas > 2) {

            switch (edificio) {

                case "casa":
                    if (podeseFacerOutraCasa()){
                        try {
                            Casa casa = new Casa(String.format("casa-%d",taboleiro.obterUltimoNumSerie(0)),this,this.getPropietario(),this.getValorCasa());

                            xogador.pagar(this.getValorCasa());
                            taboleiro.engadirEdificacion(casa);
                            this.engadirEdificacion(casa);
                            aluguer(taboleiro);

                        } catch (FortunaInsuficienteExcepcion e){
                            Xogo.getConsola().imprimir("Non ten suficiente diñeiro para edificar unha casa.");
                        }

                    } else throw new NonMaisCasasExcepcion("Non se poden construír máis casas.");

                    break;

                case "hotel":
                    if (podeseFacerOutroHotel()){
                        try {
                            Hotel hotel = new Hotel(String.format("hotel-%d",taboleiro.obterUltimoNumSerie(1)),this,this.getPropietario(),this.getValorHotel());

                            xogador.pagar(this.getValorHotel());
                            taboleiro.engadirEdificacion(hotel);
                            this.engadirEdificacion(hotel);

                            for (Edificacion edificacion: edificacions.values()){
                                if (edificacion instanceof Casa){
                                    taboleiro.quitarEdificacion(edificacion.getId());
                                    this.quitarEdificacion(edificacion.getId());
                                }

                            }

                            aluguer(taboleiro);

                        } catch (FortunaInsuficienteExcepcion e){
                            Xogo.getConsola().imprimir("Non suficiente diñeiro para edificar un hotel.");
                        }

                    } else throw new NonMaisHoteisExcepcion("Non se poden construír máis hoteis.");

                    break;

                case "piscina":
                    if (podeseFacerOutraPiscina()){
                        try{
                            Piscina piscina = new Piscina(String.format("piscina-%d",taboleiro.obterUltimoNumSerie(2)),this,this.getPropietario(),this.getValorPiscina());

                            xogador.pagar(this.getValorPiscina());
                            taboleiro.engadirEdificacion(piscina);
                            this.engadirEdificacion(piscina);
                            aluguer(taboleiro);

                        } catch (FortunaInsuficienteExcepcion e){
                            Xogo.getConsola().imprimir("Non suficiente diñeiro para edificar unha piscina.");
                        }

                    } else throw new NonMaisPiscinasExcepcion("Non se poden construír máis piscinas");

                    break;

                case "pista":
                    if (podeseFacerOutraPista()){
                        try {
                            PistaDeporte pistaDeporte = new PistaDeporte(String.format("pista-%d",taboleiro.obterUltimoNumSerie(3)),this,this.getPropietario(),this.getValorPista());

                            xogador.pagar(this.getValorPista());
                            taboleiro.engadirEdificacion(pistaDeporte);
                            this.engadirEdificacion(pistaDeporte);
                            aluguer(taboleiro);

                        } catch(FortunaInsuficienteExcepcion e){
                            Xogo.getConsola().imprimir("Non suficiente diñeiro para edificar unha pista de deporte.");
                        }

                    } else throw new NonMaisPistasExcepcion("Non se poden construír máis pistas de deporte.");

                    break;

                default:
                    Xogo.getConsola().imprimir("Non se recoñece o tipo de edifiicio que se quere construír.");

            }

        }

    }

    public void venderEdificios(Taboleiro taboleiro, String edificio, Integer cantidade){

        if (!edificio.isEmpty()){
            Integer vendido = 0;

            switch (edificio){

                case "casas":

                    if (contarCasas() >= cantidade){

                        vendido = cantidade;

                        for (Edificacion edificacion: this.edificacions.values())
                            if (edificacion instanceof Casa && cantidade > 0){
                                this.edificacions.remove(edificacion.getId());
                                taboleiro.quitarEdificacion(edificacion.getId());
                                cantidade--;
                            }

                        aluguer(taboleiro);
                        Xogo.getConsola().imprimir(String.format("%s vendeu/venderon %d casa(s) en %s, percibindo %.2f. Queda(n) %d casa(s) na propiedade.",
                                this.getPropietario().getNome(),vendido,this.getNome(),this.valorCasa*vendido,contarCasas()));

                    } else {

                        cantidade = contarCasas();
                        vendido = cantidade;

                        for (Edificacion edificacion: this.edificacions.values())
                            if (edificacion instanceof Casa && cantidade > 0){
                                this.edificacions.remove(edificacion.getId());
                                taboleiro.quitarEdificacion(edificacion.getId());
                                cantidade--;
                            }
                        aluguer(taboleiro);
                        Xogo.getConsola().imprimir(String.format("Só se vendeu/venderon %d casa(s) en %s, percibindo %.2f. Queda(n) %d casa(s) na propiedade.",
                                vendido,this.getNome(),this.valorCasa*vendido,contarCasas()));

                    }

                    break;

                case "hoteis":

                    if (contarHoteis() >= cantidade){

                        vendido = cantidade;

                        for (Edificacion edificacion: this.edificacions.values())
                            if (edificacion instanceof Hotel && cantidade > 0){
                                this.edificacions.remove(edificacion.getId());
                                taboleiro.quitarEdificacion(edificacion.getId());
                                cantidade--;
                            }

                        aluguer(taboleiro);
                        Xogo.getConsola().imprimir(String.format("%s vendeu %d hotel/-teis en %s, percibindo %.2f. Quedan %d hotel/-teis na propiedade.",
                                this.getPropietario().getNome(),vendido,this.getNome(),this.valorHotel*vendido,contarHoteis()));

                    } else {

                        cantidade = contarHoteis();
                        vendido = cantidade;

                        for (Edificacion edificacion: this.edificacions.values())
                            if (edificacion instanceof Hotel && cantidade > 0){
                                this.edificacions.remove(edificacion.getId());
                                taboleiro.quitarEdificacion(edificacion.getId());
                                cantidade--;
                            }

                        aluguer(taboleiro);
                        Xogo.getConsola().imprimir(String.format("%s vendeu %d hotel/-teis en %s, percibindo %.2f. Quedan %d hotel/-teis na propiedade.",
                                this.getPropietario().getNome(),vendido,this.getNome(),this.valorHotel*vendido,contarHoteis()));
                    }

                    break;

                case "piscinas":

                    if (contarPiscinas() >= cantidade){

                        vendido = cantidade;

                        for (Edificacion edificacion: this.edificacions.values())
                            if (edificacion instanceof Piscina && cantidade > 0){
                                this.edificacions.remove(edificacion.getId());
                                taboleiro.quitarEdificacion(edificacion.getId());
                                cantidade--;
                            }

                        aluguer(taboleiro);
                        Xogo.getConsola().imprimir(String.format("%s vendeu %d piscina(s) en %s, percibindo %.2f. Quedan %d piscina(s) na propiedade.",
                                this.getPropietario().getNome(),vendido,this.getNome(),this.valorPiscina*vendido,contarPiscinas()));

                    } else {

                        cantidade = contarPiscinas();
                        vendido = cantidade;

                        for (Edificacion edificacion: this.edificacions.values())
                            if (edificacion instanceof Piscina && cantidade > 0){
                                this.edificacions.remove(edificacion.getId());
                                taboleiro.quitarEdificacion(edificacion.getId());
                                cantidade--;
                            }

                        aluguer(taboleiro);
                        Xogo.getConsola().imprimir(String.format("%s vendeu %d piscina(s) en %s, percibindo %.2f. Quedan %d piscina(s) na propiedade.",
                                this.getPropietario().getNome(),vendido,this.getNome(),this.valorPiscina*vendido,contarPiscinas()));

                    }

                    break;

                case "pistas":

                    if (contarPistas() >= cantidade){

                        vendido = cantidade;

                        for (Edificacion edificacion: this.edificacions.values())
                            if (edificacion instanceof PistaDeporte && cantidade > 0){
                                this.edificacions.remove(edificacion.getId());
                                taboleiro.quitarEdificacion(edificacion.getId());
                                cantidade--;
                            }

                        aluguer(taboleiro);
                        Xogo.getConsola().imprimir(String.format("%s vendeu %d pista(s) en %s, percibindo %.2f. Quedan %d pista(s) na propiedade.",
                                this.getPropietario().getNome(),vendido,this.getNome(),this.valorPista*vendido,contarPistas()));

                    } else {

                        cantidade = contarPistas();
                        vendido = cantidade;

                        for (Edificacion edificacion: this.edificacions.values())
                            if (edificacion instanceof PistaDeporte && cantidade > 0){
                                this.edificacions.remove(edificacion.getId());
                                taboleiro.quitarEdificacion(edificacion.getId());
                                cantidade--;
                            }

                        aluguer(taboleiro);
                        Xogo.getConsola().imprimir(String.format("%s vendeu %d casa(s) en %s, percibindo %.2f. Quedan %d casa(s) na propiedade.",
                                this.getPropietario().getNome(),vendido,this.getNome(),this.valorPista*vendido,contarPistas()));
                    }

                    break;

                default:
                    Xogo.getConsola().imprimir("Non se recoñece o tipo de edificación a derrubar.");
            }

        }

    }

    public Integer contarCasas(){
        Integer conta = 0;

        for (Edificacion edificacion: edificacions.values())
            if (edificacion instanceof Casa) conta++;

        return conta;
    }

    public Integer contarHoteis(){
        Integer conta = 0;

        for (Edificacion edificacion: edificacions.values())
            if (edificacion instanceof Hotel) conta++;

        return conta;
    }

    public Integer contarPiscinas(){
        Integer conta = 0;

        for (Edificacion edificacion: edificacions.values())
            if (edificacion instanceof Piscina) conta++;

        return conta;
    }

    public Integer contarPistas(){
        Integer conta = 0;

        for (Edificacion edificacion: edificacions.values())
            if (edificacion instanceof PistaDeporte) conta++;

        return conta;
    }

    private Boolean podeseFacerOutraPista(){

        Integer pistas = contarPistas(), hoteis = contarHoteis();

        if (pistas.equals(getGrupo().getTamanhoGrupo())){
            return false;
        } else {

            if (hoteis > 1) return true;
            return false;

        }

    }

    private Boolean podeseFacerOutraPiscina(){

        Integer piscinas = contarPiscinas(), hoteis = contarHoteis(), casas = contarCasas();

        if (piscinas.equals(getGrupo().getTamanhoGrupo())){
            return false;
        } else{

            if (hoteis > 0 && casas > 1)    return true;
            return false;

        }

    }

    private Boolean podeseFacerOutraCasa(){

        Integer casas = contarCasas(), hoteis = contarHoteis();

        if (hoteis.equals(getGrupo().getTamanhoGrupo())){

            if (casas.equals(getGrupo().getTamanhoGrupo())) return false;
            return true;

        } else {

            if (casas.equals(4)) return false;
            return true;

        }
    }

    private Boolean podeseFacerOutroHotel(){

        Integer casas = contarCasas(), hoteis = contarHoteis();

        if (hoteis.equals(getGrupo().getTamanhoGrupo())){
            return false;

        } else {

            if (casas < 4)  return false;
            return true;
        }

    }

    @Override
    public String informacionVendaBasica() {

        StringBuilder informacion = new StringBuilder();

        informacion.append(String.format("{\n\ttipo: solar,\n\tgrupo: %s,\n\tvalor: %.2f\n}",this.grupo.getNomeGrupo(),this.getValor()));

        return informacion.toString();
    }

    @Override
    public String toString() {
        StringBuilder descricion = new StringBuilder();

        descricion.append(String.format("{\n\ttipo: solar,\n\tgrupo: %s,\n\tvalor: %.2f,",this.grupo.getNomeGrupo(),this.getValor()));
        descricion.append(String.format("\n\taluguer: %.2f,\n\tvalor casa: %.2f,\n\tvalor hotel: %.2f,\n\tvalor piscina: %.2f,\n\tvalor pista de deporte: %.2f,",
                this.getAluguer(),this.getValorCasa(),this.getValorHotel(),this.getValorPiscina(),this.getValorPista()));
        descricion.append(String.format("\n\taluguer dunha casa: %.2f,\n\taluguer de dúas casas: %.2f,\n\taluguer de tres casas: %.2f,\n\taluguer de catro casas: %.2f,",
                this.getAluguer1Casa(),this.getAluguer2Casa(),this.getAluguer3Casa(),this.getAluguer4Casa()));
        descricion.append(String.format("\n\taluguer hotel: %.2f,\n\taluguer piscina: %.2f,\n\taluguer pista de deporte: %.2f\n}",
                this.getAluguerHotel(),this.getAluguerPiscina(),this.getAluguerPista()));

        return descricion.toString();
    }
}
