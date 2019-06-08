package xogo;

import avatares.Avatar;
import cadros.*;
import interfaces.Constantes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Taboleiro {

    private HashMap<String, Cadro> cadros;
    private HashMap<Integer, Cadro> cadrosOrdenados;
    private ArrayList<Avatar> avatares;


    public Taboleiro(){

        Cadro cadro = null;

        this.cadros = new HashMap<>();
        this.cadrosOrdenados = new HashMap<>();
        this.avatares = new ArrayList<>();

        /* creación do taboleiro */

        // SUR
        cadro = new Especial("saida","Saída",Constantes.salario);
        cadros.put("saida",cadro);
        cadrosOrdenados.put(1,cadro);

        cadro = new Solar("solar1",Constantes.SOLAR1,Constantes.valoresSolares.get("solar1"));
        cadros.put("solar2",cadro);
        cadrosOrdenados.put(2,cadro);

        cadro = new CaixaComunidade("caixaSur","Caixa de comunidade",null);
        cadros.put("caixaSur",cadro);
        cadrosOrdenados.put(3,cadro);

        cadro = new Solar("solar2",Constantes.SOLAR2,Constantes.valoresSolares.get("solar2"));
        cadros.put("solar2",cadro);
        cadrosOrdenados.put(4,cadro);

        cadro = new Imposto("impCapital","Imposto sobre o capital",Constantes.impostoCapital);
        cadros.put("impCapital",cadro);
        cadrosOrdenados.put(5,cadro);

        cadro = new Transporte("transSur","Estación do Sur",Constantes.transporte);
        cadros.put("transSur",cadro);
        cadrosOrdenados.put(6,cadro);

        cadro = new Solar("solar3",Constantes.SOLAR3,Constantes.valoresSolares.get("solar3"));
        cadros.put("solar3",cadro);
        cadrosOrdenados.put(7,cadro);

        cadro = new Sorte("sorteSur","Sorte",null);
        cadros.put("sorteSur",cadro);
        cadrosOrdenados.put(8,cadro);

        cadro = new Solar("solar4",Constantes.SOLAR4,Constantes.valoresSolares.get("solar4"));
        cadros.put("solar4",cadro);
        cadrosOrdenados.put(9,cadro);

        cadro = new Solar("solar5",Constantes.SOLAR5,Constantes.valoresSolares.get("solar5"));
        cadros.put("solar5",cadro);
        cadrosOrdenados.put(10,cadro);

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
        cadros.put("solar7",cadro);
        cadrosOrdenados.put(15,cadro);

        cadro = new Transporte("transOeste","Estación do Oeste",Constantes.transporte);
        cadros.put("transOeste",cadro);
        cadrosOrdenados.put(16,cadro);

        cadro = new Solar("solar9",Constantes.SOLAR9,Constantes.valoresSolares.get("solar9"));
        cadros.put("solar9",cadro);
        cadrosOrdenados.put(17,cadro);

        cadro = new CaixaComunidade("caixaOeste","Caixa de comunidade",null);
        cadros.put("caixaOeste",cadro);
        cadrosOrdenados.put(18,cadro);

        cadro = new Solar("solar10",Constantes.SOLAR10,Constantes.valoresSolares.get("solar10"));
        cadros.put("solar10",cadro);
        cadrosOrdenados.put(19,cadro);

        cadro = new Solar("solar11",Constantes.SOLAR11,Constantes.valoresSolares.get("solar11"));
        cadros.put("solar11",cadro);
        cadrosOrdenados.put(20,cadro);

        // NORTE
        cadro = new Especial("aparcamento","Aparcamento Praza de Galiza",0.0);
        cadros.put("aparcamento",cadro);
        cadrosOrdenados.put(21,cadro);

        cadro = new Solar("solar12",Constantes.SOLAR12, Constantes.valoresSolares.get("solar12"));
        cadros.put("solar12",cadro);
        cadrosOrdenados.put(22,cadro);

        cadro = new Sorte("sorteNorte","Sorte",null);
        cadros.put("sorteNorte",cadro);
        cadrosOrdenados.put(23,cadro);

        cadro = new Solar("solar13",Constantes.SOLAR13, Constantes.valoresSolares.get("solar13"));
        cadros.put("solar13",cadro);
        cadrosOrdenados.put(24,cadro);

        cadro = new Solar("solar14",Constantes.SOLAR14,Constantes.valoresSolares.get("solar14"));
        cadros.put("solar14",cadro);
        cadrosOrdenados.put(25,cadro);

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

        cadro = new CaixaComunidade("caixaLeste", "Caixa de comunidade", null);
        cadros.put("caixaLeste",cadro);
        cadrosOrdenados.put(34,cadro);

        cadro = new Solar("solar20",Constantes.SOLAR20, Constantes.valoresSolares.get("solar20"));
        cadros.put("solar20",cadro);
        cadrosOrdenados.put(35,cadro);

        cadro = new Transporte("transLeste","Estación do Leste",Constantes.transporte);
        cadros.put("transLeste",cadro);
        cadrosOrdenados.put(36,cadro);

        cadro = new Sorte("sorteLeste","Sorte",null);
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

    }

    /* getters */
    public HashMap<Integer, Cadro> getCadrosOrdenados() {
        return this.cadrosOrdenados;
    }

    public HashMap<String, Cadro> getCadros() {
        return this.cadros;
    }

    /* métodos */
    public Cadro obterCadro(Integer i){
        return cadrosOrdenados.get(i);
    }

    public Cadro obterCadro(String id){
        return cadros.get(id);
    }

    @Override
    public String toString() {

        StringBuilder taboleiro = new StringBuilder();
        StringBuffer temp = new StringBuffer();

        // parte superior

        taboleiro.append("----------------".repeat(11));

        taboleiro.append("\n|");

        for (int i=21; i<32; i++)   taboleiro.append(String.format(" %12s |",this.obterCadro(i).getId()));

        taboleiro.append("\n|");

        for (int i=21; i<32; i++){

            Cadro cadro = this.obterCadro(i);

            for (Avatar avatar : this.avatares) {

                if (avatar.getPosicion().equals(cadro)){

                    temp.append(String.format("&%s",avatar.getId()));

                }

            }

            taboleiro.append(String.format(" %12s |",temp));

            temp.setLength(0); //para baleirar o buffer

        }
        taboleiro.append("----------------".repeat(11));


        //laterais

        for (int i = 20, j = 32; j < 41; j++, i--){

            taboleiro.append(String.format("\n| %12s |",this.obterCadro(i).getId()));

            taboleiro.append(String.format("%135|"," "));

            taboleiro.append(String.format(" %12s |",this.obterCadro(j).getId()));

            for (Avatar avatar: avatares){

                if (avatar.getPosicion().equals(this.obterCadro(i))){

                    temp.append(String.format("&%s",avatar.getId()));

                }

            }

            taboleiro.append(String.format("\n| %12s |",temp));
            temp.setLength(0);
            taboleiro.append(String.format("%135|"," "));

            for (Avatar avatar: avatares){

                if (avatar.getPosicion().equals(this.obterCadro(j))){

                    temp.append(String.format("&%s",avatar.getId()));

                }

            }

            taboleiro.append(String.format(" %12s |",temp));
            temp.setLength(0);

            if (j!=40) {
                taboleiro.append(String.format("\n----------------%135s----------------", ""));
            } else taboleiro.append("----------------".repeat(11));

        }

        //parte inferior

        taboleiro.append("\n|");

        for (int i=11; i > 0; i--)   taboleiro.append(String.format(" %12s |",this.obterCadro(i).getId()));

        taboleiro.append("\n|");

        for (int i=11; i > 0; i--){

            Cadro cadro = this.obterCadro(i);

            for (Avatar avatar : this.avatares) {

                if (avatar.getPosicion().equals(cadro)){

                    temp.append(String.format("&%s",avatar.getId()));

                }

            }

            taboleiro.append(String.format(" %12s |",temp));

            temp.setLength(0); //para baleirar o buffer

        }

        taboleiro.append("----------------".repeat(11));

        return new String(taboleiro);
    }
}
