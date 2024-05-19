

import defArticle.FitxerArticles;
import defArticle.Article;
import static defArticle.FitxerArticles.fInd;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Classe TallerDos que gestiona un conjunt d'articles.
 * 
 * @author estudiant
 */
public class TallerDos {

    // Nom de l'arxiu que conté els articles
    private static final String arxiuAmbArticles = "articlesmal.txt";

    // Objecte que gestiona els articles
    private static FitxerArticles f;

    /**
     * Mètode principal que gestiona el menú d'opcions per als articles.
     * 
     * @param args arguments de la línia de comandos
     * @throws Exception si ocorre algun error
     */
    public static void main(String[] args) throws Exception {
        f = new FitxerArticles("articles.dat");
        try {
            boolean sortir = false;
            int opcio, codi, quantitat;
            String descripcio;

            // Càrrega els articles de l'arxiu
            altaFitxer();
            while (!sortir) {
                menu();
                opcio = llegirEnter("\n\n\tInserir opció: ");
                switch (opcio) {
                    case 1: // Inserció d'un artícle
                        codi = llegirEnter("Codi de l'article: ");
                        descripcio = llegirCadena("Descripcio del article: ");
                        quantitat = llegirEnter("Quantitat de l'article: ");
                        Article a = new Article(codi, descripcio, quantitat, false);
                        try {
                            f.alta(a);
                        } catch (FitxerArticles.ArticleJaExistent ex) {
                            System.out.println(ex.getMessage());
                        }
                        break;
                    case 2: // Consulta d'un artícle
                        codi = llegirEnter("Codi de l'article: ");
                        System.out.println(f.consultar(codi));
                        break;
                    case 3: // Eliminació d'un artícle
                        codi = llegirEnter("Codi de l'article per esborrar: ");
                        try {
                            f.esborra(codi);
                        } catch (FitxerArticles.ArticleInexistent ex) {
                            System.out.println(ex.getMessage());
                        }
                        break;
                    case 4: // Modificació d'un artícle
                        codi = llegirEnter("Codi de l'article: ");
                        descripcio = llegirCadena("Descripció de l'article: ");
                        quantitat = llegirEnter("Quantitat de l'article: ");
                        a = new Article(codi, descripcio, quantitat, false);
                        try {
                            f.modifica(a);
                        } catch (FitxerArticles.ArticleInexistent ex) {
                            System.out.println(ex.getMessage());
                        }
                        break;
                    case 5: // Mostra tots els articles ordenats
                        fInd.escribirIndex();
                        System.out.println("\nContingut del fitxer:\n" + fInd.leerFicheroConIndices("articles.dat"));
                        break;
                    case 6: // Mostra tots els articles (no ordenats)
                        System.out.println("\nContingut del fitxer:\n" + f.toString());
                        break;
                    case 0: // Salir
                        sortir = true;
                        break;
                }
            }

        } catch (Exception ex) {
        }
    }

    /**
     * Mostra el menú d'opcions.
     */
    private static void menu() {
        System.out.println("\n\nTALLER 2 - ARTICLES");
        System.out.println("\n\t1. Inserir un article");
        System.out.println("\t2. Consultar un article");
        System.out.println("\t3. Eliminació d'un article");
        System.out.println("\t4. Modificació d'un article");
        System.out.println("\t5. Mostra tots els articles ordenats");
        System.out.println("\t6. Mostra tots els articles (no ordenats)");
        System.out.println("\t0. Sortir");
    }

    /**
     * Càrrega els articles de l'arxiu.
     * 
     * @throws Exception si ocorre algun error
     */
    private static void altaFitxer() throws Exception {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(arxiuAmbArticles));
            String line = reader.readLine();
            while (line != null) {

                // Dividir la línia en parts usant la coma com a separador
                String[] parts = line.split(",");

                // Si el nombre de parts no és igual a 3, el format de la línia és incorrecte 
                if (parts.length != 3) {
                    System.out.println("Format incorrecte: " + line);
                } else {
                    try {
                        int codi = Integer.parseInt(parts[0].trim());
                        String descripcio = parts[1].trim();
                        int quantitat = Integer.parseInt(parts[2].trim());

                        // Cregui un nou article amb les dades llegides
                        Article article = new Article(codi, descripcio, quantitat, false);

                        // Agregar l'article a la llista d'articles
                        f.alta(article);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: El codi o la quantitat no son enters: " + e.getMessage());
                    } catch (FitxerArticles.ArticleJaExistent e) {
                        System.out.println(e.getMessage());
                    }
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: El archivo no se encontró: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error: Hubo un problema al leer el archivo: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Llegeix un nombre enter de l'usuari.
     * 
     * @param msg missatge a mostrar a l'usuari
     * @return el nombre enter llegit
     */
    private static int llegirEnter(String msg) {
        int x = 0;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.print(msg);
            String s = in.readLine();
            x = Integer.parseInt(s);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return x;
    }

    /**
     * Llegeix una cadena de l'usuari.
     * 
     * @param msg missatge a mostrar a l'usuari
     * @return la cadena llegida
     */
    private static String llegirCadena(String msg) {
        String s = null;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.print(msg);
            s = in.readLine();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return s;
    }

}
