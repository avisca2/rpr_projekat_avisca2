package ba.unsa.etf.rpr.exceptions;

public class NeispravniPodaciException extends Exception {

    public NeispravniPodaciException(String message, Throwable cause) {
        super(message, cause);
    }

    public NeispravniPodaciException(String message) {
        super(message);
    }
}
