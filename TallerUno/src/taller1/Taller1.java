/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package taller1;

import defArticle.Article;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

/**
 * Taller 1
 * 
 * Esta clase contiene métodos para crear, fusionar y mostrar archivos que 
 * contienen objetos de la clase Article.
 * 
 * @author Blanca
 */
public class Taller1 {

    /**
     * Constante maxima aleatoria para el valor pseudo aleatorio de cada fichero
     */
    private static int constAleat = 15;
    
    /**
     * Método principal que genera un número aleatorio de archivos, cada uno 
     * con un número aleatorio de objetos Article.
     * Luego fusiona todos los archivos en uno y muestra su contenido.
     *
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        Random ran = new Random();
        
        int nFitx = ran.nextInt(2,10);
        // Rango aleatorio que cubre entre 2 y 8, ya que para una fusion 
        // se requieren minimo dos ficheros.
        
        System.out.println("N = " + nFitx);
        for (int i = 1; i <= nFitx; i++) {
            int valor = ran.nextInt(1,constAleat);
            System.out.println("Valor pseudo aleatori: " + valor);
            insereixPersones("f" + i + ".dat", valor);
        }
        System.out.println("");
        for (int i = 1; i <= nFitx; i++) {
            System.out.println("Fitxer f" + i + ".dat");
            mostra("f" + i + ".dat");
        }

        fusiona(nFitx);

        System.out.println("Resultat: ");
        mostra("fusio.dat");
    }

    /**
     * Inserta un número específico de objetos Article en un archivo.
     *
     * @param nom El nombre del archivo.
     * @param n El número de objetos Article a insertar.
     */
    private static void insereixPersones(String nom, int n) {
        try {
            FileOutputStream sortida = new FileOutputStream(nom);
            ObjectOutputStream oos = new ObjectOutputStream(sortida);
            int c = 0;
            for (int i = 0; i < n; i++) {
                String s = "article" + i;
                c += n;
                String x = nom;
                Article p = new Article(s, c, x);
                oos.writeObject(p);
            }
            oos.writeObject(Article.centinela);
            oos.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Fusiona varios archivos que contienen objetos Article en un solo archivo.
     *
     * @param n El número de archivos a fusionar.
     */
    private static void fusiona(int n) {
        try {
            ObjectInputStream[] files = new ObjectInputStream[n];
            for (int i = 0; i < n; i++) {
                FileInputStream entrada = new FileInputStream("f" + (i + 1) + ".dat");
                files[i] = new ObjectInputStream(entrada);
            }

            FileOutputStream sortida = new FileOutputStream("fusio.dat");
            ObjectOutputStream fFusio = new ObjectOutputStream(sortida);

            Article[] articles = new Article[n];
            for (int i = 0; i < n; i++) {
                articles[i] = (Article) files[i].readObject();
            }

            while (true) {
                int minIndex = -1;
                for (int i = 0; i < n; i++) {
                    if (!articles[i].esCentinela() && (minIndex == -1 || 
                        articles[i].getCodi() < articles[minIndex].getCodi())) {
                        minIndex = i;
                    }
                }

                if (minIndex == -1) break;
                
                fFusio.writeObject(articles[minIndex]);
                articles[minIndex] = (Article) files[minIndex].readObject();
            }

            fFusio.writeObject(Article.centinela);

            for (ObjectInputStream file : files) {
                file.close();
            }
            
            fFusio.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Muestra el contenido de un archivo que contiene objetos Article.
     *
     * @param s El nombre del archivo.
     */
    private static void mostra(String s) {
        try {
            FileInputStream entrada = new FileInputStream(s);
            ObjectInputStream ois = new ObjectInputStream(entrada);

            Article p = (Article) ois.readObject();
            while (!p.esCentinela()) {
                System.out.println(p);
                p = (Article) ois.readObject();
            }
            ois.close();
            System.out.println(" ");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
