/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Paneles;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import tallerdos.TallerDos;

/** 
 *  Clase PanelContenidos que crea los separadores
 *  para componer y introducir los diferentes
 *  paneles del programa
 *  Tambien utiliza metodos para ser cambiados 
 *  durante su ejecucion
 * 
 * @author B
 */
public class PanelContenidos extends JPanel { 

    private final JSplitPane separadorUno = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    private final JSplitPane separadorDos = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    private final JSplitPane separadorTres = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

    public PanelContenidos() {
        setup();
        initComponents();
    }

    public void setup() {
        setLayout(new BorderLayout());
    }
    
    /**
     * Metodo que inizializa la interfaz principal que 
     * visualiza PanelImagen (UIB) y PanelFondo (que 
     * contiene la etiqueta titulo)
     */
    public void initComponents() { //Panel principal
        separadorUno.setLeftComponent(new PanelImagen());
        separadorUno.setDividerLocation(530);
        separadorUno.setRightComponent(new PanelFondo().titulo());
        separadorUno.setEnabled(false);

        separadorDos.setLeftComponent(separadorUno);
        separadorDos.setRightComponent(new PanelBotones(new PanelDibujo(TallerDos.notass)));
        separadorDos.setDividerLocation(575);
        separadorDos.setEnabled(false);
        separadorTres.setRightComponent(separadorDos);

        add(separadorTres);
    }
    
    /**
     * Metodo que inizializa una de las interfaces secundarias 
     * que visualiza PanelDibujo  y PanelReproduccion (que 
     * contiene un boton)(Tablero donde se da al play)
     */
    public void cambiarActividadesReproduccion() { 
        PanelDibujo tablero2 = new PanelDibujo(TallerDos.notass);
        separadorUno.setLeftComponent(tablero2);
        separadorUno.setDividerLocation(530);
        separadorUno.setRightComponent(new PanelReproduccion(tablero2));

        separadorDos.setDividerLocation(575);
    }
    
    /**
     * Metodo que actualiza una de las interfaces secundarias 
     * que visualiza el PanelFondo ( con la etiqueta que 
     * contiene titulo) utilizada despues de acabar el play
     */
    public void cambiarAdivinarPonerLetras() { 
        separadorUno.setDividerLocation(530);
        separadorUno.setRightComponent(new PanelFondo().titulo());

        separadorDos.setDividerLocation(575);
    }

    /**
     * Metodo que actualiza una de las interfaces secundarias 
     * que visualiza el panelDibujo y panelActividades por 
     * los botones respectivos a las notas musicales para la
     * funcionalidad de crear melodia
     */
    public void cambiarActividadesNotas() {
        PanelDibujo tablero1 = new PanelDibujo(TallerDos.notass);
        separadorUno.setLeftComponent(tablero1);
        separadorUno.setDividerLocation(530);
        separadorUno.setRightComponent(new PanelBotonesNotas(tablero1));

        separadorDos.setDividerLocation(575);
    }

    /**
     * Metodo que actualiza una de las interfaces secundarias 
     * que visualiza el panelDibujo y panelActividades por 
     * los botones respectivos a las notas musicales para la
     * funcionalidad de adivinar melodia
     */
    public void cambiarActividadesAdivinar() {
        PanelDibujo tablero = new PanelDibujo(TallerDos.notass);

        separadorUno.setLeftComponent(tablero);
        separadorUno.setDividerLocation(530);
        separadorUno.setRightComponent(new PanelAdivinar(tablero));

        separadorDos.setDividerLocation(575);
    }

}