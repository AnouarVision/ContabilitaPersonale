public class Spesa extends Operazione {

    // Costruttore della classe Spesa
    public Spesa(float importo, String motivo, int codice) {
        super(importo, motivo, codice);
    }

    @Override
    public Operazione clone() {
        Spesa sp = new Spesa(super.importo, super.motivo, super.codice);
        return sp;
    }

    @Override
    public String toString() {
        String tmp = "Spesa: #" + super.codice + " <" + super.motivo + "> " + super.importo + "$";
        return tmp;
    }
}