/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package defTetris;

import static PracticaFinal.PracticaFinal.datosConfiguracion;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import static defTetris.Tauler.COSTAT;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Clase que representa una casilla en el juego de Tetris.
 * UIB - 2023-2024
 * @author Blanca e Hai Zi
 */
public class Casella {

    private BufferedImage img;
    private Rectangle2D.Float rec;
    private Boolean ocupada;

    /**
     * Constructor de la clase Casella.
     * @param r Rectángulo que representa la casilla.
     * @param ocu Estado de ocupación de la casilla.
     */
    public Casella(Rectangle2D.Float r, Boolean ocu) {
        this.rec = r;
        this.ocupada = ocu;
        try {
            img = ImageIO.read(new File(datosConfiguracion[3]));
        } catch (IOException e) {
        }
    }

    /**
     * Método para dibujar la casilla.
     * @param g Objeto Graphics donde se dibujará la casilla.
     */
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fill(this.rec);
        if (this.ocupada) {
            g.drawImage(img.getScaledInstance(COSTAT - 2, COSTAT - 2, 
       Image.SCALE_SMOOTH), (int) this.rec.x, (int) this.rec.y, null);
        }
    }

    /**
     * Método para establecer la casilla como ocupada.
     */
    void setCasella() {
        this.ocupada = true;
        try {
            img = ImageIO.read(new File(datosConfiguracion[3]));
        } catch (IOException e) {
        }
    }

    /**
     * Método para obtener el rectángulo que representa la casilla.
     * @return El rectángulo que representa la casilla.
     */
    public Rectangle2D.Float getRec() {
        return rec;
    }

    /**
     * Método para verificar si la casilla está ocupada.
     * @return True si la casilla está ocupada, false en caso contrario.
     */
    boolean isOcupada() {
        return ocupada;
    }

    /**
     * Método para establecer el estado de ocupación de la casilla.
     * @param b Estado de ocupación de la casilla.
     */
    void setOcupada(boolean b) {
        ocupada = b;
    }
}
