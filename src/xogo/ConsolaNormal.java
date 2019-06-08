package xogo;

import interfaces.Consola;

import java.util.Scanner;

public final class ConsolaNormal implements Consola {

    @Override
    public void imprimir(String mensaxe) {
        System.out.println(mensaxe);
    }

    @Override
    public String ler(String descricion) {

        Scanner lector = new Scanner(System.in);
        String entrada = null;

        imprimir(descricion);

        entrada = lector.nextLine();

        return entrada;
    }
}