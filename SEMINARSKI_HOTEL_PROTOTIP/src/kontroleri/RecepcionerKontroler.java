/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import komunikacijaKlijent.Komunikacija;
import model.Recepcioner;
import view.RecepcionerForma;
import modelTabela.RecepcionerModelTabela;
import glavniKontroler.GlavniKontroler;


/**
 *
 * @author vuk
 */
public class RecepcionerKontroler {
    private final RecepcionerForma rf;

    public RecepcionerKontroler(RecepcionerForma rf) {
        this.rf = rf;
        popuniTabelu();
        addActionListener();
    }

    public RecepcionerForma getRf() {
        return rf;
    }
    
    public void otvoriFormu() {
        rf.setVisible(true);
    }
    
    private void popuniTabelu()
    {
        try {
            List<Recepcioner> lista=Komunikacija.getInstance().vratiListuRecepcionera();
            RecepcionerModelTabela rmt=new RecepcionerModelTabela(lista);
            rf.getjTableRecepcioneri().setModel(rmt);
            
        } catch (Exception ex) {
            Logger.getLogger(RecepcionerKontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void addActionListener() {
        
        rf.pretraziAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String imePrezime=rf.getjTextFieldPretraga().getText().trim();
                if(provera(imePrezime))
                {
                    JOptionPane.showMessageDialog(rf, "Neispravan unos!", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;                    
                }
                Recepcioner r=new Recepcioner();
                String imeIPrezime[]=imePrezime.split(" ");
                if(imeIPrezime.length>0)
                {
                    if(imeIPrezime.length==1)
                    {
                        r.setIme(imeIPrezime[0]);
                        r.setPrezime(imeIPrezime[0]);
                    }
                    else
                    {
                        r.setIme(imeIPrezime[0]);
                        String prezime = String.join(" ", Arrays.copyOfRange(imeIPrezime, 1, imeIPrezime.length));
                        r.setPrezime(prezime);
                    }
                    try {
                        azuriraj(r);
                    } catch (Exception ex) {
                        Logger.getLogger(RecepcionerKontroler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }

            private boolean provera(String imePrezime) {
                return imePrezime.isEmpty() || !imePrezime.matches("^[A-Z][a-zA-Z ]*$");
            }

            private void azuriraj(Recepcioner r) throws Exception {
                List<Recepcioner> lista=Komunikacija.getInstance().vratiFilterListuRecepcionera(r);
                RecepcionerModelTabela rmt=new RecepcionerModelTabela(lista);
                rf.getjTableRecepcioneri().setModel(rmt);                
            }
        });
        
        rf.ocistiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rf.getjTextFieldPretraga().setText("");
                popuniTabelu();
            }
        });
        
        rf.nazadAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rf.dispose();
                GlavniKontroler.getInstance().otvoriGlavnuFormu();
            }
        });
        
        rf.detaljiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(rf.getjTableRecepcioneri().getSelectedRow()==-1)
                {
                    JOptionPane.showMessageDialog(rf, "Odaberite recepcionera!", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                RecepcionerModelTabela rmt=(RecepcionerModelTabela)rf.getjTableRecepcioneri().getModel();
                Recepcioner recepcionerSelektovani=rmt.getLista().get(rf.getjTableRecepcioneri().getSelectedRow());
                Recepcioner recepcionerUlogovani=GlavniKontroler.getInstance().getUlogovaniRecepcioner();
                GlavniKontroler.getInstance().otvoriRecepcionerNalogFormu(recepcionerSelektovani,recepcionerUlogovani);
            }
        });
    }

}
