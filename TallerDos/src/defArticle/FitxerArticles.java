package defArticle;

import defIndexats.Index;
import defArticle.Article;
import static defArticle.Article.MIDADESC;
import static defArticle.Article.MIDAREGISTRE;
import defIndexats.FitxersIndex;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe fitxer articles implementat amb un fitxer d'accés aleatori que
 * permetrà l'actualització interactiva. No té un control d'errors exahustiu.
 * 
 * @author estudiant
 */
public class FitxerArticles {

    public static FitxersIndex fInd = new FitxersIndex("articles.indx");
    private static File arxiu;
    private RandomAccessFile f;

    /**
     * Error activat quan s'intenta modificar o donar de baixa un article
     * inexistent
     */
    public static class ArticleInexistent extends Exception {

        public ArticleInexistent(String s) {
            super(s);
        }
    }

    /**
     * Error activat quan s'intenta donar d'alta un article ja existent
     */
    public static class ArticleJaExistent extends Exception {

        public ArticleJaExistent(String s) {
            super(s);
        }
    }

    /**
     * Constructor: Crea un fitxer buit amb el nom del paràmetre
     *
     * @param nomFitxer
     * @throws Exception
     */
    public FitxerArticles(String nomFitxer) throws Exception {
        arxiu = new File(nomFitxer);
        f = new RandomAccessFile(arxiu, "rw");
        f.close();
    }

    /**
     * Mètode que dona d'alta l'article que passa per paràmetre. En primer lloc
     * cerca aquest codi dins el fitxer si el resultat de la cerca és diferent
     * -1 (no hi és) o de 0 (no hi ha cap article) llegeix l'article que hi ha a
     * posició pos - 1 (l'article que coincideix amb el codi) si el boolea
     * indica que no està esborrat s'activa l'error d'article ja existent si no
     * escriure el nou article per això es situarà a pos - 1 o al principi si no
     * hi ha elements
     *
     * @param a Article a donar d'alta
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ArticleJaExistent
     */
    public void alta(Article a) throws FileNotFoundException, IOException, ArticleJaExistent {
        int pos = this.cercaCodi(a.getCodi());

        f = new RandomAccessFile(arxiu, "rw");
        if (pos != -1) { // Articulo encontrado
            f.seek(pos * MIDAREGISTRE);
            int cod = f.readInt();
            String s = "";
            for (int j = 0; j < MIDADESC; j++) {
                s += f.readChar();
            }
            int quantita = f.readInt();
            boolean esb = f.readBoolean();
            if (!esb) {
                throw new ArticleJaExistent("Error: Article ja està al fitxer");
            }
            f.seek(pos * MIDAREGISTRE);
        } else {
            // Si l'article no es troba, es posiciona al final de l'arxiu
            f.seek(f.length());
        }

        // Estableix l'índex de l'arxiu al codi de l'article i la longitud de l'arxiu
        fInd.setIndex(new Index(a.getCodi(), f.length()));

        f.writeInt(a.getCodi());
        f.writeChars(toMidaString(a.getDescripcio()));
        f.writeInt(a.getQuantitat());
        f.writeBoolean(false);
        f.close();
    }

    /**
     * Mètode que genera una cadena de caràcters amb el contingut del fitxer.
     * Depenent del paràmetre si val vertader mostrarà els articles esborrats
     * (aquells que tenen el booleà esborrat a true en cas contrari només
     * mostrarà el articles actius
     *
     * @param artEsborrats Si es vol mostrar també els articles esborrats
     * @return Totes les dades del fitxer
     */
    public String toString() {
        String s = "";
        try {
            f = new RandomAccessFile(arxiu, "r");
            long numReg = f.length() / MIDAREGISTRE;
            for (int r = 0; r < numReg; r++) {
                int codi = f.readInt();
                String descripcio = "";
                for (int i = 0; i < MIDADESC; i++) {
                    descripcio += f.readChar();
                }
                int quantitat = f.readInt();
                boolean esb = f.readBoolean();
                Article a = new Article(codi, descripcio, quantitat, esb);
                s += a.toString() + "\n";

            }
            f.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FitxerArticles.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FitxerArticles.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    /**
     * Mètode que cerca un article del fitxer que tingui el mateix codi que
     * l'article que passa per paràmetre, si el troba substitueix el nom de
     * l'aticle del fitxer pel nom de 'article paràmetre. Si el troba considera
     * el valor del boolea de l'article del fitxer, si val vertader activa
     * l'error d'article inexistent
     *
     * @param a
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ArticleInexistent
     */
    public void modifica(Article a) throws FileNotFoundException, IOException, ArticleInexistent {
        int pos = this.cercaCodi(a.getCodi());
        if (pos == -1) {
            throw new ArticleInexistent("ERROR: Article inexistent");
        }
        f = new RandomAccessFile(arxiu, "rw");
        f.seek(pos * MIDAREGISTRE);
        int cod = f.readInt();
        String s = "";
        for (int j = 0; j < MIDADESC; j++) {
            s += f.readChar();
        }
        int quantitat = f.readInt();
        boolean esb = f.readBoolean();
        if (esb) {
            throw new ArticleInexistent("ERROR: Article ja està borrat");
        }
        f.seek((pos * MIDAREGISTRE) + 4);
        f.writeChars(toMidaString(a.getDescripcio())); // Només modifica el nom
        f.writeInt(a.getQuantitat());
        f.close();
    }

    /**
     * Aquest mètode consulta un article en l'arxiu pel seu codi.
     *
     * @param codi El codi de l'article a consultar.
     * @return Una cadena que representa l'article, o un missatge d'error si 
     *         l'article no existeix o està esborrat.
     * @throws FileNotFoundException Si l'arxiu no es troba.
     * @throws IOException           Si ocorre un error d'entrada/sortida.
     * @throws ArticleInexistent     Si l'article no existeix.
     */
    public String consultar(int codi) throws FileNotFoundException, IOException, ArticleInexistent {
        // Cerca la posició de l'article en l'arxiu
        int pos = this.cercaCodi(codi);

        if (pos == -1) { // Article no trobat
            return "ERROR: Article inexistent";
        }

        // Crea un arxiu d'accés aleatori per a llegir i escriure
        f = new RandomAccessFile(arxiu, "rw");

        // Es posiciona en el lloc de l'arxiu on es troba l'article
        f.seek(pos * MIDAREGISTRE);
        int cod = f.readInt();
        String s = "";
        for (int j = 0; j < MIDADESC; j++) {
            s += f.readChar();
        }
        int quantitat = f.readInt();
        boolean esb = f.readBoolean();

        if (esb) { // Article esborrat
            return "ERROR: Article ja està borrat";
        }

        // Cregui un nou article amb les dades llegides
        Article a = new Article(cod, s, quantitat, esb);
        return a.toString(); // Devuelve el artículo
    }

    /**
     * Mètode que esborra l'article que tengui el codi coincident amb el
     * paràmetre. Si el troba al fitxer comprova que tingui el booleà a false i
     * el posa a true. Tansi no el troba com si té el booleà a vertader activa
     * l'error d'article inexistent
     *
     * @param codi Codi de l'article a donar de baixa
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ArticleInexistent
     */
    public void esborra(int codi) throws FileNotFoundException, IOException, ArticleInexistent {
        int pos = this.cercaCodi(codi);
        if (pos == -1) {
            throw new ArticleInexistent("ERROR: Article inexistent");
        }
        f = new RandomAccessFile(arxiu, "rw");
        f.seek(pos * MIDAREGISTRE);
        int cod = f.readInt();
        String s = "";
        for (int j = 0; j < MIDADESC; j++) {
            s += f.readChar();
        }
        int quantitat = f.readInt();
        boolean esb = f.readBoolean();
        if (esb) {
            throw new ArticleInexistent("ERROR: Article ja està borrat");
        }
        f.seek(((pos + 1) * MIDAREGISTRE) - 1); // Es col·loca al final de l'article on hi ha el booleà
        f.writeBoolean(true); // Canvia el valor del booleà
        f.close();
    }

    /**
     * Mètode que cerca dins el fitxer un article que tengui el codi coincident
     * amb el paràmetre. Si el troba el mètode retorna un enter en la posició
     * que es troba, i=-1 (fitxer buit) ò i=numReg si no el troba
     *
     * @param codi
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public int cercaCodi(int codi) throws FileNotFoundException, IOException {
        f = new RandomAccessFile(arxiu, "r");
        long numReg = f.length() / MIDAREGISTRE;
        boolean trobat = (numReg == 0);
        int i = 0;
        String s;
        int cod, quantitat;
        boolean esb;
        while (!trobat && i < numReg) {
            cod = f.readInt();
            s = "";
            for (int j = 0; j < MIDADESC; j++) {
                s += f.readChar();
            }
            quantitat = f.readInt();
            esb = f.readBoolean();
            i++;
            trobat = (cod == codi);
        }
        f.close();
        if (trobat) {
            return i - 1; // L'article trobat està a i-1
        } else {
            return -1; // L'article no hi és (inclou no n'hi ha cap)
        }
    }

    /**
     * Mètode intern a la classe que donat una cadena de caracters de qualsevol
     * longitud la converteix a una cadena de 20 caracters
     *
     * @param s
     * @return
     */
    private String toMidaString(String s) {
        if (s.length() < MIDADESC) {
            for (int i = s.length(); i < MIDADESC; i++) {
                s += " ";
            }
        } else {
            s = s.substring(0, MIDADESC);
        }
        return s;
    }
}
