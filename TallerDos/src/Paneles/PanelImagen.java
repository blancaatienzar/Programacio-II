/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Paneles;

import java.awt.BorderLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *  Clase PanelImagen que crea el panel/interficie
 *  para poder visualizar la imagen/logo de la UIb
 *  en la inteficie principal
 * 
 * @author B
 */
public class PanelImagen extends JPanel {

    public PanelImagen(){
        setup();
        initcomponent();
    }
    public void setup() {
        setLayout(new BorderLayout());
        setSize(670, 670);
    }

    /**
     * Metodo que inizializa el panelImagen introduciendo
     * la imagen a traves de las clases Image y ImageIcon
     */
    public void initcomponent() {
        JLabel imagen = new JLabel();
        Image img = new ImageIcon(getClass().getResource("/dibujo/UIB.jpg")).getImage();
        Image imgtam = img.getScaledInstance(675, 570, 4);
        ImageIcon imgIcon = new ImageIcon(imgtam);
        imagen.setIcon(imgIcon);
        this.add(imagen);
    }
}
