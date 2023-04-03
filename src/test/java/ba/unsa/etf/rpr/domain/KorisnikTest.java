package ba.unsa.etf.rpr.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KorisnikTest {

    @Test
    void getId() {
        Korisnik korisnik = new Korisnik("avisca2", "123123123");
        korisnik.setId(1);
        assertEquals(1, korisnik.getId());
    }

    @Test
    void setId() {
        Korisnik korisnik = new Korisnik("avisca2", "123123123");
        korisnik.setId(2);
        assertEquals(2, korisnik.getId());
    }

    @Test
    void getKorisnickoIme() {
        Korisnik korisnik = new Korisnik("avisca2", "123123123");
        assertEquals("avisca2", korisnik.getKorisnickoIme());
    }

    @Test
    void setKorisnickoIme() {
        Korisnik korisnik = new Korisnik("avisca", "123123123");
        korisnik.setKorisnickoIme("avisca2");
        assertEquals("avisca2", korisnik.getKorisnickoIme());
    }

    @Test
    void getSifra() {
        Korisnik korisnik = new Korisnik("avisca", "123123123");
        assertEquals("123123123", korisnik.getSifra());
    }

    @Test
    void setSifra() {
        Korisnik korisnik = new Korisnik("avisca", "123123123");
        korisnik.setSifra("00000000");
        assertEquals("00000000", korisnik.getSifra());
    }

    @Test
    void testToString() {
        Korisnik korisnik = new Korisnik("avisca2", "123123123");
        assertEquals("avisca2", korisnik.toString());
    }
}