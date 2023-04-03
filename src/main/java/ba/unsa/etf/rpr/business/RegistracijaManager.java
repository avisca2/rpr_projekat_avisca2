package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Korisnik;
import ba.unsa.etf.rpr.exceptions.NeispravniPodaciException;

public class RegistracijaManager {

    public Korisnik registrujKorisnika(String korisnickoIme, String sifra, String potvrdaSifre) throws NeispravniPodaciException {
        if( korisnickoIme.equals("") || sifra.equals("") || potvrdaSifre.equals("") ) {
            throw new NeispravniPodaciException("Niste unijeli korisničko ime, šifru ili potvrdu šifre");
        }

        if(sifra.length() < 8 || potvrdaSifre.length() < 8) {
            throw new NeispravniPodaciException("Šifra mora biti duga minimalno 8 karaktera");
        }

        if(!sifra.equals(potvrdaSifre)) {
            throw new NeispravniPodaciException("Šifra i potvrda šifre se ne poklapaju");
        }

        return DaoFactory.korisnikDao().add(new Korisnik(korisnickoIme, sifra));
    }

}
