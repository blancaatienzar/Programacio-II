/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package tallerdos;

import java.awt.Color;

/**
 * Clase enumerada a las notas musicales 
 * donde se les asigna color y sonido
 * 
 * @author B
 */
public enum NotasMusicales {
    DO(Color.RED, "do"),
    RE(Color.PINK, "re"),
    MI(Color.CYAN, "mi"),
    FA(Color.YELLOW, "fa"),
    SOL(Color.MAGENTA, "sol"),
    LA(Color.WHITE, "la"),
    SI(Color.GREEN, "si"),
    FIN(Color.BLACK,"fin");

    private NotasMusicales(Color color, String so) {
        this.Colores = color;
        this.sonido = so;
    }

    private final Color Colores;
    private String sonido;

    public Color getColor() {
        return Colores;
    }
    public String cogerSonido() {
        
        return sonido;
    }
}