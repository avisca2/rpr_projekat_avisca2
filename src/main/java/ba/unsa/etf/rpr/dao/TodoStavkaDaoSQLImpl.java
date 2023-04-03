package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Korisnik;
import ba.unsa.etf.rpr.domain.TodoStavka;
import ba.unsa.etf.rpr.exceptions.NeispravniPodaciException;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL implementation of the DAO
 * @author Ajla Višća
 */
public class TodoStavkaDaoSQLImpl extends AbstractDao<TodoStavka> implements TodoStavkaDao {
    private static  TodoStavkaDaoSQLImpl instance = null;
    private TodoStavkaDaoSQLImpl() {
        super("TodoStavka");
    }

    public static TodoStavkaDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new TodoStavkaDaoSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    @Override
    public TodoStavka row2object(ResultSet rs) throws NeispravniPodaciException {
        try {
            TodoStavka todoStavka = new TodoStavka();
            todoStavka.setId(rs.getInt("id"));
            todoStavka.setNaziv(rs.getString("naziv"));
            todoStavka.setTip(rs.getString("tip"));
            todoStavka.setStatus(DaoFactory.todoStatusDao().getById(rs.getInt("status")));
            todoStavka.setKorisnik(DaoFactory.korisnikDao().getById(rs.getInt("korisnik")));
            return todoStavka;
        } catch (SQLException e) {
            throw new NeispravniPodaciException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(TodoStavka object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", object.getId());
        row.put("naziv", object.getNaziv());
        row.put("tip", object.getTip());
        row.put("status", object.getStatus().getId());
        row.put("korisnik", object.getKorisnik().getId());
        return row;
    }

    @Override
    public List<TodoStavka> ucitajTodoStavkeZaKorisnika(Integer idKorisnika) throws NeispravniPodaciException {
        return executeQuery("SELECT * FROM TodoStavka WHERE korisnik=?", new Object[]{idKorisnika});
    }
}