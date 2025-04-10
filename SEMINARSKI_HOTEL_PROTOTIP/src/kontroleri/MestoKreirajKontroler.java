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
import model.Drzava;
import model.FormaMod;
import model.Mesto;
import view.MestoKreirajForma;

/**
 *
 * @author vuk
 */
public class MestoKreirajKontroler {
    
    private final MestoKreirajForma mkf;
    private final MestoKontroler roditeljKontroler;
    private final FormaMod mod;

    public MestoKreirajKontroler(MestoKreirajForma mkf) {
        this.mkf = mkf;
        roditeljKontroler=mkf.getRoditelj();
        mod=mkf.getMod();
        popuniComboBox();
        addActionListener();
    }

    public void otvoriFormu() {
        mkf.setVisible(true);
    }

    private void addActionListener() {
        mkf.kreirajAddActionListener((ActionEvent e) -> {
            if(mkf.getjTextFieldNaziv().getText().isEmpty() || mkf.getjTextFieldNaziv().getText().isBlank())
            {
                JOptionPane.showMessageDialog(mkf, "Niste uneli naziv mesta!", "Kreiraj mesto", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String naziv=mkf.getjTextFieldNaziv().getText();
            if(mkf.getjComboBoxDrzava().getSelectedItem()==null)
            {
                JOptionPane.showMessageDialog(mkf, "Niste odabrali državu!", "Kreiraj mesto", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Drzava drzava=(Drzava) mkf.getjComboBoxDrzava().getSelectedItem();
            Mesto m=new Mesto();
            m.setDrzava(drzava);
            m.setNaziv(naziv);
            try
            {
                m=Komunikacija.getInstance().kreirajMesto(m);
                JOptionPane.showMessageDialog(mkf, "Sistem je kreirao mesto", "Kreiraj mesto", JOptionPane.INFORMATION_MESSAGE);
                mkf.getjTextFieldNaziv().setText("");
                mkf.getjComboBoxDrzava().setSelectedItem(null);
                onMestoPerformed();
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(mkf, ex.getMessage(), "Kreiraj mesto", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        mkf.odustaniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rezultat = JOptionPane.showConfirmDialog(mkf, "Da li odustajete?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (rezultat == JOptionPane.YES_OPTION) {
                    mkf.dispose(); 
                }
            }
        });        
    }
    
    private void popuniComboBox() {
        try {
            List<Drzava> drzave = new ArrayList<>(List.of(Drzava.values()));
            drzave.sort(Comparator.comparing(Drzava::toString));
            for(Drzava d:drzave)
            {
                mkf.getjComboBoxDrzava().addItem(d);
            }
             mkf.getjComboBoxDrzava().setSelectedItem(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(mkf, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }    
    
    private void onMestoPerformed() {
        if(roditeljKontroler!=null)
            roditeljKontroler.popuniTabelu();
    }
}
