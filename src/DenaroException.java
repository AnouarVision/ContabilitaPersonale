import java.lang.*;

public class DenaroException extends Exception {
    public DenaroException() {
        super("Errore di denaro.");
    }

    public DenaroException(String message) {
        super(message);
    }
}
