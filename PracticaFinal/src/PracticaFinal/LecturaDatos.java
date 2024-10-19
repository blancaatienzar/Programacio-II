package PracticaFinal;

import static PracticaFinal.GestorEventos.nombreJugador;
import static PracticaFinal.PracticaFinal.datosConfiguracion;
import static PracticaFinal.PracticaFinal.tiempo;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Clase para la lectura y configuración de datos del juego.
 * Esta clase extiende JDialog y proporciona interfaces para la entrada de datos
 * del jugador,
 * configuración específica del juego y modificación del tiempo de partida.
 * UIB - 2023-2024
 * 
 * @autor Blanca e Hai Zi
 */
public class LecturaDatos extends JDialog {

    private JTextField dato;
    private JTextField[] datos;
    public static String nombreFoto;
    public static boolean partidaEnCurso = false;

    private ActionListener gestorEvento;

    /**
     * Constructor para la introducción de datos del jugador.
     * 
     * @param frame el contenedor JFrame desde el que se ha instanciado.
     */
    public LecturaDatos(JFrame frame) {
        super(frame, true);
        setTitle("INTRODUCCIÓN DATOS");
        setResizable(false);

        // Crear el campo de texto para la entrada del nombre del jugador
        dato = new JTextField();
        dato = new JTextField(5);
        dato.setText("");
        dato.setHorizontalAlignment(JTextField.RIGHT);

        // Crear y configurar la etiqueta para el nombre del jugador
        JLabel conceptos = new JLabel("NOMBRE JUGADOR");
        conceptos.setForeground(Color.white);
        conceptos.setFont(new Font("Arial", Font.BOLD, 18));

        // Crear el panel y agregar la etiqueta y el campo de texto
        JPanel paneles = new JPanel();
        paneles.setBackground(Color.black);
        paneles.setLayout(new GridLayout());
        paneles.add(conceptos);
        paneles.add(dato);

        // Crear el botón de confirmar y agregar su ActionListener
        JButton confirmarBoton = new JButton("CONFIRMAR");
        confirmarBoton.addActionListener(gestorEvento = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (dato.getText().trim().isEmpty()) {
                    partidaEnCurso = false;
                    JOptionPane.showMessageDialog(null, "ENTRADA INCORRECTA");
                    PracticaFinal.panelContenidos.initComponents();
                    setVisible(false);
                } else {
                    partidaEnCurso = true;

                    nombreJugador = dato.getText().trim();
                    PracticaFinal.panelContenidos.Partida();

                    setVisible(false);
                }
            }
        });

        // Agregar los componentes al contenedor principal
        Container panelContenidos = getContentPane();
        panelContenidos.setLayout(new GridLayout(2, 1));
        panelContenidos.add(paneles);
        panelContenidos.add(confirmarBoton);

        // Configurar el tamaño y la posición del diálogo
        setSize(400, 100);
        setLocationRelativeTo(frame);
        setVisible(true);
    }

    /**
     * Obtiene el nombre del jugador.
     * 
     * @return el nombre del jugador como un String.
     */
    public String getNombreJugador() {
        return dato.getText();
    }

    /**
     * Constructor para configuraciones específicas del juego y modificación del
     * tiempo.
     * 
     * @param frame el contenedor JFrame desde el que se ha instanciado.
     * @param nada  parámetro adicional para distinguir el constructor.
     */
    public LecturaDatos(JFrame frame, String nada) {
        super(frame, true);
        setResizable(false);
        String[] options = { "CONFIGURACIÓN ESPECÍFICA JUEGO", "MODIFICAR TIEMPO PARTIDA", "NADA" };
        int seleccion = JOptionPane.showOptionDialog(this,
                "¿QUÉ DESEAS REALIZAR?",
                "CONFIGURACIÓN",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        // Manejar la selección del usuario
        switch (seleccion) {
            case 0:
                // Código para "CONFIGURACIÓN ESPECÍFICA JUEGO"super(frame, true);
                configuracionEspecifica(frame);
                break;
            case 1:
                // Código para "MODIFICAR TIEMPO PARTIDA"
                configuracionTiempo(frame);
                break;
        }
        setVisible(false);
    }

    /**
     * Configuración específica del juego.
     * 
     * @param frame el contenedor JFrame desde el que se ha instanciado.
     */
    public void configuracionEspecifica(JFrame frame) {
        setResizable(false);

        setTitle("CONFIGURACIÓN ESPECÍFICA JUEGO");

        Container panelContenidos = getContentPane();

        panelContenidos.setLayout(new GridLayout(4 + 1, 1));
        datos = new JTextField[4];
        JLabel[] conceptos = {
                new JLabel("PUNTUACIÓN CASILLAS FORMAS ELIMINADAS DEL TABLERO [" + datosConfiguracion[0] + " punto]"),
                new JLabel("PUNTUACIÓN ROTAR FORMA [" + datosConfiguracion[1] + " punto]"),
                new JLabel("PUNTUACIÓN NUEVA FORMA [" + datosConfiguracion[2] + " punto]"),
                new JLabel("IMAGEN CASILLAS FORMAS [" + datosConfiguracion[3] + "]") };

        JPanel[] paneles = new JPanel[4];
        try {
            for (int indice = 0; indice < datos.length; indice++) {
                paneles[indice] = new JPanel();

                // instanciación de los diferentes arrays
                conceptos[indice].setForeground(Color.white);
                conceptos[indice].setFont(new Font("Arial", Font.BOLD, 12));

                datos[indice] = new JTextField(5);
                datos[indice].setText("");
                datos[indice].setHorizontalAlignment(JTextField.RIGHT);

                paneles[indice] = new JPanel();
                paneles[indice].setBackground(Color.black);
                paneles[indice].setLayout(new GridLayout());

                paneles[indice].add(conceptos[indice]);
                paneles[indice].add(datos[indice]);

                panelContenidos.add(paneles[indice]);
            }
        } catch (NumberFormatException e) {
        }

        JButton confirmarBoton = new JButton("CONFIRMAR");
        panelContenidos.add(confirmarBoton);
        confirmarBoton.addActionListener(gestorEvento = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                for (int i = 0; i < datos.length - 1; i++) {
                    if (!datos[i].getText().trim().isEmpty()) {
                        int number = 0;
                        try {
                            number = Integer.parseInt(datos[i].getText());

                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null,
                                    "La " + number + 1 + "º no es un número entero válido.");
                            // Aquí puedes manejar la excepción de acuerdo a tus necesidades
                        }
                        datosConfiguracion[i] = datos[i].getText();
                    }
                }

                try {
                    // Verifica si la ruta no está vacía y no es nula
                    if (datos[3].getText() != null && !datos[3].getText().trim().isEmpty()) {
                        // Crea un objeto File con la ruta proporcionada
                        File imageFile = new File(datos[3].getText());

                        // Verifica si el archivo es un directorio
                        if (imageFile.isDirectory()) {
                            JOptionPane.showMessageDialog(null, "La ruta " + datos[3].getText()
                                    + " proporcionada es un directorio, no un archivo de imagen");
                        } else {
                            // Verifica si el archivo es una imagen válida
                            BufferedImage image = ImageIO.read(imageFile);
                            if (image == null) {
                                JOptionPane.showMessageDialog(null, "El archivo " + datos[3].getText()
                                        + " no es una imagen válida o está corrupto");
                            } else {
                                datosConfiguracion[3] = datos[3].getText();
                            }
                        }
                    }
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null,
                            "Ocurrió un problema al intentar leer la imagen: " + e.getMessage());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Ocurrió un problema inesperado.");
                }

                setVisible(false);
            }
        });
        setSize(900, 5 * 50);
        setLocationRelativeTo(frame);
        setVisible(true);
    }

    /**
     * Configuración del tiempo de partida.
     * 
     * @param frame el contenedor JFrame desde el que se ha instanciado.
     */
    public void configuracionTiempo(JFrame frame) {
        setResizable(false);

        setTitle("MODIFICAR TIEMPO PARTIDA");

        Container panelContenidos = getContentPane();

        panelContenidos.setLayout(new GridLayout(1 + 1, 1));
        dato = new JTextField();
        JLabel concepto = new JLabel("TIEMPO DE PARTIDA[" + tiempo + " segundos]");

        // instanciación de los diferentes arrays
        concepto.setForeground(Color.white);
        concepto.setFont(new Font("Arial", Font.BOLD, 18));

        dato = new JTextField(5);
        dato.setText("");
        dato.setHorizontalAlignment(JTextField.LEFT);

        JPanel jpanel = new JPanel();
        jpanel.setBackground(Color.black);
        jpanel.setLayout(new GridLayout());

        jpanel.add(concepto);
        jpanel.add(dato);

        panelContenidos.add(jpanel);

        JButton confirmarBoton = new JButton("CONFIRMAR");
        panelContenidos.add(confirmarBoton);
        confirmarBoton.addActionListener(gestorEvento = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!dato.getText().trim().isEmpty()) {
                    try {
                        int number = Integer.parseInt(dato.getText());
                        if (number < 0) {
                            JOptionPane.showMessageDialog(null, "El número no puede ser negativo.");
                        } else {
                            tiempo = Integer.parseInt(dato.getText());
                        }
                    } catch (NumberFormatException ev) {
                        JOptionPane.showMessageDialog(null, "La entrada no es un número entero válido.");
                        // Aquí puedes manejar la excepción de acuerdo a tus necesidades
                    }
                }
                setVisible(false);
            }
        });
        setSize(900, 2 * 50);
        setLocationRelativeTo(frame);
        setVisible(true);
    }
}
