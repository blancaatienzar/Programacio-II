/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Paneles;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *  Clase PanelReproduccion que crea el boton de 
 *  reproduccion y accion su funcionalidad de play 
 *  sobre la Melodia creada
 * 
 * @author B
 */
public class PanelReproduccion extends JPanel {

    private PanelDibujo tablero;

    public PanelReproduccion(PanelDibujo tablero) {
        this.tablero = tablero;
        initComponents();
    }
    
    /**
     * Metodo que inizializa el boton de 
     * play y su accion en el panelDibujo
     */
    private void initComponents() {
        this.setLayout(new GridLayout(0, 1));
        JButton botonReproducir = new JButton(">");
        botonReproducir.setFont(new Font("arial", Font.BOLD, 13));
        botonReproducir.addActionListener(evento -> {
            tablero.reproducirMelodia();
        });
        
        this.add(botonReproducir);
    }
}
