package cadros;

public abstract class Cadro {

    private String id;
    private String nome;

    public Cadro(String id, String nome){

        this.id = id;
        this.nome = nome;

    }

    /* getters */

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj!=null)  return ((Cadro)obj).getId().equals(this.id);

        return false;

    }
}
