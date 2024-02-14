/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import tallerdos.NotasMusicales;
import tallerdos.TallerDos;

/**
 * Clase PanelDibujo que actualiza el tablero/dibujo durante la ejecucion segun
 * la funcionalidad presionada por los botones
 *
 * @author B
 */
public class PanelDibujo extends JPanel {

    private JButton botonNotas[] = new JButton[110]; //creacion de botones por cada rectangulo
    private int indice = 0;
    private NotasMusicales notasMus[] = new NotasMusicales[110]; //Array de Enum sobre todas las notas

    public PanelDibujo(NotasMusicales[] notas) {//constructor
        this.notasMus = notas;
        setup();
        initComponents();
    }

    /**
     * Inicializa el panel como una tabla de 10x11 y le asigna sus valores
     * correspondientes
     */
    public void setup() {
        setLayout(new GridLayout(10, 11, 10, 10));
        setSize(670, 670);
        setBackground(Color.BLACK);
    }

    /**
     * Inicializa el panel como una tabla de 10x11 y le asigna a todos sus
     * componentes el color negro
     */
    public void initComponents() {
        for (int i = 0; i < 110; i++) {
            botonNotas[i] = new JButton();
            Border empty = BorderFactory.createEmptyBorder(10, 10, 10, 10);

            botonNotas[i].setBackground(Color.BLACK);
            botonNotas[i].setOpaque(true);
            botonNotas[i].setBorder(empty);
            add(botonNotas[i]);
        }
    }

    /**
     * Actualiza la tabla segun la nota musical seleccionada y se le asigna los
     * atributos obtenidos por la clase Enum (color y sonido)
     */
    public void sumarNota(NotasMusicales nota) {
        if ((110 <= indice) || (nota == NotasMusicales.FIN)) {
            //JOptionPane.showMessageDialog(this, "MELODIA CREADA");
            TallerDos.notass = notasMus;  //guarda en un array la melodia
            TallerDos.panelContenidos.initComponents();
            indice = 0; //retorna el inici a 0
            return;
        }
        notasMus[indice] = nota;
        botonNotas[indice].setLayout(new BorderLayout());
        botonNotas[indice].setBackground(nota.getColor()); //asignaccion de color
        indice++;
    }


    /**
     * Actualiza la tabla de uno en uno con la melodia creada anteriormente y se
     * asignan los atributos segun el boton play
     */
    public void reproducirMelodia() {
        NotasMusicales name = notasMus[indice]; //guardamos la ultima nota a reproducir
        if (name == null) {
            TallerDos.panelContenidos.cambiarAdivinarPonerLetras();
            TallerDos.notass = notasMus;
            indice = 0;
            return;
        }
        botonNotas[indice].setBackground(name.getColor());
        reproduccionSonido(name.cogerSonido());
        indice++;
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

    /**
     * Actualiza la tabla de uno en uno con la melodia creada anteriormente y se
     * asignan los atributos y unos sonidos de error o ganar
     */
    public void adivinarMelodia(NotasMusicales nota) {
        //primero comprueba que el primero boton sea nullo, si es nulo significa 
        //que no hay melodia y todos los botones menos FIN deben dar error
        if ((notasMus[0] == null) && (nota != NotasMusicales.FIN)) {
            reproduccionSonido("error");

            //Segundo seguros de que hay melodia, comprobamos que no sea nulo 
            //y por lo tanto comprobamos si son iguales, si son iguales salta
            
        } else if (notasMus[indice] != null && notasMus[indice].equals(nota)) {
            botonNotas[indice].setBackground(nota.getColor());
            reproduccionSonido(nota.cogerSonido());
            indice++;

            //Tercero si pulsa FIN o intenta poner mas de 110 rectangulos
            //acabara inmediatamente
        } else if ((indice >= 110) || (nota == NotasMusicales.FIN)) {
            TallerDos.panelContenidos.initComponents();
            return;

            //Cuarto si no cumple ninguno de los anteriores significa que es un error
        } else {
            reproduccionSonido("error");
        }

        //Quinto salta aqui si lo ha adivinado
        if ((notasMus[indice] == null) && (notasMus[0] != null)) {

            reproduccionSonido("campeones");
            JOptionPane.showMessageDialog(null, "¡Has adivinado todas las notas!");

        }

    }
}
