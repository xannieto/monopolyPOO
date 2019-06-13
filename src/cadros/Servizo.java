package cadros;

import excepcions.ExcepcionFortunaInsuficiente;
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
    public void accion(Taboleiro taboleiro, Xogador xogador) {

        if (this.getPropietario() != null){

            try{
                Double pago = this.getAluguer()*xogador.getAvatar().getUltimoAvance();
                xogador.pagar(pago);
                this.getPropietario().cobrar(pago);
                Xogo.getConsola().imprimir(String.format("O xogador %s paga %.2f€ de aluguer.",xogador.getNome(),pago));

            } catch (ExcepcionFortunaInsuficiente e){
                Xogo.getConsola().imprimir(e.getMessage());
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