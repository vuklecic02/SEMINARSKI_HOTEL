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
import komunikacijaKlijent.Komunikacija;
import model.TerminDezurstva;
import view.TerminDezurstvaForma;
import view.TerminDezurstvaKreirajForma;

/**
 *
 * @author vuk
 */
public class TerminDezurstvaKreirajKontroler {
    private final TerminDezurstvaKreirajForma tdkf;
    private final TerminDezurstvaKontroler roditeljKontroler;

    public TerminDezurstvaKreirajKontroler(TerminDezurstvaKreirajForma tdkf) {
        this.tdkf=tdkf;
        this.roditeljKontroler=tdkf.getRoditelj();
        addActionListener();
    }
    
    public void otvoriFormu() {
        tdkf.setVisible(true);
    }

    private void addActionListener() {
        tdkf.unesiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String smena=tdkf.getjTextFieldSmena().getText();
                if(smena.isBlank() || smena.isEmpty())
                {
                    JOptionPane.showMessageDialog(tdkf, "Niste uneli ništa!", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(smena.matches("\\d{2}-\\d{2}"))
                {
                    String[] sati=smena.split("-");
                    int pocetak=Integer.parseInt(sati[0]);
                    int kraj=Integer.parseInt(sati[1]);
                    if(trajanjeSmena(pocetak,kraj)>10)
                    {
                        JOptionPane.showMessageDialog(tdkf, "Smena ne sme trajati duže od 10 sati!", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    TerminDezurstva td=new TerminDezurstva();
                    td.setSmena(smena);
                    try 
                    {
                        
                        td=Komunikacija.getInstance().kreirajTerminDezurstva(td);
                        JOptionPane.showMessageDialog(tdkf, td.getSmena()+" je kreirana!", "Kreiraj termin dežurstva", JOptionPane.INFORMATION_MESSAGE); 
                        tdkf.getjTextFieldSmena().setText("");
                        onSmenaKreirana();

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(tdkf, "Smena nije kreirana!", "Greška", JOptionPane.ERROR_MESSAGE);
                        Logger.getLogger(TerminDezurstvaKreirajKontroler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(tdkf, "Nepravilan unos!", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }

            private static int trajanjeSmena(int pocetak, int kraj) {
                if(kraj>=pocetak)
                    return kraj-pocetak;
                else
                    return (24+kraj)-pocetak;

            }

            private void onSmenaKreirana() {
                if(roditeljKontroler!=null)
                    roditeljKontroler.azurirajTabelu();
            }
        });
        
        tdkf.odustaniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rezultat = JOptionPane.showConfirmDialog(null, "Da li odustajete?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (rezultat == JOptionPane.YES_OPTION) {
                    tdkf.dispose(); 
                }
            }
        });
    }
    
    
}
