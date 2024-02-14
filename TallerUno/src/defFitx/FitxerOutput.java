/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package defFitx;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Blanca
 */
public class FitxerOutput {

    private FileOutputStream fos;
    private ObjectOutputStream f;

    public FitxerOutput(String nom) {
        try {
            fos = new FileOutputStream(nom);
            f = new ObjectOutputStream(fos);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FitxerOutput.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FitxerOutput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void escriu(DadesJugadors m) {
        try {
            f.writeObject(m);
        } catch (IOException ex) {
            Logger.getLogger(FitxerOutput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void tancaOut() {
        try {
            f.writeObject(DadesJugadors.CENTINELA);
            f.close();
        } catch (IOException ex) {
            Logger.getLogger(FitxerOutput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}