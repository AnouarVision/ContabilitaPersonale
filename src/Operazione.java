import java.io.*;

public abstract class Operazione implements Serializable {
    protected float importo;
    protected String motivo;
    protected int codice;

    public Operazione(float importo, String motivo, int codice) {
        this.importo = importo;
        this.motivo = motivo;
        this.codice = codice;
    }

    public String getMotivo() {
        return motivo;
    }

    public float getImporto() {
        return importo;
    }

    public int getCodice() {
        return codice;
    }

    public abstract Operazione clone();

    public abstract String toString();
}
