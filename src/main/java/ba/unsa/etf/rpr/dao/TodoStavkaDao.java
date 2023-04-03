package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Korisnik;
import ba.unsa.etf.rpr.domain.TodoStavka;
import ba.unsa.etf.rpr.exceptions.NeispravniPodaciException;

import java.util.List;

public interface TodoStavkaDao extends Dao<TodoStavka> {
    List<TodoStavka> ucitajTodoStavkeZaKorisnika(Integer idKorisnika) throws NeispravniPodaciException;
}
