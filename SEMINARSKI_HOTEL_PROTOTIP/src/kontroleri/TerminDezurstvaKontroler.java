/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import glavniKontroler.GlavniKontroler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacijaKlijent.Komunikacija;
import model.TerminDezurstva;
import modelTabela.TerminDezModelTabela;
import view.TerminDezurstvaForma;

/**
 *
 * @author vuk
 */
public class TerminDezurstvaKontroler {
    
    private final TerminDezurstvaForma tdf;

    public TerminDezurstvaKontroler(TerminDezurstvaForma tdf) {
        this.tdf = tdf;
        popuniTabelu();
        addActionListener();
    }

    public TerminDezurstvaForma getTdf() {
        return tdf;
    }
    
    public void otvoriFormu() {
        tdf.setVisible(true);
    }

    private void addActionListener() {
        tdf.ocistiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tdf.getjTextFieldPretraga().setText("");
                popuniTabelu();
            }
        });
        
        tdf.nazadAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tdf.dispose();
                GlavniKontroler.getInstance().otvoriGlavnuFormu();
            }
        });
        
        tdf.pretraziAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String smena=tdf.getjTextFieldPretraga().getText().trim();
                if(provera(smena))
                {
                    JOptionPane.showMessageDialog(tdf, "Neispravan unos!", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;                    
                }
                TerminDezurstva td=new TerminDezurstva();
                td.setSmena(smena);
                try {
                    azuriraj(td);
                } catch (Exception ex) {
                    Logger.getLogger(TerminDezurstvaKontroler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private boolean provera(String smena) {
                return smena.isEmpty() || !smena.matches("(\\d{1,2}-\\d{1,2})|(\\d{1,2}-)|(-\\d{1,2})|(\\d{1,2})");
            }

            private void azuriraj(TerminDezurstva td) throws Exception {
                List<TerminDezurstva> lista=Komunikacija.getInstance().vratiFilterListuTerminDez(td);
                TerminDezModelTabela tmt=new TerminDezModelTabela(lista);
                tdf.getjTableTerminDez().setModel(tmt);                
            }                
            
        });
        
        tdf.kreirajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GlavniKontroler.getInstance().otvoriTerminDezKreirajFormu();
            }
        });
    }

    private void popuniTabelu() {
        try {
            List<TerminDezurstva> lista=Komunikacija.getInstance().vratiListuTerminaDezurstava();
            TerminDezModelTabela tdm=new TerminDezModelTabela(lista);
            tdf.getjTableTerminDez().setModel(tdm);
            
        } catch (Exception ex) {
            Logger.getLogger(RecepcionerKontroler.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public void azurirajTabelu()
    {
        popuniTabelu();
    }
}
