/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import glavniKontroler.GlavniKontroler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacijaKlijent.Komunikacija;
import model.Iznajmljivanje;
import model.Osoba;
import model.Recepcioner;
import model.Soba;
import model.StavkaIznajmljivanja;
import modelTabela.StavkeModelTabela;
import modelTabela.ZaposleniTerminModelTabele;
import view.IznajmljivanjeKreirajForma;

/**
 *
 * @author vuk
 */
public class IznajmljivanjeKreirajKontroler {
    
    private IznajmljivanjeKreirajForma ikf;
    private Recepcioner ulogovani;
    private List<StavkaIznajmljivanja> listaStavki;
    private double ukupnaCena;
    
    public IznajmljivanjeKreirajKontroler(IznajmljivanjeKreirajForma ikf)
    {
        this.ikf=ikf;
        listaStavki=new ArrayList<>();
        popuniComboBox();
        addActionListeners();
        ulogovani=ikf.getUlogovani();
        ukupnaCena=0;
        ikf.getjTextFieldUkupnaCena().setText(String.valueOf(ukupnaCena));
        ikf.getjLabelImePrezime().setText(ulogovani.getIme()+" "+ulogovani.getPrezime());
    }

    public List<StavkaIznajmljivanja> getListaStavki() {
        return listaStavki;
    }
    
    
    
    public void otvoriFormu() {
        ikf.setVisible(true);
    }  

    private void popuniComboBox() {
        try {
            List<Osoba> osobe=Komunikacija.getInstance().vratiListuOsoba();
            List<Soba> sobe = Komunikacija.getInstance().vratiListuSoba();
            sobe.sort(Comparator.comparing(tipSobe -> tipSobe.toString()));
            for(Soba s:sobe)
            {
                ikf.getjComboBoxSobe().addItem(s);
            }
            for(Osoba o:osobe)
            {
                ikf.getjComboBoxOsobe().addItem(o);
            }
             ikf.getjComboBoxSobe().setSelectedItem(null);
             ikf.getjComboBoxOsobe().setSelectedItem(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(ikf, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }          
    }

    private void addActionListeners() {
        ikf.nazadAddActionListener((ActionEvent e) -> {
            ikf.dispose();
            GlavniKontroler.getInstance().otvoriIznajmljivanjaFormu();
        });
        
        ikf.dodajStavkuAddActionListener((ActionEvent e) -> {
            try
            {
                Date utilDatumOd=ikf.getjDateChooserOd().getDate();
                Date utilDatumDo=ikf.getjDateChooserDo().getDate();
                
                if(utilDatumDo==null || utilDatumOd==null)
                {
                    JOptionPane.showMessageDialog(ikf, "Izaberite datum!","Dodavanje stavki",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                LocalDate datumOd=utilDatumOd.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate datumDo=utilDatumDo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                
                if(datumOd.isBefore(LocalDate.now())){
                    JOptionPane.showMessageDialog(ikf, "Datum se mora odnositi na budućnost!","Dodavanje stavki",JOptionPane.WARNING_MESSAGE);
                    return;
                }  
                
                if(datumDo.isBefore(datumOd)){
                    JOptionPane.showMessageDialog(ikf, "Neispravan odabir datuma!","Dodavanje stavki",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                Soba soba=(Soba) ikf.getjComboBoxSobe().getSelectedItem();
                if(soba==null)
                {
                    JOptionPane.showMessageDialog(ikf, "Morate odabrati sobu!","Dodavanje stavki",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                int brojDana=(int) ChronoUnit.DAYS.between(datumOd, datumDo);
                double iznos=brojDana*soba.getCenaDan();
                System.out.println(brojDana);
                
                StavkaIznajmljivanja stavka=new StavkaIznajmljivanja();
                stavka.setBrojDana(brojDana);
                stavka.setDatumOd(datumOd);
                stavka.setDatumDo(datumDo);
                stavka.setSoba(soba);
                stavka.setIznos(iznos);
                
                if(listaStavki.contains(stavka))
                {
                    JOptionPane.showMessageDialog(ikf, "Ne možete dodati istu stavku više puta!","Dodavanje stavki",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                StavkeModelTabela smt=new StavkeModelTabela(listaStavki);
                smt.dodajElement(stavka);
                ukupnaCena+=stavka.getIznos();
                ikf.getjTextFieldUkupnaCena().setText(String.valueOf(ukupnaCena));
                ikf.getjTableStavke().setModel(smt);
                ikf.getjTableStavke().setFillsViewportHeight(true);
                
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(ikf, ex.getMessage(),"Dodavanje stavki",JOptionPane.ERROR_MESSAGE);
            }
        });
        
        ikf.ukloniAddActionListener((ActionEvent e) -> {
            int selektovaniRed=ikf.getjTableStavke().getSelectedRow();
            if(selektovaniRed==-1)
            {
                JOptionPane.showMessageDialog(ikf, "Odaberite stavku za brisanje!","Brisanje stavki",JOptionPane.WARNING_MESSAGE);
                return;
            }
            StavkeModelTabela smt=(StavkeModelTabela) ikf.getjTableStavke().getModel();
            ukupnaCena-=smt.getLista().get(selektovaniRed).getIznos();
            ikf.getjTextFieldUkupnaCena().setText(String.valueOf(ukupnaCena));
            smt.ukloniElement(selektovaniRed);

        });
        
        ikf.kreirajAddActionListener((ActionEvent e) -> {
            try {
                if(listaStavki.isEmpty())
                {
                    JOptionPane.showMessageDialog(ikf, "Nemate stavku/e iznajmljivanja!","Kreiranje iznajmljivanja",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                Osoba gost=(Osoba) ikf.getjComboBoxOsobe().getSelectedItem();
                if(gost==null)
                {
                    JOptionPane.showMessageDialog(ikf, "Morate odabrati gosta!","Kreiranje iznajmljivanja",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Iznajmljivanje i=new Iznajmljivanje(ukupnaCena, ulogovani, gost);
                i=Komunikacija.getInstance().kreirajIznajmljivanje(i);
               
                if(i!=null)
                {
                    System.out.println("Iznajmljivanje ID: " + i.getIdIznajmljivanje());
                    for(StavkaIznajmljivanja stavka:listaStavki)
                    {
                        stavka.setIznajmljivanje(i);
                        System.out.println("ID iznajmljivanja:"+" "+stavka.getIznajmljivanje().getIdIznajmljivanje());
                    }
                    listaStavki=Komunikacija.getInstance().kreirajStavkeIznajmljivanja(listaStavki);
                    if(listaStavki!=null)
                    {
                        JOptionPane.showMessageDialog(ikf, "Kreirano je iznajmljivanje i stavke iznajmljivanja!","Kreiranje iznajmljivanja",JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                }
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(ikf, ex.getMessage(),"Kreiranje iznajmljivanja",JOptionPane.ERROR_MESSAGE);
            }
            
            
        });
    }
}
