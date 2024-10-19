/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import static Interfaces.PanelVisualizaciones.barraTemporal;
import static Interfaces.PanelVisualizaciones.progresoThread;
import static PracticaFinal.LecturaDatos.partidaEnCurso;
import PracticaFinal.PracticaFinal;
import static PracticaFinal.PracticaFinal.puntos;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import static PracticaFinal.PracticaFinal.tiempo;
import defPartida.Partida;
import javax.swing.JOptionPane;

/**
 * Clase que organiza los paneles de la interfaz gráfica de usuario. Contiene
 * métodos para inicializar y mostrar diferentes paneles.
 * UIB - 2023-2024
 * @author Blanca e Hai Zi
 */
public class PanelContenidos extends JPanel { 

    // Componentes de la interfaz
    private final JSplitPane separador1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    private final JSplitPane separador2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    private final JSplitPane separador3 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    private final JSplitPane separador4 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    private final Menu menu = new Menu();
    private final PanelBotones panelBotones = new PanelBotones();
    public PanelVisualizaciones panelVisualizaciones = new PanelVisualizaciones();

    /**
     * Constructor de la clase PanelContenidos. Inicializa la disposición de los
     * componentes.
     */
    public PanelContenidos() {
        setup();
        initComponents();

    }

    /**
     * Configura el diseño del panel principal.
     */
    public void setup() {
        setLayout(new BorderLayout());
    }

    /**
     * Inicializa y organiza los componentes de la interfaz. Panel principal con
     * la imagen de la UIB
     */
    public void initComponents() { 
        removeAll();
        revalidate();
        separador1.setLeftComponent(menu);
        separador1.setDividerLocation(60);
        separador1.setEnabled(false);

        separador3.setLeftComponent(panelBotones);

        separador3.setRightComponent(panelVisualizaciones.panelStandby());
        separador3.setEnabled(false);

        separador1.setRightComponent(separador3);

        separador2.setLeftComponent(separador1);

        add(separador2);
    }

    /**
     * Muestra el panel de partida con una barra de progreso.
     */
    public void Partida() { 

        barraTemporal = new BarraProgresionTemporal(500);
        progresoThread = new Thread(new Runnable() {
            @Override
            public void run() {

                // Actualizamos la barra de progreso en incrementos hasta llegar al 100%
                for (int i = 0; i <= 100; i++) {
                    barraTemporal.setValorBarraTemporal(i);
                    try {
                        Thread.sleep(tiempo * 10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // Finaliza la partida y muestra un mensaje
                partidaEnCurso = false;
                JOptionPane.showMessageDialog(null, "PARTIDA FINALIZADA - EL TIEMPO HA TERMINADO");
                
                // Guarda los datos de la partida
                Partida p = new Partida("partidasTetrisUIB.dat");
                p.inserirRegistre();
                puntos = 0;
                
                // Reinicia la interfaz
                PracticaFinal.panelContenidos.initComponents();                
            }
        });

        separador4.setLeftComponent(panelVisualizaciones.panelPartida());
        separador4.setDividerLocation(527);
        separador4.setEnabled(false);
        separador4.setRightComponent(barraTemporal);
        separador3.setLeftComponent(panelBotones);

        separador3.setRightComponent(separador4);
        separador3.revalidate();
        separador3.repaint();
        progresoThread.start();

    }

    /**
     * Muestra el historial de partidas del usuario.
     * @param nombreUsuario El nombre del usuario cuyo historial se mostrará.
     */
    public void Historial(String nombreUsuario) { 
        separador3.setLeftComponent(panelBotones);
        separador3.setRightComponent(panelVisualizaciones.panelHistorial(nombreUsuario));
        separador3.revalidate();
        separador3.repaint();
    }

    /**
     * Muestra información adicional.
     */
    public void Informacion() { 
        separador3.setLeftComponent(panelBotones);
        separador3.setRightComponent(panelVisualizaciones.panelInformacion());
        separador3.revalidate();
        separador3.repaint();
    }

}
