package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.TodoStatus;
import ba.unsa.etf.rpr.exceptions.NeispravniPodaciException;

import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL implementation of the DAO
 * @author Ajla Višća
 */
public class TodoStatusDaoSQLImpl extends AbstractDao<TodoStatus> implements TodoStatusDao {
    private static  TodoStatusDaoSQLImpl instance = null;
    private TodoStatusDaoSQLImpl() {
        super("TodoStatus");
    }

    public static TodoStatusDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new TodoStatusDaoSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    @Override
    public TodoStatus row2object(ResultSet rs) throws NeispravniPodaciException {
        try {
            TodoStatus todoStatus = new TodoStatus();
            todoStatus.setId(rs.getInt("id"));
            todoStatus.setNaziv(rs.getString("naziv"));
            return todoStatus;
        } catch (SQLException e) {
            throw new NeispravniPodaciException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(TodoStatus object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", object.getId());
        row.put("naziv", object.getNaziv());
        return row;
    }
}