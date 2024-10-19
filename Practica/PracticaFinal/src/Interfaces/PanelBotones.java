/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import PracticaFinal.GestorEventos;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Panel que contiene botones para diferentes funciones.
 * UIB - 2023-2024
 * @author Blanca e Hai Zi
 */
public class PanelBotones extends JPanel{ 

    private GestorEventos event = new GestorEventos();
    
    /**
     * Constructor de la clase PanelBotones.
     * Inicializa el panel y sus componentes.
     */
    public PanelBotones() { 
        setup();
        initComponents();
    }

    /**
     * Configura la disposición del panel.
     */
    private void setup() {
        setLayout(new GridLayout(5, 1));
    }

    /**
     * Inicializa y configura los botones del panel.
     */
    private void initComponents() {

        //COMPONENTE JButton para iniciar una nueva partida
        JButton nuevaPartidaBoton = new JButton("NUEVA PARTIDA");
        nuevaPartidaBoton.setFont(new Font("arial", Font.BOLD, 11));
        nuevaPartidaBoton.setForeground(Color.WHITE);
        nuevaPartidaBoton.setBackground(Color.BLACK);
        nuevaPartidaBoton.addActionListener(event.crearPartida());
        add("CREAR", nuevaPartidaBoton);

        //COMPONENTE JButton  para acceder a la configuración del juego
        JButton configuracionBoton = new JButton("CONFIGURACIÓN");
        configuracionBoton.setFont(new Font("arial", Font.BOLD, 11));
        configuracionBoton.setForeground(Color.WHITE);
        configuracionBoton.setBackground(Color.BLACK);
        configuracionBoton.addActionListener(event.configuracionJuego());
        add("REPRODUCIR", configuracionBoton);

        //COMPONENTE JButton para ver el historial de partidas
        JButton historialBoton = new JButton("HISTORIAL");
        historialBoton.setFont(new Font("arial", Font.BOLD, 11));
        historialBoton.setForeground(Color.WHITE);
        historialBoton.setBackground(Color.BLACK);
        historialBoton.addActionListener(event.estadisticas());
        add("ADIVINAR", historialBoton);
        
        //COMPONENTE JButton para acceder a la información del juego
        JButton informacionBoton = new JButton("INFORMACIÓN");
        informacionBoton.setFont(new Font("arial", Font.BOLD, 11));
        informacionBoton.setForeground(Color.WHITE);
        informacionBoton.setBackground(Color.BLACK);
        informacionBoton.addActionListener(event.informacionJuego());
        add("ADIVINAR", informacionBoton);

        //COMPONENTE JButton para salir del juego
        JButton salirBoton = new JButton("SALIR");
        salirBoton.setFont(new Font("arial", Font.BOLD, 11));
        salirBoton.setForeground(Color.WHITE);
        salirBoton.setBackground(Color.BLACK);
        salirBoton.addActionListener(event.exit());
        add("SALIR", salirBoton);
    }

}

