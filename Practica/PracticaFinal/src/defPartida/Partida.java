package defPartida;

import static PracticaFinal.GestorEventos.nombreJugador;
import static PracticaFinal.PracticaFinal.puntos;
import static PracticaFinal.PracticaFinal.tiempo;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase la cual usaremos para registrar los datos en el fichero
 * "partidasTetrisUIB.dat" y para en sus respectivos momentos enseñar el contenido,
 * tanto en historial general como en historial específico. Para ello se ha
 * usado la clase RandomAccessFile dado en clase.
 * UIB - 2023-2024
 * @author Blanca e Hai Zi
 */
public class Partida {

     //array con la forma en la que se guardarán los datos.
    private final String FORMAT = " - %s > partida jugada con un tiempo de %d segundos por %s obteniendo una puntuacion de %d puntos\n"; 
    private String file;
    private String nombreJug = "";
    private String fecha = "";
    private final int longExacta = 30;
    private final long midaReg = (2 * 14) + (1 * 4) + (longExacta * 2) + (1 * 4);

    /**
     * Constructor de la clase Partida.
     * @param fichero Nombre del archivo donde se guardarán los datos de la partida.
     */
    public Partida(String fichero) {
        DateFormat fomatoFecha = new SimpleDateFormat("dd:MM:yy HH:mm"); //fecha 
        fecha = fomatoFecha.format(new Date());

        nombreJug = nombreJugador;

        file = fichero;
    }

    /**
     * Método para insertar un registro en el archivo de resultados.
     */
    public void inserirRegistre() {

        try {
            RandomAccessFile f = new RandomAccessFile(file, "rw");
            if (f.length() != 0) {
                f.seek(f.length());
            }
            f.writeChars(fecha);
            f.writeInt(tiempo);

            nombreJug += " ".repeat(Math.max(0, 30 - nombreJug.length()));
            f.writeChars(nombreJug);

            f.writeInt(puntos);
        } catch (FileNotFoundException e) {
            System.out.println("Error: El archivo no se encuentra. Verifique que la ruta del archivo es correcta.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error: Ocurrió un problema de E/S durante la escritura en el archivo.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error: Ocurrió un problema inesperado.");
            e.printStackTrace();
        }
    }

    /**
     * Método para obtener el contenido del archivo de resultados.
     * @return El contenido del archivo como una cadena de texto.
     */
    public String contingutFitxer() {
        String texto = "";

        try ( RandomAccessFile f = new RandomAccessFile(file, "r")) {
            f.seek(0);
            long numreg = f.length() / midaReg;

            for (int r = 0; r < numreg; r++) {
                String nom = "";
                String fech = "";

                for (int x = 0; x < 14; x++) {
                    fech += f.readChar();
                }

                int tiemp = f.readInt();

                for (int x = 0; x < longExacta; x++) {
                    nom += f.readChar();
                }

                int punt = f.readInt();
                texto += String.format(FORMAT, fech, tiemp, nom.trim(), punt);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: El archivo no se encuentra. Verifique que la ruta del archivo es correcta.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error: Ocurrió un problema de E/S durante la escritura en el archivo.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error: Ocurrió un problema inesperado.");
            e.printStackTrace();
        }
        if (texto.equals("")) {
            texto = "No se ha encontrado";
        }

        return texto;

    }

    /**
     * Método para filtrar los registros por nombre de jugador.
     * @param name Nombre del jugador a filtrar.
     * @return Los registros correspondientes al jugador especificado como una cadena de texto.
     */
    public String filtreNombre(String name) {
        if (name == null || name.equals("")) {
            return "- " + name + ", no se ha encontrado";
        }
        String texto = "";

        try ( RandomAccessFile f = new RandomAccessFile(file, "r")) {
            f.seek(0);
            long numreg = f.length() / midaReg;

            for (int r = 0; r < numreg; r++) {
                String nom = "";
                String fech = "";
                for (int x = 0; x < 14; x++) {
                    fech += f.readChar();
                }

                int tiemp = f.readInt();

                for (int x = 0; x < longExacta; x++) {
                    nom += f.readChar();
                }

                int punt = f.readInt();

                if (nom.trim().equalsIgnoreCase(name.trim())) {
                    boolean igual = false;
                    for (int x = 0; x < name.length(); x++) {
                        char namei = name.charAt(x);
                        char nomi = nom.charAt(x);

                        if (namei == nomi) {
                            igual = true;

                        } else {
                            igual = false;
                            x = longExacta;
                        }
                    }
                    if (igual) {
                        texto += String.format(FORMAT, fech, tiemp, nom.trim(), punt);
                    }
                } else {
                    continue;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: El archivo no se encuentra. Verifique que la ruta del archivo es correcta.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error: Ocurrió un problema de E/S durante la escritura en el archivo.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error: Ocurrió un problema inesperado.");
            e.printStackTrace();
        }
        if (texto.equals("")) {
            texto = "- " + name + ", no se ha encontrado";
        }
        return texto;
    }

}
