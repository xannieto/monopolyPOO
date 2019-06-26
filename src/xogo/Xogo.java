package xogo;

import avatares.Avatar;
import cadros.*;
import edificacions.Edificacion;
import excepcions.EdificacionExcepcion;
import excepcions.FortunaInsuficienteExcepcion;
import excepcions.HipotecaExcepcion;
import interfaces.Comando;
import interfaces.Constantes;
import tratos.Trato;
import tratos.TratoPropiedadeCartos;
import tratos.TratoPropiedadePropiedade;
import xogadores.Xogador;

import java.util.Collection;

public class Xogo implements Comando {

    private static ConsolaNormal consola = new ConsolaNormal();
    private Taboleiro taboleiro;
    private Avatar enQuenda;
    private Integer numQuenda;
    private Boolean debeAcabarQuenda;
    private Boolean acabarDeIrAPrision;

    public Xogo(){

        this.taboleiro = new Taboleiro();
        this.enQuenda = null;
        this.numQuenda = -1;
        this.debeAcabarQuenda = false;
        this.acabarDeIrAPrision = false;

    }

    /* getters */

    public static ConsolaNormal getConsola() {
        return consola;
    }

    /* metodos */

    public void menuPrincipal(){

        crearXogadores();
        Boolean sair = false;

        quendaInicial();

        verTaboleiro(taboleiro);
        consola.imprimir(Constantes.bold+"~ Comezo da partida ~\n"+Constantes.normal);
        consola.imprimir("Para calquera cousa, execute o comando \"axuda\".");

        while(!sair){

            String entrada = Xogo.consola.ler(String.format("$%s> ",this.enQuenda.getXogador().getNome()));

            String[] comando = entrada.split(" ");

            switch (comando[0]){

                case "axuda":

                    if (comando.length==1) axudaMenuPrincipal();
                    else Xogo.getConsola().imprimir("Cantidade incorrecta de argumentos.");

                    break;

                case "acabar":

                    if (comando.length==2){

                        if (comando[1].equals("quenda")){

                            acabarQuenda();

                        } else Xogo.getConsola().imprimir("Argumento incorrecto.");

                    } else Xogo.getConsola().imprimir("Cantidade incorrecta de argumentos.");

                    break;

                case "aceptar":

                    if (comando.length==2){

                        aceptarTrato(comando[1]);

                    } else Xogo.getConsola().imprimir("Cantidade incorrecta de argumentos.");

                    break;

                case "comprar":

                    if (comando.length==2){

                        comprarCadro(comando[1]);

                    } else Xogo.getConsola().imprimir("Cantidade incorrecta de argumentos.");

                    break;

                case "describir":

                    if (comando.length==2){

                        describirCadro(comando[1]);

                    } else if (comando.length==3){

                        if (comando[1].equals("xogador"))   describirXogador(comando[2]);
                        else if (comando[1].equals("avatar"))   describirAvatar(comando[2]);
                        else Xogo.getConsola().imprimir("Argumento incorrecto");

                    } else Xogo.getConsola().imprimir("Cantidade incorrecta de argumentos.");

                    break;

                case "deshipotecar":

                    if (comando.length==2){

                        deshipotecar(comando[1]);

                    } else Xogo.getConsola().imprimir("Cantidade incorrecta de argumentos.");

                    break;

                case "edificar":

                    if (comando.length==2){
                        edificar(comando[1]);

                    } else Xogo.getConsola().imprimir("Cantidade incorrecta de argumentos.");

                    break;

                case "eliminar":

                    if (comando.length==2){
                        eliminarTrato(comando[1]);

                    } else Xogo.getConsola().imprimir("Cantidade incorrecta de argumentos.");

                case "estatisticas":

                    if (comando.length==1){
                        estatisticas();

                    } else if (comando.length==2){
                        estatisticasXogador(comando[1]);

                    } else Xogo.getConsola().imprimir("Cantidade incorrecta de argumentos.");

                    break;

                case "hipotecar":

                    if (comando.length==2){

                        hipotecar(comando[1]);

                    } else Xogo.getConsola().imprimir("Cantidade incorrecta de argumentos.");

                    break;

                case "lanzar":

                    if (comando.length==2){

                        if (comando[1].equals("dados")){

                            lanzarDados();

                        } else Xogo.getConsola().imprimir("Argumento incorrecto.");

                    } else Xogo.getConsola().imprimir("Cantidade incorrecta de argumentos, ténteo de novo.");

                    break;

                case "listar":

                    if (comando.length==2){

                        if (comando[1].equals("xogadores")) listarXogadores();
                        else if (comando[1].equals("avatares")) listarAvatares();
                        else if (comando[1].equals("aVenda"))   listarAVenda();
                        else if (comando[1].equals("edificios"))    listarEdificios();
                        else if (comando[1].equals("tratos"))   listarTratos();
                        else Xogo.getConsola().imprimir("Argumento incorrecto.");

                    } else if (comando.length==3){

                        if (comando[1].equals("edificios")) listarEdificiosGrupo(comando[2]);
                        else Xogo.getConsola().imprimir("Argumento incorrecto.");

                    } else Xogo.getConsola().imprimir("Cantidade incorrecta de argumentos, ténteo de novo");

                    break;

                case "sair":

                    if (comando.length==2){

                        if (comando[1].equals("carcere"))   sairCarcere();
                        else consola.imprimir("Argumento incorrecto.");

                    } else {
                        if (Xogo.getConsola().ler("\n\nEstá vostede seguro de saír do xogo?[Si/Non]: ").equalsIgnoreCase("si")) {
                            consola.imprimir("A saír do xogo...");
                            sair = Boolean.TRUE;
                        }
                    }

                    break;

                case "vender":

                    if (comando.length==4){

                        if (comando[1].equals("casas") || comando[1].equals("hoteis") || comando[1].equals("piscinas") || comando[1].equals("pistas"))
                            venderEdificios(comando[1],comando[2],Integer.valueOf(comando[3]));

                    } else Xogo.getConsola().imprimir("Cantidade incorrecta de argumentos, ténteo de novo");

                    break;

                case "trato":

                    if (comando.length==5){

                        proporTrato(comando);

                    } else Xogo.consola.imprimir("Cantidade incorrecta de argumentos, ténteo de novo.");

                    break;

                case "ver":

                    if (comando.length==2){

                        if (comando[1].equals("taboleiro")) verTaboleiro(taboleiro);
                        else Xogo.getConsola().imprimir("Argumento incorrecto");

                    } else Xogo.getConsola().imprimir("Cantidade incorrecta de argumentos.");

                    break;

                case "xogador":

                    if (comando.length==1)
                        Xogo.getConsola().imprimir("{\n\tNome: "+this.enQuenda.getXogador().getNome()+",\n\tAvatar: "+this.enQuenda.getId()+"\n}");

                    else Xogo.getConsola().imprimir("Cantidade incorrecta de argumentos.");

                    break;

                case "comprarGrupo":

                    if (comando.length==2){
                        comprarGrupo(comando[1]);

                    }

                    break;

                default:
                    consola.imprimir("Comando incorrecto, ténteo de novo.");
            }

            aumentarAsCatroVoltas();
        }


    }

    private void actualizarAluguerGrupo(Grupo grupo){
        if (grupo!=null){
            if (grupo.estaCompradoPorUnPropietario()){
                for (Solar solar: grupo.getSolares())
                    solar.aluguer(taboleiro);
            }
        }
    }

    private void actualizarAluguerTransporte(){
        Transporte t1 = (Transporte) taboleiro.obterCadro("transSur");
        Transporte t2 = (Transporte) taboleiro.obterCadro("transOeste");
        Transporte t3 = (Transporte) taboleiro.obterCadro("transNorte");
        Transporte t4 = (Transporte) taboleiro.obterCadro("transLeste");

        t1.aluguer(taboleiro);
        t2.aluguer(taboleiro);
        t3.aluguer(taboleiro);
        t4.aluguer(taboleiro);
    }

    private void actualizarAluguerServizo(){
        Servizo s1 = (Servizo) taboleiro.obterCadro("luz");
        Servizo s2 = (Servizo) taboleiro.obterCadro("auga");

        s1.aluguer(taboleiro);
        s2.aluguer(taboleiro);
    }

    private void axudaMenuPrincipal(){

        StringBuffer axuda = new StringBuffer();

        axuda.append(String.format("\n%s ~ Comandos do menú principal ~%s\n\n",Constantes.bold,Constantes.normal));
        axuda.append(String.format("%s axuda: %s amosa por pantalla esta lista de comandos.\n",Constantes.bold,Constantes.normal));
        axuda.append(String.format("%s acabar: %s usando o argumento \"quenda\", permite a un xogador rematar a súa quenda actual.\n",Constantes.bold,Constantes.normal));
        axuda.append(String.format("%s aceptar: %s usando como argumento o identificador do trato, permite a un xogador aceptar un trato proposto por outro.\n",Constantes.bold,Constantes.normal));
        axuda.append(String.format("%s comprar: %s usando como argumento o identificador dunha propiedade válida, permite a un xogador adquirir mediante un pago unha propiedade (solar, transporte, servizo).\n",Constantes.bold,Constantes.normal));
        axuda.append(String.format("%s describir: %s usando como argumentos \"xogador\" ou \"avatar\" e un identificador, permite obter información relevante sobre un xogador ou avatar.\n A carenza de argumentos xunto cun identificador dun cadro proporciona a descrición deste.\n",Constantes.bold,Constantes.normal));
        axuda.append(String.format("%s deshipotecar: %s indicando como argumento o identificador da propiedade hipoteca, procederase a deshipotecala.\n",Constantes.bold,Constantes.normal));
        axuda.append(String.format("%s edificar: %s indicando como único argumento (casa, hotel, piscina ou pista) construirase no solar no que estea actualmente o avatar a edificación escollida.\n",Constantes.bold,Constantes.normal));
        axuda.append(String.format("%s eliminar: %s indicando como argumento o identificador dun trato e procédese a borralo.\n",Constantes.bold,Constantes.normal));
        axuda.append(String.format("%s estatisticas: %s sen argumentos amosa as estatísticas do xogo en xeral. Pasando como argumento o nome dun xogador, pódese obter estatísticas personalizadas.\n",Constantes.bold,Constantes.normal));
        axuda.append(String.format("%s hipotecar: %s pasando como argumento unha propiedade do xogador, hipotécase dita propiedade.\n",Constantes.bold,Constantes.normal));
        axuda.append(String.format("%s lanzar: %s usando como único argumento \"dados\", procederase a lanzar os dados e a mover o avatar.\n",Constantes.bold,Constantes.normal));
        axuda.append(String.format("%s listar: %s en función do argumento (xogadores, avatares, aVenda, edificios, tratos) listará tódolos xogadores da partida, tódolos avatares, as propiedades a venda, os edificios contruidos ou os tratos propostos polo xogador.\n",Constantes.bold,Constantes.normal));
        axuda.append(String.format("%s listar edificios: %s engadindo o identificador do grupo, lista todo o construido nese grupo.\n",Constantes.bold,Constantes.normal));
        axuda.append(String.format("%s sair: %s sáese do xogo, con previa confirmación. Se se engade o argumento \"carcere\" enténdese que o xogador desexa saír do cárcere.\n",Constantes.bold,Constantes.normal));
        axuda.append(String.format("%s vender [casas, hoteis, piscinas ou pistas]: %s engadindo o identificador do solar e a cantidade de edificacións a vender, procederase a súa venda mentres haxa existencias.\n",Constantes.bold,Constantes.normal));
        axuda.append(String.format("%s trato xogador: cambiar ([id_solar1 ou cartos], [id_solar2 ou cartos]) : %s o xogador pode propor cambiar un solar por cartos, un solar por outro solar ou cartos por a cambio dun solar.\n Salientar a obviedade de que non se admiten cartos por cartos.\n",Constantes.bold,Constantes.normal));
        axuda.append(String.format("%s ver: %s engadindo como argumento \"taboleiro\", permite visualizar o taboleiro e as posicións actuais dos avatares sobre este.\n",Constantes.bold,Constantes.normal));
        axuda.append(String.format("%s xogador: %s indica o xogador que ten a quenda actualmente.\n",Constantes.bold,Constantes.normal));

        consola.imprimir(axuda.toString());
        axuda.setLength(0);
    }

    private void axudaMenuXogadores(){

        StringBuffer axuda = new StringBuffer();

        axuda.append(String.format("%s ~ Comandos do menú de inscrición de xogadores ~%s\n\n",Constantes.bold,Constantes.normal));
        axuda.append(String.format("%s axuda: %s amosa por pantalla esta lista de comandos.\n",Constantes.bold,Constantes.normal));
        axuda.append(String.format("%s crear: %s neste menú só se poden crear xogadores, polo que admite como único argumento \"xogador\".\n",Constantes.bold,Constantes.normal));
        axuda.append(" Hai que indicar o nome do xogador e o tipo de avatar desexado.\n");
        axuda.append(String.format("%s rematar: %s termina a inscrición de xogadores e comeza a partida.\n",Constantes.bold,Constantes.normal));
        axuda.append(String.format("%s abortar: %s provoca a saída do xogo.\n",Constantes.bold,Constantes.normal));
        axuda.append(String.format("\n%s Deben rexistrarse como mínimo dous xogadores e como máximo seis. Os nomes deben ser únicos %s.\n",Constantes.bold,Constantes.normal));
        axuda.append(String.format(" Hai catro tipos de avatar a escoller:%s coche, sombreiro, pelota e esfinxe.%s\n",Constantes.bold,Constantes.normal));

        consola.imprimir(axuda.toString());
        axuda.setLength(0);
    }

    private void comprarGrupo(String id){

        Grupo grupo = this.taboleiro.obterGrupo(id);

        if (grupo!=null){

            for (Solar solar: grupo.getSolares()){
                solar.comprar(this.enQuenda.getXogador());
                this.enQuenda.getXogador().engadirPropiedade(solar);
            }

            actualizarAluguerGrupo(grupo);
        }
    }

    private void aumentarAsCatroVoltas(){

        if (this.taboleiro.deronCatroVoltas()){

            for (Cadro cadro : this.taboleiro.getCadros().values())
                if (cadro instanceof Servizo || cadro instanceof  Solar || cadro instanceof Transporte)
                    ((Propiedade)cadro).valor();
        }

    }

    private void cambiarQuenda(){

        if (this.enQuenda.podeRematarQuenda()){

            this.acabarDeIrAPrision = false;
            this.enQuenda.resetDatosTirada();
            this.numQuenda = (this.numQuenda + 1) % this.taboleiro.getAvatares().size();
            this.enQuenda = this.taboleiro.getAvatares().get(this.numQuenda);
            this.debeAcabarQuenda = false;
        }

    }

    private void crearXogadores(){
        Boolean sair = false;

        consola.imprimir("\n\nBenvidos ao Monopoly!\n\n");
        axudaMenuXogadores();
        consola.imprimir(Constantes.bold+"~ Inscrición de xogadores ~\n"+Constantes.normal);

        while(!sair.booleanValue()){

            String entrada = Xogo.consola.ler("$> ");

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

                                Xogador xogador = new Xogador(comando[2], Constantes.fortunaInicial, comando[3]);
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

    private void determinarAccionCadro(Cadro cadro){

        Xogador xogador = this.enQuenda.getXogador();

        /* isto é por se non pertence a ninguén a propiedades */
        if ((cadro instanceof Solar || cadro instanceof Transporte || cadro instanceof Servizo) &&  ((Propiedade) cadro).getPropietario()==null){

            if (this.enQuenda.getCobrarSaida() && !this.enQuenda.getPosicion().getId().equals("saida")) {
                this.enQuenda.setCobrarSaida(false);
                xogador.cobrar(Constantes.salario);
                Xogo.consola.imprimir(String.format("O xogador %s deu unha volta completa e cobra %.2f€.", xogador.getNome(), Constantes.salario));
            }

            describirCadro(cadro.getId());
            String resposta = Xogo.getConsola().ler("Vaia, esta propiedade non ten dono. Queres mercala? [Si/Non]: ");

            if (resposta.equalsIgnoreCase("si"))    comprarCadro(cadro.getId());

            if (this.enQuenda.podeRematarQuenda())  this.debeAcabarQuenda = true;
            else Xogo.getConsola().imprimir(String.format("%sSacou dobres%s, cando remate deberá volver lanzar os dados.", Constantes.bold,Constantes.normal));

            if (this.enQuenda.getCarcere()) this.acabarDeIrAPrision = true;

        /* se xa ten un propietario, execútase a acción de cadro */
        } else {

            executarAccionDeCadro();

            /* se por efecto das cartas de sorte ou comunidade hai desprazamentos... */
            if (!cadro.equals(this.enQuenda.getPosicion()) && !this.enQuenda.getPosicion().getId().equals("saida") && !this.enQuenda.getPosicion().getId().equals("carcere")){
                //executarAccionDeCadro();
                determinarAccionCadro(this.enQuenda.getPosicion());
                //describirXogador(xogador.getNome());
                /*if (this.enQuenda.getSacarDobres()) Xogo.getConsola().imprimir(String.format("%sSacou dobres%s, cando remate deberá volver lanzar os dados.", Constantes.bold,Constantes.normal));
                if (this.enQuenda.getCarcere()) this.acabarDeIrAPrision = true;*/

            } else {
                describirXogador(xogador.getNome());
                if (this.enQuenda.getSacarDobres()) Xogo.getConsola().imprimir(String.format("%sSacou dobres%s, cando remate deberá volver lanzar os dados.", Constantes.bold,Constantes.normal));
                if (this.enQuenda.getCarcere()) this.acabarDeIrAPrision = true;
            }

        }

    }

    private void executarAccionDeCadro(){

        Xogador xogador = this.enQuenda.getXogador();

        try {
            if (this.enQuenda.getCobrarSaida() && !this.enQuenda.getPosicion().getId().equals("saida")){
                this.enQuenda.setCobrarSaida(false);
                xogador.cobrar(Constantes.salario);
                xogador.incrementarCobroDeSaida(Constantes.salario);
                Xogo.consola.imprimir(String.format("O xogador %s deu unha volta completa e cobra %.2f€.",xogador.getNome(),Constantes.salario));
            }

            this.enQuenda.getPosicion().accion(this.taboleiro,xogador);

            if (this.enQuenda.podeRematarQuenda())  this.debeAcabarQuenda = true;

        } catch (HipotecaExcepcion e){

            xestionHipotecas();

            /* se xa non ten que seguir hipotecando, pode pagar */
            if (!xogador.getHipotecar()) {
                try {
                    if (this.enQuenda.getCobrarSaida() && !this.enQuenda.getPosicion().getId().equals("saida")){
                        this.enQuenda.setCobrarSaida(false);
                        xogador.cobrar(Constantes.salario);
                        Xogo.consola.imprimir(String.format("O xogador %s deu unha volta completa e cobra %.2f€.",xogador.getNome(),Constantes.salario));
                    }

                    this.enQuenda.getPosicion().accion(this.taboleiro,xogador);

                    if (this.enQuenda.podeRematarQuenda())  this.debeAcabarQuenda = true;

                    xogador.setDebeda(0.0);

                } catch (HipotecaExcepcion h){
                    Xogo.consola.imprimir(h.getMessage());
                }

            } else xestionBancarrota();
        }
    }

    private void quendaInicial(){

        this.numQuenda = (this.numQuenda + 1) % this.taboleiro.getAvatares().size();

        this.enQuenda = this.taboleiro.getAvatares().get(this.numQuenda);

    }

    private void xestionBancarrota(){

        Cadro cadro = this.enQuenda.getPosicion();

        /* en caso de ser o acreedor un xogador */
        if (cadro instanceof Solar || cadro instanceof Servizo || cadro instanceof Transporte){
            Xogador acreedor = ((Propiedade) cadro).getPropietario(), debedor = this.enQuenda.getXogador();

            if (!debedor.getPropiedades().isEmpty()){

                for (Propiedade propiedade: debedor.getPropiedades().values()){
                    acreedor.engadirPropiedade(propiedade);
                    propiedade.setPropietario(acreedor);
                    debedor.quitarPropiedade(propiedade);

                    if (propiedade instanceof Solar){
                        if (!((Solar) propiedade).getEdificacions().isEmpty()){
                            for (Edificacion edificacion: ((Solar) propiedade).getEdificacions().values()){
                                edificacion.setPropietario(acreedor);
                            }
                        }
                    }
                }
            }

        /* no caso de deberlle a banca */
        } else if (cadro instanceof Imposto || cadro instanceof CaixaComunidade || cadro instanceof Sorte){
           Xogador debedor = this.enQuenda.getXogador();

            if (!debedor.getPropiedades().isEmpty()){

                for (Propiedade propiedade: debedor.getPropiedades().values()){
                    propiedade.setPropietario(null);
                    debedor.quitarPropiedade(propiedade);

                    if (propiedade instanceof Solar){
                        if (!((Solar) propiedade).getEdificacions().isEmpty()){
                            for (Edificacion edificacion: ((Solar) propiedade).getEdificacions().values()){
                                ((Solar) propiedade).quitarEdificacion(edificacion.getId());
                            }
                        }
                    }
                }
            }
        }

        /* eliminación do xogador */
        Avatar avatar = this.enQuenda;

        avatar.resetDatosTirada();
        this.debeAcabarQuenda = true;
        this.taboleiro.quitarXogador(avatar.getXogador());

        if (this.taboleiro.getXogadores().size()==1) {
            cambiarQuenda();
            Xogo.consola.imprimir(String.format("%sParabéns, %s! Ao ser o único que queda en pé é declarado gañador desta partida.%s",Constantes.bold,this.enQuenda.getXogador().getNome(),Constantes.normal));
            System.exit(0);
        }

        cambiarQuenda();
    }

    private void xestionHipotecas(){

        Xogador xogador = this.enQuenda.getXogador();

        while(xogador.getFortuna() < xogador.getDebeda()){
            if (!xogador.getPropiedades().isEmpty()){

                describirXogador(xogador.getNome());

                String entrada = Xogo.consola.ler("Escolla que propiedade/edificación quere hipotecar ou vender: ");
                String[] comando = entrada.split(" ");

                /* pequeno menú que só permite hipotecar ou vender cousas */
                switch (comando[0]){
                    case "hipotecar":
                        if (comando.length==2){

                            hipotecar(comando[1]);

                        } else Xogo.consola.imprimir("Cantidade incorrecta de argumentos, ténteo de novo.");

                        break;

                    case "vender":

                        if (comando.length==4){

                            venderEdificios(comando[1],comando[2],Integer.valueOf(comando[3]));

                        } else Xogo.getConsola().imprimir("Cantidade incorrecta de argumentos, ténteo de novo.");

                        break;

                    default:
                        Xogo.consola.imprimir("Comando incorrecto.");
                }

            } else{
                Xogo.consola.imprimir(String.format("O xogador %s non ten máis propiedades para hipotecar ou vender. Está en situación de bancarrota.",xogador.getNome()));
                return;
            }

        }

        /* xa non ten que seguir hipotecando */
        xogador.setHipotecar(false);

    }

    private Double obterValor(String s){

        if (s!=null){
            Double valor;

            try {
                valor = Double.valueOf(s);
            } catch (NumberFormatException e){
                valor = null;
            }

            return valor;
        }
        return null;
    }


    /* interface comando */
    @Override
    public void acabarQuenda() {

        if (this.debeAcabarQuenda && !this.enQuenda.getXogador().getHipotecar()) {

            cambiarQuenda();
            Xogo.getConsola().imprimir("A quenda é para " + this.enQuenda.getXogador().getNome());

        } else if (this.enQuenda.getXogador().getHipotecar()) {
            Xogo.getConsola().imprimir(String.format("O xogador %s non pode rematar a súa quenda debido a que debe axustar contas...",this.enQuenda.getXogador().getNome()));

        } else Xogo.getConsola().imprimir(String.format("O xogador %s non pode rematar a súa quenda aínda.",this.enQuenda.getXogador().getNome()));

    }

    @Override
    public void aceptarTrato(String id) {

        Trato trato = this.enQuenda.getXogador().buscarTrato(id);

        if (trato != null){

            if (!trato.getEmisorTrato().equals(this.enQuenda.getXogador())) {
                trato.accion(taboleiro);

            } else Xogo.getConsola().imprimir("Non podes aceptar os tratos que ti mesmo propós.");

        } else Xogo.getConsola().imprimir("O trato non existe.");
    }

    @Override
    public void comprarCadro(String id) {

        Cadro cadro = this.taboleiro.obterCadro(id);

        if (!this.enQuenda.getPosicion().equals(cadro)){

            Xogo.getConsola().imprimir("O avatar non está no cadro para poder realizar a compra.");

        } else if (cadro instanceof Servizo || cadro instanceof Transporte || cadro instanceof Solar){

            if (((Propiedade) cadro).getPropietario() == null){

                try {

                    this.enQuenda.getXogador().pagar(((Propiedade)cadro).getValor());
                    ((Propiedade) cadro).comprar(this.enQuenda.getXogador());
                    this.enQuenda.getXogador().engadirPropiedade((Propiedade) cadro);
                    ((Propiedade) cadro).aluguer(taboleiro);

                    /* para que cando compren, actualicen os prezos */
                    if (cadro instanceof Solar)
                        actualizarAluguerGrupo(((Solar) cadro).getGrupo());

                    if (cadro instanceof Servizo)
                        actualizarAluguerServizo();

                    if (cadro instanceof Transporte)
                        actualizarAluguerTransporte();

                    Xogo.getConsola().imprimir(String.format("O xogador %s compra %s por %.2f€.",
                            this.enQuenda.getXogador().getNome(),cadro.getNome(),((Propiedade)cadro).getValor(),this.enQuenda.getXogador().getFortuna()));

                    describirXogador(this.enQuenda.getXogador().getNome());

                } catch(FortunaInsuficienteExcepcion e){
                    this.enQuenda.getXogador().setHipotecar(false);
                    Xogo.getConsola().imprimir(e.getMessage());
                }

            } else if (((Propiedade) cadro).pertenceAXogador(this.enQuenda.getXogador())){
                Xogo.getConsola().imprimir("A propiedade xa está comprada.");

            } else Xogo.getConsola().imprimir("O cadro xa ten un propietario, deberá negociar a súa adquisición mediante un trato.");

        } else Xogo.getConsola().imprimir("O cadro que quere comprar non existe ou non é unha propiedade.");

    }

    @Override
    public void describirAvatar(String id) {

        if (taboleiro.existeAvatar(id)){

            Xogo.getConsola().imprimir(taboleiro.obterAvatar(id).toString());

        } else Xogo.getConsola().imprimir("O avatar non existe");

    }

    @Override
    public void describirCadro(String id) {

        Cadro cadro = taboleiro.obterCadro(id);

        if (cadro == null){
            Xogo.getConsola().imprimir("O cadro do cal se quere obter información non existe.");

        } else if (cadro.getId().equals("carcere") || cadro.getId().equals("aparcamento")){
            Xogo.getConsola().imprimir(((Especial)cadro).toString(taboleiro));

        } else if (!(cadro instanceof CaixaComunidade) && !(cadro instanceof Sorte) && !cadro.getId().equals("irCarcere")) {
            Xogo.getConsola().imprimir(cadro.toString());

        } else Xogo.getConsola().imprimir("Non se pode obter información deste cadro.");

    }

    @Override
    public void describirXogador(String xogador) {

        if (this.taboleiro.existeXogador(xogador)){

            Xogo.getConsola().imprimir(this.taboleiro.obterXogador(xogador).toString());

        } else Xogo.getConsola().imprimir("O xogador do cal se pide unha descrición non existe");

    }

    @Override
    public void deshipotecar(String id) {

        Cadro cadro = this.taboleiro.obterCadro(id);

        if (cadro instanceof Servizo || cadro instanceof Transporte || cadro instanceof Solar){
            if (((Propiedade) cadro).pertenceAXogador(this.enQuenda.getXogador())){
                try{
                    ((Propiedade) cadro).deshipotecarPropiedade();

                }catch (HipotecaExcepcion e){
                    Xogo.getConsola().imprimir(e.getMessage());

                }

            } else Xogo.getConsola().imprimir(String.format("%s non se pode deshipotecar %s (%s), debido a que non lle pertence.",this.enQuenda.getXogador().getNome(),cadro.getNome(),cadro.getId()));

        } else Xogo.getConsola().imprimir(String.format("Só se poden deshipotecar propiedades (servizo, transporte, solar), non outro tipo de cadros."));

    }

    @Override
    public void edificar(String edificacion) {

        Cadro cadro = this.enQuenda.getPosicion();

        if (cadro instanceof Solar){
            Grupo grupo = ((Solar) cadro).getGrupo();
            if (grupo.pertenceAXogador(this.enQuenda.getXogador()) || ((Solar) cadro).getVecesCaidas() > 2){
                try {
                    ((Solar) cadro).edificar(taboleiro,edificacion);
                } catch (EdificacionExcepcion e){
                    Xogo.getConsola().imprimir(e.getMessage());
                }

            } else Xogo.getConsola().imprimir("Non se pode edificar en solares dos que non posúe todo o grupo ou non caeu máis de dúas veces nese solar que lle pertence.");

        } else Xogo.getConsola().imprimir("Só se poden edificar edificios en solares.");

    }

    @Override
    public void eliminarTrato(String id) {

        Trato trato = this.enQuenda.getXogador().buscarTrato(id);

        if (trato != null){
            this.enQuenda.getXogador().eliminarTrato(id);
            trato.getReceptorTrato().eliminarTrato(id);

            Xogo.getConsola().imprimir(String.format("Eliminou o trato %s",trato.getId()));

        } else Xogo.getConsola().imprimir("O trato que pretende eliminar non existe.");

    }

    @Override
    public void estatisticas() {

        Xogo.consola.imprimir(taboleiro.estatisticas());

    }

    @Override
    public void estatisticasXogador(String id) {

        Xogador xogador = this.taboleiro.obterXogador(id);

        if (xogador != null){

            Xogo.consola.imprimir(xogador.estatiscas());

        } else Xogo.consola.imprimir("O xogador non existe.");

    }

    @Override
    public void hipotecar(String id) {

        Cadro cadro = this.taboleiro.obterCadro(id);

        if (cadro instanceof Solar || cadro instanceof Servizo || cadro instanceof Transporte){
            if (((Propiedade) cadro).pertenceAXogador(this.enQuenda.getXogador())){

                try {
                    ((Propiedade) cadro).hipotecarPropiedade();

                } catch (HipotecaExcepcion e){
                    Xogo.getConsola().imprimir(e.getMessage());

                }

            } else Xogo.getConsola().imprimir(String.format("%s non se pode deshipotecar %s (%s), debido a que non lle pertence.",this.enQuenda.getXogador().getNome(),cadro.getNome(),cadro.getId()));

        } else Xogo.getConsola().imprimir(String.format("Só se poden hipotecar propiedades (servizo, transporte, solar), non outro tipo de cadros."));

    }

    @Override
    public void lanzarDados(){

        if (this.debeAcabarQuenda || this.enQuenda.getXogador().getHipotecar()) {
            Xogo.getConsola().imprimir(String.format("O xogador %s non pode lanzar os dados.",this.enQuenda.getXogador().getNome()));

        } else if (!this.enQuenda.getCarcere()){
            Integer[] tirada = this.taboleiro.tiradaDados();
            Cadro cadro;
            Xogador xogador = this.enQuenda.getXogador();

            this.enQuenda.mover(this.taboleiro,tirada);
            cadro = this.enQuenda.getPosicion();

            /* ofrecer a súa compra ou cumprir coa acción requerida */
            determinarAccionCadro(cadro);

            verTaboleiro(taboleiro);

        } else if (this.enQuenda.getQuendasPrision() > 0) {

            Xogo.consola.imprimir("O xogador está en prisión, lanzaranse os dados para ver se saca dobres e poder saír sen pagar a fianza.");

            Integer[] tirada = this.taboleiro.tiradaDados();

            if (tirada[0].equals(tirada[1])){
                this.enQuenda.setQuendasPrision(0);
                this.enQuenda.setCarcere(false);
                this.enQuenda.mover(this.taboleiro,tirada);

                Xogo.getConsola().imprimir("Sacou dobres, polo que sae da prisión.");
                determinarAccionCadro(this.enQuenda.getPosicion());

            } else {
                this.enQuenda.setQuendasPrision(this.enQuenda.getQuendasPrision()-1);
                this.debeAcabarQuenda = true;
                this.acabarDeIrAPrision = true; /* isto é por se se lle dá por querer pagar a fianza despois de probar sorte... */

                Xogo.consola.imprimir(String.format("Non sacou dobres, quédanlle %d quendas en prisión.",this.enQuenda.getQuendasPrision()));
            }

            verTaboleiro(taboleiro);

        } else if (this.enQuenda.getQuendasPrision()==0){

            Xogo.consola.imprimir("Debes pagar a fianza da prisión.");
            sairCarcere();

            verTaboleiro(taboleiro);
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
            if ((cadro instanceof Servizo || cadro instanceof Transporte || cadro instanceof Solar )&& ((Propiedade) cadro).getPropietario()==null){
                Xogo.getConsola().imprimir(String.format("%s%s (%s) %s",Constantes.bold,cadro.getNome(),cadro.getId(),Constantes.normal));
                Xogo.getConsola().imprimir(cadro.toString());
            }
        }

    }

    @Override
    public void listarEdificios() {

        if (!taboleiro.getEdificacions().isEmpty()){
            for (Edificacion edificacion: this.taboleiro.getEdificacions())
                Xogo.getConsola().imprimir(edificacion.toString());

        } else Xogo.getConsola().imprimir("Non hai edificacións construídas.");
    }

    @Override
    public void listarEdificiosGrupo(String idGrupo) {

        Grupo grupo = this.taboleiro.obterGrupo(idGrupo);

        if (grupo != null){
            Xogo.getConsola().imprimir(grupo.toString());
            Xogo.getConsola().imprimir(grupo.queSePodeConstruir());

        } else Xogo.getConsola().imprimir("O grupo non existe.");

    }

    @Override
    public void listarXogadores() {

        Collection<Xogador> xogadores = this.taboleiro.getXogadores().values();

        for (Xogador xogador: xogadores){
            Xogo.getConsola().imprimir(xogador.toString());
        }

    }

    @Override
    public void listarTratos() {

        if (this.enQuenda.getXogador().getTratosPropostos().isEmpty())  Xogo.consola.imprimir("Non ten ningún trato proposto.");
        else{
            for (Trato trato: this.enQuenda.getXogador().getTratosPropostos())
                Xogo.consola.imprimir(trato.toString());
        }

    }

    @Override
    public void proporTrato(String[] args) {

        Xogador receptor;
        Trato trato = null;
        String cadea;
        Double[] valor = new Double[2];

        /* xogador receptor */
        args[1] = args[1].replaceFirst(":","");
        receptor = this.taboleiro.obterXogador(args[1]);

        if (this.enQuenda.getXogador().equals(receptor))    Xogo.consola.imprimir("Ante a dúbida de se tes ou non dobre personalidade, non podes propor tratos a ti mesmo.");

        else {

            switch (args.length){

                case 5:

                    /* solares/cartos implicados */
                    args[3] = args[3].replaceFirst("\\(",""); args[3] = args[3].replaceFirst(",","");
                    args[4] = args[4].replaceFirst("\\)","");

                    /* creacion do id */
                    cadea = String.format("trato%d",taboleiro.getIdTrato()); taboleiro.incrementarIdTrato();

                    // para saber se hai numeros
                    valor[0] = obterValor(args[3]);
                    valor[1] = obterValor(args[4]);

                    if (valor[0]==null && valor[1]==null){
                        trato = new TratoPropiedadePropiedade(cadea,this.enQuenda.getXogador(),receptor,args[3],args[4]);
                        cadea = String.format("%s, douche %s e ti dasme %s?",receptor.getNome(),args[3],args[4]);

                    } else if (valor[0]==null){
                        trato = new TratoPropiedadeCartos(cadea,this.enQuenda.getXogador(),receptor,args[3],valor[1],false);
                        cadea = String.format("%s, douche %s e ti dasme %.2f€?",receptor.getNome(),args[3],valor[1]);

                    } else if (valor[1]==null){
                        trato = new TratoPropiedadeCartos(cadea,this.enQuenda.getXogador(),receptor,args[4],valor[0],true);
                        cadea = String.format("%s, douche %.2f€ e ti dasme %s?",receptor.getNome(),valor[0],args[4]);

                    } else Xogo.getConsola().imprimir(String.format("Non ten sentido que propoñas cambiar %.2f€ por %.2f€",valor[0],valor[1]));


                    if (trato != null){
                        receptor.engadirTrato(trato);
                        this.enQuenda.getXogador().engadirTrato(trato);

                        /* impresion da informacion xerada nos if-else anteriores */
                        Xogo.consola.imprimir(cadea);
                    }

                    break;

                /*case 7:

                    break;

                case 9:

                    break;*/

            }

        }


    }

    @Override
    public void sairCarcere() {

        if (this.acabarDeIrAPrision && this.enQuenda.getQuendasPrision()==3)    Xogo.getConsola().imprimir("Non pretenderás saír do cárcere nada máis entrar, eh?.");
        else if (this.acabarDeIrAPrision && this.enQuenda.getQuendasPrision()<3)    Xogo.getConsola().imprimir("\"Non se pode estar en tódolos pratos á vez\".\nAcabas de probar sorte lanzando os dados, polo cal non podes agora querer pagar a fianza.");
        else {
            try {

                if (this.enQuenda.getCarcere()){

                    this.enQuenda.getXogador().pagar(Constantes.fianzaCarcere);
                    this.enQuenda.setCarcere(false);
                    this.enQuenda.setQuendasPrision(0);
                    this.debeAcabarQuenda = false;

                    Xogo.getConsola().imprimir(String.format("O xogador %s sae de prisión, pagando unha fianza de %.2f€. Agora podes lanzar os dados.",
                            enQuenda.getXogador().getNome(),Constantes.fianzaCarcere));

                } else {
                    Xogo.getConsola().imprimir("O xogador "+enQuenda.getXogador().getNome()+" non está en prisión.");
                }

            } catch (FortunaInsuficienteExcepcion e) {
                Xogo.consola.imprimir("Non ten diñeiro suficiente para pagar a fianza. Debe vender ou hipotecar.");
                this.enQuenda.getXogador().setHipotecar(true);

                xestionHipotecas();

                if (!this.enQuenda.getXogador().getHipotecar()){
                    try{
                        this.enQuenda.getXogador().pagar(Constantes.fianzaCarcere);
                        this.enQuenda.setCarcere(false);
                        this.enQuenda.setQuendasPrision(0);
                        this.debeAcabarQuenda = false;

                        Xogo.getConsola().imprimir(String.format("O xogador %s sae de prisión, pagando unha fianza de %.2f€. Agora podes lanzar os dados.",
                                enQuenda.getXogador().getNome(),Constantes.fianzaCarcere,enQuenda.getXogador().getFortuna()));

                    } catch (FortunaInsuficienteExcepcion f){
                        Xogo.getConsola().imprimir(f.getMessage());
                    }

                } else xestionBancarrota();
            }

        }

    }

    @Override
    public void venderEdificios(String edificacion, String solar, Integer cantidade) {

        Cadro cadro = this.taboleiro.obterCadro(solar);

        if (cadro instanceof Solar){
            if (cantidade > 0){

                ((Solar) cadro).venderEdificios(this.taboleiro,edificacion,cantidade);

            } else Xogo.getConsola().imprimir("A cantidade de edificacións a eliminar debe ser maior que cero.");

        } else Xogo.getConsola().imprimir("O solar indicado non existe ou ben é un cadro no que non se pode edificar.");

    }

    @Override
    public void verTaboleiro(Taboleiro taboleiro) {

        consola.imprimir(taboleiro.toString());
        consola.imprimir(" ");

    }

}
