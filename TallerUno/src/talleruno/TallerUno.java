/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package talleruno;

import defFitx.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 *
 * @author Blanca
 */
public class TallerUno {

    private static Random ran = new Random();

    private static void menu() {
        System.out.println("\n--------------------------------------------------- ");
        System.out.println("  ESTADÍSTIQUES D'UN JOC MASSIU");
        System.out.println("\n\t1. Inserir jugadors");
        System.out.println("\t2. Mostrar dades dels jugadors");
        System.out.println("\t3. Estadístiques globals");
        System.out.println("\t4. Formació d'equips");
        System.out.println("\t5. Mostrar jugadors per equips");
        System.out.println("\t6. Estadístiques per equips");
        System.out.println("\t0. Sortir");
        System.out.println("--------------------------------------------------- ");
    }

    public static void main(String[] args) {
        boolean sortir = false;
        int opcio = 0;
        while (!sortir) {
            menu();
            opcio = llegirNum("\n\tInserir opció: ");
            switch (opcio) {
                case 1:
                    jugador();
                    break;
                case 2: //abre y lee el fitxero de jugadores
                    FitxerInput gin = new FitxerInput("Jugadors.dat");
                    System.out.println("\n\tContingut del fitxer de jugadors\n" + "\n" + gin.mostraJugadors());
                    gin.tancaInput();
                    break;
                case 3: // llama al metodo resul() de la clase EstadistiquesFitxer
                    EstadistiquesFitxer est = new EstadistiquesFitxer("Jugadors.dat");
                    est.resul();
                case 4:// lee el equipo de cada jugador y lo escribe en el fichero correspondiente
                    FitxerInput f = new FitxerInput("Jugadors.dat");
                    FitxerOutput fo = new FitxerOutput("Bastos.dat");
                    FitxerOutput fa = new FitxerOutput("Copes.dat");
                    FitxerOutput fi = new FitxerOutput("Espases.dat");
                    FitxerOutput fe = new FitxerOutput("Oros.dat");
                    DadesJugadors dj = f.llegir();
                    while (!dj.esCentinela()) {
                        Equips gru = dj.getGrup();
                        switch (gru) {
                            case BASTOS:
                                fo.escriu(dj);
                                break;
                            case COPES:
                                fa.escriu(dj);
                                break;
                            case ESPASES:
                                fi.escriu(dj);
                                break;
                            case OROS:
                                fe.escriu(dj);
                                break;
                        }
                        dj = f.llegir();
                    }
                    fo.tancaOut();
                    fa.tancaOut();
                    fi.tancaOut();
                    fe.tancaOut();
                    break;
                case 5: //abre y lee cada fitxero de equipo
                    FitxerInput b = new FitxerInput("Bastos.dat");
                    FitxerInput c = new FitxerInput("Copes.dat");
                    FitxerInput e = new FitxerInput("Espases.dat");
                    FitxerInput o = new FitxerInput("Oros.dat");
                    System.out.println("\n\tContingut dels fitxers de equips:");
                    System.out.println("\n\tContingut del fitxer de BASTOS\n"+"\n" + b.mostraJugadors());
                    b.tancaInput();
                    System.out.println("\n\tContingut del fitxer de COPES\n" +"\n" + c.mostraJugadors());
                    c.tancaInput();
                    System.out.println("\n\tContingut del fitxer de ESPASES\n" +"\n" + e.mostraJugadors());
                    e.tancaInput();
                    System.out.println("\n\tContingut del fitxer de OROS\n" +"\n" + o.mostraJugadors());
                    o.tancaInput();
                    break;
                case 6: // llama al metodo resul() de la clase EstadistiquesFitxer
                    EstadistiquesFitxer ba = new EstadistiquesFitxer("Bastos.dat");
                    ba.resul();
                    EstadistiquesFitxer co = new EstadistiquesFitxer("Copes.dat");
                    co.resul();
                    EstadistiquesFitxer es = new EstadistiquesFitxer("Espases.dat");
                    es.resul();
                    EstadistiquesFitxer or = new EstadistiquesFitxer("Oros.dat");
                    or.resul();
                    break;
                case 0:
                    System.out.println("\n\tAdeu, moltes gràcies");
                    sortir = true;
                    break;
            }
        }
    }

    private static int llegirNum(String msg) {
        int x = 0;
        try {
            String s;
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.print(msg);
            s = in.readLine();
            x = Integer.parseInt(s);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return x;
    }

    private static Equips llegirEqui() { //asigna a traves de numeros aleatorios el equipo del jugador
        Equips grup = null;
        int numequi = ran.nextInt(4);
        switch (numequi) {
            case 0:
                grup = Equips.BASTOS;
                break;
            case 1:
                grup = Equips.COPES;
                break;
            case 2:
                grup = Equips.ESPASES;
                break;
            case 3:
                grup = Equips.OROS;
                break;
        }
        return grup;
    }

    private static void jugador() {
        String nom = null;
        FitxerOutput gout = new FitxerOutput("Jugadors.dat");
        int max = ran.nextInt(200); //variable que guarda el numero maximo de jugadores
        for (int i = 0; i < max; i++) {
            Equips pep = llegirEqui();
            int num = ran.nextInt(1000); //variable que guarda el numero de trofeos ganado por jugador

            int numAleat = ran.nextInt(510);//variable que guarda el numero aleatoria que asigna el nombre del jugador
            try {
                FileInputStream fis = new FileInputStream("LlistaNoms.txt"); //Obtiene el nombre segun el numero aleatorio
                BufferedReader in = new BufferedReader(new InputStreamReader(fis));
                for (int z = 0; z < numAleat; z++) {
                    nom = in.readLine();
                }
                fis.close();
                in.close();
                DadesJugadors p = new DadesJugadors(nom, pep, num); //guarda las caracteristicas en el objeto
                gout.escriu(p);
            } catch (IOException ex) {
                System.out.println("ERROR: " + ex.getMessage());
            }
        }
        gout.tancaOut();
    }
}
