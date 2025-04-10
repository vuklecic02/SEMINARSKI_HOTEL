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
import model.Mesto;
import modelTabela.MestoModelTabela;
import modelTabela.TerminDezModelTabela;
import view.MestoForma;

/**
 *
 * @author vuk
 */
public class MestoKontroler {
    
    private final MestoForma mf;

    public MestoKontroler(MestoForma mf) {
        this.mf = mf;
        popuniTabelu();
        popuniComboBox();
        addActionListener();
    }

    public MestoForma getMf() {
        return mf;
    }

    public void otvoriFormu() {
        mf.setVisible(true);
    }

    private void addActionListener() {
        mf.kreirajAddActionListener((ActionEvent e) -> {
            GlavniKontroler.getInstance().otvoriMestoKreirajFormu(null,FormaMod.KREIRAJ);
        });
        
        mf.obrisiAddActionListener((ActionEvent e) -> {
                try {
                    if(mf.getjTableMesto().getSelectedRow()==-1)
                    {
                        JOptionPane.showMessageDialog(mf, "Odaberite mesto!", "Greska", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    MestoModelTabela mmt=(MestoModelTabela) mf.getjTableMesto().getModel();
                    Mesto selektovano=mmt.getLista().get(mf.getjTableMesto().getSelectedRow());
                    Komunikacija.getInstance().obrisiMesto(selektovano);
                    JOptionPane.showMessageDialog(mf, "Sistem je obrisao mesto", "Greska", JOptionPane.INFORMATION_MESSAGE);
                    popuniTabelu();
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(mf, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
                }            
        });
        
        mf.nazadAddActionListener((ActionEvent e) -> {
            mf.dispose();
            GlavniKontroler.getInstance().otvoriGlavnuFormu();
        });
        
        mf.ocistiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mf.getjTextFieldMesto().setText("");
                mf.getjComboBoxDrzave().setSelectedItem(null);
                popuniTabelu();
            }
        });
        
        mf.pretraziAddActionListener((ActionEvent e) -> {
            String naziv=mf.getjTextFieldMesto().getText().trim();
            Drzava drzava=(Drzava) mf.getjComboBoxDrzave().getSelectedItem();
            if(provera(naziv,drzava))
            {
                JOptionPane.showMessageDialog(mf, "Neispravan unos!", "Greska", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Mesto m=new Mesto();
            m.setNaziv(naziv);
            m.setDrzava(drzava);
            try {
                azuriraj(m);
            } catch (Exception ex) {
                Logger.getLogger(TerminDezurstvaKontroler.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void popuniTabelu() {
        try {
            List<Mesto> lista=Komunikacija.getInstance().vratiListuMesta();
            System.out.println("Broj mesta iz baze: " + lista.size());
            MestoModelTabela mmt=new MestoModelTabela(lista);
            mf.getjTableMesto().setModel(mmt);
            mf.getjTableMesto().setFillsViewportHeight(true);
            
        } catch (Exception ex) {
            Logger.getLogger(RecepcionerKontroler.class.getName()).log(Level.SEVERE, null, ex);
        }           
    }

    private void popuniComboBox() {
        try {
            List<Drzava> drzave = new ArrayList<>(List.of(Drzava.values()));
            drzave.sort(Comparator.comparing(Drzava::toString));
            for(Drzava d:drzave)
            {
                mf.getjComboBoxDrzave().addItem(d);
            }
             mf.getjComboBoxDrzave().setSelectedItem(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(mf, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    } 

    private boolean provera(String naziv, Drzava drzava) {
        return naziv.isEmpty() && drzava==null;
    }

    private void azuriraj(Mesto m) throws Exception {
        List<Mesto> lista=Komunikacija.getInstance().vratiFilterListuMesto(m);
        MestoModelTabela tmt=new MestoModelTabela(lista);
        mf.getjTableMesto().setModel(tmt);          
    }
    
}
