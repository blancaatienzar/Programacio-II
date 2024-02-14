/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Paneles;

import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JPanel;
import tallerdos.NotasMusicales;

/**
 *  Clase PanelBotonesNotas que crea los botones de 
 *  notas musicales y accion se funcionalidad de 
 *  crear una Melodia segun los botones presionados
 * 
 * @author B
 */
public class PanelBotonesNotas extends JPanel {

    private PanelDibujo tablero;

    public PanelBotonesNotas(PanelDibujo tablero) {
        this.tablero = tablero;
        initComponents();
    }

    /**
     * Metodo que inizializa los botones de notas
     * musicales y su accion en el panelDibujo
     */
    private void initComponents() {
        this.setLayout(new GridLayout(0, 8));

        for (NotasMusicales nota : NotasMusicales.values()) {
            JButton jButton = new JButton(nota.name());

            jButton.setBackground(nota.getColor());
            jButton.setOpaque(true);

            jButton.addActionListener(evento -> { //introduce la accion
                if (nota.name() != "FIN") { //mientras no sea FIN se reproduce la musica corrrespondiente
                    reproduccionSonido(nota.cogerSonido()); //se reproduzca su respectivo sonido
                    tablero.sumarNota(nota); //anadir al dibujo
                } else { //si es FIN solo hace la funcion si reproduccir musica
                    tablero.sumarNota(nota); //anadir al dibujo
                }
            });
            this.add(jButton); //boton individual se añade a una columna del panel
        }
    }

    //Metodo que reproduce el sonido de las notas musicales seleccionadas
    private void reproduccionSonido(String sonido) {
        Clip clip = null;
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File("sonidos/" + sonido + ".wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException error) {
            System.err.println("ERROR: PROBLEMAS CON LA REPRODUCCIÓN SONIDO");
        }

    }

}
