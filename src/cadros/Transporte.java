package cadros;

import interfaces.Constantes;
import xogadores.Xogador;
import xogo.Taboleiro;

public final class Transporte extends Propiedade {

    public Transporte(String id, String nome, Double valor){

        this.setId(id);
        this.setNome(nome);
        this.setValor(valor);
        this.setAluguer(Constantes.salario);
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
            this.setAluguer(Constantes.salario);
        }

    }

    @Override
    public void accion(Taboleiro taboleiro, Xogador xogador) {



    }

    @Override
    public String toString() {

        StringBuilder saida = new StringBuilder();

        saida.append(String.format("{\n\ttipo: transporte,\n\tAluguer: %.2f",this.getAluguer()));

        return new String(saida);
    }

    @Override
    public String informacionVendaBasica() {
        return this.toString();
    }
}