/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import glavniKontroler.GlavniKontroler;
import hashing.Hash;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacijaKlijent.Komunikacija;
import model.Recepcioner;
import model.Rola;
import view.LoginForma;
import view.RegistracijaForma;

/**
 *
 * @author vuk
 */
public class RegistracijaKontroler {
    private final RegistracijaForma rf;

    public RegistracijaKontroler(RegistracijaForma rf) {
        this.rf = rf;
        addActionListener();
    }
    
    public void otvoriFormu() {
        rf.setVisible(true);
    }

    private void addActionListener() {
        rf.odustaniAddActionListener((ActionEvent e) -> {
            int rezultat = JOptionPane.showConfirmDialog(null, "Da li odustajete?", "Potvrda", JOptionPane.YES_NO_OPTION);
            if (rezultat == JOptionPane.YES_OPTION) {
                rf.dispose();
                GlavniKontroler.getInstance().otvoriLoginFormu();                
            }
        });
        
        rf.registrujSeAddActionListener((ActionEvent e) -> {
            try
            {        
                String ime=rf.getjTextFieldIme().getText();
                String prezime=rf.getjTextFieldPrezime().getText();
                String username=rf.getjTextFieldUsername().getText().trim();
                String email=rf.getjTextFieldEmail().getText().trim();
                String password=Hash.hesirajLozinku(String.valueOf(rf.getjPasswordField().getPassword()));

                Recepcioner recepcioner=new Recepcioner(ime, prezime, username, password, email);
                recepcioner=Komunikacija.getInstance().kreirajRecepcionera(recepcioner);
                JOptionPane.showMessageDialog(rf,"Sistem je kreirao korisnika "+recepcioner.getUsername(), "Register", JOptionPane.INFORMATION_MESSAGE);
                rf.dispose();
                GlavniKontroler.getInstance().otvoriLoginFormu();

            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(rf, ex.getMessage(),"Register",JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
