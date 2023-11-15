package elidrissi.othmancontrolecontinueandroid.entity;

public class Service {

    private Long id;
    private String nom;

    public Service(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Service() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
