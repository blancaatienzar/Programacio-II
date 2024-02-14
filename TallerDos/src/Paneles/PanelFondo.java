/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Paneles;

import java.awt.CardLayout;
import java.awt.Color;
import static java.awt.Color.black;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *  Clase PanelFondo que crea el panel/inteficie para 
 *  introducir el titulo principal que se visualiza 
 *  en la interficia de Inicio, entre otras
 * 
 * @author B
 */
public class PanelFondo extends JPanel { 

    private JLabel etiqueta; //etiqueta que contendra el titulo
    private JPanel panelFondo; //panel que contendra la etiqueta

    
    /**
     * Metodo que inizializa el panel que contendra
     * la etiqueta titular del programa
     */
    public JPanel titulo() {
        this.setLayout(new CardLayout());

        //CONTENEDOR JPanel panelFondo
        etiqueta = new JLabel();
        panelFondo = new JPanel();

        etiqueta.setText("TALLER 2 - PROGRAMACION II - 2022-2023 - UIB");
        etiqueta.setHorizontalAlignment(SwingConstants.CENTER);
        etiqueta.setFont(new Font("Arial", 1, 20));
        etiqueta.setForeground(Color.GRAY);
        panelFondo.setBackground(black);
        panelFondo.add(etiqueta);
        this.add("INICIO", panelFondo);

        return panelFondo;
    }
}