package cadros;

import cartas.Carta;
import excepcions.HipotecaExcepcion;
import interfaces.Constantes;
import xogadores.Xogador;
import xogo.Taboleiro;
import xogo.Xogo;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public final class Sorte extends Accion {

    private static ArrayList<Carta> monteDeCartas = Constantes.sorte;

    public Sorte(String id, String nome){

        this.setId(id);
        this.setNome(nome);
        this.setUltimaEscollida(null);
        this.setVisitas();

    }

    /* setters */
    private static void setMonteDeCartas(ArrayList<Carta> montoDeCartas) {
        monteDeCartas = monteDeCartas;
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
                    try{
                        numCarta = Integer.valueOf(cachos[0]);
                    } catch (NumberFormatException e){
                        Xogo.getConsola().imprimir(e.getMessage());
                    }

                    if (numCarta!=null)
                        if (numCarta < 1 || numCarta > 6)
                            numCarta = null;

                }

            }

            baralla.get(numCarta % 6).accion(taboleiro,xogador);

            this.setVisitas();

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
