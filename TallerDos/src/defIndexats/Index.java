package defIndexats;

import java.io.Serializable;

/**
 * Classe que representa un índex.
 * 
 * @autor estudiant
 */
public class Index implements Serializable {

    public static final long serialVersionUID = 1L;

    private int codi;
    private long posicio;

    /**
     * Mida del registre del Index: 
     * - codi: 4 bytes 
     * - posicio: 8 bytes 
     * MIDAREGISTRE = 12
     */
    public static final int MIDAREGISTREINDEX = 12; // Mida del registre

    /**
     * Constructor de la classe Index.
     * 
     * @param codi El codi de l'índex.
     * @param posicio La posició de l'índex.
     */
    public Index(int codi, long posicio) {
        this.codi = codi;
        this.posicio = posicio;
    }

    /**
     * Obté el codi de l'índex.
     * 
     * @return El codi de l'índex.
     */
    public int getCodi() {
        return codi;
    }

    /**
     * Estableix el codi de l'índex.
     * 
     * @param codi El codi de l'índex a establir.
     */
    public void setCodi(int codi) {
        this.codi = codi;
    }

    /**
     * Obté la posició de l'índex.
     * 
     * @return La posició de l'índex.
     */
    public long getPosicio() {
        return posicio;
    }

    /**
     * Estableix la posició de l'índex.
     * 
     * @param posicio La posició de l'índex a establir.
     */
    public void setPosicio(long posicio) {
        this.posicio = posicio;
    }

    /**
     * Retorna una representació en cadena de caràcters de l'índex.
     * 
     * @return Una cadena de caràcters que representa l'índex.
     */
    @Override
    public String toString() {
        return "Índex {" + "codi: " + codi + " posició: " + posicio + '}';
    }
}
