package xogo;

import avatares.Avatar;
import cadros.*;
import edificacions.*;
import interfaces.Constantes;
import xogadores.Xogador;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Taboleiro {

    private HashMap<String, Cadro> cadros;
    private HashMap<Integer, Cadro> cadrosOrdenados;
    private HashMap<String, Xogador> xogadores;
    private ArrayList<Grupo> grupos;
    private ArrayList<Avatar> avatares;
    private ArrayList<Edificacion> edificacions;
    private Integer[] idEdificacion;

    public Taboleiro(){

        Cadro cadro;
        Grupo grupo;
        this.cadros = new HashMap<>();
        this.cadrosOrdenados = new HashMap<>();
        this.avatares = new ArrayList<>();
        this.xogadores = new HashMap<>();
        this.grupos = new ArrayList<>();

        this.edificacions = new ArrayList<>();
        this.idEdificacion = new Integer[4];
        for (int i=0; i < this.idEdificacion.length; i++)   idEdificacion[i] = 0;

        /* creación do taboleiro */

        // SUR
        cadro = new Especial("saida","Saída",Constantes.salario);
        cadros.put("saida",cadro);
        cadrosOrdenados.put(1,cadro);

        cadro = new Solar("solar1",Constantes.SOLAR1,Constantes.valoresSolares.get("solar1"));
        cadros.put("solar1",cadro);
        cadrosOrdenados.put(2,cadro);

        cadro = new CaixaComunidade("caixaSur","Caixa de comunidade");
        cadros.put("caixaSur",cadro);
        cadrosOrdenados.put(3,cadro);

        cadro = new Solar("solar2",Constantes.SOLAR2,Constantes.valoresSolares.get("solar2"));
        cadros.put("solar2",cadro);
        cadrosOrdenados.put(4,cadro);

        /* grupo 1 */
        grupo = new Grupo("g1","Grupo 1",new ArrayList<>(){{add((Solar)cadros.get("solar1"));add((Solar)cadros.get("solar2"));}},Constantes.G1);
        grupos.add(grupo);
        ((Solar)cadros.get("solar1")).setGrupo(grupo);
        ((Solar)cadros.get("solar2")).setGrupo(grupo);
        //////////////////////////////

        cadro = new Imposto("impCapital","Imposto sobre o capital",Constantes.impostoCapital);
        cadros.put("impCapital",cadro);
        cadrosOrdenados.put(5,cadro);

        cadro = new Transporte("transSur","Estación do Sur",Constantes.transporte);
        cadros.put("transSur",cadro);
        cadrosOrdenados.put(6,cadro);

        cadro = new Solar("solar3",Constantes.SOLAR3,Constantes.valoresSolares.get("solar3"));
        cadros.put("solar3",cadro);
        cadrosOrdenados.put(7,cadro);

        cadro = new Sorte("sorteSur","Sorte");
        cadros.put("sorteSur",cadro);
        cadrosOrdenados.put(8,cadro);

        cadro = new Solar("solar4",Constantes.SOLAR4,Constantes.valoresSolares.get("solar4"));
        cadros.put("solar4",cadro);
        cadrosOrdenados.put(9,cadro);

        cadro = new Solar("solar5",Constantes.SOLAR5,Constantes.valoresSolares.get("solar5"));
        cadros.put("solar5",cadro);
        cadrosOrdenados.put(10,cadro);

        /* grupo 2 */
        grupo = new Grupo("g2","Grupo 2",new ArrayList<>(){{add((Solar)cadros.get("solar3"));add((Solar)cadros.get("solar4"));add((Solar)cadros.get("solar5"));}},Constantes.G2);
        grupos.add(grupo);
        ((Solar)cadros.get("solar3")).setGrupo(grupo);
        ((Solar)cadros.get("solar4")).setGrupo(grupo);
        ((Solar)cadros.get("solar5")).setGrupo(grupo);
        ///////////////////////////////////

        cadro = new Especial("carcere","Cárcere",Constantes.fianzaCarcere);
        cadros.put("carcere",cadro);
        cadrosOrdenados.put(11,cadro);

        // OESTE
        cadro = new Solar("solar6", Constantes.SOLAR6, Constantes.valoresSolares.get("solar6"));
        cadros.put("solar6",cadro);
        cadrosOrdenados.put(12,cadro);

        cadro = new Servizo("luz","Servizo de electricidade",Constantes.servizo);
        cadros.put("luz",cadro);
        cadrosOrdenados.put(13,cadro);

        cadro = new Solar("solar7",Constantes.SOLAR7,Constantes.valoresSolares.get("solar7"));
        cadros.put("solar7",cadro);
        cadrosOrdenados.put(14,cadro);

        cadro = new Solar("solar8",Constantes.SOLAR8,Constantes.valoresSolares.get("solar8"));
        cadros.put("solar8",cadro);
        cadrosOrdenados.put(15,cadro);

        /* grupo 3 */
        grupo = new Grupo("g3","Grupo 3",new ArrayList<>(){{add((Solar)cadros.get("solar6"));add((Solar)cadros.get("solar7"));add((Solar)cadros.get("solar8"));}},Constantes.G3);
        grupos.add(grupo);
        ((Solar)cadros.get("solar6")).setGrupo(grupo);
        ((Solar)cadros.get("solar7")).setGrupo(grupo);
        ((Solar)cadros.get("solar8")).setGrupo(grupo);
        ///////////////////////////////////

        cadro = new Transporte("transOeste","Estación do Oeste",Constantes.transporte);
        cadros.put("transOeste",cadro);
        cadrosOrdenados.put(16,cadro);

        cadro = new Solar("solar9",Constantes.SOLAR9,Constantes.valoresSolares.get("solar9"));
        cadros.put("solar9",cadro);
        cadrosOrdenados.put(17,cadro);

        cadro = new CaixaComunidade("caixaOeste","Caixa de comunidade");
        cadros.put("caixaOeste",cadro);
        cadrosOrdenados.put(18,cadro);

        cadro = new Solar("solar10",Constantes.SOLAR10,Constantes.valoresSolares.get("solar10"));
        cadros.put("solar10",cadro);
        cadrosOrdenados.put(19,cadro);

        cadro = new Solar("solar11",Constantes.SOLAR11,Constantes.valoresSolares.get("solar11"));
        cadros.put("solar11",cadro);
        cadrosOrdenados.put(20,cadro);

        /* grupo 4 */
        grupo = new Grupo("g4","Grupo 4",new ArrayList<>(){{add((Solar)cadros.get("solar9"));add((Solar)cadros.get("solar10"));add((Solar)cadros.get("solar11"));}},Constantes.G4);
        grupos.add(grupo);
        ((Solar)cadros.get("solar9")).setGrupo(grupo);
        ((Solar)cadros.get("solar10")).setGrupo(grupo);
        ((Solar)cadros.get("solar11")).setGrupo(grupo);
        ///////////////////////////////////

        // NORTE
        cadro = new Especial("aparcamento","Aparcamento Praza de Galiza",0.0);
        cadros.put("aparcamento",cadro);
        cadrosOrdenados.put(21,cadro);

        cadro = new Solar("solar12",Constantes.SOLAR12, Constantes.valoresSolares.get("solar12"));
        cadros.put("solar12",cadro);
        cadrosOrdenados.put(22,cadro);

        cadro = new Sorte("sorteNorte","Sorte");
        cadros.put("sorteNorte",cadro);
        cadrosOrdenados.put(23,cadro);

        cadro = new Solar("solar13",Constantes.SOLAR13, Constantes.valoresSolares.get("solar13"));
        cadros.put("solar13",cadro);
        cadrosOrdenados.put(24,cadro);

        cadro = new Solar("solar14",Constantes.SOLAR14,Constantes.valoresSolares.get("solar14"));
        cadros.put("solar14",cadro);
        cadrosOrdenados.put(25,cadro);

        /* grupo 5 */
        grupo = new Grupo("g5","Grupo 5",new ArrayList<>(){{add((Solar)cadros.get("solar12"));add((Solar)cadros.get("solar13"));add((Solar)cadros.get("solar14"));}},Constantes.G5);
        grupos.add(grupo);
        ((Solar)cadros.get("solar12")).setGrupo(grupo);
        ((Solar)cadros.get("solar13")).setGrupo(grupo);
        ((Solar)cadros.get("solar14")).setGrupo(grupo);
        ///////////////////////////////////

        cadro = new Transporte("transNorte","Estación do Norte",Constantes.transporte);
        cadros.put("transNorte",cadro);
        cadrosOrdenados.put(26,cadro);

        cadro = new Solar("solar15",Constantes.SOLAR14,Constantes.valoresSolares.get("solar15"));
        cadros.put("solar15",cadro);
        cadrosOrdenados.put(27,cadro);

        cadro = new Solar("solar16",Constantes.SOLAR15,Constantes.valoresSolares.get("solar16"));
        cadros.put("solar16",cadro);
        cadrosOrdenados.put(28,cadro);

        cadro = new Servizo("auga","Servizo de auga",Constantes.servizo);
        cadros.put("auga",cadro);
        cadrosOrdenados.put(29,cadro);

        cadro = new Solar("solar17",Constantes.SOLAR17,Constantes.valoresSolares.get("solar17"));
        cadros.put("solar17",cadro);
        cadrosOrdenados.put(30,cadro);

        /* grupo 6 */
        grupo = new Grupo("g6","Grupo 6",new ArrayList<>(){{add((Solar)cadros.get("solar15"));add((Solar)cadros.get("solar16"));add((Solar)cadros.get("solar17"));}},Constantes.G6);
        grupos.add(grupo);
        ((Solar)cadros.get("solar15")).setGrupo(grupo);
        ((Solar)cadros.get("solar16")).setGrupo(grupo);
        ((Solar)cadros.get("solar17")).setGrupo(grupo);
        ///////////////////////////////////

        // LESTE
        cadro = new Especial("irCarcere", "Atallo ao cárcere",0.0);
        cadros.put("irCarcere",cadro);
        cadrosOrdenados.put(31,cadro);

        cadro = new Solar("solar18",Constantes.SOLAR18,Constantes.valoresSolares.get("solar18"));
        cadros.put("solar18",cadro);
        cadrosOrdenados.put(32,cadro);

        cadro = new Solar("solar19",Constantes.SOLAR18,Constantes.valoresSolares.get("solar19"));
        cadros.put("solar19",cadro);
        cadrosOrdenados.put(33,cadro);

        cadro = new CaixaComunidade("caixaLeste", "Caixa de comunidade");
        cadros.put("caixaLeste",cadro);
        cadrosOrdenados.put(34,cadro);

        cadro = new Solar("solar20",Constantes.SOLAR20, Constantes.valoresSolares.get("solar20"));
        cadros.put("solar20",cadro);
        cadrosOrdenados.put(35,cadro);

        /* grupo 7 */
        grupo = new Grupo("g7","Grupo 7",new ArrayList<>(){{add((Solar)cadros.get("solar18"));add((Solar)cadros.get("solar19"));add((Solar)cadros.get("solar20"));}},Constantes.G7);
        grupos.add(grupo);
        ((Solar)cadros.get("solar18")).setGrupo(grupo);
        ((Solar)cadros.get("solar19")).setGrupo(grupo);
        ((Solar)cadros.get("solar20")).setGrupo(grupo);
        ///////////////////////////////////

        cadro = new Transporte("transLeste","Estación do Leste",Constantes.transporte);
        cadros.put("transLeste",cadro);
        cadrosOrdenados.put(36,cadro);

        cadro = new Sorte("sorteLeste","Sorte");
        cadros.put("sorteLeste",cadro);
        cadrosOrdenados.put(37,cadro);

        cadro = new Solar("solar21",Constantes.SOLAR21,Constantes.valoresSolares.get("solar21"));
        cadros.put("solar21",cadro);
        cadrosOrdenados.put(38,cadro);

        cadro = new Imposto("impLuxo","Imposto sobre o luxo",Constantes.impostoLuxo);
        cadros.put("impLuxo",cadro);
        cadrosOrdenados.put(39,cadro);

        cadro = new Solar("solar22",Constantes.SOLAR22,Constantes.valoresSolares.get("solar22"));
        cadros.put("solar22",cadro);
        cadrosOrdenados.put(40,cadro);

        /* grupo 8 */
        grupo = new Grupo("g8","Grupo 8",new ArrayList<>(){{add((Solar)cadros.get("solar21"));add((Solar)cadros.get("solar22"));}},Constantes.G8);
        grupos.add(grupo);
        ((Solar)cadros.get("solar21")).setGrupo(grupo);
        ((Solar)cadros.get("solar22")).setGrupo(grupo);
        ///////////////////////////////////

    }

    /* getters */
    public HashMap<Integer, Cadro> getCadrosOrdenados() {
        return this.cadrosOrdenados;
    }

    public HashMap<String, Cadro> getCadros() {
        return this.cadros;
    }

    public HashMap<String, Xogador> getXogadores() {
        return xogadores;
    }

    public ArrayList<Avatar> getAvatares(){return  avatares;}

    public ArrayList<Grupo> getGrupos() {
        return grupos;
    }

    public ArrayList<Edificacion> getEdificacions() {
        return edificacions;
    }

    public Integer[] getIdEdificacion() {
        return idEdificacion;
    }

    /* métodos */

    public Integer obterUltimoNumSerie(Integer num){
        return this.idEdificacion[num];
    }

    private void incrementarNumSerie(Integer num){
        if(num > 0 || num < idEdificacion.length)   idEdificacion[num]++;
    }

    public void engadirEdificacion(Edificacion edificacion){
        if (edificacion != null){
            if (edificacion instanceof Casa){
                incrementarNumSerie(0);
                this.edificacions.add(edificacion);
            } else if (edificacion instanceof Hotel){
                incrementarNumSerie(1);
                this.edificacions.add(edificacion);
            } else if (edificacion instanceof Piscina){
                incrementarNumSerie(2);
                this.edificacions.add(edificacion);
            } else{
                incrementarNumSerie(3);
                this.edificacions.add(edificacion);
            }

        }
    }

    public void quitarEdificacion(String nome){

        if (!edificacions.isEmpty()){
            for (Edificacion edificacion: this.edificacions)
                if(edificacion.getId().equals(nome)){
                    this.edificacions.remove(edificacion);
                    break;
                }
        }

    }

    public Grupo obterGrupo(String id){

        if (this.grupos != null){
            for (Grupo grupo: this.getGrupos())
                if (grupo.getIdGrupo().equals(id)) return grupo;

        }
        return null;
    }

    public Cadro obterCadro(Integer i){
        return cadrosOrdenados.get(i);
    }

    public Cadro obterCadro(String id){
        return cadros.get(id);
    }

    public ArrayList<Avatar> obterAvataresCadro(String id){

        Cadro cadro = obterCadro(id);

        if (cadro != null){

            ArrayList<Avatar> listaAvatares = new ArrayList<>();

            for (Avatar avatar: avatares)
                if (cadro.equals(avatar.getPosicion())) listaAvatares.add(avatar);

            return listaAvatares;
        }
        return null;
    }

    public ArrayList<Avatar> obterAvataresCadro(Cadro cadro){

        if (cadro != null){

            ArrayList<Avatar> listaAvatares = new ArrayList<>();

            for (Avatar avatar: avatares)
                if (cadro.equals(avatar.getPosicion())) listaAvatares.add(avatar);

            return listaAvatares;
        }
        return null;
    }

    public Integer posicionActual(Avatar avatar){

        Set<Integer> indices = this.cadrosOrdenados.keySet();
        Integer posicion = null;
        Cadro cadroActual = avatar.getPosicion();

        for (Integer i : indices){
            if (this.cadrosOrdenados.get(i).equals(cadroActual)){
                posicion = i;
                break;
            }
        }

        return posicion;
    }

    public Integer[] tiradaDados(){

        Integer[] tirada = new Integer[2];
        SecureRandom numAleatorio = new SecureRandom(new byte[1]);

        tirada[0] = numAleatorio.nextInt(6) + 1;
        tirada[1] = numAleatorio.nextInt(6) + 1;

        return tirada;

    }

    public Xogador obterXogador(String id){
        return xogadores.get(id);
    }

    public Boolean existeXogador(String id){
        return xogadores.get(id) != null; // true se existe
    }

    public Avatar obterAvatar(String id){

        if (!id.isEmpty()){
            for(Avatar avatar: avatares)
                if (avatar.getId().equals(id))  return avatar;
        }

        return null;

    }

    public Boolean existeAvatar(String id){

        if (!id.isEmpty()){
            for(Avatar avatar: avatares)
                if (avatar.getId().equals(id))  return true;
        }

        return false;
    }

    public void novoXogador(Xogador xogador){
        if (!existeXogador(xogador.getNome())){
            this.xogadores.put(xogador.getNome(),xogador);
            this.avatares.add(xogador.getAvatar());
        }
    }

    public Boolean deronCatroVoltas(){

        for (Avatar avatar: avatares)
            if ((avatar.getVoltasDadas()-4)< 0) return false;

        for (Avatar avatar: avatares)
            avatar.setVoltasDadas(avatar.getVoltasDadas()-4);

        return true;
    }

    @Override
    public String toString() {

        StringBuilder taboleiro = new StringBuilder();
        StringBuffer temp = new StringBuffer();

        // parte superior

        taboleiro.append("---------------".repeat(11));
        taboleiro.append("-");

        taboleiro.append("\n$");

        for (int i=21; i<32; i++){
            Cadro cadro = obterCadro(i);
            if (cadro instanceof Solar){
                if (((Solar) cadro).getGrupo().getIdGrupo().equals("g5"))
                    taboleiro.append(String.format(" %s%-12s%s $",Constantes.G5,cadro.getId(),Constantes.normal));
                else taboleiro.append(String.format(" %s%-12s%s $",Constantes.G6,cadro.getId(),Constantes.normal));
            } else taboleiro.append(String.format(" %-12s $",cadro.getId()));
        }

        taboleiro.append("\n$");

        for (int i=21; i<32; i++){

            Cadro cadro = this.obterCadro(i);

            for (Avatar avatar : this.avatares) {

                if (avatar.getPosicion().equals(cadro)){

                    temp.append(String.format("&%s",avatar.getId()));

                }

            }

            taboleiro.append(String.format(" %-12s $",temp));

            temp.setLength(0); //para baleirar o buffer

        }
        taboleiro.append("\n");
        taboleiro.append("---------------".repeat(11));
        taboleiro.append("-");


        //laterais

        for (int i = 20, j = 32; j < 41; j++, i--){

            Cadro oeste = this.obterCadro(i), leste = this.obterCadro(j);

            taboleiro.append("\n");

            if (oeste instanceof Solar){
                if (((Solar) oeste).getGrupo().getIdGrupo().equals("g3"))
                    taboleiro.append(String.format("$ %s%-12s%s $",Constantes.G3,oeste.getId(),Constantes.normal));
                else taboleiro.append(String.format("$ %s%-12s%s $",Constantes.G4,oeste.getId(),Constantes.normal));
            } else taboleiro.append(String.format("$ %-12s $",oeste.getId()));

            taboleiro.append(String.format("%134s$"," "));

            if (leste instanceof Solar){
                if (((Solar) leste).getGrupo().getIdGrupo().equals("g7"))
                    taboleiro.append(String.format(" %s%-12s%s $",Constantes.G7,leste.getId(),Constantes.normal));
                else taboleiro.append(String.format(" %s%-12s%s $",Constantes.G8,leste.getId(),Constantes.normal));
            } else taboleiro.append(String.format(" %-12s $",leste.getId()));

            for (Avatar avatar: avatares){

                if (avatar.getPosicion().equals(oeste)){

                    temp.append(String.format("&%s",avatar.getId()));

                }

            }

            taboleiro.append(String.format("\n$ %-12s $",temp));
            temp.setLength(0);
            taboleiro.append(String.format("%134s$"," "));

            for (Avatar avatar: avatares){

                if (avatar.getPosicion().equals(leste)){

                    temp.append(String.format("&%s",avatar.getId()));

                }

            }

            taboleiro.append(String.format(" %-12s $",temp));
            temp.setLength(0);

            if (j!=40) {
                taboleiro.append(String.format("\n----------------%134s----------------", ""));
            } else {
                taboleiro.append("\n");
                taboleiro.append("---------------".repeat(11));
                taboleiro.append("-");
            }

        }

        //parte inferior

        taboleiro.append("\n$");

        for (int i=11; i > 0; i--){
            Cadro cadro = obterCadro(i);

            if (cadro instanceof Solar){
                if (((Solar) cadro).getGrupo().getIdGrupo().equals("g2"))
                    taboleiro.append(String.format(" %s%-12s%s $",Constantes.G2,cadro.getId(),Constantes.normal));
                else taboleiro.append(String.format(" %s%-12s%s $",Constantes.G1,cadro.getId(),Constantes.normal));
            } else taboleiro.append(String.format(" %-12s $",cadro.getId()));
        }

        taboleiro.append("\n$");

        for (int i=11; i > 0; i--){

            Cadro cadro = this.obterCadro(i);

            for (Avatar avatar : this.avatares) {

                if (avatar.getPosicion().equals(cadro)){

                    temp.append(String.format("&%s",avatar.getId()));

                }

            }

            taboleiro.append(String.format(" %-12s $",temp));

            temp.setLength(0); //para baleirar o buffer

        }

        taboleiro.append("\n");
        taboleiro.append("---------------".repeat(11));
        taboleiro.append("-");

        return new String(taboleiro);
    }
}
