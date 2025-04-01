/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import model.Recepcioner;
import view.RecepcionerNalogForma;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author vuk
 */
public class RecepcionerNalogKontroler {
    
    private final RecepcionerNalogForma rnf;
    private Recepcioner selektovani;
    private Recepcioner ulogovani;

    public RecepcionerNalogKontroler(RecepcionerNalogForma rnf) {
        this.rnf = rnf;
        popuniPolja();
        addActionListener();
    }
    
    public void otvoriFormu() {
        rnf.setVisible(true);
    }    

    private void popuniPolja() {
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
                
            }
        });
    }
}
