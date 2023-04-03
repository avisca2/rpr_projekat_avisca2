package ba.unsa.etf.rpr.domain;

public class TodoStatus implements Idable {

    private int id;
    private String naziv;

    public TodoStatus() {
    }

    public TodoStatus(String naziv) {
        this.naziv = naziv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return naziv;
    }
}
