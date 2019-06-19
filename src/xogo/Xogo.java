package xogo;

import avatares.Avatar;
import cadros.*;
import edificacions.Edificacion;
import excepcions.EdificacionExcepcion;
import excepcions.FortunaInsuficienteExcepcion;
import excepcions.HipotecaExcepcion;
import interfaces.Comando;
import interfaces.Constantes;
import xogadores.Xogador;

import java.util.Collection;

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
        Boolean sair = false;

        quendaInicial();

        verTaboleiro(taboleiro);
        consola.imprimir(Constantes.bold+"~ Comezo da partida ~\n"+Constantes.normal);

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

                case "hipotecar":

                    if (comando.length==2){

                        hipotecar(comando[1]);

                    } else Xogo.getConsola().imprimir("Cantidade incorrecta de argumentos.");

                    break;

                case "lanzar":

                    if (comando.length==2){

                        if (comando[1].equals("dados")){

                            lanzarDados();

                            verTaboleiro(taboleiro);

                        } else Xogo.getConsola().imprimir("Argumento incorrecto.");

                    } else Xogo.getConsola().imprimir("Cantidade incorrecta de argumentos, ténteo de novo.");

                    break;

                case "listar":

                    if (comando.length==2){

                        verTaboleiro(taboleiro);
                        if (comando[1].equals("xogadores")) listarXogadores();
                        else if (comando[1].equals("avatares")) listarAvatares();
                        else if (comando[1].equals("aVenda"))   listarAVenda();
                        else if (comando[1].equals("edificios"))    listarEdificios();
                        else Xogo.getConsola().imprimir("Argumento incorrecto.");

                    } else if (comando.length==3){

                        if (comando[1].equals("edificios")) listarEdificiosGrupo(comando[2]);
                        else Xogo.getConsola().imprimir("Argumento incorrecto.");

                    } else Xogo.getConsola().imprimir("Cantidade incorrecta de argumentos, ténteo de novo");

                    break;

                case "sair":

                    if (comando.length==2){

                        if (comando[1].equals("carcere"))   sairCarcere();

                    } else {
                        if (Xogo.getConsola().ler("\n\nEstá vostede seguro de saír do xogo?[Si/Non]: ").equalsIgnoreCase("si"))
                            consola.imprimir("A saír do xogo...");
                        sair = Boolean.TRUE;
                    }

                    break;

                case "vender":

                    if (comando.length==4){

                        if (comando[1].equals("casas") || comando[1].equals("hoteis") || comando[1].equals("piscinas") || comando[1].equals("pistas"))
                            venderEdificios(comando[1],comando[2],Integer.valueOf(comando[3]));

                    } else Xogo.getConsola().imprimir("Cantidade incorrecta de argumentos, ténteo de novo");

                    break;

                case "ver":

                    if (comando.length==2){

                        if (comando[1].equals("taboleiro")) verTaboleiro(taboleiro);
                        else Xogo.getConsola().imprimir("Argumento incorrecto");

                    } else Xogo.getConsola().imprimir("Cantidade incorrecta de argumentos");

                    break;

                case "xogador":

                    if (comando.length==1)
                        Xogo.getConsola().imprimir("{\n\tNome: "+this.enQuenda.getXogador().getNome()+",\n\tAvatar: "+this.enQuenda.getId()+"\n}");

                    else Xogo.getConsola().imprimir("Cantidade incorrecta de argumentos");

                    break;

                default:
                    consola.imprimir("Comando incorrecto, ténteo de novo.");
            }

            aumentarAsCatroVoltas();
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

    private void executarAccionDeCadro(){

        Xogador xogador = this.enQuenda.getXogador();

        try {
            if (this.enQuenda.getCobrarSaida() && !this.enQuenda.getPosicion().getId().equals("saida")){
                this.enQuenda.setCobrarSaida(false);
                xogador.cobrar(Constantes.salario);
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

    private void xestionBancarrota(){

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

                    Xogo.getConsola().imprimir(String.format("O xogador %s compra %s por %.2f€. A súa fortuna actual é de %.2f€.",
                            this.enQuenda.getXogador().getNome(),cadro.getNome(),((Propiedade)cadro).getValor(),this.enQuenda.getXogador().getFortuna()));

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
            Xogo.getConsola().imprimir("O xogador " + this.enQuenda.getXogador().getNome() + " non pode lanzar os dados.");

        } else if (!this.enQuenda.getCarcere()){
            Integer[] tirada = this.taboleiro.tiradaDados();
            Cadro cadro;
            Xogador xogador = this.enQuenda.getXogador();

            this.enQuenda.mover(this.taboleiro,tirada);
            cadro = this.enQuenda.getPosicion();

            /* isto é por se non pertence a ninguén a propiedades */
            if ((cadro instanceof Solar || cadro instanceof Transporte || cadro instanceof Servizo) &&  ((Propiedade) cadro).getPropietario()==null){

                if (this.enQuenda.getCobrarSaida() && !this.enQuenda.getPosicion().getId().equals("saida")) {
                    this.enQuenda.setCobrarSaida(false);
                    xogador.cobrar(Constantes.salario);
                    Xogo.consola.imprimir(String.format("O xogador %s deu unha volta completa e cobra %.2f€.", xogador.getNome(), Constantes.salario));
                }

                describirCadro(cadro.getId());
                String resposta = Xogo.getConsola().ler("Vaia, esta propiedade non ten dono. Queres mercala? [Si/Non]: ");

                if (resposta.equalsIgnoreCase("si")) comprarCadro(cadro.getId());

                if (this.enQuenda.podeRematarQuenda())  this.debeAcabarQuenda = true;

            /* se xa ten un propietario, execútase a acción de cadro */
            } else {

                executarAccionDeCadro();

                /* se por efecto das cartas de sorte ou comunidade hai desprazamentos... */
                if (!cadro.equals(this.enQuenda.getPosicion()) && !this.enQuenda.getPosicion().getId().equals("saida")){
                    executarAccionDeCadro();
                }

            }

        } else if (this.enQuenda.getQuendasPrision() > 0) {

            Xogo.consola.imprimir("O xogador está en prisión, lanzaranse os dados para ver se saca dobres e poder saír sen pagar a fianza.");

            Integer[] tirada = this.taboleiro.tiradaDados();

            if (tirada[0].equals(tirada[1])){
                this.enQuenda.setQuendasPrision(0);
                this.enQuenda.setCarcere(false);
                this.enQuenda.mover(this.taboleiro,tirada);
            } else {
                this.enQuenda.setQuendasPrision(this.enQuenda.getQuendasPrision()-1);
                this.debeAcabarQuenda = true;
                Xogo.consola.imprimir(String.format("Non sacou dobres, quédanlle %d quendas en prisión",this.enQuenda.getQuendasPrision()));
            }
        } else if (this.enQuenda.getQuendasPrision()==0){

            Xogo.consola.imprimir("Debes pagar a fianza da prisión.");
            sairCarcere();
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

        } else Xogo.getConsola().imprimir("Non hai edificación construídas.");
    }

    @Override
    public void listarEdificiosGrupo(String idGrupo) {

        Grupo grupo = this.taboleiro.obterGrupo(idGrupo);

        if (grupo != null){
            if (!grupo.getSolares().isEmpty()){
                for (Solar solar: grupo.getSolares()){
                    if (!solar.getEdificacions().isEmpty()){
                        for (Edificacion edificacion: solar.getEdificacions().values())
                            Xogo.getConsola().imprimir(edificacion.toString());

                    }

                }

            }

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
    public void sairCarcere() {

        try {

            if (this.enQuenda.getCarcere()){

                this.enQuenda.getXogador().pagar(Constantes.fianzaCarcere);
                this.enQuenda.setCarcere(false);
                this.enQuenda.setQuendasPrision(0);
                Xogo.getConsola().imprimir(String.format("O xogador %s sae de prisión, pagando unha fianza de %.2f€. A súa fortuna actual é de %.2f€.",
                        enQuenda.getXogador().getNome(),Constantes.fianzaCarcere,enQuenda.getXogador().getFortuna()));

            } else {
                Xogo.getConsola().imprimir("O xogador "+enQuenda.getXogador().getNome()+" non está en prisión");
            }

        } catch (FortunaInsuficienteExcepcion e) {
            Xogo.consola.imprimir("Non ten diñeiro suficiente para pagar a fianza. Debe vender ou hipotecar.");
            this.enQuenda.getXogador().getHipotecar();
            xestionHipotecas();

            if (!this.enQuenda.getXogador().getHipotecar()){
                try{
                    this.enQuenda.getXogador().pagar(Constantes.fianzaCarcere);
                    this.enQuenda.setCarcere(false);
                    this.enQuenda.setQuendasPrision(0);
                    Xogo.getConsola().imprimir(String.format("O xogador %s sae de prisión, pagando unha fianza de %.2f€. A súa fortuna actual é de %.2f€.",
                            enQuenda.getXogador().getNome(),Constantes.fianzaCarcere,enQuenda.getXogador().getFortuna()));

                } catch (FortunaInsuficienteExcepcion f){
                    Xogo.getConsola().imprimir(f.getMessage());
                }

            } else xestionBancarrota();
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
