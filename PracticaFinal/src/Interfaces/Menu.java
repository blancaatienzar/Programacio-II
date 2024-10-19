/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import java.awt.BorderLayout;
import PracticaFinal.GestorEventos;
import java.awt.Color;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
 * Clase que define el menú de la aplicación.
 * Contiene una barra de menú y botones con iconos.
 * UIB - 2023-2024
 * @author Blanca e Hai Zi
 */
public class Menu extends JPanel {
    
    private BarraMenu barraMenu;
    private IconosMenu iconosMenu;

    /**
     * Constructor de la clase Menu.
     * Configura la disposición de los componentes.
     */
    public Menu() {
        setLayout(new BorderLayout());
        barraMenu = new BarraMenu();
        iconosMenu = new IconosMenu();
        add(barraMenu, BorderLayout.NORTH);
        add(iconosMenu, BorderLayout.CENTER);
    }

    /**
     * Clase interna que representa la barra de menú.
     */
    public class BarraMenu extends JMenuBar {
        
        private GestorEventos event = new GestorEventos();
        
        /**
         * Constructor de la barra de menú.
         * Configura su apariencia y componentes.
         */
        public BarraMenu() {
            this.setBackground(Color.BLACK);
            this.setOpaque(true);
            initComponents();
        }

        /**
         * Inicializa y configura los componentes del menú.
         */
        private void initComponents() {
           
            ////////DECLARACIÓN DE LAS COMPONENTES JMenu ASOCIADAS A barraMenu
            JMenu menu = new JMenu("MENU");
            menu.setForeground(Color.WHITE);
            menu.setBackground(Color.BLACK);
            menu.setOpaque(true);

            ////////COMPONENTES JMenuItem asociadas a la componente JMenu generalMenu
            //DECLARACIONES COMPONENTES JMenuItem ASOCIADAS A generalMenu
            JMenuItem nuevaPartidaMenu = new JMenuItem("NUEVA PARTIDA");
            nuevaPartidaMenu.addActionListener(event.crearPartida());
            nuevaPartidaMenu.setForeground(Color.WHITE);
            nuevaPartidaMenu.setBackground(Color.BLACK);

            JMenuItem configuracionMenu = new JMenuItem("CONFIGURACIÓN");
            configuracionMenu.addActionListener(event.configuracionJuego());
            configuracionMenu.setForeground(Color.WHITE);
            configuracionMenu.setBackground(Color.BLACK);
            
            JMenuItem historialMenu = new JMenuItem("HISTORIAL");
            historialMenu.addActionListener(event.estadisticas());
            historialMenu.setForeground(Color.WHITE);
            historialMenu.setBackground(Color.BLACK);

            JMenuItem informacioMenu = new JMenuItem("INFORMACIÓN");
            informacioMenu.addActionListener(event.informacionJuego());
            informacioMenu.setForeground(Color.WHITE);
            informacioMenu.setBackground(Color.BLACK);

            JMenuItem salirMenu = new JMenuItem("SALIR");
            salirMenu.addActionListener(event.exit());
            salirMenu.setForeground(Color.WHITE);
            salirMenu.setBackground(Color.BLACK);
            
            //inclusión de las componentes JMenuItem a la componente 
            //JMenu generalMenu
            menu.add(nuevaPartidaMenu);
            menu.add(configuracionMenu);
            menu.add(historialMenu);
            menu.add(informacioMenu);
            menu.add(salirMenu);

            //INLCUSIÓN MENUS EN LA BARRA DE MENUS
            add(menu);
        }
    }

    /**
     * Clase interna que representa los botones con iconos del menú.
     */
    public class IconosMenu extends JToolBar {

        private JButton nuevaPartidaIcono, configuracionIcono, historialIcono, informacionIcono, salirIcono;
        private GestorEventos event = new GestorEventos();

        /**
         * Constructor de los iconos del menú.
         * Configura la disposición y carga los iconos.
         */
        public IconosMenu() {
            setup();
            initComponents();
        }

        /**
         * Configura la disposición de los iconos.
         */
        public void setup() {
            setLayout(new FlowLayout(FlowLayout.LEFT));
            setBackground(Color.BLACK);
            this.setEnabled(false);
        }

        /**
         * Inicializa y configura los botones con iconos del menú.
         */
        public void initComponents() {
            try {
                nuevaPartidaIcono = new JButton(new ImageIcon(ImageIO.read(new File("iconos/iconoNuevaPartida.jpg"))));
                nuevaPartidaIcono.setBackground(Color.BLACK);
                nuevaPartidaIcono.setBorder(BorderFactory.createEmptyBorder());
                nuevaPartidaIcono.addActionListener(event.crearPartida());
                add(nuevaPartidaIcono);
            } catch (IOException e) {
                System.out.println("ERROR "+ e);
            }
            try {
                configuracionIcono = new JButton(new ImageIcon(ImageIO.read(new File("iconos/iconoConfiguracion.jpg"))));
                configuracionIcono.setBackground(Color.BLACK);
                configuracionIcono.setBorder(BorderFactory.createEmptyBorder());
                configuracionIcono.addActionListener(event.configuracionJuego());
                add(configuracionIcono);
            } catch (IOException e) {
                System.out.println("ERROR "+ e);
            }
            try {
                historialIcono = new JButton(new ImageIcon(ImageIO.read(new File("iconos/iconoHistorial.jpg"))));
                historialIcono.setBackground(Color.BLACK);
                historialIcono.setBorder(BorderFactory.createEmptyBorder());
                historialIcono.addActionListener(event.estadisticas());
                add(historialIcono);
            } catch (IOException e) {
                System.out.println("ERROR "+ e);
            }
            try {
                informacionIcono = new JButton(new ImageIcon(ImageIO.read(new File("iconos/iconoInformacion.jpg"))));
                informacionIcono.setBackground(Color.BLACK);
                informacionIcono.setBorder(BorderFactory.createEmptyBorder());
                informacionIcono.addActionListener(event.informacionJuego());
                add(informacionIcono);
            } catch (IOException e) {
                System.out.println("ERROR "+ e);
            }
            try {
                salirIcono = new JButton(new ImageIcon(ImageIO.read(new File("iconos/iconoSalir.jpg"))));
                salirIcono.setBackground(Color.BLACK);
                salirIcono.setBorder(BorderFactory.createEmptyBorder());
                salirIcono.addActionListener(event.exit());
                add(salirIcono);
            } catch (IOException e) {
                System.out.println("ERROR " + e);
            }
        }
    }
}
