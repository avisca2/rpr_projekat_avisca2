package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class TodoStavka implements Idable {

    private int id;
    private String naziv;
    private String tip;
    private TodoStatus status;
    private Korisnik korisnik;

    public TodoStavka() {
    }

    public TodoStavka(String naziv, String tip, TodoStatus status, Korisnik korisnik) {
        this.naziv = naziv;
        this.tip = tip;
        this.status = status;
        this.korisnik = korisnik;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public TodoStatus getStatus() {
        return status;
    }

    public void setStatus(TodoStatus status) {
        this.status = status;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoStavka that = (TodoStavka) o;
        return id == that.id;
    }
}
