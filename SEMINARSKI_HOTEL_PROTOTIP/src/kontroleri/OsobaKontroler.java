/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import glavniKontroler.GlavniKontroler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacijaKlijent.Komunikacija;
import model.Drzava;
import model.FormaMod;
import model.Mesto;
import model.Osoba;
import modelTabela.MestoModelTabela;
import modelTabela.OsobaModelTabela;
import view.OsobaForma;

/**
 *
 * @author vuk
 */
public class OsobaKontroler {
    private final OsobaForma of;

    public OsobaKontroler(OsobaForma of) {
        this.of = of;
        popuniTabelu();
        popuniComboBox();
        addActionListener();
    }

    public OsobaForma getOf() {
        return of;
    }

    public void otvoriFormu() {
        of.setVisible(true);
    }

    public void popuniTabelu() {
        try {
            List<Osoba> lista=Komunikacija.getInstance().vratiListuOsoba();
            System.out.println("Broj osoba iz baze: " + lista.size());
            OsobaModelTabela mmt=new OsobaModelTabela(lista);
            of.getjTableOsoba().setModel(mmt);
            of.getjTableOsoba().setFillsViewportHeight(true);
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(of, ex.getMessage(), "Tabela osoba", JOptionPane.ERROR_MESSAGE);
        }          
    }

    public void popuniComboBox() {
        try {
            List<Mesto> mesta = Komunikacija.getInstance().vratiListuMesta();
            List<Drzava> drzave=new ArrayList<>(List.of(Drzava.values()));
            drzave.sort(Comparator.comparing(Drzava::toString));
            mesta.sort(Comparator.comparing(mesto -> mesto.toString()));
            for(Mesto m:mesta)
            {
                of.getjComboBoxMesto().addItem(m.getNaziv());
            }
             
            for(Drzava d:drzave)
            {
                of.getjComboBoxDrzava().addItem(d);
            }
             of.getjComboBoxDrzava().setSelectedItem(null);
             of.getjComboBoxMesto().setSelectedItem(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(of, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }        
    }

    private void addActionListener() {
        of.ocistiAddActionListener((ActionEvent e) -> {
            of.getjComboBoxDrzava().setSelectedItem(null);
            of.getjComboBoxMesto().setSelectedItem(null);
            popuniTabelu();
        });
        
        of.nazadAddActionListener((ActionEvent e) -> {
            of.dispose();
            GlavniKontroler.getInstance().otvoriGlavnuFormu();
        });
        
        of.kreirajAddActionListener((ActionEvent e) -> {
            GlavniKontroler.getInstance().otvoriOsobaKreirajFormu(null,FormaMod.KREIRAJ);
        });
        
        of.obrisiAddActionListener((ActionEvent e) -> {
            try {
                if(of.getjTableOsoba().getSelectedRow()==-1)
                {
                    JOptionPane.showMessageDialog(of, "Odaberite osobu!", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                OsobaModelTabela omt=(OsobaModelTabela) of.getjTableOsoba().getModel();
                Osoba selektovani=omt.getLista().get(of.getjTableOsoba().getSelectedRow());
                Komunikacija.getInstance().obrisiOsobu(selektovani);
                JOptionPane.showMessageDialog(of, "Sistem je obrisao osobu", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                popuniTabelu();
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(of, ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            }            
        });
        
        of.pretraziAddActionListener((ActionEvent e) -> {
            if(of.getjComboBoxMesto().getSelectedItem()==null && of.getjComboBoxDrzava().getSelectedItem()==null)
            {
                return;
            }
            String naziv=(String) of.getjComboBoxMesto().getSelectedItem();
            Drzava drzava=(Drzava) of.getjComboBoxDrzava().getSelectedItem();
            
            Mesto m=new Mesto();
            m.setNaziv(naziv);
            m.setDrzava(drzava);
            Osoba o=new Osoba();
            o.setMesto(m);
            try {
                azuriraj(o);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(of, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        of.detaljiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            try {
                if(of.getjTableOsoba().getSelectedRow()==-1)
                {
                    JOptionPane.showMessageDialog(of, "Odaberite osobu!", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                OsobaModelTabela omt=(OsobaModelTabela) of.getjTableOsoba().getModel();
                Osoba selektovani=omt.getLista().get(of.getjTableOsoba().getSelectedRow());
                GlavniKontroler.getInstance().otvoriOsobaKreirajFormu(selektovani,FormaMod.IZMENI);
                popuniTabelu();
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(of, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);                
            }                
            }
        });
    }

    private boolean provera(String naziv, Drzava drzava) {
        return naziv.isEmpty() && drzava==null;
    }

    private void azuriraj(Osoba m) throws Exception {
        List<Osoba> lista=Komunikacija.getInstance().vratiFilterListuOsoba(m);
        OsobaModelTabela tmt=new OsobaModelTabela(lista);
        of.getjTableOsoba().setModel(tmt);         
    }
    
    
    
}
