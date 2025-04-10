/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacijaKlijent.Komunikacija;
import model.Soba;
import model.TipSobe;
import view.SobaKreirajForma;

/**
 *
 * @author vuk
 */
public class SobaKreirajKontroler {
    private final  SobaKreirajForma skf;
    private final SobaKontroler roditelj;

    public SobaKreirajKontroler(SobaKreirajForma skf) {
        this.skf = skf;
        roditelj=skf.getRoditelj();
        popuniComboBox();
        addActionListener();
    }

    public SobaKreirajForma getSkf() {
        return skf;
    }

    public void otvoriFormu() {
        skf.setVisible(true);
    }

    private void popuniComboBox() {
        try {
            List<TipSobe> sobe = new ArrayList<>(List.of(TipSobe.values()));
            sobe.sort(Comparator.comparing(tipSobe -> tipSobe.toString()));
            for(TipSobe ts:sobe)
            {
                skf.getjComboBoxTipSobe().addItem(ts);
            }
             skf.getjComboBoxTipSobe().setSelectedItem(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(skf, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }     
    }

    private void addActionListener() {
        skf.kreirajAddActionListener((ActionEvent e) -> {
            try
            {
                if(skf.getjTextFieldCena().getText().isEmpty() || skf.getjTextFieldCena().getText().isBlank())
                {
                    JOptionPane.showMessageDialog(skf, "Niste uneli cenu!", "Kreiraj sobu", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                double cena=Double.parseDouble(skf.getjTextFieldCena().getText());
                if(cena<=0)
                {
                    JOptionPane.showMessageDialog(skf, "Cena mora biti veća od nule!", "Kreiraj sobu", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(skf.getjComboBoxTipSobe().getSelectedItem()==null)
                {
                    JOptionPane.showMessageDialog(skf, "Niste odabrali tip sobe!", "Kreiraj sobu", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                TipSobe tipSobe=(TipSobe) skf.getjComboBoxTipSobe().getSelectedItem();
                
                Soba s=new Soba();
                s.setCenaDan(cena);
                s.setTipSobe(tipSobe);
                s=Komunikacija.getInstance().kreirajSobu(s);
                JOptionPane.showMessageDialog(skf, "Sistem je kreirao sobu", "Kreiraj sobu", JOptionPane.INFORMATION_MESSAGE); 
                skf.getjTextFieldCena().setText("");
                skf.getjComboBoxTipSobe().setSelectedItem(null);
                onSmenaPerformed();                
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(skf, ex.getMessage(), "Kreiraj sobu", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        skf.odustaniAddActionListener((ActionEvent e) -> {
            int rezultat = JOptionPane.showConfirmDialog(skf, "Da li odustajete?", "Potvrda", JOptionPane.YES_NO_OPTION);
            if (rezultat == JOptionPane.YES_OPTION) {
                skf.dispose();
            }
        });
        
    }

    private void onSmenaPerformed() {
        if(roditelj!=null)
            roditelj.popuniTabelu();
    }
    
    
    
    
}
