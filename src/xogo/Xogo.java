package xogo;

import avatares.Avatar;
import cadros.*;
import excepcions.ExcepcionFortunaInsuficiente;
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

                    } else Xogo.getConsola().imprimir("Cantidade incorrecta de argumentos");

                    break;

                case "comprar":

                    if (comando.length==2){

                        comprarCadro(comando[1]);

                    } else Xogo.getConsola().imprimir("Cantidade incorrecta de argumentos");

                    break;

                case "describir":

                    if (comando.length==2){

                        describirCadro(comando[1]);

                    } else if (comando.length==3){

                        if (comando[1].equals("xogador"))   describirXogador(comando[2]);
                        else if (comando[1].equals("avatar"))   describirAvatar(comando[2]);
                        else Xogo.getConsola().imprimir("Argumento incorrecto");

                    } else Xogo.getConsola().imprimir("Cantidade incorrecta de argumentos");

                    break;

                case "lanzar":

                    if (comando.length==2){

                        if (comando[1].equals("dados")){

                            lanzarDados();

                            verTaboleiro(taboleiro);

                        } else Xogo.getConsola().imprimir("Argumento incorrecto");

                    } else Xogo.getConsola().imprimir("Cantidade incorrecta de argumentos, ténteo de novo");

                    break;

                case "listar":

                    if (comando.length==2){

                        if (comando[1].equals("xogadores")) listarXogadores();
                        else if (comando[1].equals("avatares")) listarAvatares();
                        else Xogo.getConsola().imprimir("Argumento incorrecto");
                        verTaboleiro(taboleiro);

                    } else Xogo.getConsola().imprimir("Cantidade incorrecta de argumentos, ténteo de novo");

                    break;

                case "ver":
                    if (comando.length==2){

                        if (comando[1].equals("taboleiro")) verTaboleiro(taboleiro);
                        else Xogo.getConsola().imprimir("Argumento incorrecto");

                    } else Xogo.getConsola().imprimir("Cantidade incorrecta de argumentos");

                    break;

                case "xogador":

                    Xogo.getConsola().imprimir("{\n\tNome: "+this.enQuenda.getXogador().getNome()+",\n\tAvatar: "+this.enQuenda.getId()+"\n}");

                    break;

                case "sair":

                    if (comando.length==2){

                        if (comando[1].equals("carcere"))   sairCarcere();

                    } else {
                        consola.imprimir("A saír do xogo...");
                        sair = Boolean.TRUE;
                    }
                    
                    break;

                default:
                    consola.imprimir("Comando incorrecto, ténteo de novo");
            }

            aumentarAsCatroVoltas();
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

    private void aumentarAsCatroVoltas(){

        if (this.taboleiro.deronCatroVoltas()){

            for (Cadro cadro : this.taboleiro.getCadros().values())
                if (cadro instanceof Servizo || cadro instanceof  Solar || cadro instanceof Transporte)
                    ((Propiedade)cadro).valor();
        }

    }

    /* interface comando */

    @Override
    public void lanzarDados(){

        if (this.debeAcabarQuenda) {

            Xogo.getConsola().imprimir("O xogador "+this.enQuenda.getXogador().getNome()+" non pode lanzar os dados");

        } else if (!this.enQuenda.getPosicion().equals("carcere")){

            Integer[] tirada = this.taboleiro.tiradaDados();
            Cadro cadro;

            this.enQuenda.mover(this.taboleiro,tirada);

            cadro = this.enQuenda.getPosicion();

            if (cadro instanceof Solar || cadro instanceof Transporte || cadro instanceof Servizo){

                describirCadro(cadro.getId());
                String resposta = Xogo.getConsola().ler("Vaia, esta propiedade non ten dono. Queres mercala? [Si/Non]: ");
                if (resposta.equalsIgnoreCase("si")) comprarCadro(cadro.getId());

            }
            else this.enQuenda.getPosicion().accion(this.taboleiro,this.enQuenda.getXogador());

            if (this.enQuenda.podeRematarQuenda())  this.debeAcabarQuenda = true;

            if (this.enQuenda.getCobrarSaida() && !this.enQuenda.getPosicion().getId().equals("saida")){
                this.enQuenda.setCobrarSaida(false);
                this.enQuenda.getXogador().cobrar(Constantes.salario);
                Xogo.getConsola().imprimir("O xogador "+enQuenda.getXogador().getNome()+" deu unha volta e cobra "+Constantes.salario+"€");
            }

        } else if (this.enQuenda.getQuendasPrision() > 0) {

            Integer[] tirada = this.taboleiro.tiradaDados();

            if (tirada[0].equals(tirada[1])){
                this.enQuenda.setQuendasPrision(0);
                this.enQuenda.setCarcere(false);
                this.enQuenda.mover(this.taboleiro,tirada);
            }

        }

    }

    @Override
    public void sairCarcere() {

        try {

            if (this.enQuenda.getCarcere()){

                this.enQuenda.getXogador().pagar(Constantes.fianzaCarcere);
                this.enQuenda.setCarcere(true);
                this.enQuenda.setQuendasPrision(0);
                Xogo.getConsola().imprimir("O xogador "+enQuenda.getXogador().getNome()+" sae de prisión");

            } else {
                Xogo.getConsola().imprimir("O xogador "+enQuenda.getXogador().getNome()+" non está en prisión");
            }

        } catch (ExcepcionFortunaInsuficiente e){
            Xogo.getConsola().imprimir(e.getMessage());
        } catch (Exception e){
            Xogo.consola.imprimir(e.getMessage());
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
    public void listarAVenda() {

        for (Cadro cadro: this.taboleiro.getCadros().values()){
            if (cadro instanceof Propiedade && ((Propiedade) cadro).getPropietario()==null){
                cadro.toString();
            }
        }

    }

    @Override
    public void comprarCadro(String id) {

        Cadro cadro = this.taboleiro.obterCadro(id);

        if (!this.enQuenda.getPosicion().equals(cadro)){

            Xogo.getConsola().imprimir("O avatar non está no cadro para poder realizar a compra");

        } else if (cadro instanceof Servizo || cadro instanceof Transporte || cadro instanceof Solar){

            if (((Propiedade) cadro).getPropietario() == null){

                try {

                    this.enQuenda.getXogador().pagar(((Propiedade)cadro).getValor());
                    ((Propiedade) cadro).comprar(this.enQuenda.getXogador());
                    this.enQuenda.getXogador().engadirPropiedade((Propiedade) cadro);
                    Xogo.getConsola().imprimir(String.format("O xogador %s compra %s por %.2f€. A súa fortuna actual é de %.2f€",
                            this.enQuenda.getXogador().getNome(),cadro.getNome(),((Propiedade)cadro).getValor(),this.enQuenda.getXogador().getFortuna()));

                } catch(ExcepcionFortunaInsuficiente e){
                    Xogo.getConsola().imprimir(e.getMessage());
                }

            } else Xogo.getConsola().imprimir("O cadro xa ten un propietario, deberá negociar a súa adquisición mediante un trato");

        } else Xogo.getConsola().imprimir("O cadro que quere comprar non existe ou non é unha propiedade");

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

        } else Xogo.getConsola().imprimir("O avatar non existe");

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
