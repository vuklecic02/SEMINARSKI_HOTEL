/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import view.RegistracijaForma;

/**
 *
 * @author vuk
 */
public class RegistracijaKontroler {
    private final RegistracijaForma rf;

    public RegistracijaKontroler(RegistracijaForma rf) {
        this.rf = rf;
    }
    
    public void otvoriFormu() {
        rf.setVisible(true);
    }
}
