/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package PracticaFinal;

import Interfaces.PanelContenidos;
import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 * Clase principal que inicia la aplicación de la práctica final.
 * Esta aplicación muestra un JFrame con un panel de contenidos.
 * Se utiliza para propósitos educativos en un taller de Programación II.
 * UIB - 2023-2024
 * @author Blanca e Hai Zi
 */
public class PracticaFinal extends JFrame {

    // Panel de contenidos que se muestra en la ventana principal
    public static final PanelContenidos panelContenidos = new PanelContenidos(); 

    // Instancia de la ventana principal    
    public static  PracticaFinal frame = new PracticaFinal(); 

    // Configuración inicial de la aplicación
    public static String[] datosConfiguracion = {"1","-1","-5", "imagenes/chocolate.JPG"};

    // Tiempo inicial del juego
    public static int tiempo = 5;

    // Puntuación inicial del juego
    public static int puntos = 0;
    
    /**
     * Método principal que inicia la aplicación.
     * @param args Los argumentos de la línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        frame.setVisible(true);
    }  

    /**
     * Constructor de la clase PracticaFinal.
     * Configura el JFrame principal y añade el panel de contenidos.
     */
    public PracticaFinal() {
        setTitle("TALLER 2 - PROGRAMACION II - 2023-2024 - UIB"); //título contenedor juego
        setSize(1000, 690); // Tamaño inicial del JFrame
        
        setResizable(false); // No se permite cambiar el tamaño de la ventana
        setLayout(new BorderLayout()); // Se utiliza un BorderLayout como Layout Manager
        setLocationRelativeTo(null); // La ventana aparece en el centro de la pantalla
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Al cerrar la ventana, la aplicación se cierra
        setContentPane(panelContenidos); // El contenido del JFrame es el panelContenidos
    }
    
}
