package edificacions;

public abstract class Edificacion {

    private String id;


    /* getter */
    public String getId() {
        return id;
    }

    /* setter  */
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Edificacion)  return ((Edificacion)obj).getId().equals(this.id);
        return false;

    }
}
