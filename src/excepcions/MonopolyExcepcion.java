package excepcions;

public class MonopolyExcepcion extends Exception {

    public MonopolyExcepcion(){
        super("Mima Hulio, a saber que fixeches para que saia isto por pantalla.");
    }

    public MonopolyExcepcion(String mensaxe){
        super(mensaxe);
    }

}
