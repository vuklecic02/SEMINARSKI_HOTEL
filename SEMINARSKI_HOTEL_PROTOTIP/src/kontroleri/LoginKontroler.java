/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import com.sun.java.accessibility.util.AWTEventMonitor;
import glavniKontroler.GlavniKontroler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacijaKlijent.Komunikacija;
import model.Recepcioner;
import view.LoginForma;

/**
 *
 * @author vuk
 */
public class LoginKontroler {
    
    private final LoginForma lf;

    public LoginKontroler(LoginForma lf) {
        this.lf = lf;
        addActionListener();
    }

    private void addActionListener() {
        lf.loginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login(e);
            }

            private void login(ActionEvent e) {
                try {
                    String username=lf.getjTextFieldUsername().getText().trim();
                    String sifra=String.valueOf(lf.getjPasswordFieldSifra().getPassword());
//                    if(username.isEmpty() || sifra.isEmpty())
//                    {
//                        JOptionPane.showMessageDialog(lf, "Niste popunili polje/a!","Login", JOptionPane.ERROR_MESSAGE);
//                        return;
//                    }
//                    System.out.println(username+" "+sifra);
                    Recepcioner recepcioner=Komunikacija.getInstance().login(username,sifra);
                    
                    if(recepcioner==null)
                    {
                        JOptionPane.showMessageDialog(lf, "Sistem ne može da prijavi korisnika","Login", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        GlavniKontroler.getInstance().setUlogovaniRecepcioner(recepcioner);
                        JOptionPane.showMessageDialog(lf, "Sistem je prijavio korisnika "+recepcioner.getIme(),"Login", JOptionPane.INFORMATION_MESSAGE);
                        GlavniKontroler.getInstance().otvoriGlavnuFormu();
                        lf.dispose();
                    }
                } catch (Exception ex) {
                     JOptionPane.showMessageDialog(lf, ex.getMessage(),"Login", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });
        
        lf.odustaniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rezultat = JOptionPane.showConfirmDialog(null, "Da li odustajete?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (rezultat == JOptionPane.YES_OPTION) {
                    lf.dispose(); 
                }
            }
        });
        
        lf.registrujSeAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lf.dispose();
                GlavniKontroler.getInstance().otvoriFormuZaRegistraciju();
            }
        });
    }

    public void otvoriFormu() {
        lf.setVisible(true);
    }
    
    
    
}
