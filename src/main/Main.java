package main;
;
import xogo.Xogo;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //Xogo xogo = new Xogo();

        //xogo.menuPrincipal();
        List<Integer> monteDeCartas = new ArrayList<>();

        for (int i = 0; i < 13; i++)
            monteDeCartas.add(i);

        List<Integer> baralladas = new ArrayList<>();
        List<Integer> copiaMonte = new ArrayList<>(monteDeCartas);
        SecureRandom sr = new SecureRandom(new byte[1]);

        System.out.println("Sen barallar: "+monteDeCartas);

        while (baralladas.size()!=monteDeCartas.size()){

            Integer num = sr.nextInt(copiaMonte.size());
            Integer copia = copiaMonte.get(num);

            baralladas.add(copia);
            copiaMonte.remove(copia);

        }
        System.out.println("Baralladas: "+baralladas);

    }

}
