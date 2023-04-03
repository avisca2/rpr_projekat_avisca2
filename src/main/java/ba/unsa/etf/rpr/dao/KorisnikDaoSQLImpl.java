package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Korisnik;
import ba.unsa.etf.rpr.exceptions.NeispravniPodaciException;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL implementation of the DAO
 * @author Ajla Višća
 */
public class KorisnikDaoSQLImpl extends AbstractDao<Korisnik> implements KorisnikDao {
    private static  KorisnikDaoSQLImpl instance = null;
    private KorisnikDaoSQLImpl() {
        super("Korisnik");
    }

    public static KorisnikDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new KorisnikDaoSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    @Override
    public Korisnik row2object(ResultSet rs) throws NeispravniPodaciException {
        try {
            Korisnik korisnik = new Korisnik();
            korisnik.setId(rs.getInt("id"));
            korisnik.setKorisnickoIme(rs.getString("korisnickoIme"));
            korisnik.setSifra(rs.getString("sifra"));
            return korisnik;
        } catch (SQLException e) {
            throw new NeispravniPodaciException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Korisnik object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", object.getId());
        row.put("korisnickoIme", object.getKorisnickoIme());
        row.put("sifra", object.getSifra());
        return row;
    }
    @Override
    public List<Korisnik> prijavaKorisnika(String korisnickoIme, String sifra) throws NeispravniPodaciException {
        return executeQuery("SELECT * FROM Korisnik WHERE korisnickoIme=? AND sifra=?", new Object[]{korisnickoIme, sifra});
    }
}