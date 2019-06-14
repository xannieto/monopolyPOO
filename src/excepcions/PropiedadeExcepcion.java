package excepcions;


public class PropiedadeExcepcion extends MonopolyExcepcion{

    public PropiedadeExcepcion(){
        super("Algún problema tes con esta propiedade, Hulio");
    }

    public PropiedadeExcepcion(String mensaxe){
        super(mensaxe);
    }

}
