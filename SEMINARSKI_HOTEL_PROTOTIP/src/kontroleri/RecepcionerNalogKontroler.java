/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import glavniKontroler.GlavniKontroler;
import hashing.Hash;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.Recepcioner;
import view.RecepcionerNalogForma;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import komunikacijaKlijent.Komunikacija;
import model.TerminDezurstva;
import model.ZaposleniTermin;
import modelTabela.OsobaModelTabela;
import modelTabela.ZaposleniTerminModelTabele;
import view.RecepcionerForma;

/**
 *
 * @author vuk
 */
public class RecepcionerNalogKontroler {
    
    private final RecepcionerNalogForma rnf;
    private final RecepcionerKontroler roditelj;
    private Recepcioner selektovani;
    private Recepcioner ulogovani;
    private List<ZaposleniTermin> listaSmena;

    public RecepcionerNalogKontroler(RecepcionerNalogForma rnf) {
        this.rnf = rnf;
        listaSmena=new ArrayList<>();
        roditelj=rnf.getRoditelj();
        pripremiFormu();
        popuniComboBox();
        popuniTabelu();
        addActionListener();
    }

    public List<ZaposleniTermin> getListaSmena() {
        return listaSmena;
    }
    
    
    public void otvoriFormu() {
        rnf.setVisible(true);
    }    

    private void pripremiFormu() {
        selektovani=rnf.getRecepcionerSelektovani();
        ulogovani=rnf.getRecepcionerUlogovani();
        rnf.getjTextFieldIme().setText(selektovani.getIme());
        rnf.getjTextFieldPrezime().setText(selektovani.getPrezime());
        rnf.getjTextFieldUsername().setText(selektovani.getUsername());
        rnf.getjTextFieldMail().setText(selektovani.getEmail());
        rnf.getjPasswordField1().setText(selektovani.getPassword());

        if(selektovani.getUsername().equals(ulogovani.getUsername()))
        {

            rnf.getjButtonIzmeni().setEnabled(true);
            rnf.getjComboBoxSmena().setEnabled(true);
            rnf.getjButtonDodajSmenu().setEnabled(true);
            rnf.getjDateChooser1().setEnabled(true);
            rnf.getjTableSmene().setRowSelectionAllowed(true);
        }
        else
        {
            rnf.getjButtonIzmeni().setEnabled(false);
            rnf.getjComboBoxSmena().setEnabled(false);
            rnf.getjButtonDodajSmenu().setEnabled(false);
            rnf.getjTableSmene().setRowSelectionAllowed(false);
            rnf.getjTableSmene().setCellSelectionEnabled(false);
        }
        rnf.getjButtonSacuvaj().setEnabled(false);

    }

    private void addActionListener() {
        rnf.izmeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rnf.getjTextFieldIme().setEnabled(true);
                rnf.getjTextFieldPrezime().setEnabled(true);
                rnf.getjTextFieldUsername().setEnabled(true);
                rnf.getjTextFieldMail().setEnabled(true);
                rnf.getjPasswordField1().setEnabled(true);
                
                addTextFieldListener(rnf.getjTextFieldIme());
                addTextFieldListener(rnf.getjTextFieldPrezime());
                addTextFieldListener(rnf.getjTextFieldUsername());
                addTextFieldListener(rnf.getjTextFieldMail());
                addTextFieldListener(rnf.getjPasswordField1());                
            }

            private void addTextFieldListener(JTextField textField) {
                textField.getDocument().addDocumentListener(new DocumentListener()
                {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        daLiJePromenjeno();
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        daLiJePromenjeno();
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        daLiJePromenjeno();
                    }

                    private void daLiJePromenjeno() {
                        rnf.getjButtonSacuvaj().setEnabled(true);
                    }
                    
                });
            }
        });
        
        rnf.sacuvajAddActionListener((ActionEvent e) -> {
            try
            {
                String ime=rnf.getjTextFieldIme().getText();
                String prezime=rnf.getjTextFieldPrezime().getText();
                String username=rnf.getjTextFieldUsername().getText();
                String email=rnf.getjTextFieldMail().getText();
                String password=Hash.hesirajLozinku(String.valueOf(rnf.getjPasswordField1().getPassword()));
                selektovani.setIme(ime);
                selektovani.setPrezime(prezime);
                selektovani.setUsername(username);
                selektovani.setEmail(email);
                selektovani.setPassword(password);
                try
                {
                    
                    Komunikacija.getInstance().promeniRecepcionera(selektovani);
                    JOptionPane.showMessageDialog(rnf, "Sistem je zapamtio recepcionera", "Promeni recepcionera", JOptionPane.INFORMATION_MESSAGE);
                    roditelj.azurirajTabelu();
                    rnf.dispose();
                    GlavniKontroler.getInstance().otvoriRecepcionerFormu();
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(rnf, ex.getMessage(), "Promeni recepcionera", JOptionPane.ERROR_MESSAGE);
                    
                }
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(RecepcionerNalogKontroler.class.getName()).log(Level.SEVERE, null, ex);
                                 
            }
        });
        
        rnf.nazadAddActionListener((ActionEvent e) -> {
            rnf.dispose();
            GlavniKontroler.getInstance().otvoriRecepcionerFormu();
        });
        
        rnf.dodajSmenuAddActionListener((ActionEvent e) -> {
            try
            {
                Date utilDatum = rnf.getjDateChooser1().getDate();
                if(rnf.getjComboBoxSmena().getSelectedItem()==null)
                {
                    JOptionPane.showMessageDialog(rnf, "Izaberite smenu!","Dodavanje smene",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(utilDatum==null){
                    JOptionPane.showMessageDialog(rnf, "Izaberite datum!","Dodavanje smene",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                LocalDate datum = utilDatum.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                
                if(datum.isBefore(LocalDate.now())){
                    JOptionPane.showMessageDialog(rnf, "Datum se mora odnositi na budućnost!","Dodavanje smene",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                ZaposleniTermin smena=new ZaposleniTermin();
                smena.setDatum(datum);
                smena.setRecepcioner(ulogovani);
                smena.setTerminDezurstva((TerminDezurstva) rnf.getjComboBoxSmena().getSelectedItem());
                if(listaSmena.contains(smena))
                {
                    JOptionPane.showMessageDialog(rnf, "Ne možete uneti ovu smenu!\n Možete raditi samo jednu smenu jednog datuma!","Dodavanje smene",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                listaSmena.add(smena);
                System.out.println(listaSmena.size());
                JOptionPane.showMessageDialog(rnf, "Smena je dodata!\n Pritisnite dugme Sačuvaj da ih zapamtite.","Dodavanje smene",JOptionPane.INFORMATION_MESSAGE);
                
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(rnf, ex.getMessage(),"Dodavanje smene",JOptionPane.ERROR_MESSAGE);
            }
        });
        
        rnf.sacuvajSmeneAddActionListener((ActionEvent e) -> {
            try
            {
                listaSmena=Komunikacija.getInstance().kreirajZaposleniTermin(listaSmena);
                popuniTabelu();
                JOptionPane.showMessageDialog(rnf, "Sistem je kreirao smene za izabranog recepcionera","Čuvanje smene",JOptionPane.INFORMATION_MESSAGE);
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(rnf, ex.getMessage(),"Čuvanje smene",JOptionPane.ERROR_MESSAGE);
            }
        });
        
    }

    private void popuniComboBox() {
        try {
            List<TerminDezurstva> termini=Komunikacija.getInstance().vratiListuTerminaDezurstava();
            for(TerminDezurstva td:termini)
            {
                rnf.getjComboBoxSmena().addItem(td);
            }
            rnf.getjComboBoxSmena().setSelectedItem(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rnf, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void popuniTabelu() {
        try {
            ZaposleniTermin zt=new ZaposleniTermin();
            zt.setRecepcioner(selektovani);
            List<ZaposleniTermin> lista=Komunikacija.getInstance().vratiListuZaposleniTermin(zt);
            System.out.println("Broj zap-termina iz baze: " + lista.size());
            ZaposleniTerminModelTabele ztmt=new ZaposleniTerminModelTabele(lista);
            rnf.getjTableSmene().setModel(ztmt);
            rnf.getjTableSmene().setFillsViewportHeight(true);
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rnf, ex.getMessage(), "Tabela osoba", JOptionPane.ERROR_MESSAGE);
        }        
    }
}
