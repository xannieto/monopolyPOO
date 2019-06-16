package excepcions;

public final class BancarrotaExcepcion extends XogadorExcepcion {

    public BancarrotaExcepcion(){
        super("Non hai fortuna suficiente nin sequera propiedades das cales obter diñeiro.");
    }

    public BancarrotaExcepcion(String mensaxe){
        super(mensaxe);
    }

}
