package defArticle;

/**
 * Classe que representa un article.
 * 
 * @autor estudiant
 */

public class Article {

    public static Object centinela;

    private int codi;
    private String descripcio;
    private int quantitat;
    private boolean esborrat;

    /**
     * Mida del registre: 
     * - codi: 4 bytes 
     * - descripcio: 30 bytes (30 caràcters * 2 bytes per caràcter Unicode)
     * - quantitat: 4 bytes 
     * - esborrat: 1 byte 
     * MIDAREGISTRE = 69
     */
    public static final int MIDAREGISTRE = 69; // Mida del registre
    public static final int MIDADESC = 30;   // Mida descripció

    /**
     * Constructor de la classe Article.
     * 
     * @param codi El codi de l'article.
     * @param descripcio La descripció de l'article.
     * @param quantitat La quantitat de l'article.
     * @param esborrat Indica si l'article està marcat com a esborrat o no.
     */
    public Article(int codi, String descripcio, int quantitat, boolean esborrat) {
        this.codi = codi;
        this.descripcio = descripcio;
        this.quantitat = quantitat;
        this.esborrat = esborrat;
    }

    /**
     * Obté el codi de l'article.
     * 
     * @return El codi de l'article.
     */
    public int getCodi() {
        return codi;
    }

    /**
     * Estableix el codi de l'article.
     * 
     * @param codi El codi de l'article a establir.
     */
    public void setCodi(int codi) {
        this.codi = codi;
    }

    /**
     * Obté la descripció de l'article.
     * 
     * @return La descripció de l'article.
     */
    public String getDescripcio() {
        return descripcio;
    }

    /**
     * Estableix la descripció de l'article.
     * 
     * @param descripcio La descripció de l'article a establir.
     */
    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    /**
     * Obté la quantitat de l'article.
     * 
     * @return La quantitat de l'article.
     */
    public int getQuantitat() {
        return quantitat;
    }

    /**
     * Estableix la quantitat de l'article.
     * 
     * @param quantitat La quantitat de l'article a establir.
     */
    public void setQuantitat(int quantitat) {
        this.quantitat = quantitat;
    }

    /**
     * Comprova si l'article està marcat com a esborrat.
     * 
     * @return Cert si l'article està marcat com a esborrat, fals altrament.
     */
    public boolean getEsborrat() {
        return esborrat;
    }

    /**
     * Estableix l'estat d'esborrat de l'article.
     * 
     * @param esborrat Cert per marcar l'article com a esborrat, fals per marcar-lo com a no esborrat.
     */
    public void setEsborrat(boolean esborrat) {
        this.esborrat = esborrat;
    }

    /**
     * Retorna una representació en cadena de caràcters de l'article.
     * 
     * @return Una cadena de caràcters que representa l'article.
     */
    @Override
    public String toString() {
        return "Article {" + "codi: " + codi + " descripció: " + descripcio + " quantitat: " + quantitat + " esborrat: " + esborrat + '}';
    }

}
