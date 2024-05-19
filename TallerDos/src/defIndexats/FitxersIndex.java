package defIndexats;

import static defArticle.Article.MIDADESC;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

import defArticle.Article;
import static defIndexats.Index.MIDAREGISTREINDEX;

/**
 * Aquesta classe gestiona un índex d'objectes de tipus Article.
 * L'índex s'emmagatzema en un arxiu per a un accés ràpid als articles.
 * 
 * @author estudiant
 */
public class FitxersIndex {

    private final File arxiu; // L'arxiu que emmagatzema l'índex
    private RandomAccessFile fInd; // S'utilitza per a llegir i escriure en l'arxiu de l'índex
    public static Index index[] = new Index[10000]; // L'índex en memòria
    private int desplazamientoIndex; // La posició actual en l'índex

    /**
     * Constructor que inicialitza l'arxiu de l'índex i llegeix el seu contingut
     * en memòria.
     * 
     * @param nomFitxer El nom de l'arxiu de l'índex
     */
    public FitxersIndex(String nomFitxer) {
        arxiu = new File(nomFitxer);
        try {
            fInd = new RandomAccessFile(arxiu, "rw");
            fInd.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FitxersIndex.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FitxersIndex.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // S'inicialitza el desplaçament de l'índex a 0
        desplazamientoIndex = 0;

        // Es llegeix l'arxiu d'índex
        leerFichero();
    }

    /**
     * Estableix un índex en la posició actual i després incrementa la posició.
     * 
     * @param ind L'índex a establir
     */
    public void setIndex(Index ind) {
        index[desplazamientoIndex] = ind;
        desplazamientoIndex++;
    }

    /**
     * Escriu l'índex en l'arxiu. Abans d'escriure, ordena l'índex en memòria.
     */
    public void escribirIndex() {

        // Primer, s'ordena l'índex en memòria utilitzant el mètode bombollaMillorada
        bombollaMillorada(index);

        try {
            fInd = new RandomAccessFile(arxiu, "rw");

            // Es posiciona a l'inici de l'arxiu
            fInd.seek(0);
            for (int i = 0; i < desplazamientoIndex; i++) {

                // Per a cada element de l'índex, s'escriu el seu codi i la seva posició
                fInd.writeInt(index[i].getCodi());
                fInd.writeLong(index[i].getPosicio());
            }
            fInd.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FitxersIndex.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FitxersIndex.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Ordena l'índex en memòria utilitzant l'algorisme de la bombolla millorat.
     * 
     * @param t L'índex a ordenar
     */
    private void bombollaMillorada(Index[] t) {
        final int N = desplazamientoIndex;
        Index x;
        int i = 0;
        while (i < N - 1) {
            int lj = N;
            for (int j = N - 2; j >= i; j--) {
                if (t[j + 1].getCodi() < t[j].getCodi()) {
                    x = t[j + 1];
                    t[j + 1] = t[j];
                    t[j] = x;
                    lj = j;
                }
            }
            i = lj + 1;
        }
    }

    /**
     * Lee l'arxiu de l'índex i càrrega el seu contingut en memòria.
     */
    public void leerFichero() {
        try {
            fInd = new RandomAccessFile(arxiu, "r");

            // Es calcula el nombre de registres en l'arxiu d'índex
            long numReg = fInd.length() / MIDAREGISTREINDEX;

            // Es recorren tots els registres
            for (int r = 0; r < numReg; r++) {

                // Es llegeix el codi i la posició de cada registre
                int codi = fInd.readInt();
                long pos = fInd.readLong();

                // Es crea un nou objecte Index amb el codi i la posició llegits
                // i s'afegeix a l'índex en memòria
                setIndex(new Index(codi, pos));
            }
            fInd.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FitxersIndex.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FitxersIndex.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Llegeix l'arxiu d'articles utilitzant l'índex per a accedir als articles,
     * de forma ordenada.
     * 
     * @param arxiuArticle El nom de l'arxiu d'articles
     * @return Una cadena que conté la representació de tots els articles
     */
    public String leerFicheroConIndices(String arxiuArticle) {
        String s = "";
        try {
            RandomAccessFile f = new RandomAccessFile(arxiuArticle, "r");
            fInd = new RandomAccessFile(arxiu, "r");

            // Calculem el nombre de registres en l'arxiu de l'índex
            long numReg = fInd.length() / MIDAREGISTREINDEX;
            for (int r = 0; r < numReg; r++) {

                // Llegim el codi i la posició de l'article en l'arxiu d'articles
                int codi = fInd.readInt();
                long pos = fInd.readLong();

                // Ens posicionem en el lloc correcte de l'arxiu d'articles
                f.seek(pos);

                // Llegim les dades de l'article
                int cod = f.readInt();
                String descripcio = "";
                for (int i = 0; i < MIDADESC; i++) {
                    descripcio += f.readChar();
                }
                int quantitat = f.readInt();
                boolean esb = f.readBoolean();

                // Si l'article no està marcat com a esborrament, l'afegim a la cadena
                if (!esb) {
                    Article a = new Article(cod, descripcio, quantitat, esb);
                    s += a.toString() + "\n";
                }

            }
            fInd.close();
            f.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FitxersIndex.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FitxersIndex.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
}
