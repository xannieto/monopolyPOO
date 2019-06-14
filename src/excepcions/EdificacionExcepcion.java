package excepcions;

public class EdificacionExcepcion extends MonopolyExcepcion {

    public EdificacionExcepcion(){
        super("Non se poden construir máis edificacións.");
    }

    public EdificacionExcepcion(String mensaxe){
        super(mensaxe);
    }

}
