package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.TodoStatus;
import ba.unsa.etf.rpr.exceptions.NeispravniPodaciException;

public class DodajIliAzurirajTodoStatusManager {
    public TodoStatus dodajTodoStatus(String naziv) throws NeispravniPodaciException {
        if(naziv.equals("")) {
            throw new NeispravniPodaciException("Niste unijeli naziv");
        }

        return DaoFactory.todoStatusDao().add(new TodoStatus(naziv));
    }

    public TodoStatus azurirajTodoStatus(TodoStatus todoStatus) throws NeispravniPodaciException {
        if(todoStatus.getNaziv().equals("")) {
            throw new NeispravniPodaciException("Niste unijeli naziv");
        }

        return DaoFactory.todoStatusDao().update(todoStatus);
    }
}
