/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package defFitx;

import java.io.Serializable;

/**
 *
 * @author Blanca
 */
public class DadesJugadors implements Serializable {
    private String nom;
    private Equips grup;
    private int num;

    public static final DadesJugadors CENTINELA = new DadesJugadors("zzz", Equips.BASTOS, -1);

    public DadesJugadors() {
        nom = null;
        grup = null;
        num = 0;
    }

    public DadesJugadors(String s, Equips x, int z) {
        nom = s;
        grup = x;
        num = z;
    }

    @Override
    public String toString() {
        return "Jugador{" + "nom = " + nom + ", equip = " + grup + ", trofeus = " + num + '}';
    }
    
    public boolean esCentinela() {
        return num == -1;
    }
    
    public Equips getGrup() {
        return grup;
    }

    public String getNom() {
        return nom;
    }

    public int getTrof() {
        return num;
    }
}
