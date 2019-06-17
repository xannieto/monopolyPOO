package cadros;

import cartas.Carta;
import excepcions.HipotecaExcepcion;
import xogadores.Xogador;
import xogo.Taboleiro;
import xogo.Xogo;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public final class CaixaComunidade extends Accion {

    private static ArrayList<Carta> monteDeCartas;

    public CaixaComunidade(String id, String nome){

        this.setId(id);
        this.setNome(nome);

    }

    private List<Carta> barallarCartas(){

        List<Carta> baralladas = new ArrayList<>();
        List<Carta> copiaMonte = new ArrayList<>(monteDeCartas);
        SecureRandom sr = new SecureRandom(new byte[1]);

        while (baralladas.size()!=monteDeCartas.size()){

            Integer num = sr.nextInt(copiaMonte.size());
            Carta copia = copiaMonte.get(num);

            baralladas.add(copia);
            copiaMonte.remove(copia);

        }

        return baralladas;
    }

    @Override
    public void accion(Taboleiro taboleiro, Xogador xogador) throws HipotecaExcepcion {

        if (this.getUltimaEscollida() == null){
            List<Carta> baralla = barallarCartas();
            Integer numCarta = null;

            /* escolle unha carta */
            while(numCarta == null){
                String[] cachos = Xogo.getConsola().ler("Escolla un número do un ao seis: ").split(" ");

                if (cachos.length==1){
                    numCarta = Integer.valueOf(cachos[0]);

                    if (numCarta < 1 || numCarta > 6)   numCarta = null;
                }

            }

            baralla.get(numCarta).accion(taboleiro,xogador);

            if (xogador.getHipotecar()){
                setUltimaEscollida(baralla.get(numCarta));
                throw new HipotecaExcepcion();
            }

        } else {
            getUltimaEscollida().accion(taboleiro,xogador);

            if (xogador.getHipotecar()){
                throw new HipotecaExcepcion();

            } else setUltimaEscollida(null);

        }

    }


}
