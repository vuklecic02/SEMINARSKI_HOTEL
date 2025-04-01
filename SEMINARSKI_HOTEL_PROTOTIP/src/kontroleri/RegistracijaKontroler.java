/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import glavniKontroler.GlavniKontroler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacijaKlijent.Komunikacija;
import model.Recepcioner;
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
        rf.odustaniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rezultat = JOptionPane.showConfirmDialog(null, "Da li odustajete?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (rezultat == JOptionPane.YES_OPTION) {
                    rf.dispose();
                    GlavniKontroler.getInstance().otvoriLoginFormu();
                }                
            }
        });
        
        rf.registrujSeAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            try
            {        
                String ime=rf.getjTextFieldIme().getText();
                String prezime=rf.getjTextFieldPrezime().getText();
                String username=rf.getjTextFieldUsername().getText().trim();
                String email=rf.getjTextFieldEmail().getText().trim();
                String password=String.valueOf(rf.getjPasswordField().getPassword());
                String ponPassword=String.valueOf(rf.getjPasswordFieldRepeat().getPassword());

                if(ime.isEmpty())
                {
                    JOptionPane.showMessageDialog(rf, "Niste uneli ime!","Register",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(prezime.isEmpty())
                {
                    JOptionPane.showMessageDialog(rf, "Niste uneli prezime!","Register",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(username.isEmpty())
                {
                    JOptionPane.showMessageDialog(rf, "Niste uneli username!","Register",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(email.isEmpty())
                {
                    JOptionPane.showMessageDialog(rf, "Niste uneli email!","Register",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(password.isEmpty())
                {
                    JOptionPane.showMessageDialog(rf, "Niste uneli sifru!","Register",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(password.length()<8)
                {
                    JOptionPane.showMessageDialog(rf, "Sifra mora imati bar 8 karaktera!","Register",JOptionPane.ERROR_MESSAGE);
                    return;                    
                }
                if(ponPassword.isEmpty())
                {
                    JOptionPane.showMessageDialog(rf, "Niste ponovili sifru!","Register",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(!password.equals(ponPassword))
                {
                    JOptionPane.showMessageDialog(rf, "Sifre se ne poklapaju!","Register",JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Recepcioner recepcioner=new Recepcioner(ime, prezime, username, password, email);
                recepcioner=Komunikacija.getInstance().kreirajRecepcionera(recepcioner);
                JOptionPane.showMessageDialog(rf, recepcioner.getUsername()+" se registrovao/la!", "Register", JOptionPane.INFORMATION_MESSAGE);
                rf.dispose();
                GlavniKontroler.getInstance().otvoriLoginFormu();

            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(rf, "Greska pri registraciji: "+ex.getMessage(),"Register",JOptionPane.ERROR_MESSAGE);
            }                 
                }
        });
    }
}
