/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package defTetris;

import static Interfaces.PanelVisualizaciones.tauler;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import static defTetris.Tauler.COSTAT;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import javax.swing.JPanel;

/**
 * Clase que representa una pieza en el juego de Tetris.
 * UIB - 2023-2024
 * 
 * @author Blanca e Hai Zi
 */
public class Pesa extends JPanel implements MouseListener, MouseMotionListener {

    public static int[][] figuraAle = new int[3][3];
    private Casella r[][];
    private Rectangle2D.Float rec;
    public static int x, y;
    private int selectedRow, selectedCol;

    /**
     * Constructor de la clase Pesa.
     */
    public Pesa() {
        setLocation(3, 201);
        this.setBackground(Color.BLACK);
        r = new Casella[3][3];

        pecesAleatories();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        inicializarCasellas();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getX() < 0 || e.getY() < 0 || e.getX() > getWidth() || e.getY() > getHeight()) {
            setLocation(e.getX() - getWidth() / 2, e.getY() - getWidth() / 2);
        }
        x = e.getX();
        y = e.getY();
        selectedRow = y / COSTAT;
        selectedCol = x / COSTAT;
    }

    @Override
    public void paintComponent(Graphics g) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (figuraAle[i][j] == 1) {
                    r[i][j].paintComponent(g);
                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COSTAT * 3, COSTAT * 3);
    }

    /**
     * Método para generar piezas de manera aleatoria.
     */
    public void pecesAleatories() {
        Random ran = new Random();
        int opc = ran.nextInt(8);
        switch (opc) {
            case 0:
                figuraAle = new int[][] {
                        { 1, 0, 0 },
                        { 1, 0, 0 },
                        { 1, 0, 0 } };
                break;
            case 1:
                figuraAle = new int[][] {
                        { 0, 0, 0 },
                        { 1, 1, 0 },
                        { 1, 1, 0 } };
                break;
            case 2:
                figuraAle = new int[][] {
                        { 1, 0, 0 },
                        { 1, 0, 0 },
                        { 1, 1, 0 } };
                break;
            case 3:
                figuraAle = new int[][] {
                        { 0, 0, 1 },
                        { 0, 0, 1 },
                        { 0, 1, 1 } };
                break;
            case 4:
                figuraAle = new int[][] {
                        { 0, 0, 0 },
                        { 0, 1, 1 },
                        { 1, 1, 0 } };
                break;
            case 5:
                figuraAle = new int[][] {
                        { 0, 0, 0 },
                        { 1, 1, 0 },
                        { 0, 1, 1 } };
                break;
            case 6:
                figuraAle = new int[][] {
                        { 0, 0, 0 },
                        { 0, 1, 0 },
                        { 1, 1, 1 } };
                break;
            case 7:
                figuraAle = new int[][] {
                        { 1, 0, 0 },
                        { 0, 0, 0 },
                        { 0, 0, 0 } };
                break;
        }

        y = 0;
        for (int i = 0; i < 3; i++) {
            x = 0;
            for (int j = 0; j < 3; j++) {

                if (figuraAle[i][j] == 1) {
                    rec = new Rectangle2D.Float(x, y, COSTAT - 2, COSTAT - 2);
                    r[i][j] = new Casella(rec, true);
                }
                x += COSTAT;
            }
            y += COSTAT;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int dx = e.getX() - x;
        int dy = e.getY() - y;
        setLocation(getLocation().x + dx, getLocation().y + dy);

        getParent().repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
        // Obtener la posición del ratón relativa al contenedor del tablero
        int mouseX = e.getXOnScreen() - tauler.getLocationOnScreen().x;
        int mouseY = e.getYOnScreen() - tauler.getLocationOnScreen().y;

        mouseX -= selectedCol * COSTAT;
        mouseY -= selectedRow * COSTAT;
        boolean trobat = false;
        int i = 0, j = 0;
        for (i = 0; i < Tauler.DIMENSIO && !trobat; i++) {
            for (j = 0; j < Tauler.DIMENSIO && !trobat; j++) {
                trobat = tauler.dinsCasella(i, j, mouseX, mouseY);
            }
        }
        i--;
        j--;

        // Es crea pesa aleatoria
        if (trobat && !tauler.isOcupat(i, j)) {
            tauler.Posa(i, j);
            tauler.repaint();

            pecesAleatories();
            setLocation(3, 201);
            getParent().repaint();
        } else {
            setLocation(3, 201);
        }
        tauler.algunaFilaOColumnaTotalmenteOcupada();
    }

    /**
     * Método para rotar la figura 90 grados en sentido horario
     */
    public void rotar() {
        int n = figuraAle.length;
        int m = figuraAle[0].length;
        int[][] rotated = new int[m][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                rotated[j][n - 1 - i] = figuraAle[i][j];
            }
        }

        figuraAle = rotated;
        inicializarCasellas();
        repaint();
    }

    /**
     * Método para inicializar las casillas según la figura actual
     */
    private void inicializarCasellas() {
        int n = figuraAle.length;
        int m = figuraAle[0].length;
        r = new Casella[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int x = j * COSTAT;
                int y = i * COSTAT;
                Rectangle2D.Float re = new Rectangle2D.Float(x, y, COSTAT - 2, COSTAT - 2);
                r[i][j] = new Casella(re, figuraAle[i][j] == 1);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
