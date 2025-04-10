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
import javax.swing.JTextField;
import model.Recepcioner;
import view.RecepcionerNalogForma;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import komunikacijaKlijent.Komunikacija;
import model.TerminDezurstva;
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

    public RecepcionerNalogKontroler(RecepcionerNalogForma rnf) {
        this.rnf = rnf;
        roditelj=rnf.getRoditelj();
        pripremiFormu();
        popuniComboBox();
        addActionListener();
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
            rnf.getjTextFieldDatum().setEnabled(true);
        }
        else
        {
            rnf.getjButtonIzmeni().setEnabled(false);
            rnf.getjComboBoxSmena().setEnabled(false);
            rnf.getjButtonDodajSmenu().setEnabled(false);            
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
        
        rnf.sacuvajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ime=rnf.getjTextFieldIme().getText();
                String prezime=rnf.getjTextFieldPrezime().getText();
                String username=rnf.getjTextFieldUsername().getText();
                String email=rnf.getjTextFieldMail().getText();
                String password=String.valueOf(rnf.getjPasswordField1().getPassword());
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
            }
        });
        
        rnf.nazadAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rnf.dispose();
                GlavniKontroler.getInstance().otvoriRecepcionerFormu();
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
}
