package Interfaces;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JProgressBar;

/**
 * Esta clase extiende JProgressBar y está diseñada para representar una barra
 * de progreso temporal personalizada.
 * UIB - 2023-2024
 * 
 * @author Blanca e Hai Zi
 */
public class BarraProgresionTemporal extends JProgressBar {
    // DECLARACIÓN ATRIBUTOS
    private int valorMinimo = 0; // Valor mínimo de la barra de progreso
    private int valorMaximo = 100; // Valor máximo de la barra de progreso
    private final int ANCHO_BARRA = 40; // Ancho fijo de la barra de progreso

    /**
     * Constructor principal que inicializa la barra de progreso con una
     * dimensión específica.
     * 
     * @param dimension la longitud de la barra de progreso
     */
    public BarraProgresionTemporal(int dimension) {
        super();
        // ASIGNACIÓN VALORES MÍNIMO Y MÁXIMO A LA JProgressBar barraTemporal
        setMinimum(valorMinimo);
        setMaximum(valorMaximo);

        // ASIGNACIÓN VALOR INICIAL A LA JProgressBar barraTemporal
        setValue(0);

        // ACTIVACIÓN VISUALIZACIÓN VALOR EN LA JProgressBar barraTemporal
        setStringPainted(true);

        // DIMENSIONAMIENTO JProgressBar barraTemporal
        setPreferredSize(new Dimension(dimension, ANCHO_BARRA));

        // ASIGNACIÓN COLORES DE FONDO Y TRAZADO JProgressBar barraTemporal
        setForeground(Color.RED);
        setBackground(Color.YELLOW);
    }

    /**
     * Obtiene el valor máximo de la barra de progreso.
     * 
     * @return el valor máximo
     */
    public int getValorMaximo() {
        return valorMaximo;
    }

    /** 
     * Establece un nuevo valor máximo para la barra de progreso.
     * 
     * @param valor el nuevo valor máximo
     */
    public void setValorMaximo(int valor) {
        valorMaximo = valor;
        setMaximum(valorMaximo);
    }

    /**
     * Obtiene el valor mínimo de la barra de progreso.
     * 
     * @return el valor mínimo
     */
    public int getValorMinimo() {
        return valorMinimo;
    }

    /**
     * Establece un nuevo valor mínimo para la barra de progreso.
     * 
     * @param valor el nuevo valor mínimo
     */
    public void setValorMinimo(int valor) {
        valorMinimo = valor;
        setMinimum(valorMinimo);
    }

    /**
     * Establece un nuevo valor actual para la barra de progreso.
     * 
     * @param valor el nuevo valor actual
     */
    public void setValorBarraTemporal(int valor) {
        setValue(valor);
    }

    /**
     * Obtiene el valor actual de la barra de progreso.
     * 
     * @return el valor actual
     */
    public int getValorBarraTemporal() {
        return getValue();
    }
}
