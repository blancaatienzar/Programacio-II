/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tallerdos;

import Paneles.PanelContenidos;
import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *  Clase principal (TallerDos) donde se inicializa el 
 *  programa y se crea la ventana que contendra todas
 *  las inteficies contenidas en un panel de contenidos
 *  
 * @author B
 */
public class TallerDos extends JFrame {
    //Array Enum que guarda las sequencias de melodia
    public static NotasMusicales[] notass= new NotasMusicales[110]; 
    
    // panel que contiene las interficies
    public static final PanelContenidos panelContenidos = new PanelContenidos(); 
   
    public static void main(String[] args) {
        new TallerDos().setVisible(true);
    }

    /**
     * Constructor que inizializa e establece los parametros 
     *  a seguir para la ventana emergente del pregoama
     */
    public TallerDos() {
        setTitle("TALLER 2 - PROGRAMACION II - 2022-2023 - UIB"); //título contenedor pruebaBotones
        setSize(675, 675);
        setResizable(false);
        setLayout(new BorderLayout()); //asignación de Layaout BorderLayout
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panelContenidos); //asignación a panelContenidos del panel de contenidos del JFrame
    }
}
