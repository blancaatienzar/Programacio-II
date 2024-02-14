/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Paneles;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import tallerdos.TallerDos;

/**
 * Clase PanelBotones que crea el panel/inteficie para introducir los botones
 * del panel de botones (crear,reproducir, salir, adivinar) Tambien accion la
 * funcionalidad respectiva de cada boton.
 *
 * @author B
 */
public class PanelBotones extends JPanel implements ActionListener {

    private PanelDibujo tablero;

    public PanelBotones(PanelDibujo tablero) { //contructor
        this.tablero = tablero;
        setup();
        initComponents();
    }

    private void setup() {
        setLayout(new GridLayout(1, 4));
    }

    /**
     * Metodo que inicializa los diferente componentes botones del PanelBotones
     * (crear, reproducir, salir, adivinar)
     */
    private void initComponents() {

        //COMPONENTE JButton crear
        JButton crear = new JButton("CREAR");
        crear.setFont(new Font("arial", Font.BOLD, 13));
        crear.setForeground(Color.WHITE);
        crear.setBackground(Color.BLACK);
        crear.addActionListener(this);
        add("CREAR", crear);

        //COMPONENTE JButton reproducir
        JButton reproducir = new JButton("REPRODUCIR");
        reproducir.setFont(new Font("arial", Font.BOLD, 13));
        reproducir.setForeground(Color.WHITE);
        reproducir.setBackground(Color.BLACK);
        reproducir.addActionListener(this);
        add("REPRODUCIR", reproducir);

        //COMPONENTE JButton adivinar
        JButton adivinar = new JButton("ADIVINAR");
        adivinar.setFont(new Font("arial", Font.BOLD, 13));
        adivinar.setForeground(Color.WHITE);
        adivinar.setBackground(Color.BLACK);
        adivinar.addActionListener(this);
        add("ADIVINAR", adivinar);

        //COMPONENTE JButton salirBoton
        JButton salirBoton = new JButton("SALIR");
        salirBoton.setFont(new Font("arial", Font.BOLD, 13));
        salirBoton.setForeground(Color.WHITE);
        salirBoton.setBackground(Color.BLACK);
        salirBoton.addActionListener(this);
        add("SALIR", salirBoton);
    }

    //ventana que se visualizan por los botones (crear, reproducir, salir, adivinar)
    @Override
    public void actionPerformed(ActionEvent evento) {

        switch (evento.getActionCommand()) {
            case "CREAR":

                //Se visualiza la interficie PanelBotonesNotas y panelDibujo
                TallerDos.panelContenidos.cambiarActividadesNotas();

                break;
            case "REPRODUCIR":

                //Se visualiza la interficie PanelReproduccion y panelDibujo

                TallerDos.panelContenidos.cambiarActividadesReproduccion();

                break;
            case "ADIVINAR":

                TallerDos.panelContenidos.cambiarActividadesAdivinar();

                break;

            case "SALIR":
                //Finaliza la ejecucion del programa
                System.exit(0);
                break;
        }
    }

}
