package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.TodoStatus;
import ba.unsa.etf.rpr.domain.TodoStavka;
import ba.unsa.etf.rpr.exceptions.NeispravniPodaciException;

import java.util.List;

public class DodajIliAzurirajTodoStavkuManager {

    public List<TodoStatus> ucitajTodoStatuse() throws NeispravniPodaciException {
        return DaoFactory.todoStatusDao().getAll();
    }
    public TodoStavka dodajTodoStavku(TodoStavka todoStavka) throws NeispravniPodaciException {
        if(todoStavka.getNaziv().equals("") || todoStavka.getStatus() == null) {
            throw new NeispravniPodaciException("Niste unijeli naziv, tip ili status");
        }

        return DaoFactory.todoStavkaDao().add(todoStavka);
    }

    public TodoStavka azurirajTodoStavku(TodoStavka todoStavka) throws NeispravniPodaciException {
        if(todoStavka.getNaziv().equals("") || todoStavka.getStatus() == null) {
            throw new NeispravniPodaciException("Niste unijeli naziv, tip ili status");
        }

        return DaoFactory.todoStavkaDao().update(todoStavka);
    }
}
