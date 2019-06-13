package cadros;

import excepcions.ExcepcionFortunaInsuficiente;
import interfaces.Constantes;
import xogadores.Xogador;
import xogo.Taboleiro;
import xogo.Xogo;

public final class Transporte extends Propiedade {

    public Transporte(String id, String nome, Double valor){

        this.setId(id);
        this.setNome(nome);
        this.setValor(valor);
        this.setAluguer(Constantes.transporte);
        this.setHipotecada(false);
        this.setHipoteca(valor*0.5);

    }

    @Override
    public void aluguer(Taboleiro taboleiro) {

        if (this.getPropietario()!=null){

            Integer conta = 1;

            for (Cadro cadro: taboleiro.getCadros().values())
                if (cadro instanceof Transporte && ((Transporte) cadro).getPropietario().equals(this.getPropietario()))
                    conta++;

            this.setAluguer(Constantes.transporte*conta*0.25);

        } else {
            this.setAluguer(Constantes.transporte);
        }

    }

    @Override
    public void accion(Taboleiro taboleiro, Xogador xogador) {

        if (this.getPropietario() != null){

            try{
                xogador.pagar(this.getAluguer());
                Xogo.getConsola().imprimir(String.format("O xogador %s paga un aluguer de %.2f€",xogador.getNome(),this.getAluguer()));
            } catch (ExcepcionFortunaInsuficiente e){
                Xogo.getConsola().imprimir(e.getMessage());
            }

        }

    }

    @Override
    public String toString() {

        StringBuilder saida = new StringBuilder();

        saida.append(String.format("{\n\ttipo: transporte,\n\tvalor: %.2f,\n\taluguer: %.2f\n}",this.getValor(),this.getAluguer()));

        return new String(saida);
    }

    @Override
    public String informacionVendaBasica() {
        return this.toString();
    }
}