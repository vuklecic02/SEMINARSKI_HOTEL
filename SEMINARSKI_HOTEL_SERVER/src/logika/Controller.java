/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logika;

import java.util.ArrayList;
import java.util.List;
import model.OpstiDomenskiObjekat;
import model.Recepcioner;
import operacija.recepcioner.LoginOperacija;

/**
 *
 * @author vuk
 */
public class Controller {
    
    private static Controller instance;
    private Recepcioner ulogovaniRecepcioner;
    
    private Controller(){
    }

    public static Controller getInstance() {
        if(instance==null)
            instance = new Controller();
        return instance;
    }

    public Recepcioner login(Recepcioner recepcioner) throws Exception {
        LoginOperacija operacija=new LoginOperacija();
        operacija.izvrsi(recepcioner, null);
        ulogovaniRecepcioner=operacija.getRecepcioner();
        return ulogovaniRecepcioner;

    }    

//    public Recepcioner registruj(Recepcioner r) throws Exception {
//        if(dbbr.kreiraj(r)){
//            return r;
//        }
//        else
//        {
//            throw new Exception("Korisnik nije dobro unetio podatke.");
//        }
//        
//    }
}
