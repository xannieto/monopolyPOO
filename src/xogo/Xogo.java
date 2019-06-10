package xogo;

import avatares.Avatar;
import interfaces.Comando;
import interfaces.Constantes;
import xogadores.Xogador;

import java.security.SecureRandom;
import java.util.Scanner;

public class Xogo implements Comando {

    private static ConsolaNormal consola = new ConsolaNormal();
    private Taboleiro taboleiro;
    private Avatar enQuenda;
    private Integer numQuenda;
    private Boolean debeAcabarQuenda;

    public Xogo(){

        this.taboleiro = new Taboleiro();
        this.enQuenda = null;
        this.numQuenda = -1;
        this.debeAcabarQuenda = false;

    }

    /* getters */

    public static ConsolaNormal getConsola() {
        return consola;
    }

    /* metodos */

    public void menuPrincipal(){

        crearXogadores();

        Scanner orde = new Scanner(System.in);
        Boolean sair = false;

        quendaInicial();

        verTaboleiro(taboleiro);

        while(!sair.booleanValue()){

            System.out.print("$> ");
            String entrada = null;

            entrada = orde.nextLine();

            String[] comando = entrada.split(" ");

            switch (comando[0]){

                case "axuda":

                    axudaMenuPrincipal();

                    break;

                case "acabar":

                    if (comando.length==2){

                        if (comando[1].equals("quenda")){

                            if (this.debeAcabarQuenda){

                                cambiarQuenda();
                                Xogo.getConsola().imprimir("A quenda é para "+this.enQuenda.getXogador().getNome());

                            } else Xogo.getConsola().imprimir("O xogador "+this.enQuenda.getXogador().getNome()+" non pode rematar a súa quenda aínda");

                        }

                    }

                    break;

                case "lanzar":

                    if (comando.length==2){

                        if (comando[1].equals("dados")){

                            if (this.debeAcabarQuenda) {

                                Xogo.getConsola().imprimir("O xogador "+this.enQuenda.getXogador().getNome()+" non pode lanzar os dados");
                                break;
                            }

                            this.enQuenda.mover(this.taboleiro,lanzarDados());
                            this.enQuenda.getPosicion().accion(this.taboleiro,this.enQuenda.getXogador());
                            if (this.enQuenda.podeRematarQuenda())  this.debeAcabarQuenda = true;

                            verTaboleiro(taboleiro);

                        } else Xogo.getConsola().imprimir("Argumento incorrecto");

                    } else Xogo.getConsola().imprimir("Cantidade incorrecta de argumentos, ténteo de novo");

                    break;

                case "ver":
                    if (comando.length==2){

                        if (comando[1].equals("taboleiro")){

                            verTaboleiro(taboleiro);

                        }

                    }
                    break;

                case "quenda":

                    Xogo.getConsola().imprimir("A quenda é para "+this.enQuenda.getXogador().getNome());

                    break;

                case "sair":
                    consola.imprimir("A saír do xogo...");
                    sair = Boolean.TRUE;
                    break;

                default:
                    consola.imprimir("Comando incorrecto, ténteo de novo");
            }


        }


    }

    private void crearXogadores(){

        Scanner orde = new Scanner(System.in);

        Boolean sair = false;

        consola.imprimir("\n\nBenvidos ao Monopoly!\n\n");
        axudaMenuXogadores();
        consola.imprimir(Constantes.bold+"~ Inscrición de xogadores ~"+Constantes.normal);

        while(!sair.booleanValue()){

            System.out.print("$> ");
            String entrada = null;

            entrada = orde.nextLine();

            String[] comando = entrada.split(" ");

            switch (comando[0]){

                case "axuda":

                    axudaMenuXogadores();

                    break;

                case "crear":

                    if (comando.length==4){

                        if (comando[1].equals("xogador")){

                            if (this.taboleiro.getXogadores().size() >= 6){

                                consola.imprimir("\"O que foi a feira perde a cadeira\".\nNon se poden inscribir máis xogadores");

                            } else if (!this.taboleiro.existeXogador(comando[2]) && (comando[3].equalsIgnoreCase("coche") | comando[3].equalsIgnoreCase("sombreiro") | comando[3].equalsIgnoreCase("esfinxe") | comando[3].equalsIgnoreCase("pelota") )){

                                Xogador xogador = new Xogador(comando[2],Constantes.fortunaInicial, comando[3]);
                                this.taboleiro.novoXogador(xogador);

                                xogador.getAvatar().setPosicion(this.taboleiro.obterCadro(1));

                            } else consola.imprimir("Xogador repetido ou avatar inexistente, ténteo de novo");

                        } else consola.imprimir("Comando incorrecto, ténteo de novo");

                    } else consola.imprimir("Cantidade incorrecta de argumentos");

                    break;

                case "rematar":

                    if (this.taboleiro.getXogadores().size() < 2)   consola.imprimir("Non hai xogadores suficientes para comezar a partida");
                    else sair = Boolean.TRUE;

                    break;

                case "abortar":
                    consola.imprimir("A saír do xogo");
                    System.exit(1);
                    break;

                default:
                    consola.imprimir("Comando incorrecto, ténteo de novo");

            }

        }

    }

    private void axudaMenuXogadores(){

        StringBuffer axuda = new StringBuffer();

        axuda.append(String.format("\n%s ~ Comandos do menú de inscrición de xogadores ~%s\n",Constantes.bold,Constantes.normal));
        axuda.append(String.format("%s axuda: %s amosa por pantalla esta lista de comandos\n",Constantes.bold,Constantes.normal));
        axuda.append(String.format("%s crear: %s neste menú só se poden crear xogadores, polo que admite como único argumento \"xogador\".\n",Constantes.bold,Constantes.normal));
        axuda.append(" Hai que indicar o nome do xogador e o tipo de avatar desexado\n");
        axuda.append(String.format("%s rematar: %s amosa por pantalla esta lista de comandos\n",Constantes.bold,Constantes.normal));
        axuda.append(String.format("%s abortar: %s provoca a saída do xogo\n",Constantes.bold,Constantes.normal));
        axuda.append(String.format("\n%s Deben rexistrarse como mínimo dous xogadores e como máximo seis. Os nomes deben ser únicos %s\n",Constantes.bold,Constantes.normal));
        axuda.append(String.format(" Hai catro tipos de avatar a escoller:%s coche, sombreiro, pelota e esfinxe.%s\n",Constantes.bold,Constantes.normal));

        consola.imprimir(axuda.toString());
        axuda.setLength(0);
    }

    private void axudaMenuPrincipal(){

        StringBuffer axuda = new StringBuffer();

        axuda.append(String.format("\n%s ~ Comandos do menú principal ~%s\n",Constantes.bold,Constantes.normal));
        axuda.append(String.format("%s axuda: %s amosa por pantalla esta lista de comandos\n",Constantes.bold,Constantes.normal));
        axuda.append(String.format("%s sair: %s sáese do xogo de forma abrupta\n",Constantes.bold,Constantes.normal));

        consola.imprimir(axuda.toString());
        axuda.setLength(0);
    }

    @Override
    public Integer[] lanzarDados(){

        Integer[] tirada = new Integer[2];
        SecureRandom numAleatorio = new SecureRandom(new byte[1]);

        tirada[0] = numAleatorio.nextInt(6) + 1;
        tirada[1] = numAleatorio.nextInt(6) + 1;

        return tirada;
    }


    private void cambiarQuenda(){

        if (this.enQuenda.podeRematarQuenda()){

            this.numQuenda = (this.numQuenda + 1) % this.taboleiro.getAvatares().size();
            this.enQuenda = this.taboleiro.getAvatares().get(this.numQuenda);
            this.debeAcabarQuenda = false;
        }

    }

    private void quendaInicial(){

        this.numQuenda = (this.numQuenda + 1) % this.taboleiro.getAvatares().size();

        this.enQuenda = this.taboleiro.getAvatares().get(this.numQuenda);

    }


    @Override
    public void verTaboleiro(Taboleiro taboleiro) {

        consola.imprimir(taboleiro.toString());
        consola.imprimir(" ");

    }


}
