/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PracticaFinal;

import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import static PracticaFinal.LecturaDatos.partidaEnCurso;

/**
 * Clase utilizada para gestionar eventos principalmente con ActionListeners.
 * UIB - 2023-2024
 * 
 * @author Blanca e Hai Zi
 */
public class GestorEventos {

    public static String nombreJugador; // Almacena el nombre del jugador actual

    // Constructor vacío
    public GestorEventos() {
    }

    /**
     * Crea un ActionListener para comenzar una nueva partida
     */
    public ActionListener crearPartida() {
        return e -> {
            if (!partidaEnCurso) { // Verifica si no hay una partida en curso

                // Solicita el nombre del jugador
                nombreJugador = new LecturaDatos(PracticaFinal.frame).getNombreJugador();

            } else {
                // Muestra un mensaje de advertencia si hay una partida en curso
                JOptionPane.showMessageDialog(null, "ANTES DEBES TERMINAR LA "
                        + "PARTIDA EN CURSO");
            }
        };
    }

    /**
     * Crea un ActionListener para configurar el juego
     */
    public ActionListener configuracionJuego() {
        return e -> {
            if (!partidaEnCurso) { // Verifica si no hay una partida en curso

                // Abre la configuración del juego
                new LecturaDatos(PracticaFinal.frame, "configuracion");

                // Vuelve a la pantalla inicio
                PracticaFinal.panelContenidos.initComponents();

            } else {
                // Muestra un mensaje de advertencia si hay una partida en curso
                JOptionPane.showMessageDialog(null, "ANTES DEBES TERMINAR LA "
                        + "PARTIDA EN CURSO");
            }
        };
    }

    /**
     * Crea un ActionListener para mostrar las estadísticas del juego
     */
    public ActionListener estadisticas() {
        return e -> {
            if (!partidaEnCurso) { // Verifica si no hay una partida en curso

                // Opciones de historial
                String[] options = { "HISTORIAL GENERAL", "HISTORIAL ESPECIFICO", "NADA" };
                int seleccion = JOptionPane.showOptionDialog(PracticaFinal.frame,
                        "¿QUÉ DESEAS REALIZAR?",
                        "HISTORIAL",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);

                // Manejar la selección del usuario
                switch (seleccion) {
                    case 0:
                        // Código para "HISTORIAL GENERAL"
                        PracticaFinal.panelContenidos.Historial(null);
                        break;
                    case 1:
                        // Código para "HISTORIAL ESPECIFICO"
                        String nomJugador = JOptionPane
                                .showInputDialog(" HISTORIAL JUGADOR \n INTRODUCIR NOMBRE DEL JUGADOR");
                        PracticaFinal.panelContenidos.Historial(nomJugador);
                        break;
                }

            } else {
                // Muestra un mensaje de advertencia si hay una partida en curso
                JOptionPane.showMessageDialog(null, "ANTES DEBES TERMINAR LA "
                        + "PARTIDA EN CURSO");
            }
        };
    }

    /**
     * Crea un ActionListener para mostrar la información del juego
     */
    public ActionListener informacionJuego() {
        return e -> {
            if (!partidaEnCurso) { // Verifica si no hay una partida en curso

                // Muestra la información del juego
                PracticaFinal.panelContenidos.Informacion();

            } else {
                // Muestra un mensaje de advertencia si hay una partida en curso
                JOptionPane.showMessageDialog(null, "ANTES DEBES TERMINAR LA "
                        + "PARTIDA EN CURSO");
            }
        };
    }

    /**
     * Crea un ActionListener para salir del juego
     */
    public ActionListener exit() {
        return e -> {
            if (!partidaEnCurso) { // Verifica si no hay una partida en curso

                // Cierra la aplicación
                System.exit(0);

            } else {
                // Muestra un mensaje de advertencia si hay una partida en curso
                JOptionPane.showMessageDialog(null, "ANTES DEBES TERMINAR LA "
                        + "PARTIDA EN CURSO");
            }

        };
    }
}
