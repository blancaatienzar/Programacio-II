/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package defFitx;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Blanca
 */
public class EstadistiquesFitxer {

    private String nomFit;
    private int numObj;
    private double sumTrof = 1;
    private double mitjana;
    private double desvEst;
    private FitxerInput aux;
    private DadesJugadors primer;
    private DadesJugadors segon;
    private DadesJugadors tercer;

    public EstadistiquesFitxer(String fit) {
        aux = new FitxerInput(fit);
        nomFit = fit;
    }

    public void resul() {//muestra los resultados de las estadisticas
        System.out.println("\n\tNom del fitxer: " + nomFit);

        numObj = aux.getNumObj();  //numero de Objetos totales del fichero
        aux.tancaInput();
        System.out.println("\n\t\tNombre d'objectes que té: " + numObj);

        sumaTrof();
        System.out.println("\t\tSumatori de tots els trofeus obtinguts: " + sumTrof);

        mitjana = sumTrof / numObj;
        System.out.println("\t\tMitjana aritmètica del nombre de trofeus per jugador: " + mitjana);

        desviació();
        System.out.println("\t\tDesviació estàndard: " + desvEst);

        ranking();
        System.out.println("\t\tDades del millor jugador: " + "\n\t\t\t" + primer);
        System.out.println("\t\tDades del segon millor jugador: " + "\n\t\t\t" + segon);
        System.out.println("\t\tDades del tercer millor jugador: " + "\n\t\t\t" + tercer);
    }

    private void desviació() { //Obtiene los trofeos de cada jugador, para finalmente hacer la desviacion estandar
        DadesJugadors[] dad = aux.getDadesJug(); //Array con todos los objetos del fichero
        double sum = 0;
        int num;
        double rest;
        double mult;
        //De cada objeto cojen su numero de trofeos y lo comparan con el anterior
        for (int i = 0; i < numObj; i++) {
            num = dad[i].getTrof();
            rest = num - mitjana;
            mult = rest * rest;//Calcular el cuadrado
            sum = sum + mult;//Hacer el sumatorio
        }
        double div = sum / numObj;//la division entre sumatorio y numero de objetos
        desvEst = Math.sqrt(div); // raiz quadrada

    }

    public void sumaTrof() { //Le los trofeos de cada jugador y los va sumando en un contador
        String s = "";
        try {
            FileInputStream fis = new FileInputStream(nomFit);
            ObjectInputStream f = new ObjectInputStream(fis);
            DadesJugadors a = (DadesJugadors) f.readObject();
            int num = a.getTrof();
            sumTrof = sumTrof + num;
            while (!a.esCentinela()) {
                s += a;
                s += "\n";
                a = (DadesJugadors) f.readObject();

                num = a.getTrof();
                sumTrof = sumTrof + num;
            }
            fis.close();
            f.close();
        } catch (IOException ex) {
            Logger.getLogger(FitxerInput.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FitxerInput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ranking() { //Le los trofeos de cada jugador y los va sumando en un contador
        DadesJugadors[] dad = aux.getDadesJug(); //Array con todos los objetos del fichero
        int num = 0; //variable con el numero de trofeos del mejor jugador
        int nume = 0; //variable con el numero de trofeos del segundo mejor jugador
        int numer = 0;//variable con el numero de trofeos del tercer mejor jugador

        //De cada objeto cojen su numero de trofeos y lo comparan con el anterior
        for (int i = 0; i < numObj; i++) { //Analiza el mejor jugador
            primer = dad[i];
            num = dad[i].getTrof();
            for (int j = 0; j < numObj; j++) {
                if (dad[j].getTrof() > num) {
                    primer = dad[j];
                    num = dad[j].getTrof();
                }
            }
            i = numObj;
        }
        for (int i = 0; i < numObj; i++) { //Analiza el segundo mejor jugador
            for (int j = 0; j < numObj; j++) {
                if ((dad[j].getTrof() > nume) && (num > dad[j].getTrof())) {
                    segon = dad[j];
                    nume = dad[j].getTrof();
                }
            }
            i = numObj;
        }
        for (int i = 0; i < numObj; i++) { //Analiza el tercero mejor jugador
            for (int j = 0; j < numObj; j++) {
                if ((dad[j].getTrof() > numer) && (nume > dad[j].getTrof()) && (num > dad[j].getTrof())) {
                    tercer = dad[j];
                    numer = dad[j].getTrof();
                }
            }
            i = numObj;
        }
    }
}
