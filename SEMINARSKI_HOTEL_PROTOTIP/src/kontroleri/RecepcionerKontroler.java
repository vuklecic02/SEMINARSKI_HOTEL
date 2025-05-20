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
import java.util.HashMap;
import java.util.Map;
import model.Rola;
import model.TerminDezurstva;


/**
 *
 * @author vuk
 */
public class RecepcionerKontroler {
    private final RecepcionerForma rf;

    public RecepcionerKontroler(RecepcionerForma rf) {
        this.rf = rf;
        popuniTabelu();
        popuniComboBox();
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
    
    public void azurirajTabelu()
    {
        popuniTabelu();
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
        
        rf.ocistiAddActionListener((ActionEvent e) -> {
            rf.getjTextFieldPretraga().setText("");
            popuniTabelu();
        });
        
        rf.nazadAddActionListener((ActionEvent e) -> {
            rf.dispose();
            GlavniKontroler.getInstance().otvoriGlavnuFormu();
        });
        
        rf.detaljiAddActionListener((ActionEvent e) -> {
            if(rf.getjTableRecepcioneri().getSelectedRow()==-1)
            {
                JOptionPane.showMessageDialog(rf, "Odaberite recepcionera!", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                return;
            }
            RecepcionerModelTabela rmt=(RecepcionerModelTabela)rf.getjTableRecepcioneri().getModel();
            Recepcioner recepcionerSelektovani=rmt.getLista().get(rf.getjTableRecepcioneri().getSelectedRow());
            Recepcioner recepcionerUlogovani=GlavniKontroler.getInstance().getUlogovaniRecepcioner();
            rf.dispose();
            GlavniKontroler.getInstance().otvoriRecepcionerNalogFormu(recepcionerSelektovani,recepcionerUlogovani);
        });
        
        rf.deaktivirajNalogAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Recepcioner ulogovani=GlavniKontroler.getInstance().getUlogovaniRecepcioner();
                Map<Boolean, Recepcioner> mapa = new HashMap<>();
                
                if(ulogovani.getRola()==Rola.ADMIN)
                {
                    if(rf.getjTableRecepcioneri().getSelectedRow()==-1)
                    {
                        JOptionPane.showMessageDialog(rf, "Odaberite recepcionera!", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    RecepcionerModelTabela rmt=(RecepcionerModelTabela)rf.getjTableRecepcioneri().getModel();
                    Recepcioner recepcionerSelektovani=rmt.getLista().get(rf.getjTableRecepcioneri().getSelectedRow());
                    if(recepcionerSelektovani.isAktivan()==1)
                    {
                        try 
                        {
                            mapa.put(Boolean.TRUE,recepcionerSelektovani);
                            boolean deaktivacija=Komunikacija.getInstance().deaktivirajNalog(mapa);
                            if(deaktivacija==true)
                            {
                                JOptionPane.showMessageDialog(rf, "Nalog je deaktiviran", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                                return;
                            }
                        } 
                        catch (Exception ex) 
                        {
                            JOptionPane.showMessageDialog(rf, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                    }
                    else
                    {
                        JOptionPane.showMessageDialog(rf, "Nalog je već deaktiviran!", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(rf, "Nemate dozvolu za ovu operaciju!", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
        });
        
        rf.aktivirajNalogAddActionListener((ActionEvent e) -> {
                Recepcioner ulogovani=GlavniKontroler.getInstance().getUlogovaniRecepcioner();
                Map<Boolean, Recepcioner> mapa = new HashMap<>();
                
                if(ulogovani.getRola()==Rola.ADMIN)
                {
                    if(rf.getjTableRecepcioneri().getSelectedRow()==-1)
                    {
                        JOptionPane.showMessageDialog(rf, "Odaberite recepcionera!", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    RecepcionerModelTabela rmt=(RecepcionerModelTabela)rf.getjTableRecepcioneri().getModel();
                    Recepcioner recepcionerSelektovani=rmt.getLista().get(rf.getjTableRecepcioneri().getSelectedRow());
                    if(recepcionerSelektovani.isAktivan()==0)
                    {
                        try 
                        {
                            mapa.put(Boolean.FALSE,recepcionerSelektovani);
                            boolean aktivacija=Komunikacija.getInstance().deaktivirajNalog(mapa);
                            if(aktivacija==true)
                            {
                                JOptionPane.showMessageDialog(rf, "Nalog je aktiviran", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                                return;
                            }
                        } 
                        catch (Exception ex) 
                        {
                            JOptionPane.showMessageDialog(rf, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                    }
                    else
                    {
                        JOptionPane.showMessageDialog(rf, "Nalog je već aktiviran!", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(rf, "Nemate dozvolu za ovu operaciju!", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            });            
    }

    private void popuniComboBox() {
        try {
            List<TerminDezurstva> termini=Komunikacija.getInstance().vratiListuTerminaDezurstava();
            for(TerminDezurstva td:termini)
            {
                rf.getjComboBoxTerminDezurstva().addItem(td);
            }
            rf.getjComboBoxTerminDezurstva().setSelectedItem(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rf, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

}
