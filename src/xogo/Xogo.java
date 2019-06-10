package xogo;

import avatares.Avatar;
import cadros.Cadro;
import cadros.CaixaComunidade;
import cadros.Sorte;
import interfaces.Comando;
import interfaces.Constantes;
import xogadores.Xogador;

import java.security.SecureRandom;
import java.util.Collection;
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
        consola.imprimir(Constantes.bold+"~ Comezo da partida ~\n"+Constantes.normal);

        while(!sair){

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

                            if (this.enQuenda.getCobrarSaida() && !this.enQuenda.getPosicion().getId().equals("saida")){
                                this.enQuenda.setCobrarSaida(false);
                                this.enQuenda.getXogador().cobrar(Constantes.salario);
                                Xogo.getConsola().imprimir("O xogador "+enQuenda.getXogador().getNome()+" deu unha volta e cobra "+Constantes.salario+"€");
                            }

                            verTaboleiro(taboleiro);

                        } else Xogo.getConsola().imprimir("Argumento incorrecto");

                    } else Xogo.getConsola().imprimir("Cantidade incorrecta de argumentos, ténteo de novo");

                    break;

                case "listar":

                    if (comando.length==2){

                        if (comando[1].equals("xogadores")) listarXogadores();
                        else if (comando[1].equals("avatares")) listarAvatares();
                        else Xogo.getConsola().imprimir("Argumento incorrecto");

                    } else Xogo.getConsola().imprimir("Cantidade incorrecta de argumentos, ténteo de novo");

                    break;

                case "ver":
                    if (comando.length==2){

                        if (comando[1].equals("taboleiro")){

                            verTaboleiro(taboleiro);

                        }

                    }
                    break;

                case "xogador":

                    Xogo.getConsola().imprimir("{\n\tNome: "+this.enQuenda.getXogador().getNome()+",\n\tAvatar: "+this.enQuenda.getId()+"\n}");

                    break;

                case "sair":

                    if (comando.length==2){
                        if (comando[1].equals("carcere")){
                            sairCarcere();
                        }

                    } else {
                        consola.imprimir("A saír do xogo...");
                        sair = Boolean.TRUE;
                    }
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
        consola.imprimir(Constantes.bold+"~ Inscrición de xogadores ~\n"+Constantes.normal);

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
                                Xogo.getConsola().imprimir("{\n\tNome: "+xogador.getNome()+",\n\tAvatar: "+xogador.getAvatar().getId()+"\n}");

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

        axuda.append(String.format("%s ~ Comandos do menú de inscrición de xogadores ~%s\n",Constantes.bold,Constantes.normal));
        axuda.append(String.format("%s axuda: %s amosa por pantalla esta lista de comandos\n",Constantes.bold,Constantes.normal));
        axuda.append(String.format("%s crear: %s neste menú só se poden crear xogadores, polo que admite como único argumento \"xogador\".\n",Constantes.bold,Constantes.normal));
        axuda.append(" Hai que indicar o nome do xogador e o tipo de avatar desexado\n");
        axuda.append(String.format("%s rematar: %s termina a inscrición de xogadores e comeza a partida\n",Constantes.bold,Constantes.normal));
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

            this.enQuenda.resetDatosTirada();
            this.numQuenda = (this.numQuenda + 1) % this.taboleiro.getAvatares().size();
            this.enQuenda = this.taboleiro.getAvatares().get(this.numQuenda);
            this.debeAcabarQuenda = false;
        }

    }

    private void quendaInicial(){

        this.numQuenda = (this.numQuenda + 1) % this.taboleiro.getAvatares().size();

        this.enQuenda = this.taboleiro.getAvatares().get(this.numQuenda);

    }

    /* interface comando */

    @Override
    public void sairCarcere() {

        if (this.enQuenda.getCarcere()){

            this.enQuenda.getXogador().pagar(Constantes.fianzaCarcere);
            this.enQuenda.setCarcere(true);
            this.enQuenda.setQuendasPrision(0);
            Xogo.getConsola().imprimir("O xogador "+enQuenda.getXogador().getNome()+" sae de prisión");
        }

    }

    @Override
    public void listarXogadores() {

        Collection<Xogador> xogadores = this.taboleiro.getXogadores().values();

        for (Xogador xogador: xogadores){
            Xogo.getConsola().imprimir(xogador.toString());
        }

    }

    @Override
    public void listarAvatares() {

        for (Avatar avatar: this.taboleiro.getAvatares()){
            Xogo.getConsola().imprimir(avatar.toString());
        }

    }

    @Override
    public void describirXogador(String xogador) {

        if (this.taboleiro.existeXogador(xogador)){

            Xogo.getConsola().imprimir(this.taboleiro.obterXogador(xogador).toString());

        } else Xogo.getConsola().imprimir("O xogador do cal se pide unha descrición non existe");

    }

    @Override
    public void describirAvatar(String id) {

        if (taboleiro.existeAvatar(id)){

            taboleiro.obterAvatar(id).toString();

        }

    }

    @Override
    public void describirCadro(String id) {

        Cadro cadro = taboleiro.obterCadro(id);

        if (cadro != null && !(cadro instanceof CaixaComunidade) && !(cadro instanceof Sorte) && !cadro.getId().equals("irCarcere")){
            Xogo.getConsola().imprimir(cadro.toString());

        } else Xogo.getConsola().imprimir("O cadro do cal se quere obter información ou ben non existe ou non se pode obter información del");

    }

    @Override
    public void verTaboleiro(Taboleiro taboleiro) {

        consola.imprimir(taboleiro.toString());
        consola.imprimir(" ");

    }


}
