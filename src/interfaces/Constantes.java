package interfaces;

import cartas.Carta;
import cartas.CartaCaixaComunidade;
import cartas.CartaSorte;

import java.util.ArrayList;
import java.util.HashMap;

public interface Constantes {

    HashMap<String,Double> valoresSolares = new HashMap<>()
    {{
        /* grupo 1 */
        put("solar1", 50000.0);
        put("solar2", 70000.0);

        /* grupo 2  */
        put("solar3", 30000.0);
        put("solar4", 54000.0);
        put("solar5", 72000.0);

        /* grupo 3 */
        put("solar6", 55000.0);
        put("solar7", 64000.0);
        put("solar8", 83800.0);

        /* grupo 4 */
        put("solar9", 66000.0);
        put("solar10", 77000.0);
        put("solar11", 126400.0);

        /* grupo 5 */
        put("solar12", 86000.0);
        put("solar13", 105000.0);
        put("solar14", 151732.0);

        /* grupo 6 */
        put("solar15", 106000.0);
        put("solar16", 146000.0);
        put("solar17", 193552.0);

        /* grupo 7 */
        put("solar18", 156000.0);
        put("solar19", 201000.0);
        put("solar20", 222218.0);

        /* grupo 8 */
        put("solar21", 300000.0);
        put("solar22", 450984.0);

    }};

    /* cartas da caixa de comunidade */
    String textoCartaComunidade1 = "Paga 32576,00€ por unha fin de semana nun balneario de cinco estrelas.";
    String textoCartaComunidade2 = "Estás a ser investigado por fraude fiscal. Vai a prisión directamente, sen cobrar os 130304,00€ do cadro de Saída.";
    String textoCartaComunidade3 = "Posiciónate no cadro de Saída. Cobra os 130304,00€";
    String textoCartaComunidade4 = "A túa compañía de Internet obtén beneficios. Cobras 130304,00€.";
    String textoCartaComunidade5 = "Paga 65152,00€ por invitar a tódolos teus amigos a unha viaxe a Ribeira Sacra.";
    String textoCartaComunidade6 = "Devolución de Facenda. Cobras 32576,00€.";

    ArrayList<Carta> comunidade = new ArrayList<>(){{
        add(new CartaCaixaComunidade("1",textoCartaComunidade1,null,32576.0,true,false));
        add(new CartaCaixaComunidade("2",textoCartaComunidade2,"carcere",null,false,false));
        add(new CartaCaixaComunidade("3",textoCartaComunidade3,"saida",null,false,true));
        add(new CartaCaixaComunidade("4",textoCartaComunidade4,null,130304.0,false,true));
        add(new CartaCaixaComunidade("5",textoCartaComunidade5,null,65152.0,true,false));
        add(new CartaCaixaComunidade("6",textoCartaComunidade6,null,32576.0,false,true));
    }};

    /*cartas de sorte*/
    String textoCartaSorte1 = "Decides ir de tapas. Avanza até O Franco.";
    String textoCartaSorte2 = "Multa por empregar o teléfono móbil mentres conduces. Pagas 13030,00€.";
    String textoCartaSorte3 = "Paga 13030,00€ pola matrícula da escola privada.";
    String textoCartaSorte4 = "Vai a Praza do Obradoiro. Se pasas polo cadro de Saída, cobras os 130304,00€.";
    String textoCartaSorte5 = "Beneficios pola venda das túas accións en ENCE. Recibes 97728,00€.";
    String textoCartaSorte6 = "Estás a ser persiguido polos teus acreedores por impagos. Vai a prisión directamente, sen cobrar os 130304,00€ do cadro de Saída.";

    ArrayList<Carta> sorte = new ArrayList<>(){{
       add(new CartaSorte("7",textoCartaSorte1,"solar14",null,false,false));
       add(new CartaSorte("8",textoCartaSorte2,null,13030.0,true,false));
       add(new CartaSorte("9",textoCartaSorte3,null,13030.0,true,false));
       add(new CartaSorte("10",textoCartaSorte4,"solar17",130304.0,false,true));
       add(new CartaSorte("11",textoCartaSorte5,null,97728.0,false,true));
       add(new CartaSorte("12",textoCartaSorte6,"carcere",null,false,false));
    }};

    /* suma total dos grupos */
    //Double totalGrupos = 2866686.0;

    /* movidas */
    Double fortunaInicial = 955562.0;
    Double salario = 130304.0;

    Double transporte = salario;
    Double servizo = 97728.0;
    Double fianzaCarcere = 32576.0;
    Double impostoLuxo = salario;
    Double impostoCapital = 65152.0;

    /* factores */
    Double factorServizo = 651.52;
    Double factorCasaHotel = 0.6;
    Double factorPiscina = 0.4;
    Double factorPistaDeporte = 1.25;
    Double factorHipoteca = 0.5;

    /* alugueres */
    Double factorAluguerSolar = 0.1;
    Double factorAluguerCasa1 = 5.0;
    Double factorAluguerCasa2 = 15.0;
    Double factorAluguerCasa3 = 35.0;
    Double factorAluguerCasa4 = 50.0;
    Double factorAluguerHotel = 70.0;
    Double factorAluguerPiscinaPista = 25.0;

    /* cadros */

    //SUR
    String SOLAR1 = "Entremurallas";
    String SOLAR2 = "San Paio de Antealtares";
    String SOLAR3 = "Praza Fuenterrabía";
    String SOLAR4 = "Praza de San Roque";
    String SOLAR5 = "San Lorenzo";
    //OESTE
    String SOLAR6 = "Os Basquiños";
    String SOLAR7 = "Praza de Vigo";
    String SOLAR8 = "República Arxentina";
    String SOLAR9 = "Praza Roxa";
    String SOLAR10 = "Alexandre Bóveda";
    String SOLAR11 = "Praza 8 de marzo";
    //NORTE
    String SOLAR12 = "Av. de Castelao";
    String SOLAR13 = "Av. Rosalía de Castro";
    String SOLAR14 = "O Franco";
    String SOLAR15 = "O Vilar";
    String SOLAR16 = "Av. Romero Donallo";
    String SOLAR17 = "Praza do Obradoiro";
    //LESTE
    String SOLAR18 = "Ourense";
    String SOLAR19 = "Monte Pío";
    String SOLAR20 = "Av. de Lugo";
    String SOLAR21 = "Av. de Barcelona";
    String SOLAR22 = "Av. da Liberdade";

    /* cores */
    String	G1 = "\u001B[36;1m";
    String	G2 = "\u001B[34;1m";
    String	G3 = "\u001B[33;1m";
    String	G4 = "\u001B[32;1m";
    String	G5 = "\u001B[46m";
    String	G6 = "\u001B[44m";
    String	G7 = "\u001B[41m";
    String	G8 = "\u001B[45m";

    String bold = "\033[0;1m";
    String normal = "\033[0;0m";

}
