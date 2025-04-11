/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;
import komunikacijaKlijent.Komunikacija;
import model.FormaMod;
import model.TerminDezurstva;
import view.TerminDezurstvaForma;
import view.TerminDezurstvaKreirajForma;
import javax.swing.event.DocumentEvent;
/**
 *
 * @author vuk
 */
public class TerminDezurstvaKreirajKontroler {
    private final TerminDezurstvaKreirajForma tdkf;
    private final TerminDezurstvaKontroler roditeljKontroler;
    private final FormaMod mod;

    public TerminDezurstvaKreirajKontroler(TerminDezurstvaKreirajForma tdkf) {
        this.tdkf=tdkf;
        this.roditeljKontroler=tdkf.getRoditelj();
        mod=tdkf.getMod();
        pripremiFormu();
        addActionListener();
    }
    
    public void otvoriFormu() {
        tdkf.setVisible(true);
    }

    private void addActionListener() {
        tdkf.unesiAddActionListener((ActionEvent e) -> {
            String smena=tdkf.getjTextFieldSmena().getText();
//                if(smena.isBlank() || smena.isEmpty())
//                {
//                    JOptionPane.showMessageDialog(tdkf, "Niste uneli ništa!", "Greška", JOptionPane.ERROR_MESSAGE);
//                    return;
//                }
//                if(smena.matches("\\d{2}-\\d{2}"))
//                {
//                    String[] sati=smena.split("-");
//                    int pocetak=Integer.parseInt(sati[0]);
//                    int kraj=Integer.parseInt(sati[1]);
//                    if(trajanjeSmena(pocetak,kraj)>10)
//                    {
//                        JOptionPane.showMessageDialog(tdkf, "Smena ne sme trajati duže od 10 sati!", "Greška", JOptionPane.ERROR_MESSAGE);
//                        return;
//                    }
                    TerminDezurstva td=new TerminDezurstva();
                    td.setSmena(smena);
                    try
                    {

                        td=Komunikacija.getInstance().kreirajTerminDezurstva(td);
                        JOptionPane.showMessageDialog(tdkf, "Sistem je kreirao termin dežurstva", "Kreiraj termin dežurstva", JOptionPane.INFORMATION_MESSAGE);
                        tdkf.getjTextFieldSmena().setText("");
                        onSmenaPerformed();

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(tdkf, ex.getMessage(), "Kreiraj termin dežurstva", JOptionPane.ERROR_MESSAGE);

                    }
//                }
//                else
//                {
//                    JOptionPane.showMessageDialog(tdkf, "Nepravilan unos!", "Greška", JOptionPane.ERROR_MESSAGE);
//                }
        }//            private static int trajanjeSmena(int pocetak, int kraj) {
//                if(kraj>=pocetak)
//                    return kraj-pocetak;
//                else
//                    return (24+kraj)-pocetak;
//
//            }
        );
        
        tdkf.odustaniAddActionListener((ActionEvent e) -> {
            int rezultat = JOptionPane.showConfirmDialog(null, "Da li odustajete?", "Potvrda", JOptionPane.YES_NO_OPTION);
            if (rezultat == JOptionPane.YES_OPTION) {
                tdkf.dispose();
            }
        });
        
        tdkf.izmeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tdkf.getjTextFieldSmena().setEnabled(true);
                addTextFieldListener(tdkf.getjTextFieldSmena());
            }

            private void addTextFieldListener(JTextField jTextFieldSmena) {
                jTextFieldSmena.getDocument().addDocumentListener(new DocumentListener() {
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
                        tdkf.getjButtonSacuvajIzmene().setEnabled(true);
                    }
                });
            }
        });
        
        tdkf.sacuvajAddActionListener((ActionEvent e) -> {
            String smena=tdkf.getjTextFieldSmena().getText();
            tdkf.getSelektovani().setSmena(smena);
            try
            {
                
                Komunikacija.getInstance().promeniTerminDezurstva(tdkf.getSelektovani());
                JOptionPane.showMessageDialog(tdkf, "Sistem je zapamtio termin dežurstva", "Promeni termin dežurstva", JOptionPane.INFORMATION_MESSAGE);
                onSmenaPerformed();
                tdkf.dispose();
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(tdkf, ex.getMessage(), "Promeni termin dežurstva", JOptionPane.ERROR_MESSAGE);
                                
            }
        });
    }

    private void pripremiFormu() {
        if(mod==FormaMod.KREIRAJ)
        {
            tdkf.getjButtonIzmeni().setEnabled(false);
            tdkf.getjButtonSacuvajIzmene().setEnabled(false);
        }
        else if(mod==FormaMod.IZMENI)
        {
            tdkf.getjTextFieldSmena().setEnabled(false);
            tdkf.getjButtonUnesi().setEnabled(false);
            tdkf.getjButtonSacuvajIzmene().setEnabled(false);
            tdkf.getjTextFieldSmena().setText(tdkf.getSelektovani().getSmena());
        }
    }
    
    private void onSmenaPerformed() 
    {
        if(roditeljKontroler!=null)
            roditeljKontroler.popuniTabelu();
    }
    
    
}
