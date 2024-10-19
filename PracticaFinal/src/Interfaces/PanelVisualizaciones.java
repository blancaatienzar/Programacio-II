/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import static PracticaFinal.GestorEventos.nombreJugador;
import defPartida.Partida;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import static PracticaFinal.PracticaFinal.datosConfiguracion;
import static PracticaFinal.PracticaFinal.puntos;
import defTetris.Tauler;
import defTetris.Pesa;
import static defTetris.Tauler.COSTAT;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

/**
 * Este panel mostrará la imagen de la UIB, el juego (tetris), el historial de
 * partidas y la informacion.
 * UIB - 2023-2024
 * @author Blanca e Hai Zi
 */
public class PanelVisualizaciones extends JPanel {  

    public static BarraProgresionTemporal barraTemporal;
    public static Thread progresoThread;
    private JTextArea areaVisualizacionResultados;
    private JTextArea areaVisualizacionInformacion;
    public static Tauler tauler;
    public static Pesa figura;
    public static JPanel panelInfoPartida[] = new JPanel[3];
    public static JTextField[] jtext = new JTextField[2];

    /**
     * Constructor de PanelVisualizaciones.
     */
    public PanelVisualizaciones() {
        setVisible(true);
        setLayout(new BorderLayout());
    }

    /**
     * Crea un panel para mostrar el historial de partidas.
     *
     * @param nom el nombre del jugador para filtrar el historial (puede ser
     * null).
     * @return el panel que contiene el historial.
     */
    public JPanel panelHistorial(String nom) {
        JPanel panelHistorial = new JPanel();
        panelHistorial.setVisible(true);
        panelHistorial.setLayout(new BorderLayout());
        Partida gout = new Partida("partidasTetrisUIB.dat");

        if (nom == null) {
            areaVisualizacionResultados = new JTextArea();
            areaVisualizacionResultados.setText("\n\t\tHISTORIAL PARTIDAS TETRIS UIB\n\n" + gout.contingutFitxer());
            areaVisualizacionResultados.setFont(new Font("Monospaced", 1, 20));
        } else {
            areaVisualizacionResultados = new JTextArea();
            areaVisualizacionResultados.setText("\n\tHISTORIAL ESPECIFICO DE " + nom + " PARTIDAS TETRIS UIB\n\n" + gout.filtreNombre(nom));
            areaVisualizacionResultados.setFont(new Font("Monospaced", 1, 20));
        }

        JScrollPane scrollPane = new JScrollPane(areaVisualizacionResultados);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        panelHistorial.add(scrollPane, BorderLayout.CENTER);

        panelHistorial.setVisible(true);

        return panelHistorial;
    }

    /**
     * Crea un panel para mostrar la información del juego.
     * 
     * @return el panel que contiene la información.
     */
    public JPanel panelInformacion() {
        
        JPanel panelInformacion = new JPanel();
        panelInformacion.setVisible(true);
        panelInformacion.setLayout(new BorderLayout());

        areaVisualizacionInformacion = new JTextArea();
        areaVisualizacionInformacion.setForeground(Color.WHITE);
        areaVisualizacionInformacion.setBackground(Color.DARK_GRAY);
        areaVisualizacionInformacion.setFont(new Font("Monospaced", 1, 16));
        areaVisualizacionInformacion.setText(
                "\n                                INFORMACIÓN SOBRE TETRIS UIB\n\n"
                + "    ESTA APLICACIÓN HA SIDO REALIZADA EN EL CONTEXTO DE LA ASIGNATURA PROGRAMACION II\n"
                + "   DEL PRIMER CURSO DE LOS ESTUDIOS DE INGENERIA INFORMÁTICA DE LA UNIVERSITAT DE LES\n"
                + "                    ILLES BALEARS PARA EL CURSO ACADÉMICO 2023-24.\n"
                + " REPRESENTA LA PRÁCTICA QUE HAN DE REALIZAR LOS ESTUDIANTES DE LA ASIGNATURA MENCIONADA.\n"
                + " LOS OBJETIVOS DE ESTA PRÁCTICA PASAN POR TRABAJAR CON UN ENTORNO GRÁFICO E INTERACTIVO\n"
                + " UTILIZANDO LAS PRESTACIONES QUE OFRECEN LAS LIBRERÍAS GRÁFICAS DE JAVA Y LA APLICACIÓN\n"
                + "     DE LOS CONCEPTOS DE OBJETOS Y TIPOS DE DATOS ABSTRACTOS CORRESPONDIENTE A LA\n"
                + "                            PROGRAMACIÓN ORIENTADA A OBJETOS.");

        panelInformacion.add(areaVisualizacionInformacion, BorderLayout.CENTER);

        panelInformacion.setVisible(true);
        return panelInformacion;
    }

    /**
     * Crea un panel de espera que muestra una imagen de la UIB.
     * 
     * @return el panel de espera.
     */
    public JPanel panelStandby() { 
        removeAll();
        revalidate();
        
        JPanel panelStandby = new JPanel();
        panelStandby.setVisible(true);
        panelStandby.setBackground(Color.BLACK);
        panelStandby.setLayout(new BorderLayout());
        JLabel imagenUIB;
        try {
            imagenUIB = new JLabel(new ImageIcon(ImageIO.read(new File("imagenes/TETRIS_UIB.jpg")).getScaledInstance(935, 600, 4)));
            imagenUIB.setBackground(Color.BLACK);
            imagenUIB.setBorder(BorderFactory.createEmptyBorder());
            panelStandby.add(imagenUIB);
        } catch (IOException e) {
            System.out.println("ERROR " + e);
        }
        add(panelStandby);
        return panelStandby;
    }

    /**
     * Crea un panel para la partida de Tetris.
     * 
     * @return el panel de la partida.
     */
    public JPanel panelPartida() {
        removeAll();
        revalidate();
        
        panelInfoPartida[2] = new JPanel();
        panelInfoPartida[2].setBackground(Color.BLACK);

        JLabel[] conceptos = {new JLabel("JUGADOR"), new JLabel("PUNTUACIÓN")};
        String[] info = {"  "+nombreJugador+"  ", "  "+puntos + " puntos  "};
        

        for (int i = 0; i < panelInfoPartida.length - 1; i++) {

            panelInfoPartida[i] = new JPanel();
            panelInfoPartida[i].setBackground(Color.BLACK);

            conceptos[i].setBackground(Color.BLACK);
            conceptos[i].setForeground(Color.red);
            conceptos[i].setFont(new Font("Arial", Font.BOLD, 20));
            panelInfoPartida[i].add(conceptos[i]);

            jtext[i] = new JTextField();
            jtext[i].setBackground(Color.BLACK);
            jtext[i].setForeground(Color.WHITE);
            jtext[i].setHorizontalAlignment(JTextField.RIGHT);
            jtext[i].setText(info[i]);
            jtext[i].setFont(new Font("Arial", Font.BOLD, 20));
            panelInfoPartida[i].add(jtext[i]);

            panelInfoPartida[2].add(panelInfoPartida[i],BorderLayout.CENTER);

        }

        JPanel panelPartida = new JPanel();
        panelPartida.setVisible(true);
        panelPartida.setBackground(Color.BLACK);
        panelPartida.setLayout(new BorderLayout());

        // CREACiÓ PESA
        figura = new Pesa();
        figura.setSize(COSTAT * 3, 600);

        // CREACiÓ TAULER
        tauler = new Tauler();
        tauler.addMouseListener(figura);

        JPanel jpanel = new JPanel();
        jpanel.addMouseListener(figura);
        jpanel.setBackground(Color.BLACK);
        jpanel.setSize(700, 600);
        jpanel.add(figura, BorderLayout.WEST);
        jpanel.add(tauler, BorderLayout.EAST);

        // CREACiÓ BOTONES JUEGO
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(2, 1));

        // COMPONENTE JButton NUEVA FORMA
        try {
            JButton nuevaForma = new JButton(new ImageIcon(ImageIO.read(new File("iconos/iconoBotonNuevaForma.jpg"))));
            nuevaForma.setBackground(Color.BLACK);
            nuevaForma.setBorder(BorderFactory.createEmptyBorder());
            nuevaForma.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    figura.pecesAleatories();
                    figura.getParent().repaint();
                    puntos += Integer.parseInt(datosConfiguracion[2]);
                    jtext[1].setText("  "+puntos + " puntos  ");
                    panelInfoPartida[1].revalidate(); 
                    panelInfoPartida[1].repaint();
                }
            });
            panelBotones.add("NUEVA FORMA", nuevaForma);
        } catch (IOException e) {
        }

        // COMPONENTE JButton ROTAR FORMA
        try {
            JButton rotarForma = new JButton(new ImageIcon(ImageIO.read(new File("iconos/iconoBotonRotar.jpg"))));
            rotarForma.setBackground(Color.BLACK);
            rotarForma.setBorder(BorderFactory.createEmptyBorder());
            rotarForma.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    figura.rotar();
                    figura.getParent().repaint();
                    puntos += Integer.parseInt(datosConfiguracion[1]);

                    jtext[1].setText("  "+puntos + " puntos  ");
                    jtext[1].repaint();
                    panelInfoPartida[1].revalidate(); // Ensure the panel is revalidated
                    panelInfoPartida[1].repaint(); // Ensure the panel is repainted
                }
            });
            panelBotones.add("ROTAR FORMA", rotarForma);
        } catch (IOException e) {
        }


        JSplitPane separador1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        separador1.setLeftComponent(jpanel);
        separador1.setRightComponent(panelBotones);
        separador1.setDividerLocation(690);
        separador1.setEnabled(false);

        JSplitPane separador2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        separador2.setLeftComponent(separador1);

        separador2.setDividerLocation(475);
        separador2.setEnabled(false);

        separador2.setRightComponent(panelInfoPartida[2]);

        panelPartida.add(separador2);
        add(panelPartida);
                
        return panelPartida;
    }
}
