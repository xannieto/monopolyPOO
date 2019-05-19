package cadros;

public abstract class Cadro {

    private String id;
    private String nome;

    /* getters */

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    /* setters */
    public void setId(String id){
        this.id = id;
    }

    public void setNome(String nome){
        this.nome = nome;
    }


    @Override
    public boolean equals(Object obj) {

        if (obj!=null)  return ((Cadro)obj).getId().equals(this.id);

        return false;

    }
}
