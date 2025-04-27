/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import glavniKontroler.GlavniKontroler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Recepcioner;
import view.GlavnaForma;

/**
 *
 * @author vuk
 */
public class GlavnaFormaKontroler {
    private final GlavnaForma gf;

    public GlavnaFormaKontroler(GlavnaForma gf) {
        this.gf = gf;
        addActionListener();
    }

    public void otvoriFormu() {
        Recepcioner ulogovaniRecepcioner=GlavniKontroler.getInstance().getUlogovaniRecepcioner();
        gf.getjLabelIme().setText(ulogovaniRecepcioner.getIme());
        gf.setVisible(true);
    }

    private void addActionListener() {
        gf.odjaviSeAddActionListener((ActionEvent e) -> {
            gf.dispose();
            GlavniKontroler.getInstance().otvoriLoginFormu();
        });
        
        gf.recepcioneriAddActionListener((ActionEvent e) -> {
            gf.dispose();
            GlavniKontroler.getInstance().otvoriRecepcionerFormu();
        });
        
        gf.smeneAddActionListener((ActionEvent e) -> {
            gf.dispose();
            GlavniKontroler.getInstance().otvoriTerminDezurstvaFormu();
        });
        
        gf.mestaAddActionListener((ActionEvent e) -> {
            gf.dispose();
            GlavniKontroler.getInstance().otvoriMestoFormu();
        });
        
        gf.sobeAddActionListener((ActionEvent e) -> {
            gf.dispose();
            GlavniKontroler.getInstance().otvoriSobaFormu();
        });
        
        gf.osobeAddActionListener((ActionEvent e) -> {
            gf.dispose();
            GlavniKontroler.getInstance().otvoriOsobaFormu();
        });
        
        gf.iznajmljivanjaAddActionListener((ActionEvent e) -> {
            gf.dispose();
            GlavniKontroler.getInstance().otvoriIznajmljivanjaFormu();
        });
        
    }
    
    
}
