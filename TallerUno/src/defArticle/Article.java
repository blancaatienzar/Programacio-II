/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package defArticle;

import java.io.Serializable;

/**
 * Classe Article
 *
 * Esta clase representa un Artículo con los atributos: nombre, código y nombre
 * de archivo. Implementa la interfaz Serializable para la serialización de
 * objetos.
 *
 * Atributs: nom: Cadena de caracters, codi: enter, nomFitxer: Cadena de caracters
 *
 * @author Blanca
 */
public class Article implements Serializable {

    /**
     * El nombre del artículo, y el nombre del archivo del articulo.
     */
    private String nom, nomFitxer;

    /**
     * El código del artículo.
     */
    private int codi;

    /**
     * Un objeto Artículo centinela con nombre, código y nombre de archivo 
     * establecidos en "zzz" y 999 respectivamente.
     */
    public static final Article centinela = new Article("zzz", 999, "zzz");

    /**
     * Constructor por defecto. Inicializa el nombre a null, el código a 0 y el 
     * nombre del archivo a null.
     */
    public Article() {
        nom = null;
        codi = 0;
        nomFitxer = null;
    }

    /**
     * Constructor con parámetros. Inicializa el nombre, el código y el nombre 
     * del archivo con los parámetros dados.
     *
     * @param s El nombre del artículo.
     * @param x El código del artículo.
     * @param n El nombre del archivo del artículo.
     */
    public Article(String s, int x, String n) {
        nom = s;
        codi = x;
        nomFitxer = n;
    }

    /**
     * Devuelve una representación en cadena del objeto Artículo.
     *
     * @return Una representación en cadena del objeto Artículo.
     */
    @Override
    public String toString() {
        return "Article{" + "nom= " + nom + " codi= " + codi + " " + nomFitxer + '}';
    }

    /**
     * Devuelve el código del artículo.
     *
     * @return El código del artículo.
     */
    public int getCodi() {
        return codi;
    }

    /**
     * Devuelve verdadero si el nombre del archivo del artículo es "zzz".
     *
     * @return Verdadero si el nombre del archivo del artículo es "zzz", falso 
     * en caso contrario.
     */
    public boolean esCentinela() {
        return nomFitxer.equals(centinela.nomFitxer);
    }
}
