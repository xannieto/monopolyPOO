package xogo;

import interfaces.Consola;

import java.util.Scanner;

public final class ConsolaNormal implements Consola {

    public ConsolaNormal(){}

    @Override
    public void imprimir(String mensaxe) {
        System.out.println(mensaxe);
    }

    @Override
    public String ler(String descricion) {

        Scanner lector = new Scanner(System.in);

        imprimir(descricion);

        String entrada = lector.nextLine();

        return entrada;
    }
}
