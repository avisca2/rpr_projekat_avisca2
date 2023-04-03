package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.KorisnikDao;
import ba.unsa.etf.rpr.domain.Korisnik;
import ba.unsa.etf.rpr.exceptions.NeispravniPodaciException;

import java.util.List;

public class PrijavaManager {
    public Korisnik prijaviKorisnika(String korisnickoIme, String sifra) throws NeispravniPodaciException {
        if(korisnickoIme.equals("") || sifra.equals("")) {
            throw new NeispravniPodaciException("Niste unijeli korisničko ime ili šifru");
        }

        List<Korisnik> korisnici = DaoFactory.korisnikDao().prijavaKorisnika(korisnickoIme, sifra);
        if(korisnici.size() == 0) return null;
        return korisnici.get(0);
    }
}
