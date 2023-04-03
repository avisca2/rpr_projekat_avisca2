package ba.unsa.etf.rpr.dao;

/**
 * Factory method for singleton implementation of DAOs
 *
 * @author Ajla Višća
 */
public class DaoFactory {

    private static final TodoStatusDao todoStatusDao = TodoStatusDaoSQLImpl.getInstance();
    private static final TodoStavkaDao todoStavkaDao = TodoStavkaDaoSQLImpl.getInstance();
    private static final KorisnikDao korisnikDao = KorisnikDaoSQLImpl.getInstance();
    private DaoFactory(){
    }

    public static TodoStatusDao todoStatusDao(){
        return todoStatusDao;
    }

    public static TodoStavkaDao todoStavkaDao(){
        return todoStavkaDao;
    }

    public static KorisnikDao korisnikDao(){
        return korisnikDao;
    }

}