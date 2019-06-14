package edificacions;

import cadros.Solar;
import xogadores.Xogador;

public final class Piscina extends Edificacion {

    public Piscina(String id, Solar solar, Xogador propietario, Double custe){

        this.setId(id);
        this.setCuste(custe);
        this.setPropietario(propietario);
        this.setCuste(custe);
        this.setSolar(solar);

    }

}
