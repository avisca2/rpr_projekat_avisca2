package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.TodoStatus;
import ba.unsa.etf.rpr.domain.TodoStavka;
import ba.unsa.etf.rpr.exceptions.NeispravniPodaciException;

import java.util.List;

public class AppManager {

    public List<TodoStatus> ucitajTodoStatuse() throws NeispravniPodaciException {
        return DaoFactory.todoStatusDao().getAll();
    }

    public List<TodoStavka> ucitajTodoStavke(Integer id) throws NeispravniPodaciException {
        return DaoFactory.todoStavkaDao().ucitajTodoStavkeZaKorisnika(id);
    }

    public void obrisiTodoStatus(Integer id) throws NeispravniPodaciException {
        DaoFactory.todoStatusDao().delete(id);
    }

    public void obrisiTodoStavku(Integer id) throws NeispravniPodaciException {
        DaoFactory.todoStavkaDao().delete(id);
    }
}
