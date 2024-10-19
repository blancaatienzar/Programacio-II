/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package defTetris;

import static Interfaces.PanelVisualizaciones.jtext;
import static Interfaces.PanelVisualizaciones.panelInfoPartida;
import static PracticaFinal.PracticaFinal.datosConfiguracion;
import static PracticaFinal.PracticaFinal.puntos;
import static defTetris.Pesa.figuraAle;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
 * Clase que representa el tablero del juego de Tetris.
 * UIB - 2023-2024
 * @author Blanca e Hai Zi
 */
public class Tauler extends JPanel {

    public static final int DIMENSIO = 20;

    private static final int MAXIM = 475;
    public static final int COSTAT = MAXIM / DIMENSIO;
    public Casella t[][];

    /**
     * Constructor de la clase Tauler. Inicializa el tablero.
     */
    public Tauler() {
        this.setBackground(Color.BLACK);
        t = new Casella[DIMENSIO][DIMENSIO];
        int y = 0;
        for (int i = 0; i < DIMENSIO; i++) {
            int x = 0;
            for (int j = 0; j < DIMENSIO; j++) {
                Rectangle2D.Float r = new Rectangle2D.Float(x, y, COSTAT - 2, COSTAT - 2);

                t[i][j] = new Casella(r, false);
                x += COSTAT;
            }
            y += COSTAT;

        }
    }

    /**
     * Método para pintar el tablero.
     */
    @Override
    public void paintComponent(Graphics g) {
        for (int i = 0; i < DIMENSIO; i++) {
            for (int j = 0; j < DIMENSIO; j++) {
                t[i][j].paintComponent(g);
            }
        }
    }

    /**
     * Método para obtener la dimensión preferida del tablero.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(MAXIM, MAXIM);
    }

    /**
     * Método para colocar una pieza en el tablero.
     * @param i Fila donde se coloca la pieza.
     * @param i0 Columna donde se coloca la pieza.
     */
    void Posa(int i, int i0) {
        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                if (figuraAle[j][k] == 1) {
                    t[i + j][i0 + k].setCasella();
                }
            }
        }
    }

    /**
     * Método para verificar si las coordenadas están dentro de una casilla.
     * @param i Fila de la casilla.
     * @param j Columna de la casilla.
     * @param x Coordenada X.
     * @param y Coordenada Y.
     * @return True si las coordenadas están dentro de la casilla, false en caso contrario.
     */
    boolean dinsCasella(int i, int j, int x, int y) {

        // Convertir las coordenadas de punto entero a flotante
        float xf = (float) x;
        float yf = (float) y;

        return t[i][j].getRec().contains(xf, yf);
    }

    /**
     * Método para verificar si una casilla está ocupada.
     * @param i Fila de la casilla.
     * @param j Columna de la casilla.
     * @return True si la casilla está ocupada, false en caso contrario.
     */
    boolean isOcupat(int i, int j) {
        try {
            for (int k = 0; k < 3; k++) {
                for (int l = 0; l < 3; l++) {
                    if ((DIMENSIO - 1 <= (k + i) && DIMENSIO <= (j + l)) || (figuraAle[k][l] == 1 && t[(k + i)][(j + l)].isOcupada())) {
                        return true;
                    }
                }
            }
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            return true;
        }
    }

    /**
     * Método para vaciar una casilla.
     * @param i Fila de la casilla.
     * @param j Columna de la casilla.
     */
    void buida(int i, int j) {
        t[i][j].setOcupada(false);
    }

    /**
     * Método para obtener el rectángulo de una casilla.
     * @param i Fila de la casilla.
     * @param j Columna de la casilla.
     * @return El rectángulo que representa la casilla.
     */
    Rectangle getRectangle(int i, int j) {
        return t[i][j].getRec().getBounds();
    }

    /**
     * Método para verificar si alguna fila o columna está totalmente ocupada y eliminarla.
     */
    public void algunaFilaOColumnaTotalmenteOcupada() {
        // Verificar filas
        for (int i = 0; i < DIMENSIO; i++) {
            boolean filaTotalmenteOcupada = true;
            for (int j = 0; j < DIMENSIO; j++) {
                if (!t[i][j].isOcupada()) {
                    filaTotalmenteOcupada = false;
                    break;
                }
            }
            if (filaTotalmenteOcupada) {
                puntos += Integer.parseInt(datosConfiguracion[0]);
                jtext[1].setText("  " + puntos + " puntos  ");
                
                // Asegurar que el panel se vuelva a validar
                panelInfoPartida[1].revalidate();

                // Asegurar que el panel se vuelva a pintar
                panelInfoPartida[1].repaint(); 
                
                for (int j = 0; j < DIMENSIO; j++) {
                    buida(i, j);
                }
            }
        }

        // Verificar columnas
        for (int j = 0; j < DIMENSIO; j++) {
            boolean columnaTotalmenteOcupada = true;
            for (int i = 0; i < DIMENSIO; i++) {
                if (!t[i][j].isOcupada()) {
                    columnaTotalmenteOcupada = false;
                    break;
                }
            }
            if (columnaTotalmenteOcupada) {
                puntos += Integer.parseInt(datosConfiguracion[0]);
                jtext[1].setText("  " + puntos + " puntos  ");
                
                // Asegurar que el panel se vuelva a validar
                panelInfoPartida[1].revalidate();

                // Asegurar que el panel se vuelva a pintar
                panelInfoPartida[1].repaint(); 
                
                for (int i = 0; i < DIMENSIO; i++) {
                    buida(i, j);
                }
            }

        }
    }
}
