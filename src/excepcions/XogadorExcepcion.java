package excepcions;

public class XogadorExcepcion extends MonopolyExcepcion {

    public XogadorExcepcion(){
        super("Hai un problema relacionado contigo");
    }

    public XogadorExcepcion(String mensaxe){
        super(mensaxe);
    }

}
