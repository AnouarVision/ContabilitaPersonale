public class Incasso extends Operazione {

    public Incasso(float importo, String motivo, int codice) {
        super(importo, motivo, codice);
    }

    public Operazione clone() {
        Incasso in = new Incasso(super.importo, super.motivo, super.codice);
        return in;
    }

    public String toString() {
        String tmp = "Incasso: #"+super.codice+" <"+super.motivo+"> "+super.importo+"$";
        return tmp;
    }
}
