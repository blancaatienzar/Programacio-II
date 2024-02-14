/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package defFitx;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Blanca
 */
public class FitxerInput {

    private FileInputStream fis;
    private ObjectInputStream f;
    private String nom;
    private int cont;

    //El constructor que obreix un fitxer d'objectes per a lectura
    public FitxerInput(String x) {
        nom = x;
        try {
            fis = new FileInputStream(nom);
            f = new ObjectInputStream(fis);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FitxerInput.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FitxerInput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //mostraArticles: Un recorregut del fitxer fins al centinel·la queenregistrant les dades a DadesJugadors.
    public String mostraJugadors() {
        String s = "";
        try {
            DadesJugadors a = (DadesJugadors) f.readObject();
            while (!a.esCentinela()) {
                s += a;
                s += "\n";
                a = (DadesJugadors) f.readObject();
            }
        } catch (IOException ex) {
            Logger.getLogger(FitxerInput.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FitxerInput.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    //tanca el fitxer
    public void tancaInput() {
        try {
            f.close();
        } catch (IOException ex) {
            Logger.getLogger(FitxerInput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //llegeix les dades del jugador del fitxer
    public DadesJugadors llegir() {
        DadesJugadors a = null;
        try {
            a = (DadesJugadors) f.readObject();
        } catch (IOException ex) {
            Logger.getLogger(FitxerInput.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FitxerInput.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    //Contador del nombre d'objectes
    public int getNumObj() {
        FitxerInput en = new FitxerInput(nom);
        try {
            DadesJugadors a = (DadesJugadors) f.readObject();
            while (!a.esCentinela()) {
                cont++;
                a = (DadesJugadors) f.readObject();
            }
        } catch (IOException ex) {
            Logger.getLogger(FitxerInput.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FitxerInput.class.getName()).log(Level.SEVERE, null, ex);
        }
        en.tancaInput();
        return cont;
    }

    public DadesJugadors[] getDadesJug() {
        DadesJugadors[] dad = new DadesJugadors[cont];
        boolean acabat = false;
        int i = 0;
        DadesJugadors ne;
        try {
            f = new ObjectInputStream(new FileInputStream(nom));
            do {
                ne = (DadesJugadors) f.readObject();
                if (!ne.esCentinela()) {
                    dad[i++] = ne;
                } else {
                    acabat = true;
                }

            } while (!acabat);
            f.close();

        } catch (IOException ex) {
            Logger.getLogger(FitxerInput.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FitxerInput.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dad;
    }

}
