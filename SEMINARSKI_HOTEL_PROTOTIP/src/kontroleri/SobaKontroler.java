/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import glavniKontroler.GlavniKontroler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacijaKlijent.Komunikacija;
import model.Drzava;
import model.FormaMod;
import model.Soba;
import model.TipSobe;
import modelTabela.SobaModelTabela;
import view.SobaForma;

/**
 *
 * @author vuk
 */
public class SobaKontroler {
    private final SobaForma sf;

    public SobaKontroler(SobaForma sf) {
        this.sf = sf;
        popuniTabelu();
        popuniComboBox();
        addActionListener();
    }

    public void otvoriFormu() {
        sf.setVisible(true);
    }

    public SobaForma getSf() {
        return sf;
    }    

    public void popuniTabelu() {
        try {
            List<Soba> lista=Komunikacija.getInstance().vratiListuSoba();
            System.out.println("Broj soba iz baze: " + lista.size());
            SobaModelTabela smt=new SobaModelTabela(lista);
            sf.getjTableSobe().setModel(smt);
            sf.getjTableSobe().setFillsViewportHeight(true);
            
        } catch (Exception ex) {
            Logger.getLogger(RecepcionerKontroler.class.getName()).log(Level.SEVERE, null, ex);
        }           
    }

    private void popuniComboBox() {
        try {
            List<TipSobe> sobe = new ArrayList<>(List.of(TipSobe.values()));
            sobe.sort(Comparator.comparing(tipSobe -> tipSobe.toString()));
            for(TipSobe ts:sobe)
            {
                sf.getjComboBoxSobe().addItem(ts);
            }
             sf.getjComboBoxSobe().setSelectedItem(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(sf, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }        
    }

    private void addActionListener() {
        sf.nazadAddActionListener((ActionEvent e) -> {
            sf.dispose();
            GlavniKontroler.getInstance().otvoriGlavnuFormu();
        });
        
        sf.ocistiAddActionListener((ActionEvent e) -> {
            sf.getjComboBoxSobe().setSelectedItem(null);
            popuniTabelu();
        });

        sf.kreirajAddActionListener((ActionEvent e) -> {
            GlavniKontroler.getInstance().otvoriSobaKreirajFormu(null,FormaMod.KREIRAJ);
        });
        
        sf.obrisiAddActionListener((ActionEvent e) -> {
                try {
                    if(sf.getjTableSobe().getSelectedRow()==-1)
                    {
                        JOptionPane.showMessageDialog(sf, "Odaberite sobu!", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    SobaModelTabela mmt=(SobaModelTabela) sf.getjTableSobe().getModel();
                    Soba selektovana=mmt.getLista().get(sf.getjTableSobe().getSelectedRow());
                    Komunikacija.getInstance().obrisiSobu(selektovana);
                    JOptionPane.showMessageDialog(sf, "Sistem je obrisao sobu", "Brisanje sobe", JOptionPane.INFORMATION_MESSAGE);
                    popuniTabelu();
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(sf, ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                }             
        });
        
        sf.pretraziAddActionListener((ActionEvent e) -> {
            
            TipSobe tipSobe=(TipSobe) sf.getjComboBoxSobe().getSelectedItem();
            if(provera(tipSobe))
            {
                JOptionPane.showMessageDialog(sf, "Niste odabrali tip sobe!", "Greska", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Soba s=new Soba();
            s.setTipSobe(tipSobe);
            try {
                azuriraj(s);
            } catch (Exception ex) {
                Logger.getLogger(TerminDezurstvaKontroler.class.getName()).log(Level.SEVERE, null, ex);
            }
        });        
    }

    private boolean provera(TipSobe tipSobe) {
        return tipSobe==null;
    }

    private void azuriraj(Soba s) throws Exception {
        List<Soba> lista=Komunikacija.getInstance().vratiFilterListuSoba(s);
        SobaModelTabela tmt=new SobaModelTabela(lista);
        sf.getjTableSobe().setModel(tmt); 
    }
    
    
    
}
