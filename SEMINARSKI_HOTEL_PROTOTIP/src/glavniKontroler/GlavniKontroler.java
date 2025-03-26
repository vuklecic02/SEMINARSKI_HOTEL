/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glavniKontroler;

import kontroleri.LoginKontroler;
import kontroleri.RegistracijaKontroler;
import view.LoginForma;
import view.RegistracijaForma;

/**
 *
 * @author vuk
 */
public class GlavniKontroler {
    private static GlavniKontroler instance;
    private LoginKontroler loginController;

    private GlavniKontroler() {
    }

    public static GlavniKontroler getInstance() {
        if(instance==null)
            instance=new GlavniKontroler();
        return instance;
    }

    public void otvoriLoginFormu() {
        loginController=new LoginKontroler(new LoginForma());
        loginController.otvoriFormu();
    }

    public void otvoriFormuZaRegistraciju() {
        RegistracijaKontroler rk=new RegistracijaKontroler(new RegistracijaForma());
        rk.otvoriFormu();
    }
    
}
