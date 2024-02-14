/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package defFitx;

/**
 *
 * @author Blanca
 */
public enum Equips {
    BASTOS, COPES, ESPASES, OROS;

    public static class IOGenereIncorrecte extends Exception {

        public IOGenereIncorrecte(String s) {
            super(s);
        }
    }
}
