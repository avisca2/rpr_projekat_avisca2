package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Korisnik;
import ba.unsa.etf.rpr.exceptions.NeispravniPodaciException;

import java.util.List;

public interface KorisnikDao extends Dao<Korisnik>{
    List<Korisnik> prijavaKorisnika(String korisnickoIme, String sifra) throws NeispravniPodaciException;
}
