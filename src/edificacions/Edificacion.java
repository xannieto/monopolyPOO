package edificacions;

public abstract class Edificacion {

    private String id;
    private Double prezoVenda;

    /* getter */
    public String getId() {
        return id;
    }

    public Double getPrezoVenda() {
        return prezoVenda;
    }

    /* setter  */
    public void setId(String id) {
        this.id = id;
    }

    public void setPrezoVenda(Double prezoVenda) {
        this.prezoVenda = prezoVenda;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Casa || obj instanceof Hotel || obj instanceof PistaDeporte || obj instanceof Piscina)
            return ((Edificacion)obj).getId().equals(this.id);

        return false;

    }
}
