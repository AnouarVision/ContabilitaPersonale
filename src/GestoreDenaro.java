import java.io.*;
import java.util.HashMap;
import java.util.Iterator;

public class GestoreDenaro {
    /*
    Si intende sviluppare una semplice applicazione Java che consenta di gestire gli
    incassi e le spese personali. A questo scopo devono essere progettate e sviluppate
    le classi che implementano una collezione di «operazioni» (incassi o spese a cui viene
    automaticamente assegnato un codice di operazione) e ne realizzano la gestione eseguendo
    le seguenti azioni:
      - nuovo incasso specificando importo e motivo;
      - nuova spesa specificando importo e motivo;
      - eliminazione di un’operazione precedente inserita;
      - salvataggio su file della lista delle operazioni;
      - caricamento da file della lista delle operazioni;
      - visualizzazione di tutte le operazioni effettuate.
     */
    private HashMap<Integer, Operazione> lista;
    int codice_operazione;

    public GestoreDenaro () {
        lista = new HashMap<Integer, Operazione>();
        codice_operazione = 0;
    }

    public int incasso(float importo, String motivo) throws DenaroException {

        if (importo>0) {
            codice_operazione++;
            Incasso in = new Incasso(importo, motivo, codice_operazione);
            if (lista.containsKey(in.getCodice()))
                throw new DenaroException();
            lista.put(in.getCodice(), in);
            return codice_operazione;
        }
        else
            throw new DenaroException();
    }

    public int spesa(float importo, String motivo) throws DenaroException {
        if (importo>0) {
            codice_operazione++;
            Spesa sp = new Spesa(importo, motivo, codice_operazione);
            if (lista.containsKey(sp.getCodice()))
                throw new DenaroException();
            lista.put(sp.getCodice(), sp);
            return codice_operazione;
        }
        else
            throw new DenaroException();
    }

    public float valore() {
        Operazione op;
        float val = 0;

        for (Iterator<Operazione> it = lista.values().iterator(); it.hasNext();) {
            op = it.next();
            if (op instanceof Incasso)
                val += op.getImporto();
            else
                val -= op.getImporto();
        }

        return val;
    }

    public Operazione getOperazione(int codice) throws DenaroException {
        Operazione op;

        if (!lista.containsKey(codice))
            throw new DenaroException();

        op = lista.get(codice);

        return op.clone();
    }

    public Operazione getOperazione(String motivo) throws DenaroException {
        Operazione op;

        for (Iterator<Operazione> it = lista.values().iterator(); it.hasNext();) {
            op = it.next();
            if (op.getMotivo().equalsIgnoreCase(motivo))
                return op.clone();
        }

        throw new DenaroException();
    }

    public void delOperazione(int codice) throws DenaroException {
        if (!lista.containsKey(codice))
            throw new DenaroException();
        lista.remove(codice);
    }

    public Operazione[] getOperazioni() throws DenaroException {
        int i = 0;
        Operazione op[] = new Operazione[lista.size()];

        if (lista.size() == 0)
            throw new DenaroException();

        for (Iterator<Operazione> it = lista.values().iterator(); it.hasNext();) {
            op[i] = it.next();
            i++;
        }

        return op;
    }

    public void writeTofile(String filename) throws IOException {
        FileOutputStream file = new FileOutputStream(filename);
        ObjectOutputStream output = new ObjectOutputStream(file);
        output.writeInt(codice_operazione);
        output.writeObject(lista);
        file.close();
    }

    public void readFromfile(String filename) throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream(filename);
        ObjectInputStream input = new ObjectInputStream(file);
        codice_operazione = input.readInt();
        lista = (HashMap<Integer, Operazione>)input.readObject();
        file.close();
    }


    public static void main (String args[]) throws IOException, ClassNotFoundException, DenaroException {
        GestoreDenaro conto_corrente = new GestoreDenaro();
        Operazione[] op;
        int codice;

        codice = conto_corrente.incasso(1000, "buy apple");
        System.out.println("Operazione #"+codice);
        codice = conto_corrente.spesa(500, "sell apple");
        System.out.println("Operazione #"+codice);
        System.out.println("Valore = "+conto_corrente.valore());
        op = conto_corrente.getOperazioni();
        for (int i=0; i<op.length; i++)
            System.out.println(op[i]);
        conto_corrente.writeTofile("conto.dat");
        conto_corrente = new GestoreDenaro();
        conto_corrente.readFromfile("conto.dat");
        System.out.println("Valore = "+conto_corrente.valore());
        op = conto_corrente.getOperazioni();
        for (int i=0; i<op.length; i++)
            System.out.println(op[i]);
        System.out.println(conto_corrente.getOperazione(1));
        System.out.println(conto_corrente.getOperazione("sell apple"));
        conto_corrente.delOperazione(2);
        System.out.println("Valore = "+ conto_corrente.valore());
        op = conto_corrente.getOperazioni();
        for (int i=0; i<op.length; i++)
            System.out.println(op[i]);
    }
}
