package edificacions;

import cadros.Grupo;
import cadros.Solar;
import xogadores.Xogador;

public abstract class Edificacion {

    private String id;
    private Double custe;
    private Xogador propietario;
    private Solar solar;


    /* getter */
    public String getId() {
        return id;
    }

    public Double getCuste() {
        return custe;
    }

    public Solar getSolar() {
        return solar;
    }

    public Xogador getPropietario() {
        return propietario;
    }

    /* setter  */
    public void setId(String id) {
        this.id = id;
    }

    public void setCuste(Double custe) {
        this.custe = custe;
    }

    public void setSolar(Solar solar) {
        this.solar = solar;
    }

    public void setPropietario(Xogador propietario) {
        this.propietario = propietario;
    }

    @Override
    public String toString() {

        StringBuilder descricion = new StringBuilder();

        descricion.append(String.format("{\n\tid: %s,\n\tpropietario: %s,\n\tgrupo: %s,\n\tcuste: %s\n}",
                this.getId(),this.getPropietario().getNome(),this.getSolar().getId(),this.getSolar().getGrupo().getNome(),this.getCuste()));

        return descricion.toString();
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Casa || obj instanceof Hotel || obj instanceof PistaDeporte || obj instanceof Piscina)
            return ((Edificacion)obj).getId().equals(this.id);

        return false;

    }
}
