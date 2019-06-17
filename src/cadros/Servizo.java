package cadros;

import excepcions.FortunaInsuficienteExcepcion;
import excepcions.HipotecaExcepcion;
import interfaces.Constantes;
import xogadores.Xogador;
import xogo.Taboleiro;
import xogo.Xogo;

public final class Servizo extends Propiedade {

    public Servizo(String id, String nome, Double valor){

        this.setId(id);
        this.setNome(nome);
        this.setValor(valor);
        this.setAluguer(Constantes.factorServizo*4);
        this.setHipoteca(valor*0.5);
        this.setHipotecada(false);

    }

    @Override
    public void aluguer(Taboleiro taboleiro) {

        if (this.getId().equals("luz")){
            if (((Servizo)taboleiro.obterCadro("auga")).pertenceAXogador(this.getPropietario()))
                this.setAluguer(Constantes.factorServizo*10);
            else this.setAluguer(Constantes.factorServizo*4);
        } else {
            if (((Servizo)taboleiro.obterCadro("luz")).pertenceAXogador(this.getPropietario()))
                this.setAluguer(Constantes.factorServizo*10);
            else this.setAluguer(Constantes.factorServizo*4);
        }

    }

    @Override
    public void accion(Taboleiro taboleiro, Xogador xogador) throws HipotecaExcepcion {

        if (this.getPropietario() != null){

            if (this.pertenceAXogador(xogador)) return;

            try{
                Double pago = this.getAluguer()*xogador.getAvatar().getUltimoAvance();
                xogador.pagar(pago);
                this.getPropietario().cobrar(pago);

                //estatiscas
                xogador.incrementarPagoDeAlugueres(pago);
                this.getPropietario().incrementarCobroDeAluguere(pago);

                Xogo.getConsola().imprimir(String.format("O xogador %s paga %.2f€ de aluguer.",xogador.getNome(),pago));

            } catch (FortunaInsuficienteExcepcion e){
                xogador.setHipotecar(true);
                xogador.setDebeda(this.getValor());
                Xogo.getConsola().imprimir(e.getMessage());
                throw new HipotecaExcepcion("Debe hipotecar ou vender propiedades.");
            }
        }
    }

    @Override
    public String toString() {

        StringBuilder descricion = new StringBuilder();

        descricion.append(String.format("{\n\ttipo: servizo,\n\tprezo: %.2f,\n\taluguer: %.2f\n}",this.getValor(),this.getAluguer()));

        return descricion.toString();
    }

    @Override
    public String informacionVendaBasica() {
        StringBuilder descricion = new StringBuilder();

        descricion.append(String.format("{\n\ttipo: servizo,\n\tvalor: %.2f\n}",this.getValor()));

        return descricion.toString();
    }
}