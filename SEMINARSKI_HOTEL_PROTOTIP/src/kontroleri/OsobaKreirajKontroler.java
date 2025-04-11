/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import glavniKontroler.GlavniKontroler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import komunikacijaKlijent.Komunikacija;
import model.FormaMod;
import model.Mesto;
import model.Osoba;
import view.OsobaKreirajForma;

/**
 *
 * @author vuk
 */
public class OsobaKreirajKontroler {
    private final OsobaKreirajForma okf;
    private final OsobaKontroler roditelj;
    private final FormaMod mod;
    private Osoba selektovani;

    public OsobaKreirajKontroler(OsobaKreirajForma okf) {
        this.okf = okf;
        roditelj=okf.getRoditelj();
        mod=okf.getMod();
        popuniComboBox();
        pripremiFormu();       
        addActionListener();
    }

    public OsobaKreirajForma getOkf() {
        return okf;
    }

    public void otvoriFormu() {
        okf.setVisible(true);
    }

    public void popuniComboBox() {
        try {
            List<Mesto> mesta = Komunikacija.getInstance().vratiListuMesta();
            mesta.sort(Comparator.comparing(mesto -> mesto.toString()));
            okf.getjComboBoxMesta().removeAllItems();
            for(Mesto m:mesta)
            {
                okf.getjComboBoxMesta().addItem(m);
            }
             okf.getjComboBoxMesta().setSelectedItem(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(okf, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        } 
    }

    private void addActionListener() {
        okf.kreirajAddActionListener((ActionEvent e) -> {
            if(!validacija(okf))
            {
                return;
            }
            else
            {
                
                String ime=okf.getjTextFieldIme().getText();
                String prezime=okf.getjTextFieldPrezime().getText();
                String telefon=okf.getjTextFieldTelefon().getText();
                String brLicneIsprave=okf.getjTextFieldBrIsprave().getText();
                Mesto mesto=(Mesto) okf.getjComboBoxMesta().getSelectedItem();
                Osoba osoba=new Osoba(ime, prezime, telefon, brLicneIsprave, mesto);
                
                try
                {
                    osoba=Komunikacija.getInstance().kreirajOsobu(osoba);
                    JOptionPane.showMessageDialog(okf, "Sistem je kreirao osobu "+osoba.getIme(), "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    resetujPolja();
                    onOsobaPerformed();
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(okf, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });
        
        okf.izmeniAddActionListener((ActionEvent e) -> {
            okf.getjTextFieldIme().setEnabled(true);
            okf.getjTextFieldPrezime().setEnabled(true);
            okf.getjTextFieldTelefon().setEnabled(true);
            okf.getjTextFieldBrIsprave().setEnabled(true);
            okf.getjComboBoxMesta().setEnabled(true);

            addTextFieldListener(okf.getjTextFieldIme());
            addTextFieldListener(okf.getjTextFieldPrezime());
            addTextFieldListener(okf.getjTextFieldTelefon());
            addTextFieldListener(okf.getjTextFieldBrIsprave());
            addComboBoxListener(okf.getjComboBoxMesta());
        });
        
        okf.odustaniAddActionListener((ActionEvent e) -> {
            int rezultat = JOptionPane.showConfirmDialog(okf, "Da li odustajete?", "Potvrda", JOptionPane.YES_NO_OPTION);
            if (rezultat == JOptionPane.YES_OPTION) {
                okf.dispose();                
            }
        });
        
        okf.dodajMestoAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GlavniKontroler.getInstance().otvoriMestoKreirajFormu(null, FormaMod.KREIRAJ);
            }
        });
        
        okf.sacuvajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selektovani=okf.getSelektovani();
                selektovani.setIme(okf.getjTextFieldIme().getText());
                selektovani.setPrezime(okf.getjTextFieldPrezime().getText());
                selektovani.setTelefon(okf.getjTextFieldTelefon().getText());
                selektovani.setBrLicneIsprave(okf.getjTextFieldBrIsprave().getText());
                selektovani.setMesto((Mesto) okf.getjComboBoxMesta().getSelectedItem());
                try
                {

                    Komunikacija.getInstance().promeniOsobu(okf.getSelektovani());
                    JOptionPane.showMessageDialog(okf, "Sistem je zapamtio osobu", "Promeni osobu", JOptionPane.INFORMATION_MESSAGE);
                    onOsobaPerformed();
                    okf.dispose();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(okf, ex.getMessage(), "Promeni osobu", JOptionPane.ERROR_MESSAGE);

                }                
            }

        });
    }

    private boolean validacija(OsobaKreirajForma okf) {
        if(okf.getjTextFieldIme().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(okf, "Niste uneli ime!", "Greska", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(okf.getjTextFieldPrezime().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(okf, "Niste uneli prezime!", "Greska", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(okf.getjTextFieldTelefon().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(okf, "Niste uneli telefon!", "Greska", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(okf.getjTextFieldBrIsprave().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(okf, "Niste uneli broj lične isprave!", "Greska", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(okf.getjComboBoxMesta().getSelectedItem()==null)
        {
            JOptionPane.showMessageDialog(okf, "Niste odabrali mesto!", "Greska", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(!okf.getjTextFieldIme().getText().matches("^[a-zA-Z ]+$"))
        {
            JOptionPane.showMessageDialog(okf, "Ime mora sadržati samo slova!", "Greska", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(!okf.getjTextFieldPrezime().getText().matches("^[a-zA-Z ]+$"))
        {
            JOptionPane.showMessageDialog(okf, "Prezime mora sadržati samo slova!", "Greska", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(!okf.getjTextFieldTelefon().getText().matches("\\+?[0-9]{9,15}"))
        {
            JOptionPane.showMessageDialog(okf, "Broj mora imati 9-15 cifara!", "Greska", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void resetujPolja() {
        okf.getjTextFieldIme().setText("");
        okf.getjTextFieldPrezime().setText("");
        okf.getjTextFieldTelefon().setText("");
        okf.getjTextFieldBrIsprave().setText("");
        okf.getjComboBoxMesta().setSelectedItem(null);
    }

    private void onOsobaPerformed() {
        if(roditelj!=null)
            roditelj.popuniTabelu();
    }

    private void pripremiFormu() {
        if(mod==FormaMod.KREIRAJ)
        {
            okf.getjButtonIzmeni().setEnabled(false);
            okf.getjButtonSacuvaj().setEnabled(false);
            //popuniComboBox();
        }
        else if(mod==FormaMod.IZMENI)
        {
            //popuniComboBox();
            okf.getjButtonUnesi().setEnabled(false);
            okf.getjButtonSacuvaj().setEnabled(false);
            okf.getjTextFieldIme().setEnabled(false);
            okf.getjTextFieldPrezime().setEnabled(false);
            okf.getjTextFieldTelefon().setEnabled(false);
            okf.getjTextFieldBrIsprave().setEnabled(false);
            okf.getjComboBoxMesta().setEnabled(false);
            
            selektovani=okf.getSelektovani();
            okf.getjTextFieldIme().setText(selektovani.getIme());
            okf.getjTextFieldPrezime().setText(selektovani.getPrezime());
            okf.getjTextFieldTelefon().setText(selektovani.getTelefon());
            okf.getjTextFieldBrIsprave().setText(selektovani.getBrLicneIsprave());
            okf.getjComboBoxMesta().setSelectedItem(selektovani.getMesto());
        }
    }

    private void addTextFieldListener(JTextField textField) {
        textField.getDocument().addDocumentListener(new DocumentListener() {
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
        });
    }

    private void addComboBoxListener(JComboBox<?> comboBox) {
        comboBox.addActionListener(e -> {
        daLiJePromenjeno();
        });
    }

    private void daLiJePromenjeno() {
        okf.getjButtonSacuvaj().setEnabled(true);
    }
    
}
