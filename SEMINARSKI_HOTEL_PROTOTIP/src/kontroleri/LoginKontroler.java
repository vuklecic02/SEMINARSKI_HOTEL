/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import com.sun.java.accessibility.util.AWTEventMonitor;
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
                    System.out.println(username+" "+sifra);
                    Recepcioner recepcioner=Komunikacija.getInstance().login(username,sifra);
                    
                    if(recepcioner==null)
                    {
                        JOptionPane.showMessageDialog(lf, "Neuspesno prijavljivanje!","Login", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(lf, recepcioner.getIme()+" je prijavljen!","Login", JOptionPane.INFORMATION_MESSAGE);
                        lf.dispose();
                    }
                } catch (Exception ex) {
                    Logger.getLogger(LoginKontroler.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
    }

    public void otvoriFormu() {
        lf.setVisible(true);
    }
    
    
    
}
