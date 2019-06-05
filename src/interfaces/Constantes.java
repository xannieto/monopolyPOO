package interfaces;

import javax.print.attribute.HashAttributeSet;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public interface Constantes {

    HashMap<String,Double> valoresSolares = new HashMap<String, Double>()
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

    /* suma total dos grupos */
    Double totalGrupos = 2866686.0;

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
    Double factorCasa = 0.6;
    Double factorHotel = 0.6;
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

}
